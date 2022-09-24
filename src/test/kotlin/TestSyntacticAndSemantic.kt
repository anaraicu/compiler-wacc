import TestFolders.invalidSemanticFolders
import TestFolders.invalidSyntaxFolders
import TextColours.ANSI_GREEN
import TextColours.ANSI_RED
import TextColours.ANSI_RESET
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import wacc.ErrorCodes.SEMANTIC_ERROR
import wacc.ErrorCodes.SYNTAX_ERROR
import java.io.File
import java.util.stream.Stream
import kotlin.test.assertEquals


class TestSyntacticAndSemantic {

    @ParameterizedTest(name = "testing \"{0}\"")

    @MethodSource("invalids")
    fun invalidRunner(filepath: String, exitCode: Int) {
        var passed = false
        var result: Int = -1
        try {
            val processBuilder = ProcessBuilder("./compile", filepath)
            val process = processBuilder.start()
            result = process.waitFor()
            assertEquals(exitCode, result)
            passed = true
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        } finally {
            // Print on the console if the test failed or passed
            if (passed) {
                print(ANSI_GREEN + "PASSED " + ANSI_RESET)
                println("Running $filepath")
            } else {
                print(ANSI_RED + "FAILED " + ANSI_RESET)
                println("Running $filepath \nExitCode expected: $exitCode, got : $result")
            }
        }
    }


    companion object {
        @JvmStatic
        fun invalids(): Stream<Arguments> {
            val invalidSyntaxPath = "src/test/resources/invalid/syntaxErr/"
            val invalidSemanticPath = "src/test/resources/invalid/semanticErr/"
            val args = mutableListOf<Arguments>()

            getArguments(args, invalidSyntaxPath, invalidSyntaxFolders, SYNTAX_ERROR)
            getArguments(args, invalidSemanticPath, invalidSemanticFolders, SEMANTIC_ERROR)

            return args.stream()
        }

        private fun getArguments(
            args: MutableList<Arguments>,
            path: String,
            folders: List<String>,
            code: Int
        ) {
            for (folder in folders) {
                File(path + folder).walkBottomUp().forEach {
                    if (it.isFile) {
                        args.add(Arguments.of(it.toString(), code))
                    }
                }
            }
        }
    }
}


