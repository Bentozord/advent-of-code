package twenty.day.two;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class PasswordPolicyStructure {
    long minOccurrences;
    long maxOccurrences;
    char letter;
    String password;
}
