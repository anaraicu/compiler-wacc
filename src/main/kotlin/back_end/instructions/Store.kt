package back_end.instructions

class Store(
    private val fromReg: Register,
    private val toReg: Register,
    private val offset: Int = 0,
    private val byte: Boolean = false,
    private val rw: Boolean = false,
    private val cond: Condition? = null
) : Instruction {

    override fun toString(): String {
        val sb = StringBuilder("\tSTR")

        when {
            cond != null -> sb.append(cond)
            byte -> sb.append("B")
        }

        sb.append(" $fromReg, [$toReg")
        if (offset != 0) {
            sb.append(", #$offset")
        }
        sb.append("]")
        if (rw) {
            sb.append("!")
        }
        return sb.toString()
    }

    fun getFromReg(): Register {
        return fromReg
    }

    fun getToReg(): Register {
        return toReg
    }

    fun getOffset(): Int {
        return offset
    }
}