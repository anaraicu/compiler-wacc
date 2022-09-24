package front_end.ASTNodes


data class FunctionSignature(
    private val name: String,
    private val params: List<ASTType>,
) {

    fun getName(): String {
        return name
    }

    fun getParams(): List<ASTType> {
        return params
    }

    fun sameParamTypes(otherTypes: List<ASTType>): Boolean {
        if (params.size != otherTypes.size)
            return false
        for (i in 0..params.size - 1) {
            if (params[i] != otherTypes[i]) {
                return false;
            }
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        return (this === other) || ((other is FunctionSignature) &&
            (name == other.getName()) && sameParamTypes(other.getParams()))
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + params.hashCode()
        return result
    }

}