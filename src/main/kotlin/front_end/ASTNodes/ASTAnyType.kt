package front_end.ASTNodes

class ASTAnyType : ASTType(TYPES.ANY) {
    override fun equals(other: Any?): Boolean {
        return (other is ASTType)
    }
}