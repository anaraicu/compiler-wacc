from pathlib import Path
import subprocess
import sys

def runTests(testType, expectedExitCode):
    print(f"Testing in the {testType} directory")
    sourcePath = "src/test/resources/" + testType
    totalTests = 0
    passedTests = 0
    failedTests = []
    for path in Path(sourcePath).rglob('*.wacc'):
        totalTests += 1
        process = subprocess.Popen(['./compile', path])
        stdout, stderr = process.communicate()
        exit_code = process.wait()
        print(f"Attempted to compile {path}, exited with code {exit_code}")
        if exit_code == expectedExitCode :
            passedTests += 1
        else:
            failedTests.append((str(path), exit_code))
    if passedTests == totalTests :
        print(f"All tests for {testType} passed")
    else:
        for failedTest in failedTests:
            print(f"Test {failedTest[0]} failed with exit code {failedTest[1]}")
        print(str(totalTests - passedTests) + "/" + str(totalTests) +  " tests failed")
        sys.exit(1)
    print("============================\n")

runTests("valid/scope", 0)
# runTests("invalid/syntaxErr", 100)
# runTests("invalid/semanticErr", 200)
