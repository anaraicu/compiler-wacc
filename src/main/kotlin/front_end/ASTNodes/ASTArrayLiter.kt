package front_end.ASTNodes

class ASTArrayLiter(
    private val type: ASTType,
    private val elems: List<ASTNode>,
) : ASTNode, ASTType(TYPES.ARRAY) {

    override fun getBaseType(): ASTType {
        return type
    }

    fun getElems(): List<ASTNode> {
        return elems
    }
}