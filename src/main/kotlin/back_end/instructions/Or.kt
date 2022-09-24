package back_end.instructions

class Or(
    private val dest: Register,
    private val src: Register,
    private val operand: Register? = null
) : Instruction {


    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tORR $dest, $src")

        if (operand != null) {
            sb.append(", $operand")
        }

        return sb.toString()
    }
}