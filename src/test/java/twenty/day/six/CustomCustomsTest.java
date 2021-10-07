package twenty.day.six;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomCustomsTest {

    private static final String FILE_NAME = "src/main/resources/six/answers";
    private static final String FILE_NAME_OTHER = "src/main/resources/six/answersExtended";
    AnswerReader reader;
    List<Set<String>> input;
    List<Set<String>> inputExtended;
    List<Set<String>> inputExtendedFilter;
    CustomCustoms customs;

    @BeforeAll
    void setup() {
        reader = new AnswerReader();
        input = reader.getAnswers(FILE_NAME);
        inputExtended = reader.getAnswers(FILE_NAME_OTHER);
        inputExtendedFilter = reader.getAnswersFiltered(FILE_NAME_OTHER);
        customs = new CustomCustoms();
    }

    @Test
    void readAnswers() {
        Assertions.assertNotNull(input);
    }

    @Test
    void countGroupAnswers() {
        Assertions.assertEquals(11, customs.countGroupAnswers(input));
    }

    @Test
    void countGroupAnswersPartOne() {
        Assertions.assertEquals(6530, customs.countGroupAnswers(inputExtended));
    }

    @Test
    void countGroupSameAnswersPartTwo() {
        Assertions.assertEquals(3323, customs.countGroupAnswers(inputExtendedFilter));
    }
}