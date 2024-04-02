import domains.*;

import java.util.List;
import java.util.Scanner;

public class LottoController {
    private final LottoConsoleView lottoConsoleView;

    public LottoController(LottoConsoleView lottoConsoleView) {
        this.lottoConsoleView = lottoConsoleView;
    }

    public void run() {
        int money = lottoConsoleView.getMoneyInput();
        Lottos lottos = LottoMachine.issue(money);
        lottoConsoleView.printIssuedLottos(lottos);
        List<Integer> winningNumbersInput = lottoConsoleView.getWinningNumbersInput();
        int bonusBallInput = lottoConsoleView.getBonusBallInput();
        WinningLotto winningLotto = new WinningLotto(Lotto.from(winningNumbersInput), new LottoNumber(bonusBallInput));
        LottoManager lottoManager = new LottoManager(lottos, winningLotto);
        lottoConsoleView.printWinningResult(lottoManager.winningResults());
        lottoConsoleView.printRevenue(lottoManager.revenueRate());
    }

    public static void main(String[] args) {
        new LottoController(new LottoConsoleView(new Scanner(System.in))).run();
    }
}
