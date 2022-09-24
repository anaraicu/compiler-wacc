// Generated from parser/WACCParser.g4 by ANTLR 4.9.3
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WACCParser}.
 */
public interface WACCParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WACCParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(WACCParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(WACCParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#cls}.
	 * @param ctx the parse tree
	 */
	void enterCls(WACCParser.ClsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#cls}.
	 * @param ctx the parse tree
	 */
	void exitCls(WACCParser.ClsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(WACCParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(WACCParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(WACCParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(WACCParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(WACCParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(WACCParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(WACCParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(WACCParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code read}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterRead(WACCParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code read}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitRead(WACCParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code break}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBreak(WACCParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code break}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBreak(WACCParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code for}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFor(WACCParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code for}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFor(WACCParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skip}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSkip(WACCParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSkip(WACCParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile(WACCParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile(WACCParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(WACCParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(WACCParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exit}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExit(WACCParser.ExitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exit}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExit(WACCParser.ExitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code print}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrint(WACCParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code print}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrint(WACCParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code println}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintln(WACCParser.PrintlnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code println}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintln(WACCParser.PrintlnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doWhile}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDoWhile(WACCParser.DoWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doWhile}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDoWhile(WACCParser.DoWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continue}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterContinue(WACCParser.ContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continue}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitContinue(WACCParser.ContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code free}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFree(WACCParser.FreeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code free}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFree(WACCParser.FreeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIf(WACCParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIf(WACCParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code begin}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBegin(WACCParser.BeginContext ctx);
	/**
	 * Exit a parse tree produced by the {@code begin}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBegin(WACCParser.BeginContext ctx);
	/**
	 * Enter a parse tree produced by the {@code return}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturn(WACCParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code return}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturn(WACCParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code semicolon}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSemicolon(WACCParser.SemicolonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code semicolon}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSemicolon(WACCParser.SemicolonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(WACCParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(WACCParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterAssign_lhs(WACCParser.Assign_lhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitAssign_lhs(WACCParser.Assign_lhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssign_rhs(WACCParser.Assign_rhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssign_rhs(WACCParser.Assign_rhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(WACCParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(WACCParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void enterArg_list(WACCParser.Arg_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void exitArg_list(WACCParser.Arg_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#class_field}.
	 * @param ctx the parse tree
	 */
	void enterClass_field(WACCParser.Class_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#class_field}.
	 * @param ctx the parse tree
	 */
	void exitClass_field(WACCParser.Class_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void enterPair_elem(WACCParser.Pair_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void exitPair_elem(WACCParser.Pair_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(WACCParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(WACCParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#base_type}.
	 * @param ctx the parse tree
	 */
	void enterBase_type(WACCParser.Base_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#base_type}.
	 * @param ctx the parse tree
	 */
	void exitBase_type(WACCParser.Base_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#pair_type}.
	 * @param ctx the parse tree
	 */
	void enterPair_type(WACCParser.Pair_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#pair_type}.
	 * @param ctx the parse tree
	 */
	void exitPair_type(WACCParser.Pair_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#pair_element_type}.
	 * @param ctx the parse tree
	 */
	void enterPair_element_type(WACCParser.Pair_element_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#pair_element_type}.
	 * @param ctx the parse tree
	 */
	void exitPair_element_type(WACCParser.Pair_element_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(WACCParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(WACCParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pairLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPairLiter(WACCParser.PairLiterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pairLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPairLiter(WACCParser.PairLiterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayElem}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayElem(WACCParser.ArrayElemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayElem}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayElem(WACCParser.ArrayElemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code openParantheses}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOpenParantheses(WACCParser.OpenParanthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code openParantheses}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOpenParantheses(WACCParser.OpenParanthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharLiter(WACCParser.CharLiterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharLiter(WACCParser.CharLiterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringLiter(WACCParser.StringLiterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringLiter(WACCParser.StringLiterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unOp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnOp(WACCParser.UnOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unOp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnOp(WACCParser.UnOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLiter2}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntLiter2(WACCParser.IntLiter2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code intLiter2}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntLiter2(WACCParser.IntLiter2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code boolLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiter(WACCParser.BoolLiterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolLiter}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiter(WACCParser.BoolLiterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinOp(WACCParser.BinOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinOp(WACCParser.BinOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classField}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterClassField(WACCParser.ClassFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classField}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitClassField(WACCParser.ClassFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(WACCParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(WACCParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#visib_ident}.
	 * @param ctx the parse tree
	 */
	void enterVisib_ident(WACCParser.Visib_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#visib_ident}.
	 * @param ctx the parse tree
	 */
	void exitVisib_ident(WACCParser.Visib_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#unary_oper}.
	 * @param ctx the parse tree
	 */
	void enterUnary_oper(WACCParser.Unary_operContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#unary_oper}.
	 * @param ctx the parse tree
	 */
	void exitUnary_oper(WACCParser.Unary_operContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#array_elem}.
	 * @param ctx the parse tree
	 */
	void enterArray_elem(WACCParser.Array_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#array_elem}.
	 * @param ctx the parse tree
	 */
	void exitArray_elem(WACCParser.Array_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#array_liter}.
	 * @param ctx the parse tree
	 */
	void enterArray_liter(WACCParser.Array_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#array_liter}.
	 * @param ctx the parse tree
	 */
	void exitArray_liter(WACCParser.Array_literContext ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#int_liter2}.
	 * @param ctx the parse tree
	 */
	void enterInt_liter2(WACCParser.Int_liter2Context ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#int_liter2}.
	 * @param ctx the parse tree
	 */
	void exitInt_liter2(WACCParser.Int_liter2Context ctx);
	/**
	 * Enter a parse tree produced by {@link WACCParser#pair_liter}.
	 * @param ctx the parse tree
	 */
	void enterPair_liter(WACCParser.Pair_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link WACCParser#pair_liter}.
	 * @param ctx the parse tree
	 */
	void exitPair_liter(WACCParser.Pair_literContext ctx);
}