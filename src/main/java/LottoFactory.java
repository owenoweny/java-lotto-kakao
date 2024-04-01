import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final int LOTTO_PRICE = 1000;

    public static List<Lotto> create(int cash) {
        validateCash(cash);
        return IntStream.range(0, cash / LOTTO_PRICE)
                .mapToObj(e -> new Lotto(Lotto.randomNumbers()))
                .collect(Collectors.toList());
    }

    private static void validateCash(int cash) {
        if (cash < LOTTO_PRICE) {
            throw new IllegalArgumentException("주어진 금액은 1000원 이상이어야 합니다.");
        }
    }
}
