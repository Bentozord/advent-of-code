package twenty.day.fourteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class DockingInstructionReader {

    //consider regex
    List<DockingInstruction> readDockingInstructions(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            final String collect = reader.lines()
                    .collect(Collectors.joining("\n"));
            final String[] split = collect.split("mask = ");
            final String[] splitCopy = Arrays.copyOfRange(split, 1, split.length); //drop first element
            List<DockingInstruction> dockingInstructions = new ArrayList<>();
            for (String lines : splitCopy) {
                dockingInstructions.add(mapLinesToDockingInstruction(lines));
            }
            return dockingInstructions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private DockingInstruction mapLinesToDockingInstruction(String lines) {
        try (Scanner scanner = new Scanner(lines)) {
            String mask = scanner.nextLine();
            List<MemoryInstruction> memoryInstructions = new ArrayList<>();
            while (scanner.hasNextLine()) {
                final String[] split = scanner.nextLine().split(" = ");
                final String memory = split[0];
                Long memoryAddress = Long.valueOf(memory.substring(memory.indexOf("[") + 1, memory.indexOf("]")));
                Long value = Long.valueOf(split[1]);
                memoryInstructions.add(new MemoryInstruction(memoryAddress, value));
            }
            return new DockingInstruction(mask, memoryInstructions);
        }
    }
}
