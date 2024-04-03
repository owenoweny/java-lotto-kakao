package domains;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    @Test
    void 포함된_로또들의_유효한_당첨_결과_리스트를_반환한다() {
        Lottos lottos = new Lottos(
                List.of(
                        Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                        Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                        Lotto.from(List.of(1, 2, 3, 4, 8, 9)),
                        Lotto.from(List.of(10, 11, 12, 13, 14, 15))
                )
        );

        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        List<WinningResult> winningResults = lottos.checkWinning(new WinningLotto(winningLotto, bonusNumber));

        assertThat(winningResults)
                .filteredOn(WinningResult.SECOND::equals)
                .hasSize(2);
        assertThat(winningResults)
                .filteredOn(WinningResult.FIRST::equals)
                .hasSize(1);
        assertThat(winningResults)
                .filteredOn(WinningResult.FORTH::equals)
                .hasSize(1);
        assertThat(winningResults)
                .filteredOn(WinningResult.NONE::equals)
                .hasSize(1);

        assertThat(winningResults)
                .hasSize(5)
                .doesNotContain(WinningResult.THIRD)
                .doesNotContain(WinningResult.FIFTH);
    }
}