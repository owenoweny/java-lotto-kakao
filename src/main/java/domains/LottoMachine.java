 package domains;

import utils.LottoUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
    private static final int RANDOM_BEGIN_INCLUDE_INDEX = 0;
    private static final int RANDOM_END_EXCLUDE_INDEX = 6;

    private LottoMachine() {
    }


    //TODO : LottoMachine 인스턴스로 만들고 LottoInputAmount 필드로 갖고 있게??
    public static Lottos issue(LottoInputAmount lottoInputAmount, List<Lotto> manualLottoList) {
        int numberOfAutoLotto = lottoInputAmount.getNumberOfAutoLottos();

        List<Lotto> lottoList = IntStream.range(0, numberOfAutoLotto)
                .mapToObj((i) -> generateRandomLotto())
                .collect(Collectors.toList());
        lottoList.addAll(manualLottoList);

        return new Lottos(lottoList);
    }

    public static Lottos issueAuto(int numberOfLottos) {
        List<Lotto> lottoList = IntStream.range(0, numberOfLottos)
                .mapToObj(i -> generateRandomLotto())
                .collect(Collectors.toList());

        return new Lottos(lottoList);
    }

    public static Lottos issueManual(List<List<Integer>> lottoNumberList) {
        List<Lotto> lottoList = lottoNumberList.stream()
                .map(lottoNumbers -> new Lotto(LottoUtils.parsePickedNumbers(lottoNumbers)))
                .collect(Collectors.toList());

        return new Lottos(lottoList);
    }

    private static Lotto generateRandomLotto() {
        List<LottoNumber> lottoNumbers = LottoUtils.parsePickedNumbers(generateRandomLottoNumbers());
        return new Lotto(lottoNumbers);
    }

    private static List<Integer> generateRandomLottoNumbers() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return CANDIDATE_NUMBERS.subList(RANDOM_BEGIN_INCLUDE_INDEX, RANDOM_END_EXCLUDE_INDEX);
    }
}
