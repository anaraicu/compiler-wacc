package back_end.instructions

enum class Condition {
    EQ, // equal
    NE, // not equal
    CS, // unsigned higher or same, carry set
    CC, // unsigned lower, carry clear
    MI, // negative, minus
    PL, // positive or zero, plus
    VS, // overflow
    VC, // no overflow
    HI, // unsigned higher
    LS, // unsigned lower or same
    GE, // signed greater or equal
    LT, // signed less than
    GT, // signed greater than
    LE, // signed less than or equal
    AL  // always
}