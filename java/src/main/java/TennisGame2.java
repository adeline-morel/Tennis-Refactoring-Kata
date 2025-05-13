import java.util.Objects;

public class TennisGame2 implements TennisGame
{

    private final String playerOneName;
    private final String playerTwoName;

    private static final String ADVANTAGE = "Advantage";
    private static final String WIN_FOR = "Win for";
    private static final String ALL = "All";
    private static final String SPACE = " ";
    private static final String SEPARATOR = "-";

    private String playerOneResult = "";
    private String playerTwoResult = "";
    private int playerOneScore = 0;
    private int playerTwoScore = 0;


    public TennisGame2(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public String getScore(){
        String result = "";

        if (isEquality()) {
            if (playerOneScore == 0) {
                result = Score.LOVE.scorePart();
            }
            if (playerOneScore == 1) {
                result = Score.FIFTEEN.scorePart();
            }
            if (playerOneScore == 2) {
                result = Score.THIRTY.scorePart();
            }

            result += SEPARATOR + ALL;
        }

        if (isDeuce()) {
            result = Score.DEUCE.scorePart();
        }

        if (hasZeroScorePlayerTwo()) {
            if (playerOneScore == 1) {
                playerOneResult = Score.FIFTEEN.scorePart();
            }
            if (playerOneScore == 2) {
                playerOneResult = Score.THIRTY.scorePart();
            }
            if (playerOneScore == 3) {
                playerOneResult = Score.FORTY.scorePart();
            }

            playerTwoResult = Score.LOVE.scorePart();
            result = playerOneResult + SEPARATOR + playerTwoResult;
        }

        if (hasZeroScorePlayerOne()) {
            if (playerTwoScore == 1) {
                playerTwoResult = Score.FIFTEEN.scorePart();
            }
            if (playerTwoScore == 2) {
                playerTwoResult = Score.THIRTY.scorePart();
            }
            if (playerTwoScore == 3) {
                playerTwoResult = Score.FORTY.scorePart();
            }

            playerOneResult = Score.LOVE.scorePart();
            result = playerOneResult + SEPARATOR + playerTwoResult;
        }

        if (isAdvantagePlayerOne()) {
            result = ADVANTAGE + SPACE + playerOneName;
        }

        if (isAdvantagePlayerTwo()) {
            result = ADVANTAGE + SPACE + playerTwoName;
        }

        if (isWinForPlayerOne()) {
            result = WIN_FOR + SPACE + playerOneName;
        }

        if (isWinForPlayerTwo()) {
            result = WIN_FOR + SPACE + playerTwoName;
        }

        if (playerOneScore > playerTwoScore && playerOneScore < 4) {
            if (playerOneScore == 2) {
                playerOneResult = Score.THIRTY.scorePart();
            }
            if (playerOneScore == 3) {
                playerOneResult = Score.FORTY.scorePart();
            }
            if (playerTwoScore == 1) {
                playerTwoResult = Score.FIFTEEN.scorePart();
            }
            if (playerTwoScore == 2) {
                playerTwoResult = Score.THIRTY.scorePart();
            }

            result = playerOneResult + SEPARATOR + playerTwoResult;
        }

        if (playerTwoScore > playerOneScore && playerTwoScore < 4) {
            if (playerTwoScore == 2) {
                playerTwoResult = Score.THIRTY.scorePart();
            }
            if (playerTwoScore == 3) {
                playerTwoResult = Score.FORTY.scorePart();
            }
            if (playerOneScore == 1) {
                playerOneResult = Score.FIFTEEN.scorePart();
            }
            if (playerOneScore == 2) {
                playerOneResult = Score.THIRTY.scorePart();
            }

            result = playerOneResult + SEPARATOR + playerTwoResult;
        }

        return result;
    }

    private boolean isWinForPlayerTwo() {
        return playerTwoScore >= 4 && playerOneScore >= 0 && (playerTwoScore - playerOneScore) >= 2;
    }

    private boolean isWinForPlayerOne() {
        return playerOneScore >= 4 && playerTwoScore >= 0 && (playerOneScore - playerTwoScore) >= 2;
    }

    private boolean isAdvantagePlayerTwo() {
        return playerTwoScore > playerOneScore && playerOneScore >= 3;
    }

    private boolean isAdvantagePlayerOne() {
        return playerOneScore > playerTwoScore && playerTwoScore >= 3;
    }

    private boolean hasZeroScorePlayerOne() {
        return playerTwoScore > 0 && playerOneScore == 0;
    }

    private boolean hasZeroScorePlayerTwo() {
        return playerOneScore > 0 && playerTwoScore == 0;
    }

    private boolean isDeuce() {
        return playerOneScore == playerTwoScore && playerOneScore >= 3;
    }

    private boolean isEquality() {
        return playerOneScore == playerTwoScore && playerOneScore < 4;
    }

    public void addToPlayerOneScore(){
        playerOneScore++;
    }

    public void addToPlayerTwoScore(){
        playerTwoScore++;
    }

    public void wonPoint(String player) {
        if (Objects.equals(player, playerOneName)) {
            addToPlayerOneScore();
        } else {
            addToPlayerTwoScore();
        }
    }
}