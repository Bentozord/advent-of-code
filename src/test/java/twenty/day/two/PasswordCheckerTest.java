package twenty.day.two;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordCheckerTest {

    private static final String FILE_NAME = "C:\\opt\\code\\advent-of-code\\src\\test\\java\\twenty\\day\\two\\inputPasswords";
    private static final String FILE_NAME_OTHER = "C:\\opt\\code\\advent-of-code\\src\\test\\java\\twenty\\day\\two\\inputPasswords2";
    List<PasswordPolicyStructure> passwordPolicyStructures;
    List<PasswordPolicyStructure> newPasswordPolicyStructures;

    @BeforeAll
    void setup() {
        passwordPolicyStructures = PasswordReader.passwordInputReader(FILE_NAME);
        newPasswordPolicyStructures = PasswordReader.passwordInputReader(FILE_NAME_OTHER);
    }

    @Test
    void testPasswordChecker() {
        Assertions.assertEquals(418, PasswordChecker.getValidPasswordsAmount(passwordPolicyStructures));
    }

    @Test
    void testNewPasswordChecker() {
        Assertions.assertEquals(616, PasswordChecker.getNewValidPasswordAmount(newPasswordPolicyStructures));
    }
}
