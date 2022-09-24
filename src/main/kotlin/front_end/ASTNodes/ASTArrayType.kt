package front_end.ASTNodes

class ASTArrayType(
    private val elemType: ASTType,
) : ASTType(TYPES.ARRAY) {

    fun getElemType(): ASTType {
        return elemType
    }

    override fun equals(other: Any?): Boolean {
        return (this === other) || (other is ASTAnyType) ||
            ((other is ASTArrayType) && (other.getElemType() == elemType))
    }
}