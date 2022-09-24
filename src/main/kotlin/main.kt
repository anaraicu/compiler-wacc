import front_end.ASTNodes.ASTProgram
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.io.FilenameUtils
import parser.WACCLexer
import parser.WACCParser
import wacc.CodeGenerator
import wacc.SemanticAnalyser
import wacc.WACCErrorListener
import java.io.File
import java.io.IOException

fun main(args: Array<String>) {
    // Checking we can compile the given input file
    val filePath = args[0]
    var optimisationFlagConstantEvaluation: Boolean = false
    var optimisationFlagControlFlow: Boolean = false
    var optimisationFlagInstructionEvaluation: Boolean = false
    var optimisationFlag: Boolean = false
    if (args.size > 1) {
        optimisationFlag = args[1] == "-o"
        optimisationFlagConstantEvaluation = args[1] == "-o1"
        optimisationFlagControlFlow = args[1] == "-o2"
        optimisationFlagInstructionEvaluation = args[1] == "-o3"
    }
    if (optimisationFlag) {
        optimisationFlagConstantEvaluation = true
        optimisationFlagControlFlow = true
        optimisationFlagInstructionEvaluation = true
    }
    var input: CharStream? = null
    try {
        input = CharStreams.fromFileName(filePath)
    } catch (e: IOException) {
        System.err.println("The given file dose not exist!")
        System.exit(1)
    }
    if (FilenameUtils.getExtension(filePath) != "wacc") {
        System.err.println("Cannot compile non .wacc files!")
        System.exit(1)
    }

    // ANTLR lexing and parsing
    val lexer = WACCLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = WACCParser(tokens)
    parser.removeErrorListeners()
    parser.addErrorListener(WACCErrorListener())
    val tree = parser.program()

    // Semantic analysis
    val semanticAnalyser = SemanticAnalyser(optimisationFlagConstantEvaluation)
    val ASTRoot = semanticAnalyser.visit(tree) as ASTProgram

    // Code generation
    val codeGenerator =
        CodeGenerator(optimisationFlagControlFlow, optimisationFlagInstructionEvaluation)
    codeGenerator.visitProgram(ASTRoot)
    val generatedCode = codeGenerator.getInstructions().joinToString("\n") + "\n"

    // Writing the generated code to a .s file
    val fileName: String = FilenameUtils.getBaseName(filePath)
    File(fileName + ".s").writeText(generatedCode)
}
