package twenty.day.seven;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/***
 * Stop using lombok in the future :x
 */
@Data
@AllArgsConstructor
class WeightedGraph implements Graph {
    private Map<String, List<Bag>> graph;

    void printGraph() {
        graph.keySet().forEach(key -> System.out.printf("%s --> %s\t", key, graph.get(key)));
    }
}
