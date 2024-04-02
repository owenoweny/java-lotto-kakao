import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
    private static final int RANDOM_BEGIN_INCLUDE_INDEX = 0;
    private static final int RANDOM_END_EXCLUDE_INDEX = 6;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        this.pickedNumbers = numbers;
    }

    private final List<LottoNumber> pickedNumbers;

    private Lotto() {
        this.pickedNumbers = randomNumbers();
    }

    public static Lotto from(List<Integer> pickedNumbers) {
        return new Lotto(parsePickedNumbers(pickedNumbers));
    }

    private static List<LottoNumber> parsePickedNumbers(List<Integer> pickedNumbers) {
        return pickedNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static Lotto issue() {
        return new Lotto();
    }

    public WinningResult compare(WinningLotto winningLotto) {
        return WinningResult.of(this, winningLotto);
    }

    private void validateDuplication(List<LottoNumber> pickedNumbers) {
        Set<LottoNumber> set = new HashSet<>(pickedNumbers);
        if (pickedNumbers.size() != set.size()) {
            throw new RuntimeException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return pickedNumbers.contains(number);
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(pickedNumbers);
    }

    public static List<LottoNumber> randomNumbers() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return parsePickedNumbers(CANDIDATE_NUMBERS.subList(RANDOM_BEGIN_INCLUDE_INDEX, RANDOM_END_EXCLUDE_INDEX));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(pickedNumbers, lotto.pickedNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickedNumbers);
    }
}
