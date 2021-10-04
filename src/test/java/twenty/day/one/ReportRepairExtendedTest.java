package twenty.day.one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import twenty.day.utils.NumbersReader;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportRepairExtendedTest {

    private static final String FILE_NAME = "src/main/resources/one/inputDayOnePartTwo";
    List<Integer> input;

    @BeforeAll
    void setup() {
        input = NumbersReader.readInput(FILE_NAME);
    }

    @Test
    void tripleSumUpAndMultiply() {
        Assertions.assertEquals(85491920, ReportRepairExtended.tripleSumUpAndMultiply(input));
    }
}