package twenty.day.three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

class MatrixReader {

    String[][] readMatrix(final String filename) {
        Path path = Paths.get(filename);
        String[][] matrix = new String[0][];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             Stream<String> lines = Files.lines(path)) {
            final List<String> list = lines.toList();
            int numberOfRows = list.size();
            int numberOfColumns = list.get(0).length();
            matrix = new String[numberOfRows][numberOfColumns];
            String currentLine;
            int row = 0;
            while ((currentLine = reader.readLine()) != null) {
                for (int i = 0; i < currentLine.length(); i++) {
                    matrix[row][i] = String.valueOf(currentLine.charAt(i));
                }
                row += 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
