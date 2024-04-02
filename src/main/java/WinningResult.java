public enum WinningResult {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FORTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0),
    ;

    public final int regularBallMatches;
    public final boolean needBonusBall;
    public final int prize;

    WinningResult(int regularBallMatches, boolean needBonusBall, int prize) {
        this.regularBallMatches = regularBallMatches;
        this.needBonusBall = needBonusBall;
        this.prize = prize;
    }

    public int prize() {
        return prize;
    }

    public static WinningResult of(Lotto bought, WinningLotto winningLotto) {
        boolean isBonusBallMatches = winningLotto.containsBonus(bought);
        int regularBallMatches = (int) bought.numbers()
                .stream()
                .filter(winningLotto::containsNumber).count();

        return fromBallMatches(regularBallMatches, isBonusBallMatches);
    }

    private static WinningResult fromBallMatches(int regularBallMatches, boolean isBonusBallMatches) {
        if (regularBallMatches < 3) {
            return NONE;
        }
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
