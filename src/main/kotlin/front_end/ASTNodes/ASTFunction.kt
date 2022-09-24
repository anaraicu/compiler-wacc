package front_end.ASTNodes

import wacc.SymbolTable

open class ASTFunction(
    private val returnType: ASTNode,
    private val name: String,
    private val params: List<ASTNode>,
    private val stat: ASTStatement,
    private val ST: SymbolTable?
) : ASTNode {

    override fun getBaseType(): ASTNode {
        return returnType
    }

    override fun getType(): TYPES {
        return TYPES.FUNC
    }

    override fun accept(node: ASTNode) {}

    open fun getReturnType(): ASTNode {
        return returnType
    }

    open fun getST(): SymbolTable? {
        return ST
    }

    open fun getName(): String {
        return name
    }

    open fun getParams(): List<ASTNode> {
        return params
    }

    open fun getStat(): ASTStatement {
        return stat
    }

}