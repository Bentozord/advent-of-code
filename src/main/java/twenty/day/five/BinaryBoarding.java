package twenty.day.five;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BinaryBoarding {

    int getMissingId(List<String> boardingPasses) {
        List<Integer> integers = boardingPasses.stream()
                .map(passport -> getSeatId(specifyRow(passport.substring(0, 7)), specifyColumn(passport.substring(7))))
                .flatMapToInt(x -> IntStream.of(x.intValue())).boxed().collect(Collectors.toList());
        final Optional<Integer> min = integers.stream().min(Integer::compareTo);
        List<Integer> extraIntegers = IntStream.rangeClosed(1, min.get() - 1).boxed().collect(Collectors.toList());
        integers.addAll(extraIntegers);
        final int sum = integers.stream().mapToInt(x -> x).sum();
        final int n = integers.size() + 1;
        return n * (n + 1) / 2 - sum;
    }

    BigInteger getHighestSeatId(List<String> boardingPasses) {
        return boardingPasses.stream()
                .map(passport -> getSeatId(specifyRow(passport.substring(0, 7)), specifyColumn(passport.substring(7))))
                .max(BigInteger::compareTo)
                .orElse(BigInteger.ZERO);
    }

    BigInteger specifyRow(final String row) {
        StringBuilder builder = new StringBuilder();
        for (char c : row.toCharArray()) {
            builder.append(c == 'F' ? 0 : 1);
        }

        return new BigInteger(builder.toString(), 2);
    }

    BigInteger specifyColumn(final String column) {
        StringBuilder builder = new StringBuilder();
        for (char c : column.toCharArray()) {
            builder.append(c == 'L' ? 0 : 1);
        }

        return new BigInteger(builder.toString(), 2);
    }

    BigInteger getSeatId(final BigInteger row, final BigInteger column) {
        return row.multiply(BigInteger.valueOf(8L)).add(column);
    }
}
