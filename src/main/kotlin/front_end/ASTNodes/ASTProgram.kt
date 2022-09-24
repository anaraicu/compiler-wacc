package front_end.ASTNodes

import wacc.SymbolTable

class ASTProgram(
    private val classes: List<ASTClass>,
    private val funcs: List<ASTFunction>,
    private val stat: ASTStatement,
    private val ST: SymbolTable?
) : ASTNode {

    override fun getBaseType(): ASTNode {
        return this
    }

    override fun getType(): TYPES {
        return TYPES.PROG
    }

    override fun accept(node: ASTNode) {}

    fun getClasses(): List<ASTClass> {
        return classes
    }

    fun getfuncs(): List<ASTFunction> {
        return funcs
    }

    fun getStat(): ASTStatement {
        return stat
    }

    fun getST(): SymbolTable? {
        return ST
    }
}