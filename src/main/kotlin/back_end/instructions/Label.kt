package back_end.instructions

class Label(
    private val label: String,
    private val requiresTab: Boolean = false
) : Instruction {

    override fun toString(): String {
        val tab: String = if (requiresTab) "\t" else ""
        return tab + "." + label
    }
}