package wacc

import back_end.instructions.*

class RuntimeErrorsCodeGenerator(val codeGenerator: CodeGenerator) {

    private val instructions = mutableListOf<Instruction>()
    private val alreadyDefinedInstructions = mutableSetOf<RuntimeErrors>()

    fun generateCode(label: RuntimeErrors) {
        // there is no need to redefine this labels 
        // if they have already been added
        if (alreadyDefinedInstructions.contains(label)) {
            return
        }
        alreadyDefinedInstructions.add(label)
        when (label) {
            RuntimeErrors.OVERFLOW_ERROR -> generateOverflowErrorCode()
            RuntimeErrors.CHECK_DIVIDE_BY_ZERO -> generateCheckDivideByZeroCode()
            RuntimeErrors.CHECK_ARRAY_BOUNDS -> generateCheckArrayBoundsCode()
            RuntimeErrors.FREE_PAIR_NULL_REFERENCE_ERROR -> generateFreePairNullReferenceCode()
            RuntimeErrors.FREE_ARRAY_NULL_REFERENCE_ERROR -> generateFreeArrayNullReferenceCode()
            RuntimeErrors.NULL_REFERENCE_PAIR -> generateNullReferencePairCode()
        }
    }

    fun generateOverflowErrorCode() {
        instructions.add(FunctionName("p_throw_overflow_error"))
        instructions.add(Load("msg_${codeGenerator.getDataSegmentSize()}", Register.R0))
        codeGenerator.addNewDataSegment("OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", true))
    }

    fun generateCheckDivideByZeroCode() {
        instructions.add(FunctionName("p_check_divide_by_zero"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Compare(Register.R1, 0))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.EQ
            )
        )
        codeGenerator.addNewDataSegment("DivideByZeroError: divide or modulo by zero\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", true, cond = Condition.EQ))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generateCheckArrayBoundsCode() {
        instructions.add(FunctionName("p_check_array_bounds"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Compare(Register.R0, 0))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.LT
            )
        )
        codeGenerator.addNewDataSegment("ArrayIndexOutOfBoundsError: negative index\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", true, cond = Condition.LT))
        instructions.add(Load(Register.R1, Register.R1))
        instructions.add(Compare(Register.R0, Register.R1))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.CS
            )
        )
        codeGenerator.addNewDataSegment("ArrayIndexOutOfBoundsError: index too large\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", true, cond = Condition.CS))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generateFreePairNullReferenceCode() {
        instructions.add(FunctionName("p_free_pair"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Compare(Register.R0, 0))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.EQ
            )
        )
        codeGenerator.addNewDataSegment("NullReferenceError: dereference a null reference\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", cond = Condition.EQ))
        instructions.add(Push(listOf(Register.R0)))
        instructions.add(Load(Register.R0, Register.R0))
        instructions.add(Branch("free", true))
        instructions.add(Load(Register.R0, Register.SP))
        instructions.add(Load(Register.R0, Register.R0, 4))
        instructions.add(Branch("free", true))
        instructions.add(Pop(listOf(Register.R0)))
        instructions.add(Branch("free", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generateFreeArrayNullReferenceCode() {
        instructions.add(FunctionName("p_free_array"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Compare(Register.R0, 0))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.EQ
            )
        )
        codeGenerator.addNewDataSegment("NullReferenceError: dereference a null reference\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", cond = Condition.EQ))
        instructions.add(Branch("free", true))
        instructions.add(Pop(listOf(Register.PC)))
    }

    fun generateNullReferencePairCode() {
        instructions.add(FunctionName("p_check_null_pointer"))
        instructions.add(Push(listOf(Register.LR)))
        instructions.add(Compare(Register.R0, 0))
        instructions.add(
            Load(
                "msg_${codeGenerator.getDataSegmentSize()}",
                Register.R0,
                cond = Condition.EQ
            )
        )
        codeGenerator.addNewDataSegment("NullReferenceError: dereference a null reference\\n\\0")
        instructions.add(Branch("p_throw_runtime_error", true, cond = Condition.EQ))
        instructions.add(Pop(listOf(Register.PC)))
    }


    fun getRuntimeErrorsInstructions(): MutableList<Instruction> {
        // if we have any runtime errors, they should all jump to this label
        if (alreadyDefinedInstructions.isNotEmpty()) {
            instructions.add(FunctionName("p_throw_runtime_error"))
            instructions.add(Branch("p_print_string", true))
            instructions.add(Move(Register.R0, -1))
            instructions.add(Branch("exit", true))
            codeGenerator.cFunctionsCodeGenerator.generateCode(CFunctions.PRINT_STRING)
        }
        return instructions
    }
}