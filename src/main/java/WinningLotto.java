public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto lotto() {
        return lotto;
    }

    public int bonus() {
        return bonusNumber;
    }

    public boolean containsNumber(int number) {
        return lotto.contains(number);
    }

    public boolean containsBonus(Lotto bought) {
        return bought.contains(bonusNumber);
    }
}
