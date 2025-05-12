
public class TennisGame2 implements TennisGame {
    //REFACTORING EN COURS... (ALWAYS GREEN)
    /*
    * Pour le moment j'essaie de regrouper, et de séparer les responsabilités petit à petit.
    * Pour voir le code d'origine, c'est par ici : https://github.com/emilybache/Tennis-Refactoring-Kata/blob/main/java/src/main/java/TennisGame2.java
    * */

    private static final int MINIMUM_WINNING_SCORE = 4;
    private static final int SCORE_DIFFERENCE_FOR_WIN = 2;

    private final Player playerOne;
    private final Player playerTwo;
    private final String playerOneName;
    private final String playerTwoName;

    public TennisGame2(String playerOneName, String playerTwoName) {
        this.playerOne = new Player(playerOneName, 0);
        this.playerTwo = new Player(playerTwoName, 0);
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public String getScore(){

        if (isLoveAll()){
            return GameAnnouncer.announceLoveAll();
        }

        if (Player.hasPoints(playerTwo.score(), 0)) {
            if (Player.hasPoints(playerOne.score(), 1)) {
                return GameAnnouncer.announceGameState(Score.FIFTEEN, Score.LOVE);
            }
            if (Player.hasPoints(playerOne.score(), 2)) {
                return GameAnnouncer.announceGameState(Score.THIRTY, Score.LOVE);
            }
            if (Player.hasPoints(playerOne.score(), 3)) {
                return GameAnnouncer.announceGameState(Score.FORTY, Score.LOVE);
            }

            return announceThatPlayerWins(playerOneName);
        }

        if (Player.hasPoints(playerOne.score(), 0)) {
            if (Player.hasPoints(playerTwo.score(), 1)) {
                return GameAnnouncer.announceGameState(Score.LOVE, Score.FIFTEEN);
            }
            if (Player.hasPoints(playerTwo.score(), 2)) {
                return GameAnnouncer.announceGameState(Score.LOVE, Score.THIRTY);
            }
            if (Player.hasPoints(playerTwo.score(), 3)) {
                return GameAnnouncer.announceGameState(Score.LOVE, Score.FORTY);
            }

            return announceThatPlayerWins(playerTwoName);
        }

        if(isNotWinningScore()){
            if (playerOne.score() > playerTwo.score()) {
                if (Player.hasPoints(playerOne.score(), 2)) {
                    return GameAnnouncer.announceGameState(Score.THIRTY, Score.FIFTEEN);
                }
                if (Player.hasPoints(playerTwo.score(), 1)) {
                    return GameAnnouncer.announceGameState(Score.FORTY, Score.FIFTEEN);
                }
                if (Player.hasPoints(playerOne.score(), 3)) {
                    return GameAnnouncer.announceGameState(Score.FORTY, Score.THIRTY);
                }
            }

            if (playerTwo.score() > playerOne.score()) {
                if (Player.hasPoints(playerOne.score(), 2)) {
                    return GameAnnouncer.announceGameState(Score.THIRTY, Score.FORTY);
                }
                if (Player.hasPoints(playerTwo.score(), 2)) {
                    return GameAnnouncer.announceGameState(Score.FIFTEEN, Score.THIRTY);
                }
                if (Player.hasPoints(playerTwo.score(), 3)) {
                    return GameAnnouncer.announceGameState(Score.FIFTEEN, Score.FORTY);
                }
            }
        }

        if (isPlayerOneWinning()) {
            return announceThatPlayerWins(playerOneName);
        }
        if (isPlayerTwoWinning()) {
            return announceThatPlayerWins(playerTwoName);
        }

        if (playersHaveSameScore() && playerOne.score() < MINIMUM_WINNING_SCORE) {
            if (Player.hasPoints(playerOne.score(), 1)) {
                return Score.FIFTEEN.scorePart() + GameAnnouncer.SEPARATOR + GameAnnouncer.ALL;
            } else if (playerOne.score() == 2) {
                return Score.THIRTY.scorePart() + GameAnnouncer.SEPARATOR + GameAnnouncer.ALL;
            }
        }

        if (isAdvantageForPlayerOne()) {
            return GameAnnouncer.ADVANTAGE + GameAnnouncer.SPACE + playerOneName;
        } else if (isAdvantageForPlayerTwo()) {
            return GameAnnouncer.ADVANTAGE + GameAnnouncer.SPACE + playerTwoName;
        }

        return Score.DEUCE.scorePart();
    }

    private boolean isPlayerTwoWinning() {
        return playerTwo.score() >= MINIMUM_WINNING_SCORE && (playerTwo.score() - playerOne.score()) >= SCORE_DIFFERENCE_FOR_WIN;
    }

    private boolean isPlayerOneWinning() {
        return playerOne.score() >= MINIMUM_WINNING_SCORE && (playerOne.score() - playerTwo.score()) >= SCORE_DIFFERENCE_FOR_WIN;
    }

    private boolean isAdvantageForPlayerTwo() {
        return playerTwo.score() > playerOne.score() && playerOne.score() >= 3;
    }

    private boolean isAdvantageForPlayerOne() {
        return playerOne.score() > playerTwo.score() && playerTwo.score() >= 3;
    }

    private boolean isNotWinningScore() {
        return playerOne.score() < MINIMUM_WINNING_SCORE && playerTwo.score() < MINIMUM_WINNING_SCORE;
    }

    public void wonPoint(String player) {
        if (player.equals(playerOneName)) {
            giveOnePointToPlayerOne();
        }
        else {
            giveOnePointToPlayerTwo();
        }
    }

    private boolean isLoveAll() {
        return playerOne.score() == 0 && playersHaveSameScore();
    }

    private String announceThatPlayerWins(String playerName) {
        return GameAnnouncer.WIN_FOR + GameAnnouncer.SPACE + playerName;
    }

    private boolean playersHaveSameScore() {
        return playerOne.score() == playerTwo.score();
    }

    private void giveOnePointToPlayerOne(){
        playerOne.giveOnePoint();
    }

    private void giveOnePointToPlayerTwo(){
        playerTwo.giveOnePoint();
    }
}