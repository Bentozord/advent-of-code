package twenty.day.two;

import java.util.List;

class SecondDay {

    public static Integer tripleSumUpAndMultiply(List<Integer> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = i; j < input.size(); j++) {
                for (int k = j; k < input.size(); k++) {
                    if (i != j && j != k) {
                        if ((input.get(i) + input.get(j) + input.get(k)) == 2020) {
                            return input.get(i) * input.get(j) * input.get(k);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
