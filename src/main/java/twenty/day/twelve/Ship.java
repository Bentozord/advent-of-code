package twenty.day.twelve;

import java.util.Map;

class Ship {
    private Direction currentDirection;
    private Map<Direction, Integer> location;

    public Ship(Direction currentDirection, Map<Direction, Integer> location) {
        this.currentDirection = currentDirection;
        this.location = location;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Map<Direction, Integer> getLocation() {
        return location;
    }

    void performInstruction(Instruction instruction) {
        switch (instruction.getInstructionType()) {
            case N -> location.put(Direction.NORTH, location.get(Direction.NORTH) + instruction.getValue());
            case E -> location.put(Direction.EAST, location.get(Direction.EAST) + instruction.getValue());
            case S -> location.put(Direction.SOUTH, location.get(Direction.SOUTH) + instruction.getValue());
            case W -> location.put(Direction.WEST, location.get(Direction.WEST) + instruction.getValue());
            case F -> location.put(currentDirection, location.get(currentDirection) + instruction.getValue());
            case R -> changeDirection(1, instruction.getValue());
            case L -> changeDirection(-1, instruction.getValue());
        }
    }

    private void changeDirection(Integer directionSign, Integer degrees) {
        final Integer orderChange = directionSign * (degrees / 90) % 4;
        int difference = currentDirection.getOrder() + orderChange;
        int result = difference < 0 ? 4 + difference : difference;
        currentDirection = Direction.valueOfOrder(result % 4);
    }

    Integer getManhattanDistance() {
        return Math.abs(location.get(Direction.NORTH) - location.get(Direction.SOUTH)) +
                Math.abs(location.get(Direction.EAST) - location.get(Direction.WEST));
    }
}
