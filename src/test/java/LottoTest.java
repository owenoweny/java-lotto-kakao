import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Lotto 객체 관련 테스트")
public class LottoTest {
    @Test
    void Lotto_객체를_생성한다() {
        Lotto lotto = Lotto.issue();
        assertThatCode(() -> Objects.requireNonNull(lotto)).doesNotThrowAnyException();
    }

    @Test
    void Lotto에서_생성된_번호들의_크기가_6개이다() {
        Lotto lotto = Lotto.issue();
        assertThat(lotto.numbers().size()).isEqualTo(6);
    }

    @Test
    void 번호_범위를_벗어나면_예외를_발생시킨다() {
        assertThatThrownBy(() -> Lotto.from(List.of(-1, 1, 2, 3, 4, 5)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 1에서 45 사이여야 합니다.");
    }

    @Test
    void 중복된_번호가_있는_경우_예외를_발생시킨다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
