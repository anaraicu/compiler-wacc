package back_end.instructions

class Multiply(
    private val dest: Register,
    private val src: Register,
    private val reg1: Register,
    private val reg2: Register
) : Instruction {

    override fun toString(): String {
        return "\tSMULL $dest, $src, $reg1, $reg2"
    }

}