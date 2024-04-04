package domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos merge(Lottos lottos1, Lottos lottos2) {
        List<Lotto> merged = Stream.concat(lottos1.values().stream(), lottos2.values().stream())
                .collect(Collectors.toList());

        return new Lottos(merged);
    }

    public List<WinningResult> checkWinning(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> WinningResult.of(lotto, winningLotto))
                .collect(Collectors.toList());
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }
}
