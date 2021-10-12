package twenty.day.nine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EncodingErrorTest {

    private static final String FILE_NAME = "src/main/resources/nine/errors";
    private static final String FILE_NAME_AOC = "src/main/resources/nine/errorsAOC";
    PreambleReader reader;
    List<Long> input;
    List<Long> inputExtended;
    EncodingError encodingError;

    @BeforeAll
    void setup() {
        reader = new PreambleReader();
        input = reader.readPreamble(FILE_NAME);
        inputExtended = reader.readPreamble(FILE_NAME_AOC);
        encodingError = new EncodingError();
    }

    @Test
    void shouldGetFirstNumberThatFailsConditionOfTwoNumbersSumUpForPreambleSizeFive() {
        Assertions.assertEquals(127, encodingError.getFirstNumberThatFailsRuleForGivenPreamble(input, 5));
    }

    @Test
    void shouldGetFirstNumberThatFailsConditionOfTwoNumbersSumUpPreambleSizeTwentyFive() {
        Assertions.assertEquals(1398413738, encodingError.getFirstNumberThatFailsRuleForGivenPreamble(inputExtended, 25));
    }

}