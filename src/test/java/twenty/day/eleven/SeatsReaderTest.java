package twenty.day.eleven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SeatsReaderTest {

    private static final String FILE_NAME = "src/main/resources/eleven/seatsLayout";
    private static final String FILE_NAME_AOC = "src/main/resources/eleven/seatsLayoutAOC";
    SeatsReader reader;
    Seat[][] seatsLayout;
    Seat[][] seatsLayoutAOC;
    SeatingSystem seatingSystem;

    @BeforeAll
    void setup() {
        reader = new SeatsReader();
        seatsLayout = reader.readSeats(FILE_NAME);
        seatsLayoutAOC = reader.readSeats(FILE_NAME_AOC);
        seatingSystem = new SeatingSystem();
    }

    @Test
    void shouldReadSeatsLayout() {
        Assertions.assertNotNull(seatsLayout);
    }

    @Test
    void shouldReturnProperAmountOfOccupiedSeats() {
        Assertions.assertEquals(37, seatingSystem.returnOccupiedSeatsAmountWhenStateDoesntChange(seatsLayout));
    }

    @Test
    void shouldReturnProperAmountOfOccupiedSeatsInLargerLayout() {
        Assertions.assertEquals(2183, seatingSystem.returnOccupiedSeatsAmountWhenStateDoesntChange(seatsLayoutAOC));
    }

}