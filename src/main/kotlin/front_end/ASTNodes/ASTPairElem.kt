package front_end.ASTNodes

class ASTPairElem(private val pair: ASTNode, private val first: Boolean) : ASTNode {

    override fun getType(): TYPES {
        return this.getBaseType().getType()
    }

    override fun getBaseType(): ASTNode {
        if (first) {
            return (pair.getBaseType() as ASTPair).getFstType().getBaseType()
        } else {
            return (pair.getBaseType() as ASTPair).getSndType().getBaseType()
        }
    }


    fun getPair(): ASTNode {
        return pair
    }

    fun getFirst(): Boolean {
        return first
    }

    override fun accept(node: ASTNode) {}

}