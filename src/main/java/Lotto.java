import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
    private static final int RANDOM_BEGIN_INCLUDE_INDEX = 0;
    private static final int RANDOM_END_EXCLUDE_INDEX = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        this.lottoNumbers = numbers;
    }

    private Lotto() {
        this.lottoNumbers = randomNumbers();
    }

    public static Lotto from(List<Integer> pickedNumbers) {
        return new Lotto(LottoUtils.parsePickedNumbers(pickedNumbers));
    }

    public static List<LottoNumber> randomNumbers() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return LottoUtils.parsePickedNumbers(CANDIDATE_NUMBERS.subList(RANDOM_BEGIN_INCLUDE_INDEX, RANDOM_END_EXCLUDE_INDEX));
    }

    public List<Integer> values() {
        return lottoNumbers.stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList());
    }

    public WinningResult compare(WinningLotto winningLotto) {
        return WinningResult.of(this, winningLotto);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    private void validateDuplication(List<LottoNumber> pickedNumbers) {
        Set<LottoNumber> set = new HashSet<>(pickedNumbers);
        if (pickedNumbers.size() != set.size()) {
            throw new RuntimeException("로또 번호는 중복될 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
