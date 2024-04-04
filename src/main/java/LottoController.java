import domains.*;

import java.util.List;
import java.util.Scanner;

import static domains.Lottos.merge;

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
        LottoInputAmount lottoInputAmount = new LottoInputAmount(money);

        int numberOfManualLottos = lottoConsoleView.getNumberOfManualInput();
        if (numberOfManualLottos < 1) {
            return new Lottos(List.of());
        }
        List<List<Integer>> manualLottoNumbers = lottoConsoleView.getManualLottoNumbers(numberOfManualLottos);
        Lottos manualLottos = LottoMachine.issueManual(manualLottoNumbers);
        Lottos autoLottos = LottoMachine.issueAuto(lottoInputAmount.getNumberOfLottos() - numberOfManualLottos);
        lottoConsoleView.printIssuedLottos(manualLottos, autoLottos);

        return merge(manualLottos, autoLottos);
    }

    public static void main(String[] args) {
        new LottoController(new LottoConsoleView(new Scanner(System.in))).run();
    }
}
