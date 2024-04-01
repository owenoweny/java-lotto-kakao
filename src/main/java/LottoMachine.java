public class LottoMachine {
    private final Lottos lottos;
    private final WinningLotto winningLotto;

    public LottoMachine(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public Lottos boughtLottos() {
        return lottos;
    }

    public int prize() {
        return lottos.values().stream()
                .mapToInt(e -> WinningResult.checkResult(e, winningLotto).prize())
                .sum();
    }

    public double revenueRate() {
        return (double) prize() / (lottos.values().size() * 1000);
    }
}
