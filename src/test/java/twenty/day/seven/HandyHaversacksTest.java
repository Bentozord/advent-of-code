package twenty.day.seven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HandyHaversacksTest {

    private static final String FILE_NAME = "src/main/resources/seven/bagsRules";
    private static final String FILE_NAME_AOC = "src/main/resources/seven/bagsRulesAOC";

    BagReader reader;
    HandyHaversacks handyHaversacks;
    GraphFactory factory;

    @BeforeAll
    void setup() {
        reader = new BagReader();
        handyHaversacks = new HandyHaversacks();
        factory = new WeightedGraphFactory();
    }

    @Test
    void countBagsShouldReturnProperNumber() {
        List<Edge> edges = reader.readBagRules(FILE_NAME);
        Graph graph = factory.createGraph(edges);
        Assertions.assertEquals(4, handyHaversacks.countBagsThatCanContainShinyGoldBag(graph));
    }

    @Test
    void countBagsRulesAOCShouldReturnProperShinyGoldOccurrences() {
        List<Edge> edgesPartOne = reader.readBagRules(FILE_NAME_AOC);
        Graph graph = factory.createGraph(edgesPartOne);
        Assertions.assertEquals(4, handyHaversacks.countBagsThatCanContainShinyGoldBag(graph));
    }
}