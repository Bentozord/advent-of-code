package twenty.day.four;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class PassportReader {

    List<Map<String, String>> readPassports(final String filename) {
        List<Map<String, String>> passports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            StringBuilder builder = new StringBuilder();
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.isBlank()) {
                    passports.add(getPassport(builder));
                    builder = new StringBuilder();
                } else {
                    builder.append(currentLine);
                    builder.append(" ");
                }
            }
            passports.add(getPassport(builder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passports;
    }

    private Map<String, String> getPassport(StringBuilder builder) {
        Map<String, String> passport = new HashMap<>();
        Arrays.stream(builder.toString().split(" "))
                .forEach(property -> {
                    final String[] split = property.split(":");
                    passport.put(split[0], split[1]);
                });
        return passport;
    }
}
