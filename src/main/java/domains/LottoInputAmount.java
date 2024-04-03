package domains;

public class LottoInputAmount {
    public static final int LOTTO_PRICE = 1000;
    private final int money;

    public LottoInputAmount(int money) {
        validateMoneyRange(money);
        this.money = money;
    }

    public int getNumberOfLottos() {
        return money / LOTTO_PRICE;
    }

    private void validateMoneyRange(int money) {
        if (money < LOTTO_PRICE) {
            throw new RuntimeException("로또 구매 금액은 1000 이상이어야합니다.");
        }
    }
}
