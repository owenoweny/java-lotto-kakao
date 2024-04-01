import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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

    private Lotto() {
        this.pickedNumbers = randomNumbers();
    }

    public Lotto(List<Integer> pickedNumbers) {
        validateNumberRange(pickedNumbers);
        validateDuplication(pickedNumbers);

        this.pickedNumbers = pickedNumbers;
    }

    public static Lotto issue() {
        return new Lotto();
    }

    private void validateDuplication(List<Integer> pickedNumbers) {
        Set<Integer> set = new HashSet<>(pickedNumbers);
        if (pickedNumbers.size() != set.size()) {
            throw new RuntimeException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private static void validateNumberRange(List<Integer> pickedNumbers) {
        pickedNumbers.forEach(number -> {
            if (number > 45 || number < 1) {
                throw new RuntimeException("로또 번호는 1에서 45 사이여야 합니다.");
            }
        });
    }

    public List<Integer> numbers() {
        return Collections.unmodifiableList(pickedNumbers);
    }

    private List<Integer> randomNumbers() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return CANDIDATE_NUMBERS.subList(RANDOM_BEGIN_INCLUDE_INDEX, RANDOM_END_EXCLUDE_INDEX);
    }
}
