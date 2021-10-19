package twenty.day.twelve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class NavigationInstructionReader {

    List<Instruction> readInstructions(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines()
                    .map(s -> new Instruction(
                            InstructionType.valueOf(String.valueOf(s.charAt(0))),
                            Integer.valueOf(s.substring(1))
                    ))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
