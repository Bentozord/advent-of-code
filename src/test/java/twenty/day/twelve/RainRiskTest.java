package twenty.day.twelve;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RainRiskTest {

    private static final String FILE_NAME = "src/main/resources/twelve/instructions";
    private static final String FILE_NAME_AOC = "src/main/resources/twelve/instructionsAOC";
    NavigationInstructionReader reader;
    List<Instruction> instructions;
    List<Instruction> instructionsAOC;
    RainRisk rainRisk;

    @BeforeAll
    void setup() {
        reader = new NavigationInstructionReader();
        instructions = reader.readInstructions(FILE_NAME);
        instructionsAOC = reader.readInstructions(FILE_NAME_AOC);
        rainRisk = new RainRisk();
    }


    @Test
    void getManhattanDistance() {
        Assertions.assertEquals(25, rainRisk.navigateAndGetDistance(instructions));
    }

    @Test
    void getManhattanDistanceAOC() {
        Assertions.assertEquals(1186, rainRisk.navigateAndGetDistance(instructionsAOC));
    }
}