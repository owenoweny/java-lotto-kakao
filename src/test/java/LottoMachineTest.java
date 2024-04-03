import domains.LottoInputAmount;
import domains.LottoMachine;
import domains.Lottos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoMachineTest {
    @ParameterizedTest
    @CsvSource({"1000,1", "14500,14"})
    void 입력_금액과_맞는_개수의_로또를_발행한다(int money, int numberOfLottos) {
        Lottos issue = LottoMachine.issue(new LottoInputAmount(money));
        assertThat(issue.values()).hasSize(numberOfLottos);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, -1, 0})
    void 최소_금액보다_작은_경우_예외를_발생시킨다(int money) {
        assertThatThrownBy(() -> LottoMachine.issue(new LottoInputAmount(money)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 구매 금액은 1000 이상이어야합니다.");
    }
}
