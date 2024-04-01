public enum WinningResult {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FORTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    ;

    private final int regularBallMatches;
    private final boolean needBonusBall;
    private final int prize;

    WinningResult(int regularBallMatches, boolean needBonusBall, int prize) {
        this.regularBallMatches = regularBallMatches;
        this.needBonusBall = needBonusBall;
        this.prize = prize;
    }

    public static WinningResult checkResult(Lotto bought, Lotto winning, int bonusNumber) {
        int regularBallMatches = 0;

        for (Integer number : bought.numbers()) {
            if (winning.numbers().contains(number)) {
                regularBallMatches++;
            }
        }
        boolean isBonusBallMatches = bought.contains(bonusNumber);

        return fromBallMatches(regularBallMatches, isBonusBallMatches);
    }

    private static WinningResult fromBallMatches(int regularBallMatches, boolean isBonusBallMatches) {
        for (WinningResult winningResult : WinningResult.values()) {
            if (winningResult.regularBallMatches != regularBallMatches) {
                continue;
            }
            if (winningResult.needBonusBall && !isBonusBallMatches) {
                continue;
            }
            return winningResult;
        }
        throw new RuntimeException("올바르지 않은 입력입니다.");
    }
}
