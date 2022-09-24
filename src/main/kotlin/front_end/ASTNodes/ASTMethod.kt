package front_end.ASTNodes

import wacc.SymbolTable

class ASTMethod(
    private val visib: Visibility,
    private val func: ASTFunction
) : ASTFunction(
    func.getReturnType(), func.getName(), func.getParams(),
    func.getStat(), func.getST()
) {

    override fun getType(): TYPES {
        return func.getType()
    }

    override fun getBaseType(): ASTNode {
        return func.getBaseType()
    }

    override fun accept(node: ASTNode) {}

    fun getFunc(): ASTFunction {
        return func
    }

    fun getVisib(): Visibility {
        return visib
    }

    override fun getReturnType(): ASTNode {
        return func.getReturnType()
    }

    override fun getST(): SymbolTable? {
        return func.getST()
    }

    override fun getName(): String {
        return func.getName()
    }

    override fun getParams(): List<ASTNode> {
        return func.getParams()
    }

    override fun getStat(): ASTStatement {
        return func.getStat()
    }


}