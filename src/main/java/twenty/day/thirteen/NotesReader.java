package twenty.day.thirteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class NotesReader {

    private static final String OUT_OF_SERVICE_BUSES = "x";

    Integer getEarliestDepartureTime(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return Integer.valueOf(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    List<Integer> getBusesIds(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine(); //ignore first line
            return Arrays.stream(reader.readLine().split(","))
                    .filter(id -> !id.contains(OUT_OF_SERVICE_BUSES))
                    .map(Integer::valueOf)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
