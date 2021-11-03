package twenty.day.fourteen;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DockingCoordinator {

    Long getMemoryAddressesValues(Map<Integer, Long> memoryMap) {
        return memoryMap.values().stream()
                .mapToLong(x -> x)
                .sum();
    }

    Map<Integer, Long> initializeDockingProgram(List<DockingInstruction> dockingInstructions) {
        Map<Integer, Long> memoryMap = new HashMap<>();
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
}
