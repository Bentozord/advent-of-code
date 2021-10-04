package twenty.day.three;

class ToboganTrajectory {

    static long countTrees(final String[][] matrix, final int numberOfColumns, final int stepsRight, final int stepsDown) {
        long counter = 0;
        int j = 0;
        for (int i = stepsDown; i < matrix.length; i += stepsDown) {
            j = (j + stepsRight) % numberOfColumns;
            if (matrix[i][j].equals("#")) {
                counter++;
            }
        }
        return counter;
    }
}
