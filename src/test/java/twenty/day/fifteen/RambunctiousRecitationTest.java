package twenty.day.fifteen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class RambunctiousRecitationTest {

    @ParameterizedTest
    @MethodSource("provideGameNumbers")
    void shouldReturnProperNumberInProvidedTurnInGame(List<Integer> gameNumbers, Integer providedTurn, Integer result) {
        RambunctiousRecitation recitation = new RambunctiousRecitation();
        final Map<Integer, GameNumber> gameNumberMap = recitation.initializeStartingNumbers(gameNumbers);
        final Integer lastNumber = gameNumbers.get(gameNumbers.size() - 1);
        Assertions.assertEquals(result, recitation.playGame(gameNumberMap, lastNumber, providedTurn));
    }

    private static Stream<Arguments> provideGameNumbers() {
        return Stream.of(
                Arguments.of(List.of(0, 3, 6), 2020, 436),
                Arguments.of(List.of(1, 3, 2), 2020, 1),
                Arguments.of(List.of(2, 1, 3), 2020, 10),
                Arguments.of(List.of(1, 2, 3), 2020, 27),
                Arguments.of(List.of(2, 3, 1), 2020, 78),
                Arguments.of(List.of(3, 2, 1), 2020, 438),
                Arguments.of(List.of(3, 1, 2), 2020, 1836),
                Arguments.of(List.of(9, 3, 1, 0, 8, 4), 2020, 371),
                Arguments.of(List.of(0, 3, 6), 30000000, 175594),
                Arguments.of(List.of(1, 3, 2), 30000000, 2578),
                Arguments.of(List.of(2, 1, 3), 30000000, 3544142),
                Arguments.of(List.of(1, 2, 3), 30000000, 261214),
                Arguments.of(List.of(2, 3, 1), 30000000, 6895259),
                Arguments.of(List.of(3, 2, 1), 30000000, 18),
                Arguments.of(List.of(3, 1, 2), 30000000, 362),
                Arguments.of(List.of(9, 3, 1, 0, 8, 4), 30000000, 352)
        );
    }
}