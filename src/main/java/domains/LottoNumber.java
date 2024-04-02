package domains;

import java.util.Objects;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    public int value() {
        return number;
    }

    private static void validateNumberRange(int number) {
        if (number > 45 || number < 1) {
            throw new RuntimeException("로또 번호는 1에서 45 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
