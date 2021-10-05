package twenty.day.four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PassportReaderTest {

    private static final String FILE_NAME = "src/main/resources/passports";

    @Test
    void readPassports() {
        PassportReader reader = new PassportReader();
        Assertions.assertNotNull(reader.readPassports(FILE_NAME));
    }
}