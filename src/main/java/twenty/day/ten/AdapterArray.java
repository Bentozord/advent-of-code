package twenty.day.ten;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class AdapterArray {


    private static final int ONE_JOLT_DIFFERENCE = 1;
    private static final int THREE_JOLT_DIFFERENCE = 3;

    Integer countJolts(List<Integer> joltRatings) {
        int oneJoltDifferences = 0;
        int threeJoltDifferences = 1; //build in adapter always has rating 3 higher

        final Integer firstRating = joltRatings.get(0);
        if (firstRating.equals(ONE_JOLT_DIFFERENCE)) {
            oneJoltDifferences++;
        } else {
            threeJoltDifferences++;
        }

        for (int i = 0; i < joltRatings.size() - 1; i++) {
            Integer difference = joltRatings.get(i + 1) - joltRatings.get(i);
            if (difference.equals(ONE_JOLT_DIFFERENCE)) {
                oneJoltDifferences++;
            } else if (difference.equals(THREE_JOLT_DIFFERENCE)) {
                threeJoltDifferences++;
            }
        }
        return oneJoltDifferences * threeJoltDifferences;
    }

    Long countPossibilities(List<Integer> joltRatings) {
        AtomicLong possibilities = new AtomicLong(1);
        AtomicInteger oneJoltStreaks = new AtomicInteger();
        joltRatings.stream()
                .reduce(0, (previous, next) -> {
                    reducePossibilities(previous, next, oneJoltStreaks, possibilities);
                    return next;
                });
        reducePossibilities(0, 0, oneJoltStreaks, possibilities);
        return possibilities.get();
    }

    private void reducePossibilities(Integer previous, Integer next, AtomicInteger oneJoltStreak, AtomicLong possibilities) {
        if (next - previous == 1) {
            oneJoltStreak.incrementAndGet();
        } else {
            int streak = oneJoltStreak.get();
            possibilities.set(possibilities.get() * countLazyCaterersSequence(streak - 1));
            oneJoltStreak.set(0);
        }
    }

    long countLazyCaterersSequence(int oneJoltStreaks) {
        return (long) ((Math.pow(oneJoltStreaks, 2) + oneJoltStreaks + 2) / 2);
    }
}
