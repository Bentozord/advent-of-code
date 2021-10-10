package twenty.day.seven;

class HandyHaversacks {

    private static final String SHINY_GOLD = "shinygold";

    long countBagsThatCanContainShinyGoldBag(final WeightedBagGraph weightedBagGraph) {
        return weightedBagGraph.getGraph().entrySet()
                .stream()
                .filter(bag -> isShinyGoldBagInside(bag.getKey(), weightedBagGraph))
                .count();
    }

    boolean isShinyGoldBagInside(String bagLabel, BagGraph bags) {
        for (Bag nestedBag : bags.getBagsInside(bagLabel)) {
            if (nestedBag.name().contains(SHINY_GOLD)) {
                return true;
            } else {
                return isShinyGoldBagInside(nestedBag.name(), bags);
            }
        }
        return false;
    }
}
