package front_end.ASTNodes

import back_end.instructions.FunctionName
import back_end.instructions.Register


interface ASTVisitor {
    fun visitProgram(program: ASTProgram)
    fun visitFunction(function: ASTFunction, label: FunctionName)
    fun visitStatement(statement: ASTStatement, reg: Register)
    fun visitBinOp(binOp: ASTBinOp, reg: Register)
    fun visitUnOp(unOp: ASTUnOp, reg: Register)
    fun visitArrayElem(arrayElem: ASTArrayElem, reg: Register)
    fun visitRootStat(statement: ASTStatement)
    fun visitSemiColonStat(statement: ASTStatement, reg: Register)
    fun visitAssignStat(statement: ASTStatement, reg: Register)
    fun visitAssignLhs(lhs: ASTNode, reg: Register)
    fun visitAssignRhs(rhs: ASTNode, reg: Register)
    fun visitPrintStat(statement: ASTStatement, reg: Register)
    fun visitPrintLnStat(statement: ASTStatement, reg: Register)
    fun visitReadStat(statement: ASTStatement, reg: Register)
    fun visitFreeStat(statement: ASTStatement, reg: Register)
    fun visitBeginStat(statement: ASTStatement, reg: Register)
    fun visitIfStat(statement: ASTStatement, reg: Register)
    fun visitWhileStat(statement: ASTStatement, reg: Register)
    fun visitDoWhileStat(statement: ASTStatement, reg: Register)
    fun visitForStat(statement: ASTStatement, reg: Register)
    fun visitBreak(statement: ASTStatement, reg: Register)
    fun visitContinue(statement: ASTStatement, reg: Register)
    fun visitReturnStat(statement: ASTStatement, reg: Register)
    fun visitExitStat(statement: ASTStatement, reg: Register)
    fun visitExpression(expr: ASTNode, reg: Register)
    fun visitPairElem(pairElem: ASTPairElem, reg: Register)
    fun visitArrayLiter(arrayLiter: ASTArrayLiter, reg: Register)
    fun visitNewPair(pair: ASTPair, reg: Register)
    fun visitClass(cls: ASTClass)
}