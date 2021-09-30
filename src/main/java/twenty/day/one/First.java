package twenty.day.one;

import java.util.List;

class First {

    public static Integer sumUpAndMultiply(final List<Integer> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = i; j < input.size(); j++) {
                if (i != j) {
                    if (input.get(i) + input.get(j) == 2020) {
                        return input.get(i) * input.get(j);
                    }
                }
            }
        }
        return 0;
    }
}
