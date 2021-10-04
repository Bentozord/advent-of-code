package twenty.day.three;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixReaderTest {

    public static final String FILE_NAME = "src/main/resources/three/matrix";

    @Test
    void readMatrix() {
        MatrixReader reader = new MatrixReader();
        final String[][] matrix = reader.readMatrix(FILE_NAME);
        Assertions.assertEquals(323, matrix.length);
    }
}