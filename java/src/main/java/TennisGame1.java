
public class TennisGame1 implements TennisGame {

    private static final String ADVANTAGE = "Advantage";
    private static final String WIN_FOR = "Win for";
    private static final String ALL = "All";
    private static final String SPACE = " ";
    private static final String SEPARATOR = "-";
    private static final int MINIMUM_WINNING_SCORE = 4;
    private static final int SCORE_DIFFERENCE_FOR_WIN = 2;
    private static final String DEUCE = "Deuce";

    private final String playerOneName;
    private final String playerTwoName;
    private int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;

    public TennisGame1(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(playerOneName)) {
            scorePlayerOne += 1;
        } else {
            scorePlayerTwo += 1;
        }
    }

    public String getScore() {
        if (playersHaveSameScore()) {
            return computeSameScore();
        }
        else if (oneOfThePlayersHasScoreFourOrOver()) {
            return computeAdvantageOrWin();
        }

        return basicScore();
    }

    private boolean playersHaveSameScore() {
        return scorePlayerOne == scorePlayerTwo;
    }

    private boolean oneOfThePlayersHasScoreFourOrOver() {
        return scorePlayerOne >= MINIMUM_WINNING_SCORE || scorePlayerTwo >= MINIMUM_WINNING_SCORE;
    }

    private String computeAdvantageOrWin() {
        int minusResult = scorePlayerOne - scorePlayerTwo;
        if (minusResult == 1) {
            return advantageScore(playerOneName);
        } else if (minusResult == -1) {
            return advantageScore(playerTwoName);
        } else if (minusResult >= SCORE_DIFFERENCE_FOR_WIN) {
            return winningScore(playerOneName);
        }

        return winningScore(playerTwoName);
    }

    private String advantageScore(String playerName) {
        return ADVANTAGE + SPACE + playerName;
    }

    private String winningScore(String playerName) {
        return WIN_FOR + SPACE + playerName;
    }

    private String basicScore() {
        return computeBasicScorePart(scorePlayerOne) + SEPARATOR + computeBasicScorePart(scorePlayerTwo);
    }

    private static String computeBasicScorePart(int tempScore) {
        return switch (tempScore) {
            case 0 -> Score.LOVE.scorePart();
            case 1 -> Score.FIFTEEN.scorePart();
            case 2 -> Score.THIRTY.scorePart();
            case 3 -> Score.FORTY.scorePart();
            default -> "";
        };
    }

    private String computeSameScore() {
        return switch (scorePlayerOne) {
            case 0 -> Score.LOVE.scorePart() + SEPARATOR + ALL;
            case 1 -> Score.FIFTEEN.scorePart() + SEPARATOR + ALL;
            case 2 -> Score.THIRTY.scorePart() + SEPARATOR + ALL;
            default -> DEUCE;
        };
    }
}
