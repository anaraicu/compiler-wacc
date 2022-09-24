package front_end.ASTNodes

interface ASTNode {

    fun getType(): TYPES

    fun getBaseType(): ASTNode

    fun accept(node: ASTNode)
}