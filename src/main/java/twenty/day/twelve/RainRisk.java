package twenty.day.twelve;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class RainRisk {

    Integer navigateAndGetDistance(List<Instruction> instructionList) {
        Map<Direction, Integer> beginningLocation = new EnumMap<>(Direction.class);
        beginningLocation.put(Direction.NORTH, 0);
        beginningLocation.put(Direction.EAST, 0);
        beginningLocation.put(Direction.SOUTH, 0);
        beginningLocation.put(Direction.WEST, 0);
        Ship ship = new Ship(Direction.EAST, beginningLocation);
        instructionList.forEach(ship::performInstruction);
        return ship.getManhattanDistance();
    }
}
