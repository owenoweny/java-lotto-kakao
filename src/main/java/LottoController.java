import domains.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoConsoleView lottoConsoleView;

    public LottoController(LottoConsoleView lottoConsoleView) {
        this.lottoConsoleView = lottoConsoleView;
    }

    public void run() {
        Lottos lottos = makeLottos();
        LottoManager lottoManager = makeLottoManager(lottos);
        showResult(lottoManager);
    }

    private void showResult(LottoManager lottoManager) {
        lottoConsoleView.printWinningResult(lottoManager.winningResults());
        lottoConsoleView.printRevenue(lottoManager.revenueRate());
    }

    private LottoManager makeLottoManager(Lottos lottos) {
        List<Integer> winningNumbersInput = lottoConsoleView.getWinningNumbersInput();
        int bonusBallInput = lottoConsoleView.getBonusBallInput();
        WinningLotto winningLotto = new WinningLotto(Lotto.from(winningNumbersInput), new LottoNumber(bonusBallInput));
        return new LottoManager(lottos, winningLotto);
    }

    private Lottos makeLottos() {
        int money = lottoConsoleView.getMoneyInput();
        int numberOfManualLottos = lottoConsoleView.getNumberOfManualInput();
        LottoInputAmount lottoInputAmount = new LottoInputAmount(money, numberOfManualLottos);

        List<Lotto> manualLottoNumbers = lottoConsoleView.getManualLottoNumbers(numberOfManualLottos)
                .stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
        Lottos lottos = LottoMachine.issue(lottoInputAmount, manualLottoNumbers);
        lottoConsoleView.printIssuedLottos(lottos, numberOfManualLottos);

        return lottos;
    }

    public static void main(String[] args) {
        new LottoController(new LottoConsoleView(new Scanner(System.in))).run();
    }
}
