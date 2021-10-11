package twenty.day.eight;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HandheldHaltingTest {

    private static final String FILE_NAME = "src/main/resources/eight/programInstructionsAOC";
    private List<Instruction> instructions;
    private HandheldHalting handheldHalting;

    @BeforeAll
    void setup() {
        ProgramReader reader = new ProgramReader();
        instructions = reader.readProgramInstructions(FILE_NAME);
        handheldHalting = new HandheldHalting();
    }

    @Test
    void shouldReadAndReturnInstructionsFromTheFile() {
        Assertions.assertTrue(CollectionUtils.isNotEmpty(instructions));
    }

    @Test
    void shouldCountAccumulatorValueBeforeAnyInstructionIsExecutedSecondTime() {
        Assertions.assertEquals(1394, handheldHalting.getAccumulatorValueWithoutRepetitions(instructions));
    }

    @Test
    void programShouldTerminate() {
        Assertions.assertEquals(1626, handheldHalting.getAccumulatorResult(instructions));
    }
}