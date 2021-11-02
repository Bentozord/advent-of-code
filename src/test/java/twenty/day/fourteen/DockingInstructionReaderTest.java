package twenty.day.fourteen;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DockingInstructionReaderTest {

    private static final String FILE_NAME = "src/main/resources/fourteen/dockingData";
    private static final String FILE_NAME_AOC = "src/main/resources/fourteen/dockingDataAOC";

    @ParameterizedTest
    @ValueSource(strings = {FILE_NAME, FILE_NAME_AOC})
    void shouldReadDockingInstructionsListFromFile(String filename) {
        DockingInstructionReader reader = new DockingInstructionReader();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(reader.readDockingInstructions(filename)));
    }
}