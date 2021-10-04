package twenty.day.three;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ToboganTrajectoryTest {

    private static final String FILE_NAME = "src/main/resources/three/smallMatrix";
    private static final String FILE_NAME_OTHER = "src/main/resources/three/matrix";
    MatrixReader reader;

    @BeforeAll
    void setup() {
        reader = new MatrixReader();
    }

    @Test
    void countTreesPartOne() {
        String[][] input = reader.readMatrix(FILE_NAME);
        int numberOfColumns = input[0].length;
        Assertions.assertEquals(7, ToboganTrajectory.countTrees(input, numberOfColumns, 3, 1));
    }

    @Test
    void countTreesExtendedPartOne() {
        String[][] input = reader.readMatrix(FILE_NAME_OTHER);
        int numberOfColumns = input[0].length;
        Assertions.assertEquals(284, ToboganTrajectory.countTrees(input, numberOfColumns, 3, 1));
    }

    @Test
    void countTressAndMultiply() {
        String[][] input = reader.readMatrix(FILE_NAME);
        int numberOfColumns = input[0].length;
        List<Long> results = new ArrayList<>();
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 1, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 3, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 5, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 7, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 1, 2));
        Assertions.assertEquals(336, results.stream().mapToLong(x -> x).reduce(1, Math::multiplyExact));
    }

    @Test
    void countTreesAndMultiplyPartTwo() {
        String[][] input = reader.readMatrix(FILE_NAME_OTHER);
        int numberOfColumns = input[0].length;
        List<Long> results = new ArrayList<>();
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 1, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 3, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 5, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 7, 1));
        results.add(ToboganTrajectory.countTrees(input, numberOfColumns, 1, 2));
        Assertions.assertEquals(3510149120L, results.stream().mapToLong(x -> x).reduce(1, Math::multiplyExact));
    }
}