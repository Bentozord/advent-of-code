package twenty.day.nine;

import java.util.List;

class EncodingError {

    Long getFirstNumberThatFailsRuleForGivenPreamble(List<Long> numbers, int preambleSize) {
        for (int i = 0, j = preambleSize; j < numbers.size() - 1; i++, j++) {
            if (!checkPropertyConditionForRangeOfNumbers(numbers, i, j, numbers.get(j))) {
                return numbers.get(j);
            }
        }
        return 0L;
    }

    private boolean checkPropertyConditionForRangeOfNumbers(List<Long> numbers, int beginIndex, int endIndex, Long number) {
        for (int i = beginIndex; i < endIndex; i++) {
            for (int j = beginIndex + 1; j < endIndex; j++) {
                if (numbers.get(i) + numbers.get(j) == number) {
                    return true;
                }
            }
        }
        return false;
    }
}
