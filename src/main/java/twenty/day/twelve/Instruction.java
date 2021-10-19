package twenty.day.twelve;

class Instruction {
    private final InstructionType instructionType;
    private final Integer value;

    Instruction(InstructionType instructionType, Integer value) {
        this.instructionType = instructionType;
        this.value = value;
    }

    InstructionType getInstructionType() {
        return instructionType;
    }

    Integer getValue() {
        return value;
    }
}
