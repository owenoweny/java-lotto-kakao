import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoMachine 관련 테스트")
public class LottoMachineTest {
    @Test
    void 로또_당첨으로_얻은_금액의을_반환한다() {
        LottoMachine machine = new LottoMachine(
                new Lottos(generateLottoData()),
                new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)
        );
        assertThat(machine.prize()).isEqualTo(2_031_555_000);
    }

    @Test
    void 로또_당첨으로_얻은_금액의_수익률을_반환한다() {
        LottoMachine machine = new LottoMachine(
                new Lottos(generateLottoData()),
                new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)
        );
        assertThat(machine.revenueRate()).isEqualTo(406311.0);
    }

    private List<Lotto> generateLottoData() {
        return List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 9, 8)),
                new Lotto(List.of(4, 5, 6, 8, 9, 10))
        );
    }
}
