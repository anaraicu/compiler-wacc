package front_end.ASTNodes

class ASTArrayElem(
    private val id: ASTIdentifier,
    private val type: ASTType,
    private val elems: List<ASTNode>,
) : ASTNode {

    override fun getBaseType(): ASTType {
        return type
    }

    override fun getType(): TYPES {
        return type.getType();
    }

    override fun accept(node: ASTNode) {}

    fun getElems(): List<ASTNode> {
        return elems
    }

    fun getId(): ASTIdentifier {
        return id
    }
}