package domains;

import utils.LottoUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        this.lottoNumbers = numbers;
    }

    public static Lotto from(List<Integer> pickedNumbers) {
        return new Lotto(LottoUtils.parsePickedNumbers(pickedNumbers));
    }

    public List<Integer> values() {
        return lottoNumbers.stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList());
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
