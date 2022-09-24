package back_end.instructions

class Subtract : Instruction {

    private val dest: Register
    private val src1: Register
    private val src2: Register?
    private val imm: Int?
    private val MAX_SUB_IMM_VALUE: Int = 1024
    private var currImm: Int = 0
    private val updateFlags: Boolean?

    constructor(dest: Register, src: Register, imm: Int) {
        this.dest = dest
        this.src1 = src
        this.src2 = null
        this.imm = imm
        this.updateFlags = null
    }

    constructor(dest: Register, src1: Register, src2: Register, updateFlags: Boolean? = false) {
        this.dest = dest
        this.src1 = src1
        this.src2 = src2
        this.imm = null
        this.updateFlags = updateFlags
    }

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tSUB")
        if (updateFlags != null && updateFlags) sb.append("S")
        if (imm != null) {
            currImm = imm
            while (currImm > MAX_SUB_IMM_VALUE) {
                sb.append(" $dest, $src1")
                when {
                    src2 != null -> sb.append(", $src2")
                    else -> {
                        sb.append(", #${MAX_SUB_IMM_VALUE}")
                        currImm -= MAX_SUB_IMM_VALUE
                    }
                }
                sb.append("\n\tSUB")
                if (updateFlags != null && updateFlags) sb.append("S")
            }
            while (currImm < -MAX_SUB_IMM_VALUE) {
                sb.append(" $dest, $src1")
                when {
                    src2 != null -> sb.append(", $src2")
                    else -> {
                        sb.append(", #${-MAX_SUB_IMM_VALUE}")
                        currImm += MAX_SUB_IMM_VALUE
                    }
                }
                sb.append("\n\tSUB")
                if (updateFlags != null && updateFlags) sb.append("S")
            }
        }

        sb.append(" $dest, $src1")

        when {
            src2 != null -> sb.append(", $src2")
            imm != null -> sb.append(", #$currImm")
        }

        return sb.toString()
    }

    fun getDest(): Register {
        return dest
    }

    fun getSrc1(): Register {
        return src1
    }
    
    fun getSrc2(): Register? {
        return src2
    }

    fun getImm(): Int? {
        return imm
    }
}