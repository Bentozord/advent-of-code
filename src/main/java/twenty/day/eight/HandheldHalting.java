package twenty.day.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class HandheldHalting {

    Long getAccumulatorResult(List<Instruction> instructions) {
        return Optional.ofNullable(getChangedOperationResult(instructions, Operation.NOP, Operation.JMP))
                .orElse(getChangedOperationResult(instructions, Operation.JMP, Operation.NOP));
    }

    private Long getChangedOperationResult(List<Instruction> instructions, Operation sourceOperation, Operation replacementOperation) {
        return getReplacementOperationsResults(
                instructions,
                getOperationIndexes(instructions, sourceOperation),
                replacementOperation)
                .stream()
                .filter(result -> result != 0)
                .findAny()
                .orElse(null);
    }

    List<Integer> getOperationIndexes(List<Instruction> instructions, Operation operation) {
        return IntStream.range(0, instructions.size())
                .filter(i -> instructions.get(i).getOperation() == operation)
                .boxed()
                .toList();
    }

    List<Long> getReplacementOperationsResults(List<Instruction> instructions, List<Integer> operationIndexes, Operation replacementOperation) {
        List<Long> accumulatorsSums = new ArrayList<>();
        for (Integer index : operationIndexes) {
            List<Instruction> copiedInstructions = instructions.stream()
                    .map(Instruction::new)
                    .collect(Collectors.toList());
            copiedInstructions.get(index).setOperation(replacementOperation);
            accumulatorsSums.add(getAccumulatorValueWithoutRepetitions(copiedInstructions));
        }
        return accumulatorsSums;
    }

    long getAccumulatorValueWithoutRepetitions(List<Instruction> instructions) {
        long globalAccumulator = 0;
        int i;
        for (i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            if (instruction.isExecutedAlready()) {
                return 0;
//                return globalAccumulator; //changed for part 2 purpose globalAccumulator
            }
            if (instruction.getOperation() == Operation.ACC) {
                globalAccumulator += instruction.getArgument();
                instruction.setExecutedAlready(true);
            } else if (instruction.getOperation() == Operation.JMP) {
                i += instruction.getArgument() - 1;
                instruction.setExecutedAlready(true);
            } else {
                instruction.setExecutedAlready(true);
            }
        }
        return i == instructions.size() ? globalAccumulator : 0;
    }
}
