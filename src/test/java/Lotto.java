import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
    private static final int RANDOM_BEGIN_INCLUDE_INDEX = 0;
    private static final int RANDOM_END_EXCLUDE_INDEX = 6;

    private final List<Integer> pickedNumbers;

    public static Lotto issue() {
        return new Lotto();
    }

    private Lotto() {
        this.pickedNumbers = randomNumbers();
    }

    public List<Integer> numbers() {
        return Collections.unmodifiableList(pickedNumbers);
    }

    private List<Integer> randomNumbers() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return CANDIDATE_NUMBERS.subList(RANDOM_BEGIN_INCLUDE_INDEX, RANDOM_END_EXCLUDE_INDEX);
    }
}
