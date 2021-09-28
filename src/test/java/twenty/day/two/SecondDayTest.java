package twenty.day.two;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import twenty.day.utils.NumbersReader;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SecondDayTest {

    List<Integer> input;

    @BeforeAll
    void setup() {
        input = NumbersReader.readInput("C:\\opt\\code\\advent-of-code\\src\\test\\resources\\inputDayTwo");
    }

    @Test
    void tripleSumUpAndMultiply() {
        Assertions.assertEquals(85491920, SecondDay.tripleSumUpAndMultiply(input));
    }
}