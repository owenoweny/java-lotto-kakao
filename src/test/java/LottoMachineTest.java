import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoMachine 관련 테스트")
public class LottoMachineTest {
    private LottoMachine lottoMachine;
    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(
                new Lottos(generateLottoData()),
                new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)
        );
    }

    @Test
    void 구매한_로또의_당첨_여부를_반환한다() {
        for (WinningResult value : WinningResult.values()) {
            assertThat(lottoMachine.winningResults())
                    .filteredOn(value::equals)
                    .hasSize(1);
        }
    }

    @Test
    void 로또_당첨으로_얻은_금액을_반환한다() {
        assertThat(lottoMachine.prize()).isEqualTo(2_031_555_000);
    }

    @Test
    void 로또_당첨으로_얻은_금액의_수익률을_반환한다() {
        assertThat(lottoMachine.revenueRate()).isEqualTo(338592.5);
    }

    private List<Lotto> generateLottoData() {
        return List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 9, 8)),
                new Lotto(List.of(4, 5, 6, 8, 9, 10)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );
    }
}
