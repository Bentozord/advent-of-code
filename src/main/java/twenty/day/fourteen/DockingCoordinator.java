package twenty.day.fourteen;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class DockingCoordinator {

    Long getMemoryAddressesValues(Map<Long, Long> memoryMap) {
        return memoryMap.values().stream()
                .mapToLong(x -> x)
                .sum();
    }

    Map<Long, Long> initializeDockingProgramWithAddressChange(List<DockingInstruction> dockingInstructions) {
        return dockingInstructions.stream()
                .flatMap(this::memoryInstructionAddressChanges)
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight, (a1, a2) -> a2));
    }

    private Stream<Pair<Long, Long>> memoryInstructionAddressChanges(DockingInstruction dockingInstruction) {
        return dockingInstruction.memoryInstructions().stream()
                .flatMap(memoryInstruction -> {
                    final List<Long> longs = calculateAddressesChange(dockingInstruction.mask(), memoryInstruction.memoryAddress());
                    return longs.stream()
                            .map(aLong -> Pair.of(aLong, memoryInstruction.value()));
                });
    }

    Map<Long, Long> initializeDockingProgram(List<DockingInstruction> dockingInstructions) {
        return dockingInstructions.stream()
                .flatMap(this::memoryInstructionValueChanges)
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight, (a1, a2) -> a2));
    }

    private Stream<Pair<Long, Long>> memoryInstructionValueChanges(DockingInstruction dockingInstruction) {
        return dockingInstruction.memoryInstructions().stream()
                .map(memoryInstruction -> Pair.of(
                        memoryInstruction.memoryAddress(),
                        calculateValueChange(dockingInstruction.mask(), memoryInstruction.value())
                ));
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
