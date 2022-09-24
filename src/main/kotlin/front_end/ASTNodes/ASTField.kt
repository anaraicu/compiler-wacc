package front_end.ASTNodes

class ASTField(
    private val visib: Visibility,
    private val ident: ASTIdentifier
) : ASTNode {

    override fun getType(): TYPES {
        return ident.getType()
    }

    override fun getBaseType(): ASTNode {
        return ident.getBaseType()
    }

    override fun accept(node: ASTNode) {}

    fun getIdent(): ASTIdentifier {
        return ident
    }

    fun getVisib(): Visibility {
        return visib
    }
}