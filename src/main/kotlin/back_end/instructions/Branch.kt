package back_end.instructions

class Branch(
    private val toLabel: String,
    private val link: Boolean = false,
    private val cond: Condition? = null
) : Instruction {

    override fun toString(): String {
        val sb = StringBuilder("\tB")

        if (link) sb.append("L")

        if (cond != null) sb.append(cond.name)

        sb.append(" $toLabel")
        return sb.toString()
    }

    fun getCond(): Condition? {
        return this.cond
    }

    fun getLabel(): String {
        return this.toLabel
    }
}