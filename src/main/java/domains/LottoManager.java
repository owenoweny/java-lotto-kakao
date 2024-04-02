package domains;

import java.util.List;

public class LottoManager {
    private final Lottos lottos;
    private final WinningLotto winningLotto;

    public LottoManager(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public int prize() {
        return winningResults().stream()
                .mapToInt(WinningResult::prize)
                .sum();
    }

    public List<WinningResult> winningResults() {
        return lottos.compare(winningLotto);
    }

    public double revenueRate() {
        return (double) prize() / (lottos.values().size() * 1000);
    }
}
