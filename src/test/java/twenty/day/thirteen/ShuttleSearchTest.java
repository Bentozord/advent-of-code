package twenty.day.thirteen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShuttleSearchTest {

    private static final String FILE_NAME = "src/main/resources/thirteen/notes";
    private static final String FILE_NAME_AOC = "src/main/resources/thirteen/notesAOC";

    NotesReader reader;
    List<Integer> busesIds;
    List<Integer> busesIdsAOC;
    Integer timestamp;
    Integer timestampAOC;
    ShuttleSearch shuttleSearch;

    @BeforeAll
    void setup() {
        reader = new NotesReader();
        busesIds = reader.getBusesIds(FILE_NAME);
        timestamp = reader.getEarliestDepartureTime(FILE_NAME);
        busesIdsAOC = reader.getBusesIds(FILE_NAME_AOC);
        timestampAOC = reader.getEarliestDepartureTime(FILE_NAME_AOC);
        shuttleSearch = new ShuttleSearch();
    }

    @Test
    void getSearchResultForBasicNotes() {
        Assertions.assertEquals(295, shuttleSearch.getSearchResult(busesIds, timestamp));
    }

    @Test
    void getSearchResultForAOCNotes() {
        Assertions.assertEquals(138, shuttleSearch.getSearchResult(busesIdsAOC, timestampAOC));
    }

    @Test
    void getLowestTimestampFulfillingShuttleCondition() {
        Assertions.assertEquals(226845233210288L, shuttleSearch.getTimestamp(reader.getIdsWithOffsets(FILE_NAME_AOC)));
    }
}