package twenty.day.five;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigInteger;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BinaryBoardingTest {

    public static final String FILE_NAME = "src/main/resources/five/boardingPasses";
    BinaryBoarding binaryBoarding;
    BoardingReader reader;
    List<String> boardingPasses;

    @BeforeAll
    void setup() {
        binaryBoarding = new BinaryBoarding();
        reader = new BoardingReader();
        boardingPasses = reader.getBoardingPasses(FILE_NAME);
    }

    @Test
    void specifyRow() {
        Assertions.assertEquals(0, BigInteger.valueOf(44L).compareTo(binaryBoarding.specifyRow("FBFBBFF")));
    }

    @Test
    void specifyColumn() {
        Assertions.assertEquals(0, BigInteger.valueOf(5L).compareTo(binaryBoarding.specifyColumn("RLR")));
    }

    @Test
    void getSeatNumber() {
        Assertions.assertEquals(0, BigInteger.valueOf(357L)
                .compareTo(binaryBoarding.getSeatId(
                        binaryBoarding.specifyRow("FBFBBFF"),
                        binaryBoarding.specifyColumn("RLR"))
                ));
    }

    @Test
    void getHighestSeatId() {
        Assertions.assertEquals(0, BigInteger.valueOf(866L).compareTo(
                binaryBoarding.getHighestSeatId(boardingPasses)));
    }

    @Test
    void getMissingId() {
        Assertions.assertEquals(583, binaryBoarding.getMissingId(boardingPasses));
    }
}