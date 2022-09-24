package wacc

import front_end.ASTNodes.*
import org.antlr.v4.runtime.ParserRuleContext
import parser.WACCParser
import parser.WACCParser.ExprContext
import parser.WACCParserBaseVisitor
import wacc.ErrorCodes.SEMANTIC_ERROR
import wacc.ErrorCodes.SYNTAX_ERROR

class SemanticAnalyser(private val optimisationFlagConstantEvaluation: Boolean) :
    WACCParserBaseVisitor<ASTNode>() {

    // Loop count flag used by visitBreak and visitContinue to check that they are called within a loop
    private var loopCount: Int = 0

    val TOP_ST: SymbolTable = SymbolTable(null)
    var CURR_ST: SymbolTable? = TOP_ST
    var CLASS_ST: SymbolTable? = null
    var CLASS_NAME: String = ""
    var method_offset = 0
    val errorListener: WACCErrorListener = WACCErrorListener()

    override fun visitProgram(ctx: WACCParser.ProgramContext): ASTNode {
        val classes = ctx.cls()
        val classList = mutableListOf<ASTClass>()

        for (cls: WACCParser.ClsContext in classes) {
            val methods = cls.method()
            for (method: WACCParser.MethodContext in methods) {
                val func = method.func()
                val returnType = visit(func.type())
                val name = cls.IDENT().toString() + "_" + func.IDENT().toString()
                val params = mutableListOf<ASTNode>()
                val paramsType = mutableListOf<ASTType>()
                if (func.param_list() != null) {
                    val param_list: WACCParser.Param_listContext = func.param_list()
                    for (param in param_list.param()) {
                        val newParam = visit(param.type())
                        params.add(newParam)
                        paramsType.add(newParam.getBaseType() as ASTType)
                    }
                }
                params.add(ASTAnyType())
                paramsType.add(ASTAnyType())
                val function = ASTFunction(
                    returnType,
                    name,
                    params,
                    ASTStatement(STAT.DECLARATION, null, null, null, CURR_ST!!),
                    SymbolTable(TOP_ST)
                )
                val functionSignature = FunctionSignature(name, paramsType)

                if (TOP_ST.lookupAllFunction(functionSignature) != null) {
                    errorListener.errorHandler(
                        "Another function ${name} with the same signature is already defined in this scope",
                        SEMANTIC_ERROR,
                        func
                    )
                }
                var visibility: Visibility
                when {
                    method.visib_ident().PRIVATE() != null -> visibility = Visibility.PRIVATE
                    else -> visibility = Visibility.PUBLIC // default visibility is public
                }
                val met = ASTMethod(visibility, function)
                TOP_ST.addFunction(functionSignature, met)
            }

        }

        val funcs = ctx.func()
        for (func: WACCParser.FuncContext in funcs) {
            val returnType = visit(func.type())
            val name = func.IDENT().toString()
            val params = mutableListOf<ASTNode>()
            val paramsType = mutableListOf<ASTType>()
            if (func.param_list() != null) {
                val param_list: WACCParser.Param_listContext = func.param_list()
                for (param in param_list.param()) {
                    val newParam = visit(param.type())
                    params.add(newParam)
                    paramsType.add(newParam.getBaseType() as ASTType)
                }
            }
            val function = ASTFunction(
                returnType,
                name,
                params,
                ASTStatement(STAT.DECLARATION, null, null, null, CURR_ST!!),
                SymbolTable(TOP_ST)
            )
            val functionSignature = FunctionSignature(name, paramsType)

            if (TOP_ST.lookupAllFunction(functionSignature) != null) {
                errorListener.errorHandler(
                    "Another function ${name} with the same signature is already defined in this scope",
                    SEMANTIC_ERROR,
                    func
                )
            }
            TOP_ST.addFunction(functionSignature, function)
        }

        for (cls: WACCParser.ClsContext in classes) {
            classList.add(visit(cls) as ASTClass)
        }


        val funcList = mutableListOf<ASTFunction>()
        for (func: WACCParser.FuncContext in funcs) {
            funcList.add(visit(func) as ASTFunction)
        }

        val visitedStatements = visit(ctx.stat()) as ASTStatement
        // we build up the list of semantic errors while traversing 
        // the program tree, and we display all the errors and exit
        // after we visit it so we can report multiple errors
        errorListener.printErrorList()
        return ASTProgram(classList, funcList, visitedStatements, CURR_ST)
    }

    override fun visitCls(ctx: WACCParser.ClsContext): ASTNode {
        CURR_ST = SymbolTable(CURR_ST)
        CLASS_ST = CURR_ST
        val name = ctx.IDENT().toString()
        CLASS_NAME = name + "_"
        val fieldsMap = mutableMapOf<String, ASTField>()
        for (field: WACCParser.FieldContext in ctx.field()) {
            val astField = visit(field) as ASTField
            val fieldName = field.IDENT().toString()
            fieldsMap.put(fieldName, astField)
            CURR_ST!!.add(fieldName, astField)
            CURR_ST!!.addField(astField)
        }

        val methodsList = mutableListOf<ASTMethod>()
        for (method: WACCParser.MethodContext in ctx.method()) {
            methodsList.add(visit(method) as ASTMethod)
        }
        val astClass = ASTClass(name, fieldsMap, methodsList, CURR_ST)
        TOP_ST.addClass(name, astClass)
        CURR_ST = CURR_ST!!.getEnclosingTable()
        CLASS_ST = null
        CLASS_NAME = ""
        return astClass
    }

    override fun visitField(ctx: WACCParser.FieldContext): ASTNode {
        val name = ctx.IDENT().toString()
        var visibility: Visibility = Visibility.PUBLIC // default visibility is public
        when {
            ctx.visib_ident().PRIVATE() != null -> visibility = Visibility.PRIVATE
            ctx.visib_ident().PUBLIC() != null -> visibility = Visibility.PUBLIC
        }
        val astIdent: ASTIdentifier
        if (ctx.ASSIGN() == null) {
            when (visit(ctx.type()).getType()) {
                TYPES.INT -> astIdent = ASTIdentifier(name, ASTInt(0), 0, CURR_ST)
                TYPES.CHAR -> astIdent = ASTIdentifier(name, ASTChar('\u0000'), 0, CURR_ST)
                TYPES.BOOL -> astIdent = ASTIdentifier(name, ASTBool(false), 0, CURR_ST)
                TYPES.STRING -> astIdent = ASTIdentifier(name, ASTString("\"\""), 0, CURR_ST)
                else -> {
                    errorListener.errorHandler(
                        "Declared field must be initialized",
                        SEMANTIC_ERROR,
                        ctx
                    )
                    astIdent = ASTIdentifier(name, ASTNull(), 0, CURR_ST)
                }
            }
        } else {
            val declaredType = visit(ctx.type()).getType()
            val actualType = visit(ctx.assign_rhs()).getType()
            if (declaredType != actualType) {
                errorListener.errorHandler(
                    "Field $name is declared as $declaredType, but initialized as $actualType",
                    SEMANTIC_ERROR,
                    ctx
                )
            }
            astIdent = ASTIdentifier(name, visit(ctx.assign_rhs()), 0, CURR_ST)
        }
        return ASTField(visibility, astIdent)
    }

    override fun visitFunc(ctx: WACCParser.FuncContext): ASTNode {
        CURR_ST = SymbolTable(CURR_ST)

        val visitedParamList = mutableListOf<ASTNode>()
        if (ctx.param_list() != null) {
            val paramSize = ctx.param_list().param().size * 4 + method_offset
            for (param in ctx.param_list().param()) {
                val ident = param.IDENT().toString()
                val visitedParam: ASTNode = visit(param.type())
                val variable = ASTIdentifier(
                    ident, visitedParam,
                    paramSize - 4 - CURR_ST!!.size(), CURR_ST
                )
                CURR_ST!!.add(ident, variable)
                visitedParamList.add(variable)
            }
        }

        if (method_offset == 4) {
            val methodIdent = ASTIdentifier("method", ASTAnyType(), 0, CURR_ST)
            CURR_ST!!.add("method", methodIdent)
            visitedParamList.add(ASTAnyType())
        }

        val declared = visit(ctx.type())
        val stat = visit(ctx.stat()) as ASTStatement

        val actualType = stat.getBaseType()

        if (actualType is ASTNull) {
            errorListener.errorHandler(
                "Function ${ctx.IDENT()} does not have a return statement!",
                SYNTAX_ERROR,
                ctx
            )
        } else if (actualType != declared.getBaseType()) {
            errorListener.errorHandler(
                "Function ${ctx.IDENT()} has incorrect type: " +
                    "expected ${declared.getBaseType().getType()}, actual ${stat.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        val funcST = CURR_ST
        CURR_ST = CURR_ST!!.getEnclosingTable()
        return ASTFunction(
            declared,
            CLASS_NAME + ctx.IDENT().toString(),
            visitedParamList,
            stat,
            funcST
        )
    }

    override fun visitMethod(ctx: WACCParser.MethodContext): ASTNode {
        method_offset = 4
        val func: ASTFunction = visit(ctx.func()) as ASTFunction
        var visibility: Visibility = Visibility.PUBLIC // default visibility is public
        when {
            ctx.visib_ident().PRIVATE() != null -> visibility = Visibility.PRIVATE
            ctx.visib_ident().PUBLIC() != null -> visibility = Visibility.PUBLIC
        }
        method_offset = 0
        return ASTMethod(visibility, func)
    }

    override fun visitSkip(ctx: WACCParser.SkipContext): ASTNode {
        val stat = ASTStatement(STAT.SKIP, null, null, null, CURR_ST!!)
        return stat
    }

    override fun visitDeclaration(ctx: WACCParser.DeclarationContext): ASTNode {
        val type = visit(ctx.type())
        val ident = ctx.IDENT().toString()
        val id = CURR_ST!!.lookup(ident)

        if (id != null) {
            //ERROR variable already defined
            errorListener.errorHandler(
                "Variable ${ctx.IDENT()} already defined",
                SEMANTIC_ERROR,
                ctx
            )
        }

        val rhs = visit(ctx.assign_rhs())

        if (type.getBaseType() != rhs.getBaseType()) {
            errorListener.errorHandler(
                "Cannot assign value of type ${rhs.getType()} to value of type ${type.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }

        val variable = ASTIdentifier(ident, type, CURR_ST!!.size() + 4, CURR_ST)
        val stat = ASTStatement(STAT.DECLARATION, variable, rhs, null, CURR_ST!!)
        CURR_ST!!.add(ident, variable)
        return stat
    }

    override fun visitAssign(ctx: WACCParser.AssignContext): ASTNode {

        val declaredType = visit(ctx.assign_lhs())
        if (declaredType == ASTNull()) {
            //ERROR declared type is not a valid type
            errorListener.errorHandler(
                "${declaredType.getType()} is not a valid type",
                SEMANTIC_ERROR,
                ctx
            )
        }
        val actualType = visit(ctx.assign_rhs())
        if (actualType == ASTNull()) {
            //ERROR actual type is not a valid type
            errorListener.errorHandler(
                "${actualType.getType()} is not a valid type",
                SEMANTIC_ERROR,
                ctx
            )
        }
        if (actualType.getBaseType() != declaredType.getBaseType()) {
            //ERROR actual type is not a valid type
            errorListener.errorHandler(
                "Cannot assign value of type ${actualType.getType()} " +
                    "to value of type ${declaredType.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }

        val stat = ASTStatement(STAT.ASSIGN, declaredType, actualType, null, CURR_ST!!)
        return stat
    }

    override fun visitRead(ctx: WACCParser.ReadContext): ASTNode {
        val inputVariable = visit(ctx.assign_lhs())
        if (inputVariable.getType() != TYPES.INT && inputVariable.getType() != TYPES.CHAR) {
            errorListener.errorHandler(
                "Read statements can only be called on int or char variables, " +
                    "instead got: ${inputVariable.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        val stat = ASTStatement(STAT.READ, inputVariable, null, null, CURR_ST!!)
        return stat
    }

    override fun visitFree(ctx: WACCParser.FreeContext): ASTNode {
        val expr = visit(ctx.expr())
        if (expr.getType() != TYPES.PAIR && expr.getType() != TYPES.ARRAY) {
            errorListener.errorHandler(
                "Free statements can only be called on pair or array variables, " +
                    "instead got: ${expr.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        val stat = ASTStatement(STAT.FREE, null, null, expr, CURR_ST!!)
        return stat

    }

    override fun visitReturn(ctx: WACCParser.ReturnContext): ASTNode {
        var currCtx: ParserRuleContext = ctx
        val returnType = visit(ctx.expr())
        while (true) {
            currCtx = currCtx.getParent()
            if (currCtx is WACCParser.FuncContext) {
                val stat = ASTStatement(STAT.RETURN, null, null, returnType, CURR_ST!!)
                return stat
            } else if (currCtx is WACCParser.ProgramContext) {
                errorListener.errorHandler(
                    "Returning from the main program is not allowed",
                    SEMANTIC_ERROR,
                    ctx
                )
                break
            }
        }
        val stat = ASTStatement(STAT.RETURN, null, null, returnType, CURR_ST!!)
        return stat
    }

    override fun visitExit(ctx: WACCParser.ExitContext): ASTNode {
        val expr = visit(ctx.expr())
        if (expr.getType() != TYPES.INT) {
            errorListener.errorHandler(
                "Exit statement should return an Int, instead got: ${expr.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        val stat = ASTStatement(STAT.EXIT, null, null, expr, CURR_ST!!)
        return stat
    }

    override fun visitBegin(ctx: WACCParser.BeginContext): ASTNode {
        CURR_ST = SymbolTable(CURR_ST)
        val childStat = visit(ctx.stat())
        val statement = ASTStatement(STAT.BEGIN, childStat, null, null, CURR_ST!!)
        CURR_ST = CURR_ST!!.getEnclosingTable()
        return statement
    }

    override fun visitPrint(ctx: WACCParser.PrintContext): ASTNode {
        val expr = visit(ctx.expr())
        val stat = ASTStatement(STAT.PRINT, null, null, expr, CURR_ST!!)
        return stat
    }

    override fun visitPrintln(ctx: WACCParser.PrintlnContext): ASTNode {
        val expr = visit(ctx.expr())
        val stat = ASTStatement(STAT.PRINTLN, null, null, expr, CURR_ST!!)
        return stat
    }

    override fun visitIf(ctx: WACCParser.IfContext): ASTNode {
        val condition = visit(ctx.expr())
        if (condition.getType() != TYPES.BOOL) {
            errorListener.errorHandler(
                "If condition should be of type bool, instead got: ${condition.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        CURR_ST = SymbolTable(CURR_ST)
        val typeThen = visit(ctx.stat(0)) as ASTStatement
        CURR_ST = CURR_ST!!.getEnclosingTable()
        CURR_ST = SymbolTable(CURR_ST)
        val typeElse = visit(ctx.stat(1)) as ASTStatement
        CURR_ST = CURR_ST!!.getEnclosingTable()
        return ASTStatement(STAT.IF, typeThen, typeElse, condition, CURR_ST!!)
    }

    override fun visitWhile(ctx: WACCParser.WhileContext): ASTNode {
        val condition = visit(ctx.expr())
        // LoopCount flag is incremented when loop is entered
        loopCount++
        if (condition.getType() != TYPES.BOOL) {
            errorListener.errorHandler(
                "While condition should be of type bool, instead got: ${condition.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        CURR_ST = SymbolTable(CURR_ST)
        val body = visit(ctx.stat()) as ASTStatement
        CURR_ST = CURR_ST!!.getEnclosingTable()
        val stat = ASTStatement(STAT.WHILE, body, null, condition, CURR_ST!!)
        // LoopCount flag is decremented when loop is exited
        loopCount--
        return stat
    }

    override fun visitDoWhile(ctx: WACCParser.DoWhileContext): ASTNode {
        // LoopCount flag is incremented when loop is entered
        loopCount++
        CURR_ST = SymbolTable(CURR_ST)
        val body = visit(ctx.stat()) as ASTStatement
        CURR_ST = CURR_ST!!.getEnclosingTable()
        val condition = visit(ctx.expr())

        if (condition.getType() != TYPES.BOOL) {
            errorListener.errorHandler(
                "Do-While condition should be of type bool, instead got: ${condition.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        val stat = ASTStatement(STAT.DOWHILE, body, null, condition, CURR_ST!!)
        // LoopCount flag is decremented when loop is exited
        loopCount--
        return stat
    }

    override fun visitFor(ctx: WACCParser.ForContext): ASTNode {
        // LoopCount flag is incremented when loop is entered
        loopCount++
        CURR_ST = SymbolTable(CURR_ST)
        val iterator = visit(ctx.stat(0)) as ASTStatement
        var iteratorType: TYPES = TYPES.ANY
        // For loops must have int iterators, they can e declared inside or outside the loop
        if (iterator.getStatType() != STAT.DECLARATION && iterator.getStatType() != STAT.ASSIGN) {
            errorListener.errorHandler(
                "Initialisation of FOR must be an integer declaration or assignment, instead got: ${iterator.getStatType()}",
                SYNTAX_ERROR,
                ctx
            )
        } else if (iterator.getStatType() == STAT.ASSIGN && (iterator.getLhs() !is ASTNull)) {
            val ident = iterator.getLhs() as ASTIdentifier
            val name = ident.getName()
            iteratorType = CURR_ST!!.lookupAll(name)!!.getType()
            if (iteratorType != TYPES.INT) {
                errorListener.errorHandler(
                    "FOR iterator must have type INT, instead got: $iteratorType",
                    SEMANTIC_ERROR,
                    ctx
                )
            }
        } else if (iterator.getLhs() !is ASTNull) {
            iteratorType = iterator.getLhs()!!.getType()
            if (iteratorType != TYPES.INT) {
                errorListener.errorHandler(
                    "FOR iterator must have type INT, instead got: $iteratorType",
                    SEMANTIC_ERROR,
                    ctx
                )
            }
        }

        // Loop condition must be boolean
        val condition = visit(ctx.expr())
        if (condition.getType() != TYPES.BOOL) {
            errorListener.errorHandler(
                "FOR loop condition is not a boolean, instead got: ${condition.getType()}",
                SEMANTIC_ERROR,
                ctx
            )
        }

        // Incrementing statement must be performed on the same type as the iterator's
        val incrementor = visit(ctx.stat(1)) as ASTStatement
        if (incrementor.getStatType() != STAT.ASSIGN) {
            errorListener.errorHandler(
                "FOR must have an ending assignment statement, instead got: ${incrementor.getStatType()}",
                SYNTAX_ERROR,
                ctx
            )
        } else {
            val incrementerLhs = incrementor.getLhs()!!.getType()
            if (incrementerLhs != iteratorType) {
                errorListener.errorHandler(
                    "Iterator incrementation must be performed over $iteratorType, instead got $incrementerLhs",
                    SEMANTIC_ERROR,
                    ctx
                )
            }
        }

        val forST = CURR_ST

        var body = visit(ctx.stat(2)) as ASTStatement
        // Add incrementer to tail of the body
        body = ASTStatement(STAT.SEMICOLON, body, incrementor, null, forST!!)

        CURR_ST = CURR_ST!!.getEnclosingTable()
        val stat = ASTStatement(STAT.FOR, iterator, body, condition, forST)
        // LoopCount flag is decremented when loop is exited
        loopCount--
        return stat
    }

    override fun visitBreak(ctx: WACCParser.BreakContext): ASTNode {
        if (loopCount <= 0) {
            errorListener.errorHandler(
                "Break statement should be inside of a loop",
                SEMANTIC_ERROR,
                ctx
            )
        }
        return ASTStatement(STAT.BREAK, null, null, null, CURR_ST!!)
    }

    override fun visitContinue(ctx: WACCParser.ContinueContext): ASTNode {
        if (loopCount <= 0) {
            errorListener.errorHandler(
                "Continue statement should be inside of a loop",
                SEMANTIC_ERROR,
                ctx
            )
        }
        return ASTStatement(STAT.CONTINUE, null, null, null, CURR_ST!!)
    }

    override fun visitSemicolon(ctx: WACCParser.SemicolonContext): ASTNode {
        val stat0 = visit(ctx.stat(0)) as ASTStatement
        val stat1 = visit(ctx.stat(1)) as ASTStatement
        return ASTStatement(STAT.SEMICOLON, stat0, stat1, null, CURR_ST!!)
    }

    override fun visitIntLiter2(ctx: WACCParser.IntLiter2Context): ASTNode {
        return visit(ctx.int_liter2())
    }

    override fun visitBoolLiter(ctx: WACCParser.BoolLiterContext): ASTNode {
        return ASTBool(ctx.BOOL_LITER().text.toBoolean())
    }

    override fun visitCharLiter(ctx: WACCParser.CharLiterContext): ASTNode {
        if (ctx.CHAR_LITER().text[1] == '\\') {
            return ASTChar(ctx.CHAR_LITER().text[2])
        }
        return ASTChar(ctx.CHAR_LITER().text[1])
    }

    override fun visitStringLiter(ctx: WACCParser.StringLiterContext): ASTNode {
        return ASTString(ctx.STRING_LITER().text)
    }

    override fun visitPairLiter(ctx: WACCParser.PairLiterContext): ASTNode {
        return visit(ctx.pair_liter())
    }

    override fun visitArrayElem(ctx: WACCParser.ArrayElemContext): ASTNode {
        return visit(ctx.array_elem())
    }

    override fun visitUnOp(ctx: WACCParser.UnOpContext): ASTNode {
        return visit(ctx.unary_oper())
    }

    override fun visitOpenParantheses(ctx: WACCParser.OpenParanthesesContext): ASTNode {
        return visit(ctx.expr())
    }

    override fun visitIdentifier(ctx: WACCParser.IdentifierContext): ASTNode {
        val id = CURR_ST!!.lookupAll(ctx.IDENT().toString())
        if (id == null) {
            errorListener.errorHandler(
                "Variable ${ctx.IDENT()} not declared in this scope",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTNull()
        }
        if (id is ASTField) {
            return ASTMethodField(CURR_ST!!.getFieldOffset(id), id.getIdent())
        } else {
            return id;
        }
    }

    override fun visitUnary_oper(ctx: WACCParser.Unary_operContext): ASTNode {

        // get parent expression
        val parentContext: WACCParser.UnOpContext = ctx.getParent() as WACCParser.UnOpContext

        // get expression over unary
        val expr: ASTNode = visit(parentContext.expr())
        when {
            expr != ASTNull() -> {
                if (ctx.NOT() != null && expr.getType() == TYPES.BOOL) {
                    return ASTUnOp(
                        WACCParser.NOT,
                        TYPES.BOOL,
                        expr
                    )
                } else if (ctx.MINUS() != null && expr.getType() == TYPES.INT) {
                    return ASTUnOp(
                        WACCParser.MINUS,
                        TYPES.INT,
                        expr
                    )
                } else if (ctx.LEN() != null && expr.getType() == TYPES.ARRAY) {
                    return ASTUnOp(WACCParser.LEN, TYPES.INT, visit(parentContext.expr()))
                } else if (ctx.CHR() != null && expr.getType() == TYPES.INT) {
                    return ASTUnOp(
                        WACCParser.CHR,
                        TYPES.CHAR,
                        expr
                    )
                } else if (ctx.ORD() != null && expr.getType() == TYPES.CHAR) {
                    return ASTUnOp(
                        WACCParser.ORD,
                        TYPES.INT,
                        expr
                    )
                } else if (ctx.BITWISE_NOT() != null && expr.getType() == TYPES.INT) {
                    return ASTUnOp(
                        WACCParser.BITWISE_NOT,
                        TYPES.INT,
                        expr
                    )
                }
            }
        }
        errorListener.errorHandler("Incorrect use of unary operator", SEMANTIC_ERROR, ctx)
        return ASTNull()
    }

    private fun checkExpressionsNotNull(
        expr1: ASTNode,
        expr2: ASTNode,
        ctx: ParserRuleContext
    ): Boolean {
        if (expr1 == ASTNull() || expr2 == ASTNull()) {
            errorListener.errorHandler("Incorrect use of binary operator", SEMANTIC_ERROR, ctx)
            return true
        }
        return false
    }

    private fun createTypeError(ctx: WACCParser.BinOpContext, lhsType: TYPES, rhsType: TYPES) {
        errorListener.errorHandler(
            "Incorrect type for arguments of binary operation ${ctx.bin_op.type}, " +
                "cannot apply binary operation on types ${lhsType} and ${rhsType}",
            SEMANTIC_ERROR,
            ctx
        )
    }

    override fun visitBinOp(ctx: WACCParser.BinOpContext): ASTNode {
        val lhs: ASTNode = visit(ctx.lhs)
        val rhs: ASTNode = visit(ctx.rhs)

        if (checkExpressionsNotNull(lhs, rhs, ctx)) return ASTNull()

        val lhsType: TYPES = lhs.getType()
        val rhsType: TYPES = rhs.getType()

        // Optimization: constant evaluation
        // For each type of operation, we test if the lhs and the rhs are 
        // already evaluated(i.e. a final node like ASTInt/ASTBool)
        // and do the operation ourselves at compile time
        when (ctx.bin_op.type) {
            WACCParser.DIV, WACCParser.MOD, WACCParser.MULT, WACCParser.PLUS,
            WACCParser.MINUS, WACCParser.BITWISE_AND, WACCParser.BITWISE_OR,
            WACCParser.BITWISE_XOR -> {
                if (lhsType == rhsType && rhsType == TYPES.INT) {
                    if (optimisationFlagConstantEvaluation && lhs is ASTInt && rhs is ASTInt) {
                        when (ctx.bin_op.type) {
                            WACCParser.PLUS -> {
                                return checkForOverflowError(
                                    lhs,
                                    rhs,
                                    ctx,
                                    lhs.getValue() + rhs.getValue()
                                )
                            }
                            WACCParser.MINUS -> {
                                return checkForOverflowError(
                                    lhs,
                                    rhs,
                                    ctx,
                                    lhs.getValue() - rhs.getValue()
                                )
                            }
                            WACCParser.MULT -> {
                                return checkForOverflowError(
                                    lhs,
                                    rhs,
                                    ctx,
                                    lhs.getValue() * rhs.getValue()
                                )
                            }
                            WACCParser.DIV -> {
                                if (rhs.getValue() == 0L) {
                                    errorListener.errorHandler(
                                        "Division by zero",
                                        SEMANTIC_ERROR,
                                        ctx
                                    )
                                    return ASTBinOp(ctx.bin_op.type, TYPES.INT, lhs, rhs)
                                }
                                return checkForOverflowError(
                                    lhs,
                                    rhs,
                                    ctx,
                                    lhs.getValue() / rhs.getValue()
                                )
                            }
                            WACCParser.MOD -> {
                                if (rhs.getValue() == 0L) {
                                    errorListener.errorHandler(
                                        "Mod by zero",
                                        SEMANTIC_ERROR,
                                        ctx
                                    )
                                    return ASTBinOp(ctx.bin_op.type, TYPES.INT, lhs, rhs)
                                }
                                return checkForOverflowError(
                                    lhs,
                                    rhs,
                                    ctx,
                                    lhs.getValue() % rhs.getValue()
                                )
                            }
                        }
                    }
                    return ASTBinOp(ctx.bin_op.type, TYPES.INT, lhs, rhs)
                } else createTypeError(ctx, lhsType, rhsType)
            }
            WACCParser.GT, WACCParser.GTE, WACCParser.LT, WACCParser.LTE -> {
                if (lhsType == rhsType && (lhsType == TYPES.INT || lhsType == TYPES.CHAR)) {
                    if (optimisationFlagConstantEvaluation) {
                        if (lhs is ASTInt && rhs is ASTInt) {
                            when (ctx.bin_op.type) {
                                WACCParser.GT -> return ASTBool(lhs.getValue() > rhs.getValue())
                                WACCParser.GTE -> return ASTBool(lhs.getValue() >= rhs.getValue())
                                WACCParser.LT -> return ASTBool(lhs.getValue() < rhs.getValue())
                                WACCParser.LTE -> return ASTBool(lhs.getValue() <= rhs.getValue())
                            }
                        }
                        if (lhs is ASTChar && rhs is ASTChar) {
                            when (ctx.bin_op.type) {
                                WACCParser.GT -> return ASTBool(lhs.getValue() > rhs.getValue())
                                WACCParser.GTE -> return ASTBool(lhs.getValue() >= rhs.getValue())
                                WACCParser.LT -> return ASTBool(lhs.getValue() < rhs.getValue())
                                WACCParser.LTE -> return ASTBool(lhs.getValue() <= rhs.getValue())
                            }
                        }
                    }
                    return ASTBinOp(ctx.bin_op.type, TYPES.BOOL, lhs, rhs)
                } else createTypeError(ctx, lhsType, rhsType)
            }
            WACCParser.EQUAL, WACCParser.NOT_EQUAL -> {
                if (lhsType == rhsType) {
                    if (optimisationFlagConstantEvaluation) {
                        if (lhs is ASTInt && rhs is ASTInt) {
                            when (ctx.bin_op.type) {
                                WACCParser.EQUAL -> return ASTBool(lhs.getValue() == rhs.getValue())
                                WACCParser.NOT_EQUAL -> return ASTBool(lhs.getValue() != rhs.getValue())
                            }
                        }
                        if (lhs is ASTBool && rhs is ASTBool) {
                            when (ctx.bin_op.type) {
                                WACCParser.EQUAL -> return ASTBool(lhs.getValue() == rhs.getValue())
                                WACCParser.NOT_EQUAL -> return ASTBool(lhs.getValue() != rhs.getValue())
                            }
                        }
                        if (lhs is ASTChar && rhs is ASTChar) {
                            when (ctx.bin_op.type) {
                                WACCParser.EQUAL -> return ASTBool(lhs.getValue() == rhs.getValue())
                                WACCParser.NOT_EQUAL -> return ASTBool(lhs.getValue() != rhs.getValue())
                            }
                        }
                        if (lhs is ASTString && rhs is ASTString) {
                            when (ctx.bin_op.type) {
                                WACCParser.EQUAL -> return ASTBool(lhs.getValue() == rhs.getValue())
                                WACCParser.NOT_EQUAL -> return ASTBool(lhs.getValue() != rhs.getValue())
                            }
                        }
                    }
                    return ASTBinOp(ctx.bin_op.type, TYPES.BOOL, lhs, rhs)
                } else createTypeError(ctx, lhsType, rhsType)
            }
            WACCParser.AND, WACCParser.OR -> {
                if (lhsType == rhsType && (lhsType == TYPES.BOOL)) {
                    if (optimisationFlagConstantEvaluation) {
                        // Shortcircuting
                        if (lhs is ASTBool) {
                            if (ctx.bin_op.type == WACCParser.AND && !lhs.getValue()) return ASTBool(
                                false
                            )
                            if (ctx.bin_op.type == WACCParser.OR && lhs.getValue()) return ASTBool(
                                true
                            )
                        }
                        if (rhs is ASTBool) {
                            if (ctx.bin_op.type == WACCParser.AND && !rhs.getValue()) return ASTBool(
                                false
                            )
                            if (ctx.bin_op.type == WACCParser.OR && rhs.getValue()) return ASTBool(
                                true
                            )
                        }
                        // Constant evaluation
                        if (lhs is ASTBool && rhs is ASTBool) {
                            when (ctx.bin_op.type) {
                                WACCParser.AND -> return ASTBool(lhs.getValue() && rhs.getValue())
                                WACCParser.OR -> return ASTBool(lhs.getValue() || rhs.getValue())
                            }
                        }
                    }
                    return ASTBinOp(ctx.bin_op.type, TYPES.BOOL, lhs, rhs)
                } else createTypeError(ctx, lhsType, rhsType)
            }
        }


        return ASTNull()
    }

    fun checkForOverflowError(
        lhs: ASTInt,
        rhs: ASTInt,
        ctx: WACCParser.BinOpContext,
        result: Long
    ): ASTNode {
        if (result > lhs.MAX || result < lhs.MIN) {
            errorListener.errorHandler(
                "OverflowError: the result of this operation is too small/large to store in a 4-byte signed-integer",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTBinOp(ctx.bin_op.type, TYPES.INT, lhs, rhs)
        }
        return ASTInt(result)
    }

    override fun visitInt_liter2(ctx: WACCParser.Int_liter2Context): ASTNode {
        var number = deleteLeadingZeros(ctx.INT_LITER().toString())
        if (ctx.MINUS() != null) {
            val bound = ASTInt(0).MIN.toString().substring(1)
            if (number.length > bound.length ||
                (number.length == bound.length && number.compareTo(bound) > 0)
            ) {
                errorListener.errorHandler("Integer is too small", SYNTAX_ERROR, ctx)
            }
        } else {
            val bound = ASTInt(0).MAX.toString()
            if (number.length > bound.length ||
                (number.length == bound.length && number.compareTo(bound) > 0)
            ) {
                errorListener.errorHandler("Integer is too big", SYNTAX_ERROR, ctx)
            }
        }
        if (number == "") {
            number = "0"
        }
        var value = number.toLong()
        if (ctx.MINUS() != null) value = -value
        return ASTInt(value)
    }

    fun deleteLeadingZeros(s: String): String {
        var i = 0
        while (i < s.length && s[i] == '0') {
            i++
        }
        return s.substring(i)
    }

    override fun visitArray_elem(ctx: WACCParser.Array_elemContext): ASTNode {
        val id = CURR_ST!!.lookupAll(ctx.IDENT().toString())
        if (id == null) {
            errorListener.errorHandler(
                "Variable ${ctx.IDENT()} not declared in this scope",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTNull()
        }
        if (id.getType() != TYPES.ARRAY) {
            errorListener.errorHandler(
                "Variable ${ctx.IDENT()} is not an array",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTNull()
        }

        var idA: ASTType = id.getBaseType() as ASTType
        var elems = mutableListOf<ASTNode>()
        for (expr in ctx.expr()) {
            if (idA.getType() != TYPES.ARRAY) {
                errorListener.errorHandler(
                    "RHS array ${ctx.IDENT()} has too many dimensions",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            }
            val exprNode = visit(expr)
            if (exprNode.getType() != TYPES.INT) {
                errorListener.errorHandler(
                    "Index of array ${ctx.IDENT()} is not an integer, " +
                        "instead got: ${exprNode.getType()}",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            }
            idA = (idA as ASTArrayType).getElemType()
            elems.add(exprNode);
        }

        return ASTArrayElem(id as ASTIdentifier, idA, elems)
    }

    override fun visitAssign_lhs(ctx: WACCParser.Assign_lhsContext): ASTNode {
        if (ctx.class_field() != null) {
            return visitClass_field(ctx.class_field())
        } else if (ctx.IDENT() != null) {
            val id: ASTNode? = CURR_ST!!.lookupAll(ctx.IDENT().toString())
            if (id == null) {
                errorListener.errorHandler(
                    "Variable ${ctx.IDENT()} not declared in this scope",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            }
            if (id is ASTField) {
                return ASTMethodField(CURR_ST!!.getFieldOffset(id), id.getIdent())
            } else {
                return id;
            }
        } else if (ctx.array_elem() != null) {
            return visitArray_elem(ctx.array_elem())
        } else if (ctx.pair_elem() != null) {
            return visitPair_elem(ctx.pair_elem())
        }
        return ASTNull()
    }

    override fun visitClass_field(ctx: WACCParser.Class_fieldContext): ASTNode {
        val class_ident = CURR_ST!!.lookup(ctx.class_ident.getText())
        if (class_ident == null) {
            errorListener.errorHandler(
                "Variable ${ctx.class_ident.getText()} not declared in this scope",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTNull()
        }
        val astClass = (class_ident as ASTIdentifier).getValue() as ASTClass
        if (astClass.getFields().get(ctx.field_ident.getText()) == null) {
            errorListener.errorHandler(
                "Class ${astClass.getName()} dose not have field ${ctx.field_ident.getText()}",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTNull()
        }
        if (astClass.getFields().get(ctx.field_ident.getText())!!
                .getVisib() == Visibility.PRIVATE
        ) {
            errorListener.errorHandler(
                "Cannot access private field ${ctx.field_ident.getText()} in class ${astClass.getName()}",
                SEMANTIC_ERROR,
                ctx
            )
        }
        return ASTFieldAccess(class_ident, ctx.field_ident.getText())
    }

    override fun visitPair_liter(ctx: WACCParser.Pair_literContext): ASTNode {
        return ASTPair(ASTAnyType(), ASTAnyType())
    }

    override fun visitType(ctx: WACCParser.TypeContext): ASTNode {
        if (ctx.type() != null) {
            val arrayType = visit(ctx.type()) as ASTType
            return ASTArrayType(arrayType)
        } else if (ctx.base_type() != null) {
            return visit(ctx.base_type())
        } else if (ctx.pair_type() != null) {
            return visit(ctx.pair_type())
        } else if (ctx.IDENT() != null && TOP_ST.lookupClass(ctx.IDENT().toString()) != null) {
            return TOP_ST.lookupClass(ctx.IDENT().toString())!!
        }

        return ASTNull()
    }

    override fun visitPair_elem(ctx: WACCParser.Pair_elemContext): ASTNode {
        var pair = visit(ctx.expr())
        if (pair is ASTPair && pair.getFstType() is ASTAnyType &&
            pair.getSndType() is ASTAnyType
        ) {
            errorListener.errorHandler(
                "NullReferenceError: dereference a null reference",
                SEMANTIC_ERROR,
                ctx
            )
            return ASTNull()
        }
        if (ctx.FST() != null) {
            if (pair.getType() != TYPES.PAIR) {
                errorListener.errorHandler("Expression is not a pair", SEMANTIC_ERROR, ctx)
                return ASTNull()
            }
            return ASTPairElem(pair, true)
        }
        if (ctx.SND() != null) {
            if (pair.getType() != TYPES.PAIR) {
                errorListener.errorHandler("Expression is not a pair", SEMANTIC_ERROR, ctx)
                return ASTNull()
            }
            return ASTPairElem(pair, false)
        }
        return ASTNull()
    }

    override fun visitBase_type(ctx: WACCParser.Base_typeContext): ASTNode {
        if (ctx.INT() != null) {
            return ASTType(TYPES.INT)
        } else if (ctx.CHAR() != null) {
            return ASTType(TYPES.CHAR)
        } else if (ctx.BOOL() != null) {
            return ASTType(TYPES.BOOL)
        } else if (ctx.STRING() != null) {
            return ASTType(TYPES.STRING)
        } else {
            errorListener.errorHandler("Type not recognized!", SYNTAX_ERROR, ctx)
        }
        return ASTNull()
    }

    override fun visitPair_type(ctx: WACCParser.Pair_typeContext): ASTNode {
        val pairSize = ctx.pair_element_type().size
        if (pairSize != 2) {
            errorListener.errorHandler(
                "Pair can only have 2 elements, instead has: $pairSize",
                SYNTAX_ERROR,
                ctx
            )
        }
        val fst = visit(ctx.fst)
        val snd = visit(ctx.snd)
        if (fst == ASTNull() || snd == ASTNull()) {
            return ASTNull()
        }
        return ASTPair(fst, snd)
    }

    override fun visitPair_element_type(ctx: WACCParser.Pair_element_typeContext): ASTNode {
        if (ctx.base_type() != null)
            return visit(ctx.base_type())
        else if (ctx.type() != null)
            return ASTArrayType(ASTAnyType())
        else if (ctx.fst != null) {
            val fst = visit(ctx.fst)
            val snd = visit(ctx.snd)
            return ASTPair(fst, snd)
        } else if (ctx.PAIR() != null)
            return ASTPair(ASTAnyType(), ASTAnyType())
        else
            errorListener.errorHandler("Invalid pairElem type", SYNTAX_ERROR, ctx)
        return ASTNull()
    }

    override fun visitArray_liter(ctx: WACCParser.Array_literContext): ASTNode {
        val elements: List<ExprContext> = ctx.expr();

        var typeArray: ASTType = ASTAnyType();

        var elems = mutableListOf<ASTNode>()

        for (element: ExprContext in elements) {
            val id: ASTNode = visit(element)
            if (id.getBaseType() == ASTNull()) {
                errorListener.errorHandler("Invalid expression", SEMANTIC_ERROR, ctx)
                return ASTNull()
            } else if (typeArray == ASTAnyType()) {
                //first element
                typeArray = id.getBaseType() as ASTType
            } else if (typeArray != (id.getBaseType() as ASTType)) {
                errorListener.errorHandler(
                    "These array values don't have the same type",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            }
            elems.add(id);
        }
        return ASTArrayLiter(ASTArrayType(typeArray), elems)
    }

    override fun visitAssign_rhs(ctx: WACCParser.Assign_rhsContext): ASTNode {

        if (ctx.NEW_PAIR() != null) {
            val fst: ASTNode = visit(ctx.expr(0))
            val snd: ASTNode = visit(ctx.expr(1))
            return ASTPair(fst, snd)
        } else if (ctx.array_liter() != null) {
            return visitArray_liter(ctx.array_liter())
        } else if (ctx.pair_elem() != null) {
            return visitPair_elem(ctx.pair_elem())
        } else if (ctx.CALL() != null) {

            var args = mutableListOf<ExprContext>()
            if (ctx.arg_list() != null) {
                val args_list: WACCParser.Arg_listContext = ctx.arg_list()
                args = args_list.expr()
            }

            val function_args = mutableListOf<ASTNode>()
            val functionArgsType = mutableListOf<ASTType>()
            for (i in 0..(args.size - 1)) {
                val param = visit(args.get(i))
                function_args.add(param)
                functionArgsType.add(param.getBaseType() as ASTType)
            }

            val functionSignature = FunctionSignature(ctx.IDENT().toString(), functionArgsType)
            val id = TOP_ST.lookupAllFunction(functionSignature)
            if (id == null) {
                //Error function not defined
                errorListener.errorHandler(
                    "There is no function with this signature",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            } else if (id is ASTMethod) {
                errorListener.errorHandler(
                    "This is a method call, not a function call as expected",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()

            }

            return ASTFunction(
                id.getReturnType(),
                id.getName(),
                function_args,
                id.getStat(),
                id.getST()
            )
        } else if (ctx.expr(0) != null) {
            return visit(ctx.expr(0));
        } else if (ctx.NEW() != null) {
            if (TOP_ST.lookupClass(
                    ctx.IDENT().toString()
                ) != null
            ) return TOP_ST.lookupClass(ctx.IDENT().toString())!!
        } else if (ctx.method_call() != null) {
            val newCtx = ctx.method_call()
            var className = CLASS_NAME

            var args = mutableListOf<ExprContext>()
            if (newCtx.arg_list() != null) {
                val args_list: WACCParser.Arg_listContext = newCtx.arg_list()
                args = args_list.expr()
            }

            val function_args = mutableListOf<ASTNode>()
            val functionArgsType = mutableListOf<ASTType>()
            for (i in 0..(args.size - 1)) {
                val param = visit(args.get(i))
                function_args.add(param)
                functionArgsType.add(param.getBaseType() as ASTType)
            }

            if (newCtx.CALL_IN_CLASS() != null) {
                val methodIdent = ASTNull()
                function_args.add(methodIdent)
                functionArgsType.add(methodIdent)
            } else {
                val objectName = newCtx.IDENT(0).toString()
                var variable = CURR_ST!!.lookupAll(objectName)
                if (variable == null) {
                    //Error object not defined
                    errorListener.errorHandler(
                        "Object $objectName not defined",
                        SEMANTIC_ERROR,
                        ctx
                    )
                    return ASTNull()
                }
                variable = variable as ASTIdentifier
                if (variable.getType() != TYPES.CLASS) {
                    errorListener.errorHandler(
                        "$objectName is not an object",
                        SEMANTIC_ERROR,
                        ctx
                    )
                    return ASTNull()
                }
                function_args.add(variable)
                functionArgsType.add(ASTNull())
                className = (variable.getValue() as ASTClass).getName() + "_"
            }

            val methodLabelName = className +
                newCtx.IDENT(newCtx.IDENT().size - 1).toString()


            val functionSignature = FunctionSignature(methodLabelName, functionArgsType)
            val id = TOP_ST.lookupAllFunction(functionSignature)
            if (id == null) {
                //Error function not defined
                errorListener.errorHandler(
                    "There is no method with this signature",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            } else if (id !is ASTMethod) {
                errorListener.errorHandler(
                    "This is a function call, not a method call as expected",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()
            }

            if (id.getVisib() == Visibility.PRIVATE &&
                newCtx.CALL_IN_CLASS() == null
            ) {

                errorListener.errorHandler(
                    "Method ${id.getName()} is private and can't be accessed",
                    SEMANTIC_ERROR,
                    ctx
                )
                return ASTNull()

            }

            val function = ASTFunction(
                id.getReturnType(),
                id.getName(),
                function_args,
                id.getStat(),
                id.getST()
            )


            return ASTMethod(Visibility.PUBLIC, function)
        }
        errorListener.errorHandler("Incorrect assignment", SEMANTIC_ERROR, ctx)
        return ASTNull()
    }

}
