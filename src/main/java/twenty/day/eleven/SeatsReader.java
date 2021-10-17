package twenty.day.eleven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SeatsReader {

    private static final char EMPTY_SEAT = 'L';
    private static final char FLOOR = '.';

    Seat[][] readSeats(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            final int seatsRowsLength = getSeatsRowsLength(filename);
            final int seatsColumnsLength = getSeatsColumnsLength(filename);
            Seat[][] seatsLayout = new Seat[seatsRowsLength][seatsColumnsLength];
            int i = 0;
            while ((currentLine = reader.readLine()) != null) {
                final char[] seatsRow = currentLine.toCharArray();
                for (int j = 0; j < seatsRow.length; j++) {
                    seatsLayout[i][j] = mapCharToSeat(seatsRow[j]);
                }
                i++;
            }
            return seatsLayout;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Seat[0][0];
    }

    private int getSeatsRowsLength(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return Math.toIntExact(reader.lines().count());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getSeatsColumnsLength(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Seat mapCharToSeat(char c) {
        Seat result;
        if (c == EMPTY_SEAT) {
            result = Seat.EMPTY;
        } else if (c == FLOOR) {
            result = Seat.FLOOR;
        } else {
            result = Seat.OCCUPIED; //not happening while reading from input
        }
        return result;
    }
}
