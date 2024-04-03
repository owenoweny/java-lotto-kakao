package domains;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    @Test
    void 보너스_번호와_로또_번호가_중복되는_경우_예외를_발생시킨다() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("보너스 번호와 로또 번호는 중복될 수 없습니다.");
    }
}