package back_end.instructions

class Move : Instruction {

    private val dest: Register
    private val src: Register?
    private val imm: Int?
    private val chr: Char?
    private val cond: Condition?

    constructor(dest: Register, src: Register) {
        this.dest = dest
        this.src = src
        this.imm = null
        this.chr = null
        this.cond = null
    }

    constructor(dest: Register, imm: Int, cond: Condition? = null) {
        this.dest = dest
        this.src = null
        this.imm = imm
        this.chr = null
        this.cond = cond
    }

    constructor(dest: Register, chr: Char) {
        this.dest = dest
        this.src = null
        this.imm = null
        this.chr = chr
        this.cond = null
    }

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder("\tMOV")

        if (cond != null) {
            sb.append(cond)
        }

        sb.append(" $dest")

        when {
            src != null -> sb.append(", $src")
            imm != null -> sb.append(", #$imm")
            chr != null -> sb.append(", #\'$chr\'")
        }

        return sb.toString()
    }

    fun getDest(): Register {
        return this.dest
    }

    fun getSrc(): Register? {
        return this.src
    }

    fun getImm(): Int? {
        return this.imm
    }

}