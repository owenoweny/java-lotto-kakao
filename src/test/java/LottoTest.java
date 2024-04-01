import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Lotto 객체 관련 테스트")
public class LottoTest {
    @Test
    void Lotto_객체를_생성() {
        Lotto lotto = Lotto.issue();
        assertThatCode(() -> Objects.requireNonNull(lotto)).doesNotThrowAnyException();
    }

    @Test
    void Lotto에서_생성된_번호들의_크기가_6개() {
        Lotto lotto = Lotto.issue();
        assertThat(lotto.numbers().size()).isEqualTo(6);
    }
}
