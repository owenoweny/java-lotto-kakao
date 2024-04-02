public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto lotto() {
        return lotto;
    }

    public LottoNumber bonus() {
        return bonusNumber;
    }

    public boolean containsNumber(LottoNumber number) {
        return lotto.contains(number);
    }

    public boolean containsBonus(Lotto bought) {
        return bought.contains(bonusNumber);
    }
}
