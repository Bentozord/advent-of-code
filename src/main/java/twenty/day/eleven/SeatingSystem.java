package twenty.day.eleven;

import java.util.Arrays;

class SeatingSystem {

    int returnOccupiedSeatsAmountWhenStateDoesntChange(Seat[][] seatsLayout) {
        Seat[][] previousSeatLayout = seatsLayout;
        Seat[][] currentSeatLayout = applySeatsRules(previousSeatLayout);
        while (!Arrays.deepEquals(previousSeatLayout, currentSeatLayout)) {
            previousSeatLayout = currentSeatLayout;
            currentSeatLayout = applySeatsRules(previousSeatLayout);
        }
        return countOccupiedSeats(previousSeatLayout);
    }

    private int countOccupiedSeats(Seat[][] seatsLayout) {
        int occupiedSeats = 0;
        for (int i = 0; i < seatsLayout.length; i++) {
            for (int j = 0; j < seatsLayout[0].length; j++) {
                if (Seat.OCCUPIED == seatsLayout[i][j]) {
                    occupiedSeats++;
                }
            }
        }
        return occupiedSeats;
    }

    private Seat[][] applySeatsRules(Seat[][] source) {
        Seat[][] seatsLayout = Arrays.stream(source).map(Seat[]::clone).toArray(Seat[][]::new);
        for (int i = 0; i < seatsLayout.length; i++) {
            for (int j = 0; j < seatsLayout[0].length; j++) {
                applyFirstRowSeatsRulesLogic(seatsLayout, source, i, j);
                applyLastRowSeatsRulesLogic(seatsLayout, source, i, j);
                applyFirstColumnSeatsRulesLogic(seatsLayout, source, i, j);
                applyLastColumnSeatsRulesLogic(seatsLayout, source, i, j);
                applyRestOfSeatsRulesLogic(seatsLayout, source, i, j);
            }
        }
        return seatsLayout;
    }

    private void applyFirstRowSeatsRulesLogic(Seat[][] seatsLayout, Seat[][] source, int i, int j) {
        if (i == 0) {
            if (j == 0) {
                if (Seat.EMPTY == source[i][j]) {
                    if (checkIfSeatEmptyOrFloor(source[i][j + 1])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j + 1])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j])) {
                        seatsLayout[i][j] = Seat.OCCUPIED;
                    }
                }
            } else if (j == source[0].length - 1) {
                if (Seat.EMPTY == source[i][j]) {
                    if (checkIfSeatEmptyOrFloor(source[i][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j])) {
                        seatsLayout[i][j] = Seat.OCCUPIED;
                    }
                }
            } else {
                if (Seat.EMPTY == source[i][j]) {
                    if (checkIfSeatEmptyOrFloor(source[i][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j])
                            && checkIfSeatEmptyOrFloor(source[i + 1][j + 1])
                            && checkIfSeatEmptyOrFloor(source[i][j + 1])) {
                        seatsLayout[i][j] = Seat.OCCUPIED;
                    }
                } else if (Seat.OCCUPIED == source[i][j]) {
                    int counter = 0;
                    if (Seat.OCCUPIED == source[i][j - 1]) counter++;
                    if (Seat.OCCUPIED == source[i + 1][j - 1]) counter++;
                    if (Seat.OCCUPIED == source[i + 1][j]) counter++;
                    if (Seat.OCCUPIED == source[i + 1][j + 1]) counter++;
                    if (Seat.OCCUPIED == source[i][j + 1]) counter++;

                    if (counter >= 4) seatsLayout[i][j] = Seat.EMPTY;
                }
            }
        }
    }

    private void applyLastRowSeatsRulesLogic(Seat[][] seatsLayout, Seat[][] source, int i, int j) {
        if (i == source.length - 1) {
            if (j == 0) {
                if (Seat.EMPTY == source[i][j]) {
                    if (checkIfSeatEmptyOrFloor(source[i][j + 1])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j + 1])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j])) {
                        seatsLayout[i][j] = Seat.OCCUPIED;
                    }
                }
            } else if (j == source[0].length - 1) {
                if (Seat.EMPTY == source[i][j]) {
                    if (checkIfSeatEmptyOrFloor(source[i][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j])) {
                        seatsLayout[i][j] = Seat.OCCUPIED;
                    }
                }
            } else {
                if (Seat.EMPTY == source[i][j]) {
                    if (checkIfSeatEmptyOrFloor(source[i][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j - 1])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j])
                            && checkIfSeatEmptyOrFloor(source[i - 1][j + 1])
                            && checkIfSeatEmptyOrFloor(source[i][j + 1])) {
                        seatsLayout[i][j] = Seat.OCCUPIED;
                    }
                } else if (Seat.OCCUPIED == source[i][j]) {
                    int counter = 0;
                    if (Seat.OCCUPIED == source[i][j - 1]) counter++;
                    if (Seat.OCCUPIED == source[i - 1][j - 1]) counter++;
                    if (Seat.OCCUPIED == source[i - 1][j]) counter++;
                    if (Seat.OCCUPIED == source[i - 1][j + 1]) counter++;
                    if (Seat.OCCUPIED == source[i][j + 1]) counter++;

                    if (counter >= 4) seatsLayout[i][j] = Seat.EMPTY;
                }
            }
        }
    }

    private void applyFirstColumnSeatsRulesLogic(Seat[][] seatsLayout, Seat[][] source, int i, int j) {
        if (i > 0 && i < source.length - 1 && j == 0) {
            if (Seat.EMPTY == source[i][j]) {
                if (checkIfSeatEmptyOrFloor(source[i - 1][j])
                        && checkIfSeatEmptyOrFloor(source[i - 1][j + 1])
                        && checkIfSeatEmptyOrFloor(source[i][j + 1])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j + 1])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j])) {
                    seatsLayout[i][j] = Seat.OCCUPIED;
                }
            } else if (Seat.OCCUPIED == source[i][j]) {
                int counter = 0;
                if (Seat.OCCUPIED == source[i - 1][j]) counter++;
                if (Seat.OCCUPIED == source[i - 1][j + 1]) counter++;
                if (Seat.OCCUPIED == source[i][j + 1]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j + 1]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j]) counter++;

                if (counter >= 4) seatsLayout[i][j] = Seat.EMPTY;
            }
        }
    }

    private void applyLastColumnSeatsRulesLogic(Seat[][] seatsLayout, Seat[][] source, int i, int j) {
        if (i > 0 && i < source.length - 1 && j == source[0].length - 1) {
            if (Seat.EMPTY == source[i][j]) {
                if (checkIfSeatEmptyOrFloor(source[i - 1][j])
                        && checkIfSeatEmptyOrFloor(source[i - 1][j - 1])
                        && checkIfSeatEmptyOrFloor(source[i][j - 1])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j - 1])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j])) {
                    seatsLayout[i][j] = Seat.OCCUPIED;
                }
            } else if (Seat.OCCUPIED == source[i][j]) {
                int counter = 0;
                if (Seat.OCCUPIED == source[i - 1][j]) counter++;
                if (Seat.OCCUPIED == source[i - 1][j - 1]) counter++;
                if (Seat.OCCUPIED == source[i][j - 1]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j - 1]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j]) counter++;

                if (counter >= 4) seatsLayout[i][j] = Seat.EMPTY;
            }
        }
    }

    private void applyRestOfSeatsRulesLogic(Seat[][] seatsLayout, Seat[][] source, int i, int j) {
        if (i > 0 && i < source.length - 1 && j > 0 && j < source[0].length - 1) {
            if (Seat.EMPTY == source[i][j]) {
                if (checkIfSeatEmptyOrFloor(source[i - 1][j - 1])
                        && checkIfSeatEmptyOrFloor(source[i - 1][j])
                        && checkIfSeatEmptyOrFloor(source[i - 1][j + 1])
                        && checkIfSeatEmptyOrFloor(source[i][j + 1])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j + 1])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j])
                        && checkIfSeatEmptyOrFloor(source[i + 1][j - 1])
                        && checkIfSeatEmptyOrFloor(source[i][j - 1])) {
                    seatsLayout[i][j] = Seat.OCCUPIED;
                }
            } else if (Seat.OCCUPIED == source[i][j]) {
                int counter = 0;
                if (Seat.OCCUPIED == source[i - 1][j - 1]) counter++;
                if (Seat.OCCUPIED == source[i - 1][j]) counter++;
                if (Seat.OCCUPIED == source[i - 1][j + 1]) counter++;
                if (Seat.OCCUPIED == source[i][j + 1]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j + 1]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j]) counter++;
                if (Seat.OCCUPIED == source[i + 1][j - 1]) counter++;
                if (Seat.OCCUPIED == source[i][j - 1]) counter++;

                if (counter >= 4) seatsLayout[i][j] = Seat.EMPTY;
            }
        }
    }

    private boolean checkIfSeatEmptyOrFloor(Seat seat) {
        return Seat.EMPTY == seat || Seat.FLOOR == seat;
    }

    private void printSeatsLayout(Seat[][] seatsLayout) {
        for (int i = 0; i < seatsLayout.length; i++) {
            System.out.println();
            for (int j = 0; j < seatsLayout[0].length; j++) {
                String c;
                if (Seat.OCCUPIED == seatsLayout[i][j]) {
                    c = "#";
                } else if (Seat.EMPTY == seatsLayout[i][j]) {
                    c = "L";
                } else {
                    c = ".";
                }
                System.out.printf(c);
            }
        }
    }
}
