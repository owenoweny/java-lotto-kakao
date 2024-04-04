import domains.Lotto;
import domains.Lottos;
import domains.WinningResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        System.out.println(lottos.values().size() + "개를 구매했습니다.");
        printLottos(lottos);
        System.out.println();
    }

    public void printIssuedLottos(Lottos manualLottos, Lottos autoLottos) {
        System.out.println("수동으로 "
                + manualLottos.values().size()
                + "장, 자동으로 "
                + autoLottos.values().size()
                + "개를 구매했습니다.");
        printLottos(manualLottos);
        printLottos(autoLottos);
    }

    private static void printLottos(Lottos lottos) {
        lottos.values().stream()
                .map(Lotto::values)
                .forEach(System.out::println);
    }

    public List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();

        return parseLottoNumberInput(input);
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

    public int getNumberOfManualInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<List<Integer>> getManualLottoNumbers(int numberOfManualLottos) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        return IntStream.range(0, numberOfManualLottos)
                .mapToObj((i) -> scanner.nextLine())
                .map(this::parseLottoNumberInput)
                .collect(Collectors.toList());
    }


    private List<Integer> parseLottoNumberInput(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
