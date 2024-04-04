package domains;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateBonusNumberDuplication(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private static void validateBonusNumberDuplication(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new RuntimeException("보너스 번호와 로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean containsNumber(LottoNumber number) {
        return lotto.contains(number);
    }

    public boolean containsBonusNumber(Lotto bought) {
        return bought.contains(bonusNumber);
    }
}
