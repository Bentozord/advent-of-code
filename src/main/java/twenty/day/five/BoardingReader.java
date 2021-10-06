package twenty.day.five;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BoardingReader {

    List<String> getBoardingPasses(String filename) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                result.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
