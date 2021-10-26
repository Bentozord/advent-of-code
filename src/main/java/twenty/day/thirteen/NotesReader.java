package twenty.day.thirteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    Map<Integer, Integer> getIdsWithOffsets(String filename) {
        List<Integer> busesIds = getBusesIds(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine(); //ignore first line
            final List<String> notes = Arrays.stream(reader.readLine().split(","))
                    .toList();
            final List<Integer> offsets = busesIds.stream()
                    .map(String::valueOf)
                    .map(notes::indexOf)
                    .toList();
            return IntStream.range(0, busesIds.size())
                    .boxed()
                    .collect(Collectors.toMap(busesIds::get, offsets::get));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }


}
