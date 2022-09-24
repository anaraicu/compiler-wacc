package back_end.instructions

class Load : Instruction {
    private val src: Register?
    private val dest: Register
    private val imm: Int
    private val byte: Boolean
    private val signed: Boolean
    private val fromLabel: String?
    private val rw: Boolean
    private val cond: Condition?

    constructor(dest: Register, imm: Int) {
        this.dest = dest
        this.src = null
        this.imm = imm
        this.byte = false
        this.signed = false
        this.rw = false
        this.fromLabel = null
        this.cond = null
    }

    constructor(
        toLabel: String,
        dest: Register,
        byte: Boolean = false,
        signed: Boolean = false,
        cond: Condition? = null
    ) {
        this.src = null
        this.fromLabel = toLabel
        this.dest = dest
        this.imm = 0
        this.rw = false
        this.byte = byte
        this.signed = signed
        this.cond = cond
    }

    constructor(
        dest: Register,
        src: Register,
        offset: Int = 0,
        rw: Boolean = false,
        byte: Boolean = false,
        signed: Boolean = false,
        cond: Condition? = null
    ) {
        this.src = src
        this.fromLabel = null
        this.dest = dest
        this.imm = offset
        this.rw = rw
        this.byte = byte
        this.signed = signed
        this.cond = cond
    }

    override fun toString(): String {
        val sb = StringBuilder("\tLDR")

        if (cond != null) {
            sb.append(cond)
        }
        if (byte) {
            if (signed) {
                sb.append("S")
            }
            sb.append("B")
        }
        sb.append(" $dest, ")

        if (src != null) {
            sb.append("[$src")
            if (imm != 0) {
                sb.append(", #$imm")
            }
            sb.append("]")
            if (rw) {
                sb.append("!")
            }
        } else {
            if (fromLabel != null) {
                sb.append("=$fromLabel")
            } else {
                sb.append("=$imm");
            }
        }
        return sb.toString()
    }

    fun getSrc(): Register? {
        return src
    }

    fun getDest(): Register {
        return dest
    }

    fun getImm(): Int {
        return imm
    }
}