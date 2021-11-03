package twenty.day.fourteen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DockingCoordinatorTest {

    private static final String FILE_NAME = "src/main/resources/fourteen/dockingData";
    private static final String FILE_NAME_AOC = "src/main/resources/fourteen/dockingDataAOC";

    DockingInstructionReader reader;
    List<DockingInstruction> dockingInstructions;
    List<DockingInstruction> dockingInstructionsAOC;
    DockingCoordinator dockingCoordinator;

    @BeforeAll
    void setup() {
        reader = new DockingInstructionReader();
        dockingInstructions = reader.readDockingInstructions(FILE_NAME);
        dockingInstructionsAOC = reader.readDockingInstructions(FILE_NAME_AOC);
        dockingCoordinator = new DockingCoordinator();
    }

    @Test
    void shouldReturnProperValueAfterBitMaskOperation() {
        Assertions.assertEquals(73, dockingCoordinator.calculateValueChange(dockingInstructions.get(0).mask(), 11L));
    }

    @ParameterizedTest
    @MethodSource("provideDockingInstructions")
    void shouldCountAllValuesInMemory(List<DockingInstruction> input, Long result) {
        final Map<Long, Long> memoryMap = dockingCoordinator.initializeDockingProgram(input);
        final Long mapValues = dockingCoordinator.getMemoryAddressesValues(memoryMap);
        Assertions.assertEquals(result, mapValues);
    }

    private Stream<Arguments> provideDockingInstructions() {
        return Stream.of(
                Arguments.of(dockingInstructions, 165L),
                Arguments.of(dockingInstructionsAOC, 18630548206046L)
        );
    }

    @Test
    void shouldCountAllValuesInMemoryAOC() {
        final Map<Long, Long> memoryMap = dockingCoordinator.initializeDockingProgramWithAddressChange(dockingInstructionsAOC);
        final Long mapValues = dockingCoordinator.getMemoryAddressesValues(memoryMap);
        Assertions.assertEquals(4254673508445L, mapValues);
    }
}