package front_end.ASTNodes

import wacc.SymbolTable

class ASTIdentifier(
    private val name: String,
    private val value: ASTNode,
    private val offset: Int,
    private val ST: SymbolTable?
) : ASTNode {

    override fun getBaseType(): ASTNode {
        return value.getBaseType()
    }

    fun getST(): SymbolTable? {
        return ST
    }

    fun getOffsetFromSp(pushedOnStackCounter: Int, CURR_ST: SymbolTable): Int {
        var CR_ST = CURR_ST
        var off = CR_ST.size() - offset + (pushedOnStackCounter * 4)
        while (CR_ST != ST) {
            CR_ST = CR_ST.getEnclosingTable()!!
            off += CR_ST.size()
        }
        return off
    }

    override fun accept(node: ASTNode) {}

    override fun getType(): TYPES {
        return value.getBaseType().getType()
    }

    fun getName(): String {
        return name
    }

    fun getOffset(): Int {
        return offset
    }

    fun getValue(): ASTNode {
        return value
    }

}