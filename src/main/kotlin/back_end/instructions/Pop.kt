package back_end.instructions

class Pop(
    private val regs: List<Register>,
    private val cond: Condition? = null
) : Instruction {

    override fun toString(): String {
        val sb = StringBuilder("\tPOP")

        if (cond != null) {
            sb.append(cond)
        }

        sb.append(" {${regs.joinToString(separator = ",")}}")
        return sb.toString()
    }

}