import java.util.List;

public class LottoMachine {
    private final Lottos lottos;
    private final WinningLotto winningLotto;

    public LottoMachine(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public int prize() {
        return winningResults().stream()
                .mapToInt(WinningResult::prize)
                .sum();
    }

    public double revenueRate() {
        return (double) prize() / (lottos.values().size() * 1000);
    }

    public List<WinningResult> winningResults() {
        return lottos.compare(winningLotto);
    }
}
