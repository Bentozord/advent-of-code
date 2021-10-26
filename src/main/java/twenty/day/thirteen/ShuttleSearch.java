package twenty.day.thirteen;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class ShuttleSearch {

    private static final int FIRST_BUS_ID = 17;

    Integer getSearchResult(final List<Integer> busIds, final Integer timestamp) {
        final List<Integer> idsMultipleClosestToTimestamp = busIds.stream()
                .map(id -> id - (timestamp % id))
                .toList();
        final int indexOfMinMinutesToWait = idsMultipleClosestToTimestamp.indexOf(Collections.min(idsMultipleClosestToTimestamp));
        return idsMultipleClosestToTimestamp.get(indexOfMinMinutesToWait) * busIds.get(indexOfMinMinutesToWait);
    }

    Long getTimestamp(Map<Integer, Integer> schedules) {
        long time = FIRST_BUS_ID;
        long increment = FIRST_BUS_ID;
        for (Map.Entry<Integer, Integer> entry : schedules.entrySet()) {
            if (entry.getValue() != 0) {
                while ((time + entry.getValue()) % entry.getKey() != 0) {
                    time += increment;
                }
                increment *= entry.getKey();
            }
        }
        return time;
    }
}
