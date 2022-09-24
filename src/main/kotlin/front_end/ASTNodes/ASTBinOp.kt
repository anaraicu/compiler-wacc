package front_end.ASTNodes

class ASTBinOp(
    private val op: Int,
    private val valType: TYPES,
    private val lhs: ASTNode,
    private val rhs: ASTNode
) : ASTNode {
    override fun getType(): TYPES {
        return valType
    }

    override fun getBaseType(): ASTNode {
        return ASTType(valType)
    }

    override fun accept(node: ASTNode) {
    }

    fun getOP(): Int {
        return op
    }

    fun getLhs(): ASTNode {
        return lhs
    }

    fun getRhs(): ASTNode {
        return rhs
    }

}