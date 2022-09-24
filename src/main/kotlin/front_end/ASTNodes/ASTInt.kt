package front_end.ASTNodes

class ASTInt(private var value: Long) : ASTType(TYPES.INT) {
    val MIN = -2147483648
    val MAX = 2147483647

    fun getValue(): Long {
        return value
    }

    fun setValue(newValue: Long) {
        value = newValue
    }
}