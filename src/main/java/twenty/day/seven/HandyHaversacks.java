package twenty.day.seven;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

class HandyHaversacks {

    private static final String SHINY_GOLD = "shinygold";

    long countBagsThatCanContainShinyGoldBag(final Graph weightedGraph) {
        return weightedGraph.getGraph().entrySet()
                .stream()
                .filter(bag -> checkShinyGoldBagOccurrencesInNode(bag.getKey(), weightedGraph.getGraph()))
                .count();
    }

    boolean checkShinyGoldBagOccurrencesInNode(String nodeLabel, Map<String, List<Bag>> graph) {
        final List<Bag> bags = graph.get(nodeLabel);
        if (CollectionUtils.isNotEmpty(bags)) {
            for (Bag nestedBag : bags) {
                if (nestedBag.label().equals(SHINY_GOLD)) {
                    return true;
                } else {
                    return checkShinyGoldBagOccurrencesInNode(nestedBag.label(), graph);
                }
            }
        }
        return false;
    }
}
