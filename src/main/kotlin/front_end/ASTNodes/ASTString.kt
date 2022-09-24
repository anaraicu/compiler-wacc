package front_end.ASTNodes

class ASTString(private var value: String) : ASTType(TYPES.STRING) {

    init {
        // we do this to remove the extra quotes that the parser stores
        // ex: ""string"" instead of "string"
        value = value.substring(1, value.length - 1)
    }

    fun getValue(): String {
        return value
    }
}