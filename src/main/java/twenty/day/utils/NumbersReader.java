package twenty.day.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NumbersReader {

    public static List<Integer> readInput(String filename) {
        List<Integer> input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                input.add(Integer.valueOf(currentLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
