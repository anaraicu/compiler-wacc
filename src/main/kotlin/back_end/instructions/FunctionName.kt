package back_end.instructions

class FunctionName(
    private val name: String,
    private val declared: Boolean = false
) : Instruction {

    override fun toString(): String {
        return (if (declared) "f_" else "") + name + ":"
    }

    fun getName(): String {
        return name
    }
}