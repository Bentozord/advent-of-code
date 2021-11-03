package twenty.day.fourteen;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class DockingCoordinator {

    Long getMemoryAddressesValues(Map<Long, Long> memoryMap) {
        return memoryMap.values().stream()
                .mapToLong(x -> x)
                .sum();
    }

    Map<Long, Long> initializeDockingProgramWithAddressChange(List<DockingInstruction> dockingInstructions) {
        Map<Long, Long> memoryMap = new HashMap<>();
        for (DockingInstruction dockingInstruction : dockingInstructions) {
            for (MemoryInstruction memoryInstruction : dockingInstruction.memoryInstructions()) {
                final List<Long> possibleAddresses = calculateAddressesChange(dockingInstruction.mask(), memoryInstruction.memoryAddress());
                for (Long address : possibleAddresses) {
                    memoryMap.put(address, memoryInstruction.value());
                }
            }
        }
        return memoryMap;
    }

    Map<Long, Long> initializeDockingProgram(List<DockingInstruction> dockingInstructions) {
        Map<Long, Long> memoryMap = new HashMap<>();
        dockingInstructions
                .forEach(dockingInstruction ->
                        dockingInstruction.memoryInstructions()
                                .forEach(memoryInstruction ->
                                        memoryMap.put(
                                                memoryInstruction.memoryAddress(),
                                                calculateValueChange(dockingInstruction.mask(), memoryInstruction.value())
                                        )));
        return memoryMap;
    }

    Long calculateValueChange(String mask, Long value) {
        final String binaryValue = StringUtils.leftPad(Long.toBinaryString(value), 36, '0');
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != 'X') {
                builder.append(mask.charAt(i));
            } else {
                builder.append(binaryValue.charAt(i));
            }
        }
        return Long.parseLong(builder.toString(), 2);
    }

    List<Long> calculateAddressesChange(String mask, Long address) {
        final String binaryAddress = StringUtils.leftPad(Long.toBinaryString(address), 36, '0');
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < binaryAddress.length(); i++) {
            if (mask.charAt(i) == '0') {
                builder.append(binaryAddress.charAt(i));
            } else if (mask.charAt(i) == '1') {
                builder.append('1');
            } else {
                builder.append('X');
            }
        }
        return getAllBinaryCombinationsOfAddress(builder.toString());
    }

    private List<Long> getAllBinaryCombinationsOfAddress(String address) {
        List<Long> combinations = new ArrayList<>();
        double number = Math.pow(2, address.chars().filter(c -> c == 'X').count());
        int[] indexes = IntStream.range(0, address.length())
                .filter(i -> address.charAt(i) == 'X')
                .toArray();
        for (int i = 0; i < number; i++) {
            StringBuilder builder = new StringBuilder(address);
            final String s = StringUtils.leftPad(Long.toBinaryString(i), indexes.length, '0');
            for (int j = 0; j < indexes.length; j++) {
                builder.setCharAt(indexes[j], s.charAt(j));
            }
            combinations.add(Long.parseLong(builder.toString(), 2));
        }
        return combinations;
    }
}
