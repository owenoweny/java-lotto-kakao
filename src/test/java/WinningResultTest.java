import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    @ParameterizedTest
    @MethodSource("provideLottosAndWinningResult")
    void 두_개의_로또의_당첨_여부를_계산한다(Lotto bought, WinningLotto winningLotto, WinningResult expectedResult) {
        WinningResult actualResult = WinningResult.checkResult(bought, winningLotto);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    static Stream<Arguments> provideLottosAndWinningResult() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7),
                        WinningResult.FIRST
                ),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                        new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7),
                        WinningResult.SECOND
                ),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                        new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7),
                        WinningResult.THIRD
                ),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 9, 8)),
                        new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7),
                        WinningResult.FORTH
                ),
                Arguments.of(new Lotto(List.of(4, 5, 6, 8, 9, 10)),
                        new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7),
                        WinningResult.FIFTH
                )
        );
    }
}
