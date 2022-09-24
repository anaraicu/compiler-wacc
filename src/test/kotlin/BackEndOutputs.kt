object OutputsAndExitCodesHashMaps {

    private val validOutputsAndExitCodesArray: HashMap<String, Pair<String, Int>> = hashMapOf(
        "array" to Pair("0x22190 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}", 0),
        "arrayBasic" to Pair("0", 0),
        "arrayEmpty" to Pair("0x22190", 0),
        "arrayLength" to Pair("4", 0),
        "arrayLookup" to Pair("43", 0),
        "arrayNested" to Pair("3\n3", 0),
        "arrayPrint" to Pair("0x22190 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}", 0),
        "arraySimple" to Pair("42", 0),
        "modifyString" to Pair("hello world!\nHello world!\nHi!", 0),
        "printRef" to Pair("Printing an array variable gives an address, such as 0x23198", 0)
    )

    private val validOutputsAndExitCodesBasicExit: HashMap<String, Pair<String, Int>> = hashMapOf(
        "exit-1" to Pair("", 255),
        "exitBasic" to Pair("", 7),
        "exitBasic2" to Pair("", 42),
        "exitWrap" to Pair("", 0),
        "comment" to Pair("", 0),
        "commentInLine" to Pair("", 0),
        "skip" to Pair("", 0)
    )

    private val validOutputsAndExitCodesExpressions: HashMap<String, Pair<String, Int>> = hashMapOf(
        "andExpr" to Pair("false\ntrue\nfalse", 0),
        "andOverOrExpr" to Pair("true\nfalse", 0),
        "bitwiseAndExpr" to Pair("2\n2", 0),
        "bitwiseNotExpr" to Pair("-5\n-14", 0),
        "bitwiseOrExpr" to Pair("21\n21", 0),
        "bitwiseXorExpr" to Pair("17\n17", 0),
        "boolCalc" to Pair("false", 0),
        "boolExpr1" to Pair("Correct", 0),
        "charComparisonExpr" to Pair("false\ntrue\ntrue\ntrue\nfalse\nfalse", 0),
        "divExpr" to Pair("1", 0),
        "equalsExpr" to Pair("false\nfalse\ntrue", 0),
        "equalsOverAnd" to Pair("false\ntrue", 0),
        "equalsOverBool" to Pair("true\nfalse", 0),
        "equalsOverOr" to Pair("true\nfalse", 0),
        "greaterEqExpr" to Pair("false\ntrue\ntrue", 0),
        "greaterExpr" to Pair("false\ntrue", 0),
        "intCalc" to Pair("72", 0),
        "intExpr1" to Pair("Correct", 0),
        "lessCharExpr" to Pair("true\nfalse", 0),
        "lessEqExpr" to Pair("true\nfalse\ntrue", 0),
        "longBitwiseExpr1" to Pair("-9\n7", 0),
        "longBitwiseExpr2" to Pair("-1\n-45", 0),
        "lessExpr" to Pair("true\nfalse", 0),
        "longExpr" to Pair("", 153),
        "longExpr2" to Pair("", 10),
        "longExpr3" to Pair("", 9),
        "longSplitExpr" to Pair("", 153),
        "longSplitExpr2" to Pair("362880\n128", 128),
        "minusExpr" to Pair("5", 0),
        "minusMinusExpr" to Pair("3", 0),
        "minusNoWhitespaceExpr" to Pair("-1", 0),
        "minusPlusExpr" to Pair("-1", 0),
        "modExpr" to Pair("2", 0),
        "multExpr" to Pair("15", 0),
        "multNoWhitespaceExpr" to Pair("2", 0),
        "negBothDiv" to Pair("2", 0),
        "negBothMod" to Pair("-2", 0),
        "negDividendDiv" to Pair("-2", 0),
        "negDividendMod" to Pair("-2", 0),
        "negDivisorDiv" to Pair("-2", 0),
        "negDivisorMod" to Pair("2", 0),
        "negExpr" to Pair("-42", 0),
        "notExpr" to Pair("false\ntrue", 0),
        "notequalsExpr" to Pair("true\ntrue\nfalse", 0),
        "orExpr" to Pair("true\ntrue\nfalse", 0),
        "ordAndchrExpr" to Pair("a is 97\n99 is c", 0),
        "plusExpr" to Pair("35", 0),
        "plusMinusExpr" to Pair("-1", 0),
        "plusNoWhitespaceExpr" to Pair("3", 0),
        "plusPlusExpr" to Pair("3", 0),
        "sequentialCount" to Pair("Can you count to 10?\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10", 0),
        "stringEqualsExpr" to Pair("true\nfalse\nfalse", 0)
    )

    private val validOutputsAndExitCodesIf: HashMap<String, Pair<String, Int>> = hashMapOf(
        "if1" to Pair("correct", 0),
        "if2" to Pair("correct", 0),
        "if3" to Pair("correct", 0),
        "if4" to Pair("correct", 0),
        "if5" to Pair("correct", 0),
        "if6" to Pair("correct", 0),
        "ifBasic" to Pair("here", 0),
        "ifFalse" to Pair("here", 0),
        "ifTrue" to Pair("here", 0),
        "whitespace" to Pair("1", 0)
    )

    private val validOutputsAndExitCodesNestedFunctions: HashMap<String, Pair<String, Int>> =
        hashMapOf(
            "fibonacciFullRec" to Pair(
                "This program calculates the nth fibonacci number recursively.\nPlease enter n (should not be too large): The input n is 10\nThe nth fibonacci number is 55",
                0
            ),
            "fibonacciRecursive" to Pair(
                "The first 20 fibonacci numbers are:\n0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181...",
                0
            ),
            "fixedPointRealArithmetic" to Pair("Using fixed-point real: 10 / 3 * 3 = 10", 0),
            "functionConditionalReturn" to Pair("true", 0),
            "mutualRecursion" to Pair(
                "r1: sending 8\nr2: received 8\nr1: sending 7\nr2: received 7\nr1: sending 6\nr2: received 6\nr1: sending 5\nr2: received 5\nr1: sending 4\nr2: received 4\nr1: sending 3\nr2: received 3\nr1: sending 2\nr2: received 2\nr1: sending 1\nr2: received 1",
                0
            ),
            "printTriangle" to Pair("--------\n-------\n------\n-----\n----\n---\n--\n-", 0),
            "simpleRecursion" to Pair("42", 0)
        )

    private val validOutputsAndExitCodesSimpleFunctions: HashMap<String, Pair<String, Int>> =
        hashMapOf(
            "asciiTable" to Pair(
                "Ascii character lookup table:\n-------------\n|   32 =    |\n|   33 = !  |\n|   34 = \"  |\n|   35 = #  |\n|   36 = $  |\n|   37 = %  |\n|   38 = &  |\n|   39 = \'  |\n|   40 = (  |\n|   41 = )  |\n|   42 = *  |\n|   43 = +  |\n|   44 = ,  |\n|   45 = -  |\n|   46 = .  |\n|   47 = /  |\n|   48 = 0  |\n|   49 = 1  |\n|   50 = 2  |\n|   51 = 3  |\n|   52 = 4  |\n|   53 = 5  |\n|   54 = 6  |\n|   55 = 7  |\n|   56 = 8  |\n|   57 = 9  |\n|   58 = :  |\n|   59 = ;  |\n|   60 = <  |\n|   61 = =  |\n|   62 = >  |\n|   63 = ?  |\n|   64 = @  |\n|   65 = A  |\n|   66 = B  |\n|   67 = C  |\n|   68 = D  |\n|   69 = E  |\n|   70 = F  |\n|   71 = G  |\n|   72 = H  |\n|   73 = I  |\n|   74 = J  |\n|   75 = K  |\n|   76 = L  |\n|   77 = M  |\n|   78 = N  |\n|   79 = O  |\n|   80 = P  |\n|   81 = Q  |\n|   82 = R  |\n|   83 = S  |\n|   84 = T  |\n|   85 = U  |\n|   86 = V  |\n|   87 = W  |\n|   88 = X  |\n|   89 = Y  |\n|   90 = Z  |\n|   91 = [  |\n|   92 = \\  |\n|   93 = ]  |\n|   94 = ^  |\n|   95 = _  |\n|   96 = `  |\n|   97 = a  |\n|   98 = b  |\n|   99 = c  |\n|  100 = d  |\n|  101 = e  |\n|  102 = f  |\n|  103 = g  |\n|  104 = h  |\n|  105 = i  |\n|  106 = j  |\n|  107 = k  |\n|  108 = l  |\n|  109 = m  |\n|  110 = n  |\n|  111 = o  |\n|  112 = p  |\n|  113 = q  |\n|  114 = r  |\n|  115 = s  |\n|  116 = t  |\n|  117 = u  |\n|  118 = v  |\n|  119 = w  |\n|  120 = x  |\n|  121 = y  |\n|  122 = z  |\n|  123 = {  |\n|  124 = |  |\n|  125 = }  |\n|  126 = ~  |\n-------------",
                0
            ),
            "functionDeclaration" to Pair("12", 0),
            "functionDoubleReturn" to Pair("3", 0),
            "functionIfReturns" to Pair("go\n1", 0),
            "functionManyArguments" to Pair(
                "a is 42\nb is true\nc is u\nd is hello\ne is 0x22190\nf is 0x221a0\nanswer is g",
                0
            ),
            "functionMultiReturns" to Pair("1", 0),
            "functionReturnPair" to Pair("10", 0),
            "functionSimple" to Pair("0", 0),
            "functionSimpleLoop" to Pair("10", 0),
            "functionUpdateParameter" to Pair("y is 1\nx is 1\nx is now 5\ny is still 1", 0),
            "incFunction" to Pair("1\n4", 0),
            "negFunction" to Pair("true\nfalse\ntrue", 0),
            "sameArgName" to Pair("99", 0),
            "sameArgName2" to Pair("99", 0),
            "sameNameAsVar" to Pair("5", 0)
        )

    private val validOutputsAndExitCodesFunctionOverloading: HashMap<String, Pair<String, Int>> =
        hashMapOf(
            "sameNameDifferentNumberOfParams" to Pair("1\n5", 0),
            "sameNameDifferentParamTypes" to Pair("1\na", 0)
        )

    private val validOutputsAndExitCodesIO: HashMap<String, Pair<String, Int>> = hashMapOf(
        "IOSequence" to Pair("Please input an integer: You input: 10", 0),
        "hashInProgram" to Pair(
            "We can print the hash character: #\nWe can also print # when its in a string.",
            0
        ),
        "multipleStringsAssignment" to Pair(
            "s1 is Hi\ns2 is Hello\nThey are not the same string.\nNow make s1 = s2\ns1 is Hello\ns2 is Hello\nThey are the same string.",
            0
        ),
        "print-backspace" to Pair("Hello\b World!\n", 0),
        "print-carridge-return" to Pair("Hello \r World!\n", 0),
        "print" to Pair("Hello World!\n", 0),
        "printBool" to Pair("True is true\nFalse is false", 0),
        "printChar" to Pair("A simple character example is f", 0),
        "printCharArray" to Pair("hi!", 0),
        "printCharAsString" to Pair("foo\nbar", 0),
        "printEscChar" to Pair("An escaped character example is \"", 0),
        "printInt" to Pair("An example integer is 189", 0),
        "println" to Pair("Hello World!", 0),
        "echoBigInt" to Pair("enter an integer to echo\n2000000000", 0),
        "echoBigNegInt" to Pair("enter an integer to echo\n-2000000000", 0),
        "echoChar" to Pair("enter a character to echo\nf", 0),
        "echoInt" to Pair("enter an integer to echo\n10", 0),
        "echoNegInt" to Pair("enter an integer to echo\n-10", 0),
        "echoPuncChar" to Pair("enter a character to echo\n?", 0),
        "read" to Pair("input an integer to continue...", 0)
    )

    private val validOutputsAndExitCodesPair: HashMap<String, Pair<String, Int>> = hashMapOf(
        "checkRefPair" to Pair("0x22190\n0x22190\ntrue\n10\n10\ntrue\na\na\ntrue", 0),
        "createPair" to Pair("0x22190 = (10, 3)", 0),
        "createPair02" to Pair("0x22190 = (a, b)", 0),
        "createPair03" to Pair("0x22190 = (10, a)", 0),
        "createRefPair" to Pair("0x22190 = (10, a)\n0x22190 = (10, a)", 0),
        "free" to Pair("", 0),
        "linkedList" to Pair("list = {1, 2, 4, 11}", 0),
        "nestedPair" to Pair("0x22190 = (2, 3)\n0x221c0 = (1, 0x22190)", 0),
        "null" to Pair("(nil)\n(nil)", 0),
        "printNull" to Pair("(nil)", 0),
        "printNullPair" to Pair("(nil)", 0),
        "printPair" to Pair("0x22190 = (10, a)", 0),
        "printPairOfNulls" to Pair("0x22190 = ((nil),(nil))", 0),
        "readPair" to Pair(
            "Please enter the first element (char): \nPlease enter the second element (int): \nThe first element was f\nThe second element was 10",
            0
        ),
        "writeFst" to Pair("10\n42", 0),
        "writeSnd" to Pair("a\nZ", 0)
    )

    private val validOutputsAndExitCodesFullPairType: HashMap<String, Pair<String, Int>> =
        hashMapOf(
            "checkRefPair" to Pair("0x22190\n0x22190\ntrue\n10\n10\ntrue\na\na\ntrue", 0),
            "createPair" to Pair("0x22190 = (10, 3)", 0),
            "createPair02" to Pair("0x22190 = (a, b)", 0),
            "createPair03" to Pair("0x22190 = (10, a)", 0),
            "createRefPair" to Pair("0x22190 = (10, a)\n0x22190 = (10, a)", 0),
            "free" to Pair("", 0),
            "nestedPair" to Pair("0x22190 = (2, 3)\n0x221c0 = (1, 0x22190)", 0),
            "null" to Pair("(nil)", 0),
            "printNull" to Pair("(nil)", 0),
            "printNullPair" to Pair("(nil)", 0),
            "printPair" to Pair("0x22190 = (10, a)", 0),
            "writeFst" to Pair("10\n42", 0),
            "writeSnd" to Pair("a\nZ", 0)
        )

    private val validOutputsAndExitCodesScope: HashMap<String, Pair<String, Int>> = hashMapOf(
        "ifNested1" to Pair("correct", 0),
        "ifNested2" to Pair("correct", 0),
        "indentationNotImportant" to Pair("", 0),
        "intsAndKeywords" to Pair("", 0),
        "printAllTypes" to Pair(
            "( [1, 2, 3] , [a, b, c] )\n[ 0x23220 = (a, true), 0x23250 = (b, false) ]\n1, 2\narray, of, strings\ntrue, false, true\nxyz\n1, 2, 3\nthis is a string\ntrue\nx\n5",
            0
        ),
        "scope" to Pair("", 0),
        "scopeBasic" to Pair("", 0),
        "scopeIfRedefine" to Pair("true\n12", 0),
        "scopeRedefine" to Pair("true\n2", 0),
        "scopeSimpleRedefine" to Pair("true\n12", 0),
        "scopeVars" to Pair("2\n4\n2", 0),
        "scopeWhileNested" to Pair(
            "counting... 5\ncounting... 4\ncounting... 3\ncounting... 2\ncounting... 1\n0 Boom!",
            0
        ),
        "scopeWhileRedefine" to Pair(
            "counting... 5\ncounting... 4\ncounting... 3\ncounting... 2\ncounting... 1\n0 Boom!",
            0
        )
    )

    private val validOutputsAndExitCodesSequence: HashMap<String, Pair<String, Int>> = hashMapOf(
        "basicSeq" to Pair("", 0),
        "basicSeq2" to Pair("", 0),
        "boolAssignment" to Pair("true", 0),
        "charAssignment" to Pair("Z", 0),
        "exitSimple" to Pair("", 42),
        "intAssignment" to Pair("", 20),
        "intLeadingZeros" to Pair("42\n0", 0),
        "stringAssignment" to Pair("foo\nbar", 0)
    )

    private val validOutputsAndExitCodesVariables: HashMap<String, Pair<String, Int>> = hashMapOf(
        "_VarNames" to Pair("", 19),
        "boolDeclaration" to Pair("false", 0),
        "boolDeclaration2" to Pair("true", 0),
        "capCharDeclaration" to Pair("M", 0),
        "charDeclaration" to Pair("a", 0),
        "charDeclaration2" to Pair("z", 0),
        "emptyStringDeclaration" to Pair("", 0),
        "intDeclaration" to Pair("42", 0),
        "longVarNames" to Pair("", 5),
        "manyVariables" to Pair("0\n256", 0),
        "negIntDeclaration" to Pair("-1", 0),
        "puncCharDeclaration" to Pair("!", 0),
        "stringDeclaration" to Pair("Hello World!", 0),
        "zeroIntDeclaration" to Pair("0", 0)
    )

    private val validOutputsAndExitCodesWhile: HashMap<String, Pair<String, Int>> = hashMapOf(
        "fibonacciFullIt" to Pair(
            "This program calculates the nth fibonacci number iteratively.\nPlease enter n (should not be too large): The input n is 10\nThe nth fibonacci number is 55",
            0
        ),
        "fibonacciIterative" to Pair(
            "The first 20 fibonacci numbers are:\n0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ...",
            0
        ),
        "loopCharCondition" to Pair("Change c\nShould print \"Change c\" once before.", 0),
        "loopIntCondition" to Pair("Change n\nShould print \"Change n\" once before.", 0),
        "max" to Pair("max value = 17", 0),
        "min" to Pair("min value = 10", 0),
        "rmStyleAdd" to Pair(
            "initial value of x: 3\n(+)(+)(+)(+)(+)(+)(+)\nfinal value of x: 10",
            0
        ),
        "rmStyleAddIO" to Pair(
            "Enter the first number: Enter the second number: Initial value of x: 0\n\nfinal value of x: 0",
            0
        ),
        "whileBasic" to Pair("", 0),
        "whileBoolFlip" to Pair("flip b!\nend of loop", 0),
        "whileCount" to Pair("Can you count to 10?\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10", 0),
        "whileFalse" to Pair("end of loop", 0)
    )

    private val validOutputsAndExitCodesRuntimeErr: HashMap<String, Pair<String, Int>> = hashMapOf(
        "arrayNegBounds" to Pair("ArrayIndexOutOfBoundsError: negative index", 255),
        "arrayOutOfBounds" to Pair("ArrayIndexOutOfBoundsError: index too large", 255),
        "arrayOutOfBoundsWrite" to Pair("ArrayIndexOutOfBoundsError: index too large", 255),
        "divideByZero" to Pair("DivideByZeroError: divide or modulo by zero", 255),
        "modByZero" to Pair("DivideByZeroError: divide or modulo by zero", 255),
        "intmultOverflow" to Pair(
            "2147483\n2147483000\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intJustOverflow" to Pair(
            "2147483646\n2147483647\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intnegateOverflow" to Pair(
            "-2147483648\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intnegateOverflow2" to Pair(
            "-2147483648\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intnegateOverflow3" to Pair(
            "-20000\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intnegateOverflow4" to Pair(
            "-2000000000\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intUnderflow" to Pair(
            "-2147483647\n-2147483648\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "intWayOverflow" to Pair(
            "2000000000\nOverflowError: the result is too small/large to store in a 4-byte signed-integer.",
            255
        ),
        "freeNull" to Pair("NullReferenceError: dereference a null reference", 255),
        "readNull1" to Pair("NullReferenceError: dereference a null reference", 255),
        "readNull2" to Pair("NullReferenceError: dereference a null reference", 255),
        "setNull1" to Pair("NullReferenceError: dereference a null reference", 255),
        "setNull2" to Pair("NullReferenceError: dereference a null reference", 255),
        "useNull1" to Pair("NullReferenceError: dereference a null reference", 255),
        "useNull2" to Pair("NullReferenceError: dereference a null reference", 255)
    )

    private val validOutputsAndExitCodesLoopExtensions: HashMap<String, Pair<String, Int>> =
        hashMapOf(
            "dowhile3ops" to Pair("4", 0),
            "dowhileSkip" to Pair("", 0),
            "dowhileNested" to Pair("1\n2\n3\n4", 0),
            "dowhileContinue" to Pair("1\nodd\n3\nodd\n5\nodd", 0),
            "doWhileBreakContinue" to Pair("0\n1\n6\n3", 0),
            "dowhileFalse" to Pair("2", 0),
            "forBasic" to Pair("", 0),
            "forBreakNested" to Pair("100\n5\n0\n100\n5\n1", 0),
            "forBreakContinue" to Pair("4\n1", 0),
            "forBreakSimple" to Pair("0", 0),
            "forContinueSimple" to Pair("1\n2\n3", 0),
            "forLong" to Pair(
                "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n21\n22\n23\n24\n25\n26\n27\n28\n29\n30\n31\n32\n33\n34\n35\n36\n37\n38\n39\n40\n41\n42\n43\n44\n45\n46\n47\n48\n49\n50\n51\n52\n53\n54\n55\n56\n57\n58\n59\n60\n61\n62\n63\n64\n65\n66\n67\n68\n69\n70\n71\n72\n73\n74\n75\n76\n77\n78\n79\n80\n81\n82\n83\n84\n85\n86\n87\n88\n89\n90\n91\n92\n93\n94\n95\n96\n97\n98\n99\n100\n4950",
                0
            ),
            "forNested" to Pair("0123456789 0123456789 2", 0),
            "forFalseCondition" to Pair("for loop skipped", 0),
            "forIfBreakContinue" to Pair("0\n1\n6\n3", 0),
            "whileBreakContinue" to Pair("0\n1\n6\n3", 0)
        )

    private val validOutputsAndExitCodesClasses: HashMap<String, Pair<String, Int>> = hashMapOf(
        "classAccessDeclaredField" to Pair("6", 0),
        "classAccessDefaultField" to Pair("5", 0),
        "multipleObjectsSameClass" to Pair("5\n15", 0),
        "fieldsGetDefaultValue" to Pair("0\n\u0000\nfalse\n", 0),
        "getterAndSetter" to Pair("5\n42", 0),
        "methodCall" to Pair("true", 0),
        "multipleObjectsSameMethodCall" to Pair("15\n42", 0),
        "privateAndPublicMethods" to Pair("55", 0)
    )

    val directoryOutputGroup = hashMapOf(
        "loops" to validOutputsAndExitCodesLoopExtensions,
        "classes" to validOutputsAndExitCodesClasses,
        "IO" to validOutputsAndExitCodesIO,
        "array" to validOutputsAndExitCodesArray,
        "basic" to validOutputsAndExitCodesBasicExit,
        "expressions" to validOutputsAndExitCodesExpressions,
        "function/nested_functions" to validOutputsAndExitCodesNestedFunctions,
        "function/simple_functions" to validOutputsAndExitCodesSimpleFunctions,
        "function-overloading" to validOutputsAndExitCodesFunctionOverloading,
        "if" to validOutputsAndExitCodesIf,
        "pairs" to validOutputsAndExitCodesPair,
        "full-pair-type" to validOutputsAndExitCodesFullPairType,
        "runtimeErr" to validOutputsAndExitCodesRuntimeErr,
        "scope" to validOutputsAndExitCodesScope,
        "sequence" to validOutputsAndExitCodesSequence,
        "variables" to validOutputsAndExitCodesVariables,
        "while" to validOutputsAndExitCodesWhile,
    )

    val inputMap = hashMapOf<String, String>(
        "fibonacciFullRec" to "/src/test/resources/valid/input/-10.txt",
        "printInputTriangle" to "/src/test/resources/valid/input/10.txt",
        "readPair" to "/src/test/resources/valid/input/f.txt"
    )
}
