import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoMachine 관련 테스트")
public class LottoMachineTest {
    @Test
    void LottoMachine은_입력한_금액에_맞는_로또_장수를_반환한다() {
        LottoMachine machine = new LottoMachine(14500);
        assertThat(machine.boughtLottos().values().size()).isEqualTo(14);
    }
}
