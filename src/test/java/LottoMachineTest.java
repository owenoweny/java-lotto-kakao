import domains.LottoInputAmount;
import domains.LottoMachine;
import domains.Lottos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoMachineTest {
    @ParameterizedTest
    @CsvSource({"1000,1", "14500,14"})
    void 입력_금액과_맞는_개수의_로또를_발행한다(int money, int numberOfLottos) {
        Lottos issue = LottoMachine.issueAuto(new LottoInputAmount(money).getNumberOfLottos());
        assertThat(issue.values()).hasSize(numberOfLottos);
    }
}
