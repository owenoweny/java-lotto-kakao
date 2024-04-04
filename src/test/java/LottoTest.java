import domains.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {
    @Test
    void 중복된_번호가_있는_경우_예외를_발생시킨다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
