package front_end.ASTNodes

class ASTChar(private var value: Char) : ASTType(TYPES.CHAR) {

    val MIN = 0
    val MAX = 255

    fun getValue(): Char {
        return value
    }

    fun setValue(newValue: Char) {
        value = newValue
    }
}