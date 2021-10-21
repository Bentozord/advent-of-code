package twenty.day.thirteen;

import java.util.Collections;
import java.util.List;

class ShuttleSearch {

    Integer getSearchResult(final List<Integer> busIds, final Integer timestamp) {
        final List<Integer> idsMultipleClosestToTimestamp = busIds.stream()
                .map(id -> id - (timestamp % id))
                .toList();
        final int indexOfMinMinutesToWait = idsMultipleClosestToTimestamp.indexOf(Collections.min(idsMultipleClosestToTimestamp));
        return idsMultipleClosestToTimestamp.get(indexOfMinMinutesToWait) * busIds.get(indexOfMinMinutesToWait);
    }
}
