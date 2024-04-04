import domains.LottoNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 46})
    void 범위를_벗어나는_경우_예외를_발생시킨다(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 1에서 45 사이여야 합니다.");
    }
}
