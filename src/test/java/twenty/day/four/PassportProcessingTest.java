package twenty.day.four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PassportProcessingTest {

    private static final String FILE_NAME = "src/main/resources/four/passports";
    private static final String FILE_NAME_OTHER = "src/main/resources/four/passportsExtended";

    PassportProcessing passportProcessing;
    PassportReader reader;
    List<Map<String, String>> passports;

    @BeforeAll
    void setup() {
        passportProcessing = new PassportProcessing();
        reader = new PassportReader();
        passports = reader.readPassports(FILE_NAME_OTHER);
    }

    @Test
    void validatePassport() {
        Assertions.assertTrue(passportProcessing.validatePassport(reader.readPassports(FILE_NAME).get(0)));
    }

    @Test
    void countValidPassports() {
        Assertions.assertEquals(2, passportProcessing.countValidPassports(reader.readPassports(FILE_NAME)));
    }

    @Test
    void countValidPassportsPartOne() {
        Assertions.assertEquals(196, passportProcessing.countValidPassports(passports));
    }

    @Test
    void countValidPassportsPartTwo() {
        Assertions.assertEquals(114, passportProcessing.countValidPassportsExtended(passports));
    }
}