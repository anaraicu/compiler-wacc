package back_end.instructions

class And(
    private val dest: Register,
    private val src: Register,
    private val operand: Register? = null
) : Instruction {

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tAND $dest, $src")

        if (operand != null) {
            sb.append(", $operand")
        }

        return sb.toString()
    }
}