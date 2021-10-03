package twenty.day.two;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PasswordReader {
    public static List<PasswordPolicyStructure> passwordInputReader(String filename) {
        List<PasswordPolicyStructure> passwordPolicies = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] structures = currentLine.split(" ");
                passwordPolicies.add(new PasswordPolicyStructure(
                        Integer.parseInt(structures[0].split("-")[0]),
                        Integer.parseInt(structures[0].split("-")[1]),
                        structures[1].charAt(0),
                        structures[2]
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passwordPolicies;
    }
}
