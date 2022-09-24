package back_end.instructions

class RSUB : Instruction {

    private val dest: Register
    private val src1: Register
    private val src2: Register?
    private val imm: Int?
    private val updateFlags: Boolean?

    constructor(dest: Register, src: Register, imm: Int, updateFlags: Boolean? = false) {
        this.dest = dest
        this.src1 = src
        this.src2 = null
        this.imm = imm
        this.updateFlags = updateFlags
    }

    constructor(dest: Register, src1: Register, src2: Register, updateFlags: Boolean? = false) {
        this.dest = dest
        this.src1 = src1
        this.src2 = src2
        this.imm = null
        this.updateFlags = updateFlags
    }

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tRSB")
        if (updateFlags != null && updateFlags) sb.append("S")

        sb.append(" $dest, $src1")

        when {
            src2 != null -> sb.append(", $src2")
            imm != null -> sb.append(", #$imm")
        }

        return sb.toString()
    }
}