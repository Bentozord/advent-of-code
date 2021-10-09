package twenty.day.seven;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
class WeightedGraph {

    private Map<String, List<Bag>> graph = new HashMap<>();

    WeightedGraph(List<Edge> edges) {
        for (Edge edge : edges) {
            if (graph.get(edge.source()) == null) {
                graph.putIfAbsent(edge.source(), new ArrayList<>(List.of(new Bag(edge.destination(), edge.weight()))));
            } else {
                graph.get(edge.source()).add(new Bag(edge.destination(), edge.weight()));
            }
        }
    }

    void printGraph() {
        graph.keySet().forEach(key -> System.out.printf("%s --> %s\t", key, graph.get(key)));
    }
}
