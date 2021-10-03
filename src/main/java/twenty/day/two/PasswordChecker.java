package twenty.day.two;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class PasswordChecker {

    private static final String FILE_NAME_OTHER = "C:\\opt\\code\\advent-of-code\\src\\test\\java\\twenty\\day\\two\\inputPasswords2";

    public static void main(String[] args) {
        System.out.println(getNewValidPasswordAmount(PasswordReader.passwordInputReader(FILE_NAME_OTHER)));
    }

    public static Integer getValidPasswordsAmount(List<PasswordPolicyStructure> passwordPolicyStructures) {
        AtomicInteger counter = new AtomicInteger();
        passwordPolicyStructures.forEach(structure -> {
            if (structure.getPassword().contains(String.valueOf(structure.getLetter()))) {
                long count = structure.getPassword().chars().filter(ch -> ch == structure.getLetter()).count();
                if (structure.getMinOccurrences() <= count && count <= structure.getMaxOccurrences()) {
                    counter.addAndGet(1);
                }
            }
        });
        return counter.get();
    }

    public static Integer getNewValidPasswordAmount(List<PasswordPolicyStructure> passwordPolicyStructures) {
        AtomicInteger counter = new AtomicInteger();
        passwordPolicyStructures.forEach(structure -> {
            String password = structure.getPassword();
            char firstChar = password.charAt((int) structure.getMinOccurrences() - 1);
            char lastChar = password.charAt((int) structure.getMaxOccurrences() - 1);
            if (password.contains(String.valueOf(structure.getLetter())) &&
                    firstChar != lastChar &&
                    (firstChar == structure.getLetter() ||
                    lastChar == structure.getLetter())) {
                counter.addAndGet(1);
            }
        });
        return counter.get();
    }
}
