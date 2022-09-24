package wacc

import back_end.instructions.*

class CFunctionsCodeGenerator(val codeGenerator: CodeGenerator) {

    private val instructions = mutableListOf<Instruction>()
    private val alreadyDefinedInstructions = mutableSetOf<CFunctions>()

    fun generateCode(label: CFunctions) {
        /* Avoid redefining labels if they have already been added */
        if (alreadyDefinedInstructions.contains(label)) {
            return
        }
        alreadyDefinedInstructions.add(label)
        when (label) {
            CFunctions.PRINT_STRING -> generatePrintStringCode()
            CFunctions.PRINT_INT -> generatePrintIntCode()
            CFunctions.PRINT_BOOL -> generatePrintBoolCode()
            CFunctions.PRINT_REFERENCE -> generatePrintReferenceCode()
            CFunctions.PRINT_LN -> generatePrintLnCode()
            CFunctions.READ_INT -> generateReadIntCode()
            CFunctions.READ_CHAR -> generateReadCharCode()
        }
    }

    fun generatePrintStringCode() {
        instructions.add(FunctionName("p_print_string"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Load(Register.R1, Register.R0))
        instructions.add(Add(Register.R2, Register.R0, 4))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment("%.*s\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("printf", true))
        instructions.add(Move(Register.R0, 0))
        instructions.add(Branch("fflush", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generatePrintIntCode() {
        instructions.add(FunctionName("p_print_int"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Move(Register.R1, Register.R0))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment("%d\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("printf", true))
        instructions.add(Move(Register.R0, 0))
        instructions.add(Branch("fflush", true))
        instructions.add(Pop(listOf(Register.PC)))
    }


    fun generatePrintBoolCode() {
        instructions.add(FunctionName("p_print_bool"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Compare(Register.R0, 0))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.NE
            )
        )
        codeGenerator.addNewDataSegment("true\\0")
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.EQ
            )
        )
        codeGenerator.addNewDataSegment("false\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("printf", true))
        instructions.add(Move(Register.R0, 0))
        instructions.add(Branch("fflush", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generatePrintReferenceCode() {
        instructions.add(FunctionName("p_print_reference"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Move(Register.R1, Register.R0))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment("%p\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("printf", true))
        instructions.add(Move(Register.R0, 0))
        instructions.add(Branch("fflush", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generatePrintLnCode() {
        instructions.add(FunctionName("p_print_ln"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment("\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("puts", true))
        instructions.add(Move(Register.R0, 0))
        instructions.add(Branch("fflush", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generateReadIntCode() {
        instructions.add(FunctionName("p_read_int"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Move(Register.R1, Register.R0))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment("%d\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("scanf", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generateReadCharCode() {
        instructions.add(FunctionName("p_read_char"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Move(Register.R1, Register.R0))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment(" %c\\0")
        instructions.add(Add(Register.R0, Register.R0, 4))
        instructions.add(Branch("scanf", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun getCFunctionsInstructions(): MutableList<Instruction> {
        return instructions
    }
}