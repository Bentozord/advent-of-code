package twenty.day.twelve;

class Instruction {
    private final InstructionType instructionType;
    private final Integer value;

    public Instruction(InstructionType instructionType, Integer value) {
        this.instructionType = instructionType;
        this.value = value;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public Integer getValue() {
        return value;
    }
}
