public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;

    public LottoMachine(int cash) {
        int numberOfLotto = cash / LOTTO_PRICE;
        lottos = new Lottos(numberOfLotto);
    }

    public Lottos boughtLottos() {
        return lottos;
    }
}
