package domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<WinningResult> compare(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> WinningResult.of(lotto, winningLotto))
                .collect(Collectors.toList());
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }
}
