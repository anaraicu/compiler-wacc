package wacc

import back_end.instructions.*
import front_end.ASTNodes.*
import parser.WACCParser
import java.util.*

class CodeGenerator(
    val optimisationFlagControlFlow: Boolean,
    val optimisationFlagInstructionEvaluation: Boolean
) : ASTVisitor {

    private var instructions = mutableListOf<Instruction>()
    private var labelCounter = 0
    private var currentAvailableRegister = Register.R4
    private val lastAvailableRegister = Register.R10
    private var pushedOnStackCounter = 0
    private var dataSegment = mutableListOf<String>()
    private var pushOnStackFromFunc = 0
    private var pushOnStackFromLoop = 0
    private var CURR_ST: SymbolTable? = null
    private var paramsOffset = 0

    // Remember break and continue labels in stacks
    // Preserves the right functionality in case of nested loops with frequent breaks
    private val breakLabelStack: Stack<String> = Stack<String>()
    private val continueLabelStack: Stack<String> = Stack<String>()

    val cFunctionsCodeGenerator = CFunctionsCodeGenerator(this)
    val runtimeErrorsCodeGenerator = RuntimeErrorsCodeGenerator(this)
    val functionName: HashMap<String, Int> = HashMap()
    val functionLabel: HashMap<FunctionSignature, String> = HashMap()

    override fun visitProgram(program: ASTProgram) {
        instructions.add(Label("text"))
        instructions.add(Label("global main"))

        CURR_ST = program.getST()

        val funcLabels = mutableListOf<FunctionName>()

        for (function in program.getfuncs()) {
            val name = addOccurrenceOfFunction(function.getName())
            val labelForFunction = FunctionName(name, true)
            functionLabel.put(getFuncSignature(function), "f_" + name)
            funcLabels.add(labelForFunction)
        }

        var nrFunc = 0

        for (cls in program.getClasses()) {
            for (method in cls.getMethods()) {
                functionLabel.put(
                    getFuncSignature(method.getFunc()),
                    cls.getName() + "_" + method.getFunc().getName()
                )
            }
        }

        for (cls in program.getClasses()) {
            visitClass(cls)
        }

        for (function in program.getfuncs()) {
            visitFunction(function, funcLabels[nrFunc])
            nrFunc++
        }

        instructions.add(FunctionName("main"))
        instructions.add(Push(listOf(Register.LR)))

        visitRootStat(program.getStat())

        instructions.add(Load("0", Register.R0))
        instructions.add(Pop(listOf(Register.PC)))
        instructions.add(Label("ltorg", true))
    }

    override fun visitClass(cls: ASTClass) {

        for (method in cls.getMethods()) {
            visitFunction(
                method.getFunc(),
                FunctionName(cls.getName() + "_" + method.getFunc().getName())
            )
        }
    }

    override fun visitFunction(function: ASTFunction, label: FunctionName) {
        pushOnStackFromFunc = 0
        paramsOffset = function.getParams().size * 4
        instructions.add(label)
        instructions.add(Push(listOf(Register.LR)))
        // pushedOnStackCounter++
        val CURR_ST = function.getST()!!

        enterScope(CURR_ST, function.getParams().size * 4)
        //TO DO: ldr into registers from stack
        visitStatement(function.getStat(), Register.R4)
        exitScope(CURR_ST, function.getParams().size * 4)

        instructions.add(Label("ltorg", true))
    }

    private fun getFuncSignature(function: ASTFunction): FunctionSignature {
        val paramsType = mutableListOf<ASTType>()
        val params = function.getParams()
        for (param in params)
            paramsType.add(param.getBaseType() as ASTType)
        return FunctionSignature(function.getName(), paramsType)
    }

    private fun addOccurrenceOfFunction(name: String): String {
        var nr = functionName.get(name);
        if (nr == null)
            nr = 0
        nr++
        functionName.put(name, nr)
        return name + nr
    }

    override fun visitRootStat(statement: ASTStatement) {
        val CURR_ST = statement.getST()!!

        enterScope(CURR_ST, 0)

        visitStatement(statement, Register.R4)

        exitScope(CURR_ST, 0)
    }

    private fun enterScope(NEW_ST: SymbolTable, offset: Int) {
        CURR_ST = NEW_ST
        if (CURR_ST!!.size() == offset) {
            return
        }
        pushOnStackFromFunc += (CURR_ST!!.size() - offset)
        pushOnStackFromLoop += (CURR_ST!!.size() - offset)
        // instructions.add(Move(Register.R12, Register.SP))
        instructions.add(Subtract(Register.SP, Register.SP, CURR_ST!!.size() - offset))

    }

    private fun exitScope(ST: SymbolTable, offset: Int) {
        if (ST.size() == offset) {
            return
        }
        instructions.add(Add(Register.SP, Register.SP, ST.size() - offset))
        pushOnStackFromFunc -= (ST.size() - offset)
        pushOnStackFromLoop -= (ST.size() - offset)
        CURR_ST = ST.getEnclosingTable()

        // instructions.add(Move(Register.R12, Register.SP))
    }

    override fun visitStatement(statement: ASTStatement, reg: Register) {
        when (statement.getStatType()) {
            STAT.SKIP -> {
            }
            STAT.SEMICOLON -> visitSemiColonStat(statement, reg)
            STAT.DECLARATION, STAT.ASSIGN -> visitAssignStat(statement, reg)
            STAT.RETURN -> visitReturnStat(statement, reg)
            STAT.READ -> visitReadStat(statement, reg)
            STAT.FREE -> visitFreeStat(statement, reg)
            STAT.EXIT -> visitExitStat(statement, reg)
            STAT.PRINT -> visitPrintStat(statement, reg)
            STAT.PRINTLN -> visitPrintLnStat(statement, reg)
            STAT.IF -> visitIfStat(statement, reg)
            STAT.WHILE -> visitWhileStat(statement, reg)
            STAT.DOWHILE -> visitDoWhileStat(statement, reg)
            STAT.FOR -> visitForStat(statement, reg)
            STAT.BEGIN -> visitBeginStat(statement, reg)
            STAT.BREAK -> visitBreak(statement, reg)
            STAT.CONTINUE -> visitContinue(statement, reg)
        }
    }

    override fun visitBreak(statement: ASTStatement, reg: Register) {

        instructions.add(
            Add(
                Register.SP,
                Register.SP,
                pushOnStackFromLoop + (pushedOnStackCounter * 4)
            )
        )

        instructions.add(Branch(breakLabelStack.peek(), true))
    }

    override fun visitContinue(statement: ASTStatement, reg: Register) {
        instructions.add(Branch(continueLabelStack.peek(), true))
    }

    override fun visitSemiColonStat(statement: ASTStatement, reg: Register) {
        visitStatement(statement.getLhs() as ASTStatement, reg)
        visitStatement(statement.getRhs() as ASTStatement, reg)
    }

    override fun visitAssignStat(statement: ASTStatement, reg: Register) {

        val rhs = statement.getRhs()!!
        val lhs = statement.getLhs()!!

        visitAssignRhs(rhs, reg)
        var nextReg = nextAvailableRegister(reg)
        visitAssignLhs(lhs, nextReg)
        nextReg = popFromStackIfNoReg(reg)
        instructions.add(Store(reg, nextReg))
    }

    override fun visitAssignLhs(lhs: ASTNode, reg: Register) {
        when (lhs) {
            is ASTMethodField -> {
                instructions.add(
                    Load(
                        reg, Register.SP,
                        pushOnStackFromFunc + (pushedOnStackCounter * 4) + paramsOffset
                    )
                )
                instructions.add(Add(reg, reg, lhs.getOffset()))
            }
            is ASTIdentifier -> {
                val lhsIdent = lhs
                val offset = lhsIdent.getOffsetFromSp(pushedOnStackCounter, CURR_ST!!)
                instructions.add(Add(reg, Register.SP, offset))
            }
            is ASTArrayElem -> {
                visitArrayElem(lhs, reg)
            }
            is ASTPairElem -> {
                visitPairElem(lhs, reg)
            }
            is ASTFieldAccess -> {
                visitFieldAccess(lhs, reg)
            }
        }
    }

    override fun visitAssignRhs(rhs: ASTNode, reg: Register) {
        when (rhs) {
            is ASTInt,
            is ASTBool,
            is ASTChar,
            is ASTBinOp,
            is ASTUnOp,
            is ASTString,
            is ASTMethodField -> {
                // LDR r4, = expr
                visitExpression(rhs, reg)
            }
            is ASTIdentifier -> {
                val rhsIdent = rhs
                val offset = rhsIdent.getOffsetFromSp(pushedOnStackCounter, CURR_ST!!)
                instructions.add(Load(reg, Register.SP, offset))
            }
            is ASTArrayElem -> {
                visitArrayElem(rhs, reg)
                instructions.add(Load(reg, reg))
            }
            is ASTPairElem -> {
                visitPairElem(rhs, reg)
                instructions.add(Load(reg, reg))
            }
            is ASTPair -> {
                val rhsPair = rhs
                val fst = rhsPair.getFstType()
                val snd = rhsPair.getSndType()
                if (fst.getType() == TYPES.ANY && snd.getType() == TYPES.ANY) {
                    instructions.add(Move(reg, 0))
                } else {
                    visitNewPair(rhs, reg)
                }
            }
            is ASTArrayLiter -> {
                visitArrayLiter(rhs, reg)
            }
            is ASTFunction, is ASTMethod -> {
                val functionCall: ASTFunction
                val isMethod = rhs is ASTMethod
                functionCall = if (isMethod)
                    (rhs as ASTMethod).getFunc()
                else
                    rhs as ASTFunction
                val name = getFunctionLabel(getFuncSignature(functionCall))!!
                val args = functionCall.getParams().reversed()
                var total_offs = 0
                //TO DO: curr_offs = size of arg
                val curr_offs = 4
                if (args.isNotEmpty()) {
                    val arg = args.get(0)
                    if ((!isMethod) || (arg !is ASTNull)) {
                        visitExpression(arg, reg)
                    } else {
                        instructions.add(
                            Load(
                                reg, Register.SP,
                                pushOnStackFromFunc + (pushedOnStackCounter * 4) + paramsOffset
                            )
                        )
                    }
                    total_offs += curr_offs
                    instructions.add(Store(reg, Register.SP, -curr_offs, rw = true))
                    pushedOnStackCounter++;
                }
                for (nrArg in 1 until args.size) {
                    val arg = args.get(nrArg)
                    visitExpression(arg, reg)
                    total_offs += curr_offs
                    instructions.add(Store(reg, Register.SP, -curr_offs, rw = true))
                    pushedOnStackCounter++;
                }
                pushedOnStackCounter -= (args.size)
                instructions.add(Branch(name, link = true))
                instructions.add(Add(Register.SP, Register.SP, total_offs))
                instructions.add(Move(reg, Register.R0))
            }
            is ASTClass -> {
                val cls = rhs
                val heapSpaceNeeded: Int = cls.getFields().size * 4
                instructions.add(Load(Register.R0, heapSpaceNeeded))
                instructions.add(Branch("malloc", true))
                instructions.add(Move(reg, Register.R0))
                var offset = 0
                var nextReg: Register
                for (field in cls.getFields().values) {
                    val id = field.getIdent()
                    nextReg = nextAvailableRegister(reg)
                    visitExpression(id.getValue(), nextReg)
                    nextReg = popFromStackIfNoReg(reg)
                    instructions.add(Store(nextReg, reg, offset))
                    offset += 4
                }
            }
        }

    }

    private fun getFunctionLabel(function: FunctionSignature): String? {
        for ((key, value) in functionLabel) {
            if (key == function) {
                return value
            }
        }
        return null
    }

    override fun visitPrintStat(statement: ASTStatement, reg: Register) {
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Move(Register.R0, reg))
        evaluateExpressionPrintType(statement.getExpr()!!, reg)
    }

    override fun visitPrintLnStat(statement: ASTStatement, reg: Register) {
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Move(Register.R0, reg))
        evaluateExpressionPrintType(statement.getExpr()!!, reg)
        instructions.add(Branch("p_print_ln", true))
        cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_LN)
    }

    private fun evaluateExpressionPrintType(expr: ASTNode, reg: Register) {
        when (expr.getType()) {
            TYPES.CHAR -> {
                instructions.add(Branch("putchar", true))
            }
            TYPES.STRING -> {
                instructions.add(Branch("p_print_string", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_STRING)
            }
            TYPES.INT -> {
                instructions.add(Branch("p_print_int", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_INT)
            }
            TYPES.BOOL -> {
                instructions.add(Branch("p_print_bool", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_BOOL)
            }
            TYPES.PAIR,
            TYPES.CLASS -> {
                // prints the reference of the pair/object
                instructions.add(Branch("p_print_reference", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_REFERENCE)
            }
            TYPES.ARRAY -> {
                // prints the array as a string when we have a char array
                if ((expr.getBaseType() as ASTArrayType).getElemType().getType() == TYPES.CHAR) {
                    val condLabel: String = newLabel()
                    val restLabel: String = newLabel()
                    instructions.add(Load(reg, Register.R0))
                    instructions.add(FunctionName(condLabel))
                    instructions.add(Compare(reg, 0))
                    instructions.add(Branch(restLabel, false, Condition.EQ))
                    pushedOnStackCounter++;
                    instructions.add(Add(Register.R0, Register.R0, 4))
                    instructions.add(Push(listOf(Register.R0)))
                    instructions.add(Load(Register.R0, Register.R0))
                    instructions.add(Branch("putchar", true))
                    instructions.add(Pop(listOf(Register.R0)))
                    pushedOnStackCounter--;
                    instructions.add(Subtract(reg, reg, 1))
                    instructions.add(Branch(condLabel))
                    instructions.add(FunctionName(restLabel))
                    return
                }

                //prints the reference of the array in all other cases
                instructions.add(Branch("p_print_reference", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_REFERENCE)
            }
            else -> {
            }
        }
    }

    override fun visitReadStat(statement: ASTStatement, reg: Register) {
        visitAssignLhs(statement.getLhs()!!, reg)
        instructions.add(Move(Register.R0, reg))

        when ((statement.getLhs() as ASTNode).getType()) {
            TYPES.INT -> {
                instructions.add(Branch("p_read_int", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.READ_INT)
            }
            TYPES.CHAR -> {
                instructions.add(Branch("p_read_char", true))
                cFunctionsCodeGenerator.generateCode(CFunctions.READ_CHAR)
            }
            else -> {
            }
        }
    }

    override fun visitFreeStat(statement: ASTStatement, reg: Register) {
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Move(Register.R0, reg))
        when ((statement.getExpr() as ASTNode).getType()) {
            TYPES.PAIR -> {
                instructions.add(Branch("p_free_pair", true))
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.FREE_PAIR_NULL_REFERENCE_ERROR)
            }
            TYPES.ARRAY -> {
                instructions.add(Branch("p_free_array", true))
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.FREE_ARRAY_NULL_REFERENCE_ERROR)
            }
            else -> {
            }
        }
    }

    override fun visitBeginStat(statement: ASTStatement, reg: Register) {
        val CURR_ST: SymbolTable = statement.getST()!!
        enterScope(CURR_ST, 0)
        visitStatement(statement.getLhs() as ASTStatement, reg)
        exitScope(CURR_ST, 0)
    }

    override fun visitIfStat(statement: ASTStatement, reg: Register) {
        // Optimization: assuming that the expression can be evaluated
        // to true/false, we skipt the else/then body respectively and 
        // remove the labels. Otherwise we generate the same code as before

        // At first we only do this if the expression is just an ASTBool
        // (i.e. we know it's value)
        if (optimisationFlagControlFlow && statement.getExpr() is ASTBool) {
            val body =
                if ((statement.getExpr() as ASTBool).getValue()) (statement.getLhs() as ASTStatement) else (statement.getRhs() as ASTStatement)
            var CURR_ST: SymbolTable = body.getST()!!
            enterScope(CURR_ST, 0)
            visitStatement(body, reg)
            exitScope(CURR_ST, 0)
            return
        }
        val thenLabel: String = newLabel()
        val elseLabel: String = newLabel()
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Compare(reg, 0))
        instructions.add(Branch(elseLabel, cond = Condition.EQ))

        // adds the instructions for the then body
        val thenBody = statement.getLhs() as ASTStatement
        var CURR_ST: SymbolTable = thenBody.getST()!!
        enterScope(CURR_ST, 0)
        visitStatement(thenBody, reg)
        exitScope(CURR_ST, 0)

        instructions.add(Branch(thenLabel))

        instructions.add(FunctionName(elseLabel))

        // adds the instructions for the else body
        val elseBody = statement.getRhs() as ASTStatement
        CURR_ST = elseBody.getST()!!
        enterScope(CURR_ST, 0)
        visitStatement(statement.getRhs() as ASTStatement, reg)
        exitScope(CURR_ST, 0)

        instructions.add(FunctionName(thenLabel))

    }

    override fun visitWhileStat(statement: ASTStatement, reg: Register) {
        // Optimization: if the condition is false, we can skip writing the body
        if (optimisationFlagControlFlow && statement.getExpr() is ASTBool &&
            !(statement.getExpr() as ASTBool).getValue()
        ) {
            return
        }

        // Create a label for the case of an existing break statement
        val breakLabel: String = newLabel()
        breakLabelStack.push(breakLabel)

        val condLabel: String = newLabel()
        val bodyLabel: String = newLabel()

        // In the case of an existing continue statement, loop has to jump straight to the condition
        continueLabelStack.push(condLabel)
        instructions.add(Branch(condLabel))

        // Visit the body of the while loop with its own separate scope
        instructions.add(FunctionName(bodyLabel))
        val whileBody = statement.getLhs() as ASTStatement
        val CURR_ST = whileBody.getST()!!
        enterScope(CURR_ST, 0)
        // Remember previous memory access offset in case of sudden breaks
        val previousOffsetFromLoop = pushOnStackFromLoop
        pushOnStackFromLoop = 0

        visitStatement(whileBody, reg)

        pushOnStackFromLoop = previousOffsetFromLoop

        // Exit the scope of the loop and visit condition body
        exitScope(CURR_ST, 0)
        instructions.add(FunctionName(condLabel))
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Compare(reg, 1))
        instructions.add(Branch(bodyLabel, cond = Condition.EQ))

        // Add break label and pop continue and break labels from the stacks
        instructions.add(FunctionName(breakLabel))
        if (!breakLabelStack.isEmpty()) {
            breakLabelStack.pop()
        }
        if (!continueLabelStack.isEmpty()) {
            continueLabelStack.pop()
        }
    }


    override fun visitDoWhileStat(statement: ASTStatement, reg: Register) {
        // Create label for the case of an existing break statement
        val breakLabel: String = newLabel()
        breakLabelStack.push(breakLabel)

        val condLabel: String = newLabel()
        val bodyLabel: String = newLabel()

        // In case of continue loop has to jump from the body to the condition
        continueLabelStack.push(condLabel)

        // Get the body of the loop from the corresponding ASTStatement
        instructions.add(FunctionName(bodyLabel))
        val body = statement.getLhs() as ASTStatement

        // Separate the scope of the loop and preserve previous memory offset
        val CURR_ST = body.getST()!!
        enterScope(CURR_ST, 0)
        val previousOffsetFromLoop = pushOnStackFromLoop
        pushOnStackFromLoop = 0

        // Visit body of the loop
        visitStatement(body, reg)

        // Retrieve the parent scope and the previous memory access offset
        pushOnStackFromLoop = previousOffsetFromLoop
        exitScope(CURR_ST, 0)

        // Visiting the condition
        instructions.add(FunctionName(condLabel))

        if (!breakLabelStack.isEmpty()) {
            breakLabelStack.pop()
        }
        if (!continueLabelStack.isEmpty()) {
            continueLabelStack.pop()
        }
        // Optimization: if the condition is false, we can skip writing
        // the condition and we only write the instructions for the body
        if (optimisationFlagControlFlow && statement.getExpr() is ASTBool &&
            !(statement.getExpr() as ASTBool).getValue()
        ) {
            // Add the break label to the end of the loop
            instructions.add(FunctionName(breakLabel))
            return
        }
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Compare(reg, 1))
        instructions.add(Branch(bodyLabel, cond = Condition.EQ))

        // Add the break label to the end of the loop
        instructions.add(FunctionName(breakLabel))
    }

    override fun visitForStat(statement: ASTStatement, reg: Register) {
        // Maintain the scope of the loop
        val CURR_ST = statement.getST()!!
        enterScope(CURR_ST, 0)

        // Add iterator at the beginning of the loop
        visitStatement(statement.getLhs()!! as ASTStatement, reg)

        // Optimization: if the condition is false, we can skip writing
        // the body and the increment code. However, in this case we still
        // want to initialize the loop variant because the condition might
        // depend on it (ex: for(int i = 5; i < 0; i = i + 1))
        if (optimisationFlagControlFlow && statement.getExpr() is ASTBool
            && !(statement.getExpr() as ASTBool).getValue()
        ) {
            exitScope(CURR_ST, 0)
            return
        }
        // Create label for the case of an existing break statement to jump to the end of the loop
        // Break label is situated at the end of the loop
        val breakLabel: String = newLabel()
        breakLabelStack.push(breakLabel)

        val bodyLabel: String = newLabel()
        val condLabel: String = newLabel()
        val incrementLabel: String = newLabel()

        // In case of continue, loop has to jump to increment label and then to the condition label
        continueLabelStack.push(incrementLabel)

        // Remember the memory offset of the variables stored before the start of the loop
        // -- Preserves safe memory access in case of breaks
        val previousOffsetFromLoop = pushOnStackFromLoop
        pushOnStackFromLoop = 0

        // Jump to condition before the start of the loop
        instructions.add(Branch(condLabel))
        instructions.add(FunctionName(bodyLabel))

        var body = statement.getRhs() as ASTStatement
        val increment = body.getRhs() as ASTStatement
        body = body.getLhs() as ASTStatement

        // Add body instructions
        visitStatement(body, reg)

        // Add incrementation body
        instructions.add(FunctionName(incrementLabel))
        visitStatement(increment, reg)

        // Add condition body
        instructions.add(FunctionName(condLabel))
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Compare(reg, 1))
        instructions.add(Branch(bodyLabel, cond = Condition.EQ))

        // Pop continue and break labels from the stack
        instructions.add(FunctionName(breakLabel))
        if (!breakLabelStack.isEmpty()) {
            breakLabelStack.pop()
        }
        if (!continueLabelStack.isEmpty()) {
            continueLabelStack.pop()
        }

        // Retrieve the memory offset from the beginning of the loop
        pushOnStackFromLoop = previousOffsetFromLoop
        exitScope(CURR_ST, 0)
    }


    override fun visitReturnStat(statement: ASTStatement, reg: Register) {

        visitExpression(statement.getExpr()!!, reg)

        instructions.add(Move(Register.R0, reg))

        instructions.add(
            Add(
                Register.SP,
                Register.SP,
                pushOnStackFromFunc + (pushedOnStackCounter * 4)
            )
        )

        instructions.add(Pop(listOf(Register.PC)))
    }


    override fun visitExitStat(statement: ASTStatement, reg: Register) {
        visitExpression(statement.getExpr()!!, reg)
        instructions.add(Move(Register.R0, reg))
        instructions.add(Branch("exit", true))
    }

    override fun visitExpression(expr: ASTNode, reg: Register) {
        // Translates the expression into the right set of instructions
        when (expr) {
            is ASTInt -> {
                instructions.add(Load(reg, expr.getValue().toInt()))
            }
            is ASTBool -> {
                if (expr.getValue()) {
                    instructions.add(Move(reg, 1))
                } else {
                    instructions.add(Move(reg, 0))
                }
            }
            is ASTChar -> {
                instructions.add(Move(reg, expr.getValue()))
            }
            is ASTString -> {
                instructions.add(Load("msg_${dataSegment.size}", reg))
                addNewDataSegment(expr.getValue())
            }
            is ASTBinOp -> {
                visitBinOp(expr, reg)
            }
            is ASTUnOp -> {
                visitUnOp(expr, reg)
            }
            is ASTIdentifier -> {
                val offset = expr.getOffsetFromSp(pushedOnStackCounter, CURR_ST!!)
                instructions.add(Load(reg, Register.SP, offset))
            }
            is ASTArrayElem -> {
                visitArrayElem(expr, reg)
                instructions.add(Load(reg, reg))
            }
            is ASTMethodField -> {
                instructions.add(
                    Load(
                        reg, Register.SP,
                        pushOnStackFromFunc + (pushedOnStackCounter * 4) + paramsOffset
                    )
                )
                instructions.add(Load(reg, reg, expr.getOffset()))
            }
            is ASTFieldAccess -> {
                val fieldAccess = expr
                val offset = fieldAccess.getIdent().getOffsetFromSp(pushedOnStackCounter, CURR_ST!!)
                instructions.add(Load(reg, Register.SP, offset))
                val fieldOffset: Int =
                    (fieldAccess.getClass().getFields().keys.indexOf(fieldAccess.getField())) * 4
                instructions.add(Load(reg, reg, fieldOffset))
            }
            else -> {
                instructions.add(Move(reg, 0))
            }
        }
    }

    override fun visitPairElem(pairElem: ASTPairElem, reg: Register) {

        visitExpression(pairElem.getPair(), reg)
        // instructions.add(Add(reg, reg))
        instructions.add(Move(Register.R0, reg))
        instructions.add(Branch("p_check_null_pointer", true))
        runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.NULL_REFERENCE_PAIR)
        var offset = 0
        if (!pairElem.getFirst()) {
            offset = 4
        }
        instructions.add(Load(reg, reg, offset))
    }

    override fun visitBinOp(binOp: ASTBinOp, reg: Register) {
        var reg1: Register = reg
        val lhs: ASTNode = binOp.getLhs()
        visitExpression(lhs, reg1)

        var reg2: Register = nextAvailableRegister(reg1)
        val rhs: ASTNode = binOp.getRhs()
        visitExpression(rhs, reg2)

        var destReg = reg1
        var op1 = reg1
        var op2 = reg2

        if (reg1 == reg2) {
            instructions.add(Pop(listOf(Register.R11)))
            reg1 = Register.R11
            pushedOnStackCounter--
            destReg = reg2
            op1 = reg1
            op2 = reg2
        }

        when (binOp.getOP()) {
            WACCParser.PLUS -> {
                instructions.addAll(
                    listOf(
                        Add(destReg, op1, op2, updateFlags = true),
                        // in case of overflow error
                        Branch("p_throw_overflow_error", true, Condition.VS)
                    )
                )
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.OVERFLOW_ERROR)
            }
            WACCParser.MINUS -> {
                instructions.addAll(
                    listOf(
                        Subtract(destReg, op1, op2, updateFlags = true),
                        Branch("p_throw_overflow_error", true, Condition.VS)
                    )
                )
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.OVERFLOW_ERROR)
            }
            WACCParser.MULT -> {
                // and do a shift right instead of multiply
                instructions.addAll(
                    listOf(
                        Multiply(reg1, reg2, reg1, reg2),
                        Compare(reg2, reg1, true, 31),
                        Branch("p_throw_overflow_error", true, Condition.NE)
                    )
                )
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.OVERFLOW_ERROR)
            }
            WACCParser.DIV -> {
                instructions.addAll(
                    listOf(
                        // MOV r0, r4
                        // MOV r1, r5
                        Move(Register.R0, reg1),
                        Move(Register.R1, reg2),
                        // branch to check if divided by zero and _aeabi
                        Branch("p_check_divide_by_zero", true),
                        Branch("__aeabi_idiv", true),
                        // Move result to reg1
                        Move(reg1, Register.R0)
                    )
                )
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.CHECK_DIVIDE_BY_ZERO)
            }
            WACCParser.MOD -> {
                instructions.addAll(
                    listOf(
                        // MOV r0, r4
                        // MOV r1, r5
                        Move(Register.R0, reg1),
                        Move(Register.R1, reg2),
                        // branch to check if divided by zero and _aeabi
                        Branch("p_check_divide_by_zero", true),
                        Branch("__aeabi_idivmod", true),
                        // Move result to reg1
                        Move(reg1, Register.R1)
                    )
                )
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.CHECK_DIVIDE_BY_ZERO)
            }
            WACCParser.AND, WACCParser.BITWISE_AND -> {
                instructions.add(And(reg1, reg2))
            }
            WACCParser.OR, WACCParser.BITWISE_OR -> {
                instructions.add(Or(reg1, reg2))
            }
            WACCParser.BITWISE_XOR -> {
                instructions.add(Xor(reg1, reg2))
            }
            else -> {
                instructions.add(Compare(reg1, reg2))
                when (binOp.getOP()) {
                    WACCParser.EQUAL -> {
                        instructions.addAll(
                            listOf(
                                Move(reg1, 1, Condition.EQ),
                                Move(reg1, 0, Condition.NE)
                            )
                        )
                    }
                    WACCParser.NOT_EQUAL -> {
                        instructions.addAll(
                            listOf(
                                Move(reg1, 1, Condition.NE),
                                Move(reg1, 0, Condition.EQ)
                            )
                        )
                    }
                    WACCParser.LT -> {
                        instructions.addAll(
                            listOf(
                                Move(reg1, 1, Condition.LT),
                                Move(reg1, 0, Condition.GE)
                            )
                        )
                    }
                    WACCParser.LTE -> {
                        instructions.addAll(
                            listOf(
                                Move(reg1, 1, Condition.LE),
                                Move(reg1, 0, Condition.GT)
                            )
                        )
                    }
                    WACCParser.GT -> {
                        instructions.addAll(
                            listOf(
                                Move(reg1, 1, Condition.GT),
                                Move(reg1, 0, Condition.LE)
                            )
                        )
                    }
                    WACCParser.GTE -> {
                        instructions.addAll(
                            listOf(
                                Move(reg1, 1, Condition.GE),
                                Move(reg1, 0, Condition.LT)
                            )
                        )
                    }
                }
            }
        }
    }

    override fun visitUnOp(unOp: ASTUnOp, reg: Register) {
        visitExpression(unOp.getChild(), reg)
        when (unOp.getOp()) {
            WACCParser.NOT -> instructions.add(RSUB(reg, reg, 1))
            WACCParser.MINUS -> {
                instructions.add(RSUB(reg, reg, 0, true))
                instructions.add(Branch("p_throw_overflow_error", true, Condition.VS))
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.OVERFLOW_ERROR)
            }
            WACCParser.LEN -> instructions.add(Load(reg, reg))
            WACCParser.BITWISE_NOT -> {
                instructions.add(Add(reg, reg, 1))
                instructions.add(RSUB(reg, reg, 0, true))
                instructions.add(Branch("p_throw_overflow_error", true, Condition.VS))
                runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.OVERFLOW_ERROR)
            }
            WACCParser.CHR, WACCParser.ORD -> {
            }
        }
    }

    fun visitFieldAccess(fieldAccess: ASTFieldAccess, reg: Register) {
        val offset = fieldAccess.getIdent().getOffsetFromSp(pushedOnStackCounter, CURR_ST!!)
        instructions.add(Load(reg, Register.SP, offset))
        val fieldOffset: Int =
            (fieldAccess.getClass().getFields().keys.indexOf(fieldAccess.getField())) * 4
        instructions.add(Add(reg, reg, fieldOffset))
    }

    override fun visitArrayLiter(arrayLiter: ASTArrayLiter, reg: Register) {
        val elems = arrayLiter.getElems()
        instructions.add(Load(Register.R0, (elems.size + 1) * 4))
        instructions.add(Branch("malloc", true))
        instructions.add(Move(reg, Register.R0)) // the address of the array

        var offset = 0
        var nextReg: Register = Register.R11
        for (elem in elems) {
            offset += 4
            nextReg = nextAvailableRegister(reg)
            visitExpression(elem, nextReg)
            nextReg = popFromStackIfNoReg(reg)
            instructions.add(Store(nextReg, reg, offset))
        }
        instructions.add(Load(nextReg, elems.size))
        instructions.add(Store(nextReg, reg))
    }

    override fun visitNewPair(pair: ASTPair, reg: Register) {

        var nextReg = nextAvailableRegister(reg)

        //8 is pair size
        instructions.add(Load(Register.R0, 8))
        instructions.add(Branch("malloc", true)) // malloc newpair
        instructions.add(Move(reg, Register.R0)) // address of the pair

        visitExpression(pair.getFstType(), nextReg)
        instructions.add(Load(Register.R0, 4))
        instructions.add(Branch("malloc", true)) // malloc fstelem

        instructions.add(Store(nextReg, Register.R0)) // stores the value of first elem
        nextReg = popFromStackIfNoReg(reg)
        instructions.add(Store(Register.R0, reg)) // stores the address of first elem in pair

        visitExpression(pair.getSndType(), nextReg)
        instructions.add(Load(Register.R0, 4))
        instructions.add(Branch("malloc", true)) // malloc second element
        instructions.add(Store(nextReg, Register.R0)) // stores the value of second elem
        instructions.add(Store(Register.R0, reg, 4)) // stores the address of second elem in pair
    }

    override fun visitArrayElem(arrayElem: ASTArrayElem, reg: Register) {
        // to do: array out of bounds
        // the address will be in reg in the end
        val variable = arrayElem.getId()
        var nextReg: Register
        instructions.add(
            Add(
                reg,
                Register.SP,
                variable.getOffsetFromSp(pushedOnStackCounter, CURR_ST!!)
            )
        )
        for (elem in arrayElem.getElems()) {
            nextReg = nextAvailableRegister(reg)
            visitExpression(elem, nextReg)
            nextReg = popFromStackIfNoReg(reg)
            instructions.add(Load(reg, reg))
            instructions.add(Move(Register.R0, nextReg))
            instructions.add(Move(Register.R1, reg))
            instructions.add(Branch("p_check_array_bounds", true))
            runtimeErrorsCodeGenerator.generateCode(RuntimeErrors.CHECK_ARRAY_BOUNDS)
            instructions.add(Add(reg, reg, 4))
            // R0 * 4
            instructions.add(Add(reg, reg, nextReg, 2, true))
        }
    }

    // DATA SEGMENT FUNCTIONS
    fun addNewDataSegment(msg: String) {
        dataSegment.add(msg)
    }

    fun getDataSegmentSize(): Int {
        return dataSegment.size
    }

    private fun getDataSegmentInstructions(): MutableList<Instruction> {
        val dataSegmentInstructions = mutableListOf<Instruction>()
        if (dataSegment.isNotEmpty()) {
            dataSegmentInstructions.add(Label("data"))
            for ((index, msg) in dataSegment.withIndex()) {
                dataSegmentInstructions.add(FunctionName("msg_$index"))
                dataSegmentInstructions.add(
                    Label("word ${msg.length - msg.count { it == '\\' }}", true)
                )
                dataSegmentInstructions.add(Label("ascii \"$msg\"", true))
            }
        }
        return dataSegmentInstructions
    }

    /* UTILITIES */
    private fun newLabel(): String {
        val newLabel: String = "L$labelCounter"
        labelCounter++
        return newLabel
    }

    private fun nextAvailableRegister(reg: Register): Register {
        if (reg == lastAvailableRegister) {
            instructions.add(Push(listOf(lastAvailableRegister)))
            pushedOnStackCounter++
            return lastAvailableRegister
        }
        return reg.next()
    }

    private fun popFromStackIfNoReg(reg: Register): Register {
        if (reg != lastAvailableRegister) {
            return nextAvailableRegister(reg)
        }
        instructions.add(Move(Register.R11, Register.R10))
        instructions.add(Pop(listOf(Register.R10)))
        return Register.R11
    }

    // Generating all the instructions
    fun getInstructions(): List<Instruction> {
        val runtimeErrorsInstructions: MutableList<Instruction> =
            runtimeErrorsCodeGenerator.getRuntimeErrorsInstructions()
        val CFunctionsInstructions: MutableList<Instruction> =
            cFunctionsCodeGenerator.getCFunctionsInstructions()
        if (optimisationFlagInstructionEvaluation) peepholeOptimizations()
        return getDataSegmentInstructions() + instructions + runtimeErrorsInstructions + CFunctionsInstructions
    }

    private fun peepholeOptimizations() {
        // General idea: removes all the unnecessary instructions,
        // detailed below for each case
        // Two passes are necessary for overlaps between
        // pattern matching cases(add/store/load)(sub/add/store)

        // First pass
        val firstPass = mutableListOf<Instruction>()
        var i = 0
        while (i < instructions.size) {
            // ADD R5, SP, #offset
            // STR R4, [R5]
            // becomes
            // STR R4, [SP, #offset]
            if (instructions.get(i) is Add && instructions.get(i + 1) is Store) {
                val add = instructions.get(i) as Add
                val store = instructions.get(i + 1) as Store
                if (add.getDest() == store.getToReg() && store.getOffset() == 0 && add.getImm() != null && add.getSrc2() == null) {
                    firstPass.add(Store(store.getFromReg(), add.getSrc1(), add.getImm()!!))
                    i += 2
                    continue
                }
            }
            // MOV R4, R5
            // MOV R5, R4
            // becomes
            // MOV R4, R5
            if (instructions.get(i) is Move && instructions.get(i + 1) is Move) {
                val move1 = instructions.get(i) as Move
                val move2 = instructions.get(i + 1) as Move
                if (move1.getSrc() != null && move2.getSrc() != null && move1.getDest() == move2.getSrc() && move2.getDest() == move1.getSrc()) {
                    firstPass.add(move1)
                    i += 2
                    continue
                }
            }
            firstPass.add(instructions.get(i))
            i++
        }

        // Second pass, removes the unnecessary load/store to the same register
        val secondPass = mutableListOf<Instruction>()
        i = 0
        while (i < firstPass.size) {
            // SUB SP, SP, #12
            // ADD SP, SP, #20
            // becomes
            // ADD SP, SP, #8
            // or is removed if the offsets are equal
            if (firstPass.get(i) is Subtract && firstPass.get(i + 1) is Add) {
                val sub = firstPass.get(i) as Subtract
                val add = firstPass.get(i + 1) as Add
                if (sub.getDest() == add.getDest() && sub.getSrc1() == add.getSrc1()) {
                    if (sub.getImm() != null && add.getImm() != null && sub.getImm()!! != add.getImm()!!) {
                        secondPass.add(
                            Add(
                                add.getDest(),
                                add.getSrc1(),
                                add.getImm()!! - sub.getImm()!!
                            )
                        )
                    }
                    i += 2
                    continue
                }
            }
            // same is true for the reverse (add/sub)
            if (firstPass.get(i) is Add && firstPass.get(i + 1) is Subtract) {
                val add = firstPass.get(i) as Add
                val sub = firstPass.get(i + 1) as Subtract
                if (sub.getDest() == add.getDest() && sub.getSrc1() == add.getSrc1()) {
                    if (sub.getImm() != null && add.getImm() != null && sub.getImm()!! != add.getImm()!!) {
                        secondPass.add(
                            Add(
                                add.getDest(),
                                add.getSrc1(),
                                add.getImm()!! - sub.getImm()!!
                            )
                        )
                    }
                    i += 2
                    continue
                }
            }
            if (firstPass.get(i) is Load && firstPass.get(i + 1) is Store) {
                val load = firstPass.get(i) as Load
                val store = firstPass.get(i + 1) as Store
                if (load.getDest() == store.getFromReg() &&
                    load.getSrc() != null && load.getSrc() == store.getToReg() &&
                    load.getImm() == store.getOffset()
                ) {
                    secondPass.add(load)
                    i += 2
                    continue
                }
            }
            if (firstPass.get(i) is Store && firstPass.get(i + 1) is Load) {
                val store = firstPass.get(i) as Store
                val load = firstPass.get(i + 1) as Load
                if (load.getDest() == store.getFromReg() &&
                    load.getSrc() != null && load.getSrc() == store.getToReg() &&
                    load.getImm() == store.getOffset()
                ) {
                    secondPass.add(store)
                    i += 2
                    continue
                }
            }
            secondPass.add(firstPass.get(i))
            i++
        }
        instructions = secondPass
    }
}