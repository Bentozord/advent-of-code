package twenty.day.eight;

class Instruction {
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    private Operation operation;
    private final int argument;
    private boolean executedAlready;

    public Instruction(Operation operation, int argument, boolean executedAlready) {
        this.operation = operation;
        this.argument = argument;
        this.executedAlready = executedAlready;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }

    public boolean isExecutedAlready() {
        return executedAlready;
    }

    public void setExecutedAlready(boolean executedAlready) {
        this.executedAlready = executedAlready;
    }

    Instruction(Instruction source) {
        this.operation = source.operation;
        this.argument = source.argument;
        this.executedAlready = source.executedAlready;
    }
}
