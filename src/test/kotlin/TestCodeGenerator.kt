import OutputsAndExitCodesHashMaps.directoryOutputGroup
import TestFolders.listOfValidPaths
import TextColours.ANSI_GREEN
import TextColours.ANSI_RED
import TextColours.ANSI_RESET
import org.junit.Assert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import wacc.ErrorCodes.VALID
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit
import java.util.stream.Stream
import kotlin.test.assertEquals

class TestCodeGenerator {

    @ParameterizedTest(name = "testing \"{0}\"")

    @MethodSource("validOutputs")
    fun runner(filepath: String, filename: String, output: HashMap<String, Pair<String, Int>>) {

        var passed = false
        var result: Int = -1
        val expectedExitCode = output.get(filename)!!.second
        val expectedStream = output.get(filename)!!.first
        var actualStream = ""

        try {
            // Run the compile command, check for no semantic/syntactic errors
            var processBuilder = ProcessBuilder("./compile", "-o", filepath)
            result = processBuilder.start().waitFor()
            assertEquals(VALID, result)

            // Create .s file
            processBuilder = ProcessBuilder(
                "arm-linux-gnueabi-gcc", "-o", filename,
                "-mcpu=arm1176jzf-s", "-mtune=arm1176jzf-s", "$filename.s",
            )
            result = processBuilder.start().waitFor()
            assertEquals(VALID, result)

            // Run the executable
            processBuilder = ProcessBuilder("qemu-arm", "-L", "/usr/arm-linux-gnueabi/", filename)

            val process = processBuilder.start()
            process.waitFor(1, TimeUnit.MINUTES)
            result = process.exitValue()
            assertEquals((expectedExitCode + 256) % 256, result)

            // Get output string
            val stdout: InputStream = process.getInputStream()
            val reader = BufferedReader(InputStreamReader(stdout))
            var line = reader.readLine()
            while (line != null) {
                actualStream += line.trim()
                line = reader.readLine()
                if (line != null) actualStream += '\n'
            }

            Assert.assertEquals(expectedStream, actualStream)
            passed = true

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        } finally {
            // Delete assembly file and executable
            val file = File(filename)
            val assembly = File("$file.s")
            file.delete()
            assembly.delete()
            // Print on the console if the test passed or failed
            if (passed) {
                print(ANSI_GREEN + "PASSED " + ANSI_RESET)
                println("Running $filepath")
            } else {
                print(ANSI_RED + "FAILED " + ANSI_RESET)
                println(
                    "Running $filepath \n" +
                        "ExitCode expected: $expectedExitCode, got : $result \n" +
                        "Expected Stream: \n$expectedStream \n" +
                        "Actually got:    \n${actualStream}"
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun validOutputs(): Stream<Arguments> {
            val validTestsPath = "src/test/resources/valid/"
            val args = mutableListOf<Arguments>()

            for (directory in listOfValidPaths) {
                File("$validTestsPath$directory").walkBottomUp().forEach {
                    if (it.isFile) {
                        val file = it.toString().split('/').last()
                        val filename = file.split('.').first()
                        args.add(
                            Arguments.of(
                                it.toString(),
                                filename,
                                directoryOutputGroup.get(directory)
                            )
                        )
                    }
                }
            }

            return args.stream()
        }
    }

}
