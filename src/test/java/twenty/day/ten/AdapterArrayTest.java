package twenty.day.ten;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdapterArrayTest {

    public static final String FILE_NAME = "src/main/resources/ten/ratings";
    public static final String FILE_NAME_AOC = "src/main/resources/ten/ratingsAOC";
    JoltReader reader;
    List<Integer> joltRatings;
    List<Integer> joltRatingsAOC;
    AdapterArray adapterArray;

    @BeforeAll
    void setup() {
        reader = new JoltReader();
        joltRatings = reader.getJoltRatings(FILE_NAME);
        joltRatingsAOC = reader.getJoltRatings(FILE_NAME_AOC);
        adapterArray = new AdapterArray();
    }

    @Test
    void jointDifferenceMultiplyShouldReturnProperValueForJointRatings() {
        Assertions.assertEquals(35, adapterArray.countJolts(joltRatings));
    }

    @Test
    void jointDifferenceMultiplyShouldReturnProperValueForExtendedJointRatings() {
        Assertions.assertEquals(2070, adapterArray.countJolts(joltRatingsAOC));
    }

    @Test
    void shouldReturnValidNumberOfPossibilities() {
        Assertions.assertEquals(24179327893504L, adapterArray.countPossibilities(joltRatingsAOC));
    }
}