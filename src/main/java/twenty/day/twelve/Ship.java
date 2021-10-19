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
            case R -> changeDirection(InstructionType.R, instruction.getValue());
            case L -> changeDirection(InstructionType.L, instruction.getValue());
        }
    }

    //todo refactor this logic
    void changeDirection(InstructionType instructionType, Integer degrees) {
        final Integer orderChange = degrees / 90;
        final int result;
        if (InstructionType.R == instructionType) {
            result = (currentDirection.getOrder() + orderChange) % 4;
        } else {
            int difference = ((currentDirection.getOrder() - orderChange) % 4);
            result = difference < 0 ? 4 + difference : difference;
        }
        switch (result) {
            case 0 -> currentDirection = Direction.NORTH;
            case 1 -> currentDirection = Direction.EAST;
            case 2 -> currentDirection = Direction.SOUTH;
            case 3 -> currentDirection = Direction.WEST;
        }
    }

    Integer getManhattanDistance() {
        return Math.abs(location.get(Direction.NORTH) - location.get(Direction.SOUTH)) +
                Math.abs(location.get(Direction.EAST) - location.get(Direction.WEST));
    }
}
