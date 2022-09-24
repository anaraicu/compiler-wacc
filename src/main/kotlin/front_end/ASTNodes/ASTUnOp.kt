package front_end.ASTNodes

class ASTUnOp(private val op: Int, private val valType: TYPES, private val child: ASTNode) :
    ASTNode {

    override fun getType(): TYPES {
        return valType
    }

    override fun getBaseType(): ASTNode {
        return ASTType(valType)
    }

    override fun accept(node: ASTNode) {}

    fun getOp(): Int {
        return op
    }

    fun getChild(): ASTNode {
        return child
    }

}