import domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("domains.LottoMachine 관련 테스트")
public class LottoManagerTest {
    private LottoManager lottoManager;
    @BeforeEach
    void setUp() {
        lottoManager = new LottoManager(
                new Lottos(generateLottoData()),
                new WinningLotto(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7))
        );
    }

    @Test
    void 구매한_로또의_당첨_여부를_반환한다() {
        for (WinningResult value : WinningResult.values()) {
            assertThat(lottoManager.winningResults())
                    .filteredOn(value::equals)
                    .hasSize(1);
        }
    }

    @Test
    void 로또_당첨으로_얻은_금액을_반환한다() {
        assertThat(lottoManager.prize()).isEqualTo(2_031_555_000);
    }

    @Test
    void 로또_당첨으로_얻은_금액의_수익률을_반환한다() {
        assertThat(lottoManager.revenueRate()).isEqualTo(338592.5);
    }

    private List<Lotto> generateLottoData() {
        return List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 8)),
                Lotto.from(List.of(1, 2, 3, 4, 9, 8)),
                Lotto.from(List.of(4, 5, 6, 8, 9, 10)),
                Lotto.from(List.of(10, 11, 12, 13, 14, 15))
        );
    }
}
