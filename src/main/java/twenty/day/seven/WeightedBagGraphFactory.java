package twenty.day.seven;

import java.util.List;
import java.util.stream.Collectors;

class WeightedBagGraphFactory implements BagGraphFactory {

    @Override
    public BagGraph createGraph(List<Edge> edges) {
        return new WeightedBagGraph(
                edges.stream()
                        .collect(Collectors.groupingBy(Edge::source,
                                Collectors.mapping(this::edgeToBag, Collectors.toList())))
        );
    }

    private Bag edgeToBag(Edge edge) {
        return new Bag(edge.destination(), edge.quantity());
    }
}
