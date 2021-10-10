package twenty.day.seven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WeightedGraphFactory implements BagGraphFactory {

    @Override
    public BagGraph createGraph(List<Edge> edges) {
        Map<String, List<Bag>> graph = new HashMap<>();
        for (Edge edge : edges) {
            if (graph.get(edge.source()) == null) {
                graph.putIfAbsent(edge.source(), new ArrayList<>(List.of(new Bag(edge.destination(), edge.quantity()))));
            } else {
                graph.get(edge.source()).add(new Bag(edge.destination(), edge.quantity()));
            }
        }
        return new WeightedBagGraph(graph);
    }
}
