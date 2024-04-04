import domains.Lotto;
import domains.LottoInputAmount;
import domains.LottoMachine;
import domains.Lottos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoMachineTest {
    @ParameterizedTest
    @CsvSource({"1000,1", "14500,14"})
    void 입력_금액과_맞는_개수의_로또를_발행한다(int money, int numberOfLottos) {
        Lottos issue = LottoMachine.issueAuto(new LottoInputAmount(money, 0).getNumberOfLottos());
        assertThat(issue.values()).hasSize(numberOfLottos);
    }

    @ParameterizedTest
    @MethodSource("provideLottoInputAmountAndExpectedLottos")
    void 입력_객체에_해당하는_로또를_발행한다(LottoInputAmount lottoInputAmount, List<Lotto> manualLottoNumbers) {
        Lottos lottos = LottoMachine.issue(lottoInputAmount, manualLottoNumbers);
        manualLottoNumbers.forEach(lotto -> assertThat(lottos.values()).contains(lotto));
    }

    static Stream<Arguments> provideLottoInputAmountAndExpectedLottos() {
        return Stream.of(
                Arguments.of(
                        new LottoInputAmount(10000, 2),
                        List.of(
                                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                                Lotto.from(List.of(1, 2, 3, 4, 5, 6))
                        )
                )
        );
    }
}
