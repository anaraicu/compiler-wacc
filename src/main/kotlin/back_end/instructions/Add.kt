package back_end.instructions

class Add : Instruction {
    private val dest: Register
    private val src1: Register
    private val src2: Register?
    private val imm: Int?
    private val updateFlags: Boolean?
    private val shiftLeft: Boolean?
    private val MAX_ADD_IMM_VALUE: Int = 1024
    private var currImm: Int = 0

    /* Constructor for add instruction over a register and an immediate */
    constructor(dest: Register, src: Register, imm: Int) {
        this.dest = dest
        this.src1 = src
        this.src2 = null
        this.imm = imm
        this.updateFlags = null
        this.shiftLeft = false
    }

    /* Constructor for add instruction over two registers */
    constructor(dest: Register, src1: Register, src2: Register, updateFlags: Boolean? = false) {
        this.dest = dest
        this.src1 = src1
        this.src2 = src2
        this.imm = null
        this.updateFlags = updateFlags
        this.shiftLeft = false
    }

    /* Constructor for ADD extended register */
    constructor(dest: Register, src1: Register) {
        this.dest = dest
        this.src1 = src1
        this.src2 = null
        this.imm = null
        this.updateFlags = null
        this.shiftLeft = false
    }

    /* Constructor for ADD Shift Left */
    constructor(
        dest: Register, src1: Register, src2: Register,
        imm: Int, shiftLeft: Boolean? = false
    ) {
        this.dest = dest
        this.src1 = src1
        this.src2 = src2
        this.imm = imm
        this.updateFlags = null
        this.shiftLeft = shiftLeft
    }


    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tADD")
        if (imm != null) {
            currImm = imm
            while (currImm > MAX_ADD_IMM_VALUE) {

                if (updateFlags != null && updateFlags) sb.append("S")
                sb.append(" $dest, $src1")
                when {
                    src2 != null -> sb.append(", $src2")
                    else -> {
                        sb.append(", #${MAX_ADD_IMM_VALUE}")
                        currImm -= MAX_ADD_IMM_VALUE
                    }
                }
                if (shiftLeft != null && shiftLeft) {
                    sb.append(", LSL #$imm")
                }
                sb.append("\n\tADD")
            }

            while (currImm < -MAX_ADD_IMM_VALUE) {

                if (updateFlags != null && updateFlags) sb.append("S")
                sb.append(" $dest, $src1")
                when {
                    src2 != null -> sb.append(", $src2")
                    else -> {
                        sb.append(", #${-MAX_ADD_IMM_VALUE}")
                        currImm += MAX_ADD_IMM_VALUE
                    }
                }
                if (shiftLeft != null && shiftLeft) {
                    sb.append(", LSL #$imm")
                }
                sb.append("\n\tADD")
            }
        }


        if (updateFlags != null && updateFlags) sb.append("S")

        sb.append(" $dest, $src1")

        when {
            src2 != null -> sb.append(", $src2")
            imm != null -> sb.append(", #$currImm")
        }
        if (shiftLeft != null && shiftLeft) {
            sb.append(", LSL #$imm")
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