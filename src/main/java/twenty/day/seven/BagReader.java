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

        if (rules[4].contains("no")) {
            return Collections.emptyList();
        }

        List<Edge> bags = new ArrayList<>();
        String bagName = rules[0] + rules[1];

        for (String bag : bagRule.split(" bags contain ")[1].split(", ")) {
            final String[] bagDetails = bag.split(" ");
            bags.add(new Edge(bagName, bagDetails[1] + bagDetails[2], Integer.valueOf(bagDetails[0])));
        }
        return bags;
    }
}
