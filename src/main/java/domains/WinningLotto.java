package domains;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public boolean containsNumber(LottoNumber number) {
        return lotto.contains(number);
    }

    public boolean containsBonusNumber(Lotto bought) {
        return bought.contains(bonusNumber);
    }
}
