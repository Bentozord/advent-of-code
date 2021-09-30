package twenty.day.one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import twenty.day.utils.NumbersReader;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstTest {

    private List<Integer> input;

    @BeforeAll
    void setup() {
        input = NumbersReader.readInput("C:\\opt\\code\\advent-of-code\\src\\test\\resources\\inputDayOne");
    }

    @Test
    void sumUpAndMultiply() {
        Assertions.assertEquals(138379, First.sumUpAndMultiply(input));
    }
}