package back_end.instructions

enum class Register {
    R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12,
    SP, // R13, stack pointer
    LR, // R14, link register
    PC, // R15, program counter 
    CPSR; // current Program Status Register

    fun next(): Register {
        return values()[(this.ordinal + 1) % values().size]
    }
}