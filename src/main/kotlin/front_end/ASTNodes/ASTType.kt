package front_end.ASTNodes

open class ASTType(private val type: TYPES) : ASTNode {

    override fun getType(): TYPES {
        return type
    }

    override fun getBaseType(): ASTType {
        return this
    }

    override fun accept(node: ASTNode) {}

    override fun equals(other: Any?): Boolean {
        return (this === other) || (other is ASTAnyType) ||
            ((other is ASTType) && (other.getType() == type))
    }
}
