package twenty.day.nine;

import java.util.Collections;
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
                Long result = numbers.get(i) + numbers.get(j);
                if (result.equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    Long getSumOfSmallestAndLargestNumbersFromRangeThatFailsCondition(List<Long> numbers, int preambleSize) {
        Long incorrectNumber = getFirstNumberThatFailsRuleForGivenPreamble(numbers, preambleSize);
        final int incorrectNumberIndex = numbers.indexOf(incorrectNumber);
        List<Integer> indexes = getFirstAndLastIndexThatFailsCondition(numbers, incorrectNumber, incorrectNumberIndex);
        final List<Long> invalidNumbers = numbers.subList(indexes.get(0), indexes.get(1));
        final Long minInvalidNumber = invalidNumbers.stream().min(Long::compareTo).orElse(0L);
        final Long maxInvalidNumber = invalidNumbers.stream().max(Long::compareTo).orElse(0L);
        return minInvalidNumber + maxInvalidNumber;
    }

    private List<Integer> getFirstAndLastIndexThatFailsCondition(List<Long> numbers, Long incorrectNumber, int incorrectNumberIndex) {
        for (int i = 0; i < incorrectNumberIndex; i++) {
            Long result = numbers.get(i);
            for (int j = i + 1; j < incorrectNumberIndex; j++) {
                result += numbers.get(j);
                if (result.equals(incorrectNumber)) {
                    return List.of(i, j);
                }
            }
        }
        return Collections.emptyList();
    }
}
