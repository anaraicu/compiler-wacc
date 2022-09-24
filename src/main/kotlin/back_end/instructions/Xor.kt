package back_end.instructions

class Xor : Instruction {

    private val dest: Register
    private val src: Register
    private val operand: Register?
    private val imm: Long?

    /* Constructor for Exclusive OR operation between 2 registers */
    constructor(dest: Register, src: Register) {
        this.dest = dest
        this.src = src
        this.operand = null
        this.imm = null
    }

    /* Constructor for Exclusive OR operation between 3 registers */
    constructor(dest: Register, src: Register, operand: Register) {
        this.dest = dest
        this.src = src
        this.operand = operand
        this.imm = null
    }

    /* Constructor for Exclusive OR operation between 2 registers and an immediate operand */
    constructor(dest: Register, src: Register, imm: Long) {
        this.dest = dest
        this.src = src
        this.operand = null
        this.imm = imm
    }

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tEOR $dest, $src")

        if (imm != null) {
            sb.append(", #$imm")
        }
        if (operand != null) {
            sb.append(", $operand")
        }

        return sb.toString()
    }
}