package twenty.day.seven;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

class HandyHaversacks {

    private static final String SHINY_GOLD = "shinygold";

    long countBagsThatCanContainShinyGoldBag(final WeightedGraph weightedGraph) {
        int counter = 0;
        for (Map.Entry<String, List<Bag>> node : weightedGraph.getGraph().entrySet()) {
            counter += checkShinyGoldBagOccurrencesInNode(node.getKey(), weightedGraph.getGraph());
        }
        return counter;
    }

    long checkShinyGoldBagOccurrencesInNode(String nodeLabel, Map<String, List<Bag>> graph) {
        final List<Bag> bags = graph.get(nodeLabel);
        if (CollectionUtils.isNotEmpty(bags)) {
            for (Bag nestedBag : bags) {
                if (nestedBag.label().equals(SHINY_GOLD)) {
                    return 1;
                } else {
                    return checkShinyGoldBagOccurrencesInNode(nestedBag.label(), graph);
                }
            }
        }
        return 0;
    }
}
