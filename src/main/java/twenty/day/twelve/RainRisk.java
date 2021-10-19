package twenty.day.twelve;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class RainRisk {

    Integer navigateAndGetDistance(List<Instruction> instructions) {
        Ship ship = new Ship(Direction.EAST, initializeLocationMap(0, 0, 0, 0));
        instructions.forEach(ship::performInstruction);
        return ship.getManhattanDistance();
    }

    Integer navigateWaypointAndShip(List<Instruction> instructions) {
        Map<Direction, Integer> waypoint = initializeLocationMap(1, 10, 0, 0);
        Ship ship = new Ship(Direction.EAST, initializeLocationMap(0, 0, 0, 0));
        instructions.forEach(instruction -> performInstructionIncludingWaypoint(instruction, waypoint, ship.getLocation()));
        return ship.getManhattanDistance();
    }

    void performInstructionIncludingWaypoint(Instruction instruction, Map<Direction, Integer> waypoint, Map<Direction, Integer> location) {
        switch (instruction.getInstructionType()) {
            case N -> countRelativeWaypointLocation(waypoint, Direction.NORTH, Direction.SOUTH, instruction.getValue());
            case E -> countRelativeWaypointLocation(waypoint, Direction.EAST, Direction.WEST, instruction.getValue());
            case S -> countRelativeWaypointLocation(waypoint, Direction.SOUTH, Direction.NORTH, instruction.getValue());
            case W -> countRelativeWaypointLocation(waypoint, Direction.WEST, Direction.EAST, instruction.getValue());
            case F -> waypoint.forEach((direction, value) -> location.put(direction, location.get(direction) + (value * instruction.getValue())));
            case R -> changeWaypointDirections(waypoint, 1, instruction.getValue());
            case L -> changeWaypointDirections(waypoint, -1, instruction.getValue());
        }
    }

    void countRelativeWaypointLocation(Map<Direction, Integer> waypoint, Direction instructionDirection, Direction oppositeDirection, Integer instructionValue) {
        if (waypoint.get(oppositeDirection) < instructionValue) {
            waypoint.put(instructionDirection, waypoint.get(instructionDirection) + Math.abs(waypoint.get(oppositeDirection) - instructionValue));
            waypoint.put(oppositeDirection, 0);
        } else {
            waypoint.put(oppositeDirection, waypoint.get(oppositeDirection) - instructionValue);
        }
    }

    void changeWaypointDirections(Map<Direction, Integer> waypoint, Integer directionSign, Integer degrees) {
        final Integer directionChange = directionSign * ((degrees / 90) % 4);
        Map<Direction, Integer> newWaypoint = initializeLocationMap(0, 0, 0, 0);
        waypoint.forEach((direction, value) -> {
            if (value != 0) {
                int difference = direction.getOrder() + directionChange;
                int result = difference < 0 ? 4 + difference : difference;
                newWaypoint.put(Direction.valueOfOrder(result % 4), value);
            }
        });
        waypoint.putAll(newWaypoint);
    }

    private Map<Direction, Integer> initializeLocationMap(Integer n, Integer e, Integer s, Integer w) {
        Map<Direction, Integer> beginningLocation = new EnumMap<>(Direction.class);
        beginningLocation.put(Direction.NORTH, n);
        beginningLocation.put(Direction.EAST, e);
        beginningLocation.put(Direction.SOUTH, s);
        beginningLocation.put(Direction.WEST, w);
        return beginningLocation;
    }
}
