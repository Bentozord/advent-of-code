package twenty.day.seven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class BagReader {

    List<Edge> readBagRules(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines()
                    .map(this::filterRuleToCreateEdges)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<Edge> filterRuleToCreateEdges(String bagRule) {
        final String[] rules = bagRule.split(" ");

        if (rules[4].equals("no")) {
            return Collections.emptyList();
        }

        List<Edge> edges = new ArrayList<>();
        String edgeSource = rules[0] + rules[1];

        edges.add(new Edge(edgeSource, rules[5] + rules[6], Integer.valueOf(rules[4])));
        if (rules.length > 8) {
            edges.add(new Edge(edgeSource, rules[9] + rules[10], Integer.valueOf(rules[8])));
        }
        return edges;
    }
}
