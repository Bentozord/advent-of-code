package twenty.day.eight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class ProgramReader {

    List<Instruction> readProgramInstructions(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines()
                    .map(this::createInstruction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Instruction createInstruction(String fileInstruction) {
        final String[] split = fileInstruction.split(" ");
        return new Instruction(Operation.valueOf(split[0].toUpperCase()), getArgument(split[1]), false);
    }

    private int getArgument(String argument) {
        boolean hasPlusSign = argument.charAt(0) == '+';
        return hasPlusSign ? Integer.parseInt(argument.substring(1)) : Integer.parseInt(argument);
    }
}
