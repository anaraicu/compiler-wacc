package front_end.ASTNodes


class ASTBool(private var value: Boolean) : ASTType(TYPES.BOOL) {

    fun getValue(): Boolean {
        return value
    }

    fun setValue(newValue: Boolean) {
        value = newValue
    }
}