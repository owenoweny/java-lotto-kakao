import domains.Lotto;
import domains.Lottos;
import domains.WinningResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoConsoleView {
    private final Scanner scanner;

    public LottoConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printIssuedLottos(Lottos lottos) {
        List<Lotto> values = lottos.values();
        System.out.println(values.size() + "개를 구매했습니다.");
        values.stream().map(Lotto::values)
                .forEach(System.out::println);
        System.out.println();
    }

    public List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();

        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printWinningResult(List<WinningResult> winningResults) {
        System.out.println("당첨 통계\n" + "---------");
        List<WinningResult> winningResultValues = Arrays.stream(WinningResult.values()).collect(Collectors.toList());
        winningResultValues.remove(WinningResult.NONE);
        Collections.reverse(winningResultValues);

        for (WinningResult winningResultValue : winningResultValues) {
            int count = (int) winningResults.stream().filter(winningResultValue::equals).count();
            System.out.println(formatWinningResult(winningResultValue, count));
        }
    }

    public void printRevenue(double revenueRate) {
        System.out.println("총 수익률은 " + String.format("%.2f", revenueRate) + "입니다.");
    }

    private static String formatWinningResult(WinningResult winningResultValue, int count) {
        return winningResultValue.regularBallMatches
                + "개 일치"
                + (winningResultValue.needBonusBall ? ", 보너스 볼 일치" : "")
                + " ("
                + winningResultValue.prize + "원)- "
                + count
                + "개";
    }
}
