import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(int numberOfLotto) {
        validateNumberOfLotto(numberOfLotto);
        lottos = new ArrayList<>();
        IntStream.range(0, numberOfLotto)
                .forEach(e -> lottos.add(new Lotto(Lotto.randomNumbers())));
    }

    public List<WinningResult> compare(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> lotto.compare(winningLotto))
                .collect(Collectors.toList());
    }

    private void validateNumberOfLotto(int numberOfLotto) {
        if (numberOfLotto <= 0) {
            throw new IllegalArgumentException("로또는 1장 이상 발급되어야 합니다.");
        }
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }
}
