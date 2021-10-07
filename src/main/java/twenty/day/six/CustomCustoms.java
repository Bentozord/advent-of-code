package twenty.day.six;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

class CustomCustoms {

    Integer countGroupAnswers(List<Set<String>> answers) {
        AtomicReference<Integer> counter = new AtomicReference<>(0);
        answers.forEach(groupAnswers -> counter.updateAndGet(v -> v + groupAnswers.size()));
        return counter.get();
    }
}
