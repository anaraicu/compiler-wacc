package back_end.instructions

class Compare : Instruction {

    private val reg1: Register
    private val reg2: Register?
    private val imm: Int?
    private val cond: Condition?
    private val shiftRight: Boolean?

    /* Constructor for compare instruction between 2 registers */
    constructor(reg1: Register, reg2: Register, cond: Condition? = null) {
        this.reg1 = reg1
        this.reg2 = reg2
        this.imm = null
        this.cond = cond
        this.shiftRight = null
    }

    /* Constructor for compare instruction between one registers and an immediate */
    constructor(thisReg: Register, imm: Int, cond: Condition? = null) {
        this.reg1 = thisReg
        this.reg2 = null
        this.imm = imm
        this.cond = cond
        this.shiftRight = null
    }

    /* Constructor for compare instruction between 2 registers and an immediate */
    constructor(reg1: Register, reg2: Register, shiftRight: Boolean, imm: Int) {
        this.reg1 = reg1
        this.reg2 = reg2
        this.imm = imm
        this.cond = null
        this.shiftRight = shiftRight
    }


    override fun toString(): String {
        val sb = StringBuilder("\tCMP")

        if (cond != null) {
            sb.append("condition")
        }
        sb.append(" $reg1, ")

        when {
            reg2 != null -> sb.append(reg2)
            else -> sb.append("#$imm")
        }

        if (shiftRight != null && shiftRight) {
            sb.append(", ASR #$imm")
        }

        return sb.toString()
    }

    fun getReg1(): Register {
        return this.reg1
    }

    fun getImm(): Int? {
        return this.imm
    }
}