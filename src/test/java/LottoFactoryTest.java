import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoFactory 관련 테스트")
public class LottoFactoryTest {
    @Test
    void LottoFactory는_주어진_금액을_입력받아_Lotto_리스트를_반환한다() {
        List<Lotto> lottos = LottoFactory.create(14500);
        assertThat(lottos.size()).isEqualTo(14);
    }

    @Test
    void LottoFactory는_로또_가격보다_적은_금액을_입력받으면_RuntimeException을_발생시킨다() {
        assertThatThrownBy(() -> LottoFactory.create(999)).isInstanceOf(IllegalArgumentException.class);
    }
}
