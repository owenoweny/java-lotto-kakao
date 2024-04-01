import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Lottos 관련 테스트")
public class LottosTest {
    @ParameterizedTest
    @CsvSource(value = {"0", "-1"})
    void 발급받으려는_로또_장수가_양수가_아닌_경우_RuntimeException을_던진다(int numberOfLotto) {
        assertThatThrownBy(() -> new Lottos(numberOfLotto)).isInstanceOf(RuntimeException.class)
                .hasMessage("로또는 1장 이상 발급되어야 합니다.");
    }
}
