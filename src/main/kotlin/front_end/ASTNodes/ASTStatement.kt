package front_end.ASTNodes

import wacc.SymbolTable

class ASTStatement(
    private val statType: STAT,
    private val lhs: ASTNode? = null,
    private val rhs: ASTNode? = null,
    private val expr: ASTNode? = null,
    private var ST: SymbolTable
) : ASTNode {

    override fun getBaseType(): ASTNode {
        when (statType) {
            STAT.EXIT -> return ASTAnyType()
            STAT.SEMICOLON -> return rhs!!.getBaseType()
            STAT.RETURN -> return expr!!.getBaseType()
            STAT.IF -> {
                val typeThen = lhs!!.getBaseType()
                val typeElse = rhs!!.getBaseType()
                if (typeThen is ASTAnyType)
                    return typeElse
                else if (typeElse is ASTAnyType)
                    return typeThen
                else if (typeThen == typeElse)
                    return typeThen
                else
                    return ASTNull()
            }
            else -> return ASTNull()
        }
    }

    override fun getType(): TYPES {
        return TYPES.STAT
    }

    override fun accept(node: ASTNode) {}

    fun getStatType(): STAT {
        return statType
    }

    fun getLhs(): ASTNode? {
        return lhs
    }

    fun getRhs(): ASTNode? {
        return rhs
    }

    fun getExpr(): ASTNode? {
        return expr
    }

    fun getST(): SymbolTable? {
        return ST
    }

}