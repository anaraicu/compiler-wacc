package wacc

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import wacc.ErrorCodes.SEMANTIC_ERROR
import wacc.ErrorCodes.SYNTAX_ERROR

class WACCErrorListener : BaseErrorListener() {

    val ErrorList = mutableListOf<String>()

    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        println("Syntax error detected at line: $line, position: $charPositionLine.")
        if (msg != null) {
            println(msg)
        }
        System.exit(SYNTAX_ERROR)
    }

    fun errorHandler(msg: String, exitCode: Int, ctx: ParserRuleContext) {
        when (exitCode) {
            SYNTAX_ERROR -> {
                println(
                    "Syntax error at line ${ctx.getStart().line}, " +
                        "character ${ctx.getStart().charPositionInLine}"
                )
                println(msg)
                System.exit(exitCode)
            }
            SEMANTIC_ERROR -> {
                ErrorList.add(
                    "Semantic error at line ${ctx.getStart().line}, " +
                        "character ${ctx.getStart().charPositionInLine}"
                )
                ErrorList.add(msg)
            }
        }
    }

    fun printErrorList() {
        if (ErrorList.isEmpty()) {
            return
        }
        for (error in ErrorList) {
            println(error)
        }
        System.exit(SEMANTIC_ERROR)
    }
}