package twenty.day.seven;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/***
 * Stop using lombok in the future :x
 */
@Data
@AllArgsConstructor
class WeightedBagGraph implements BagGraph {
    private Map<String, List<Bag>> graph;

    @Override
    public Collection<Bag> getBagsInside(String bagName) {
        return Optional.ofNullable(graph.get(bagName)).orElse(Collections.emptyList());
    }

    void printGraph() {
        graph.keySet().forEach(key -> System.out.printf("%s --> %s\t", key, graph.get(key)));
    }
}
