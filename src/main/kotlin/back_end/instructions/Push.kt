package back_end.instructions

class Push(
    private val regs: List<Register>,
    private val cond: Condition? = null
) : Instruction {

    override fun toString(): String {
        val sb = StringBuilder("\tPUSH")
        if (cond != null) {
            sb.append(cond)
        }
        sb.append(" {${regs.joinToString(separator = ",")}}")
        return sb.toString()
    }

}