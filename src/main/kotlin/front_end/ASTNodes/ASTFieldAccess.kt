package front_end.ASTNodes

class ASTFieldAccess(
    private val id: ASTIdentifier,
    private val field: String
) : ASTNode {

    override fun getType(): TYPES {
        return (id.getValue() as ASTClass).getFields().get(field)!!.getType()
    }

    override fun getBaseType(): ASTNode {
        return (id.getValue() as ASTClass).getFields().get(field)!!.getBaseType()
    }

    override fun accept(node: ASTNode) {}

    fun getIdent(): ASTIdentifier {
        return id
    }

    fun getClass(): ASTClass {
        return id.getValue() as ASTClass
    }

    fun getField(): String {
        return field
    }
}