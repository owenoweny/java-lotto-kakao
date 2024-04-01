import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("WinningLottoTest 관련 테스트")
public class WinningLottoTest {
    @Test
    void Lotto와_bonusNumber를_이용하여_WinningLotto를_생성() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        assertThat(winningLotto.lotto()).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(winningLotto.bonus()).isEqualTo(7);
    }
}
