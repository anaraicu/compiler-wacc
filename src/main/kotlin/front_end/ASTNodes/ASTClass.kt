package front_end.ASTNodes

import wacc.SymbolTable

class ASTClass(
    private val name: String,
    private val fields: MutableMap<String, ASTField>,
    private val methods: List<ASTMethod>,
    private val ST: SymbolTable?
) : ASTType(TYPES.CLASS) {

    override fun getBaseType(): ASTType {
        return this
    }

    override fun getType(): TYPES {
        return TYPES.CLASS
    }

    override fun accept(node: ASTNode) {}

    fun getST(): SymbolTable? {
        return ST
    }

    fun getName(): String {
        return name
    }

    fun getFields(): MutableMap<String, ASTField> {
        return fields
    }

    fun getMethods(): List<ASTMethod> {
        return methods
    }

    override fun equals(other: Any?): Boolean {
        return (this === other) || (other is ASTAnyType) ||
            ((other is ASTClass) && name == other.name)

    }

}