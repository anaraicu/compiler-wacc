package front_end.ASTNodes

class ASTPair(private val fstType: ASTNode, private val sndType: ASTNode) : ASTType(TYPES.PAIR) {

    fun getFstType(): ASTNode {
        return fstType
    }

    fun getSndType(): ASTNode {
        return sndType
    }

    override fun equals(other: Any?): Boolean {
        if (other is ASTAnyType)
            return true
        if (other is ASTPair) {
            return (other.getFstType().getBaseType() == fstType.getBaseType())
                && (other.getSndType().getBaseType() == sndType.getBaseType())
        }
        return false
    }

}