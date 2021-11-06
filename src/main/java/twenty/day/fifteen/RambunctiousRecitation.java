package twenty.day.fifteen;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class RambunctiousRecitation {

    Integer playGame(Map<Integer, GameNumber> gameNumbersMap, Integer lastNumber, Integer providedTurn) {
        AtomicInteger turn = new AtomicInteger(gameNumbersMap.size() + 1);
        Integer currentNumber = lastNumber;
        while (turn.get() <= providedTurn) {
            final GameNumber gameNumber = gameNumbersMap.get(currentNumber);
            currentNumber = gameNumber.wasSpoken() ? gameNumber.lastTurn() - gameNumber.latestTurn() : 0;
            final GameNumber nextNumber = gameNumbersMap.get(currentNumber);
            if (nextNumber != null) {
                gameNumbersMap.put(currentNumber, new GameNumber(nextNumber.lastTurn(), turn.get(), true));
            } else {
                gameNumbersMap.put(currentNumber, new GameNumber(0, turn.get(), false));
            }
            turn.incrementAndGet();
        }
        return currentNumber;
    }

    Map<Integer, GameNumber> initializeStartingNumbers(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> new GameNumber(0, numbers.indexOf(key) + 1, false)
                ));
    }
}
