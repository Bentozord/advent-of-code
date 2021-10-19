package twenty.day.twelve;

enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    public Integer getOrder() {
        return order;
    }

    private final Integer order;

    Direction(Integer order) {
        this.order = order;
    }
}
