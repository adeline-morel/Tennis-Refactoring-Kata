
public class TennisGame2 implements TennisGame {
    //REFACTORING EN COURS... (ALWAYS GREEN)
    /*
    * Pour le moment j'essaie de regrouper, et de séparer les responsabilités petit à petit.
    * Pour voir le code d'origine, c'est par ici : https://github.com/emilybache/Tennis-Refactoring-Kata/blob/main/java/src/main/java/TennisGame2.java
    * */

    private static final int MINIMUM_WINNING_SCORE = 4;
    private static final int SCORE_DIFFERENCE_FOR_WIN = 2;

    private Player playerOne;
    private Player playerTwo;
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
                return GameAnnouncer.announceFifteenLove();
            }
            if (Player.hasPoints(playerOne.score(), 2)) {
                return GameAnnouncer.announceThirtyLove();
            }
            if (Player.hasPoints(playerOne.score(), 3)) {
                return GameAnnouncer.announceFortyLove();
            }

            return announceThatPlayerWins(playerOneName);
        }

        if (Player.hasPoints(playerOne.score(), 0)) {
            if (Player.hasPoints(playerTwo.score(), 1)) {
                return GameAnnouncer.announceLoveFifteen();
            }
            if (Player.hasPoints(playerTwo.score(), 2)) {
                return GameAnnouncer.announceLoveThirty();
            }
            if (Player.hasPoints(playerTwo.score(), 3)) {
                return GameAnnouncer.announceLoveForty();
            }

            return announceThatPlayerWins(playerTwoName);
        }

        String scorePartPlayerOne = Score.LOVE.scorePart();
        String scorePartPlayerTwo = Score.LOVE.scorePart();

        if(isNotWinningScore()){
            if (playerOne.score() > playerTwo.score()) {
                if (Player.hasPoints(playerOne.score(), 2)) {
                    scorePartPlayerOne = Score.THIRTY.scorePart();
                }
                if (Player.hasPoints(playerOne.score(), 3)) {
                    scorePartPlayerOne = Score.FORTY.scorePart();
                }
                if (Player.hasPoints(playerTwo.score(), 1)) {
                    scorePartPlayerTwo = Score.FIFTEEN.scorePart();
                }
                if (Player.hasPoints(playerTwo.score(), 2)) {
                    scorePartPlayerTwo = Score.THIRTY.scorePart();
                }

                return scorePartPlayerOne + GameAnnouncer.SEPARATOR + scorePartPlayerTwo;
            }

            if (playerTwo.score() > playerOne.score()) {
                if (Player.hasPoints(playerOne.score(), 1)) {
                    scorePartPlayerOne = Score.FIFTEEN.scorePart();
                }
                if (Player.hasPoints(playerOne.score(), 2)) {
                    scorePartPlayerOne = Score.THIRTY.scorePart();
                }
                if (Player.hasPoints(playerTwo.score(), 2)) {
                    scorePartPlayerTwo = Score.THIRTY.scorePart();
                }
                if (Player.hasPoints(playerTwo.score(), 3)) {
                    scorePartPlayerTwo = Score.FORTY.scorePart();
                }

                return scorePartPlayerOne + GameAnnouncer.SEPARATOR + scorePartPlayerTwo;
            }
        }

        if (playerOne.score() >= MINIMUM_WINNING_SCORE && (playerOne.score() - playerTwo.score()) >= SCORE_DIFFERENCE_FOR_WIN) {
            return announceThatPlayerWins(playerOneName);
        }

        if (playerTwo.score() >= MINIMUM_WINNING_SCORE && (playerTwo.score() - playerOne.score()) >= SCORE_DIFFERENCE_FOR_WIN) {
            return announceThatPlayerWins(playerTwoName);
        }

        String score;

        score = computeSameScores();

        return computeAdvantageScore(score);
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

    private String computeAdvantageScore(String score) {
        if (playerOne.score() > playerTwo.score() && playerTwo.score() >= 3) {
            return GameAnnouncer.ADVANTAGE + GameAnnouncer.SPACE + playerOneName;
        } else if (playerTwo.score() > playerOne.score() && playerOne.score() >= 3) {
            return GameAnnouncer.ADVANTAGE + GameAnnouncer.SPACE + playerTwoName;
        }
        return score;
    }

    private String computeSameScores() {
        if (playersHaveSameScore() && playerOne.score() < MINIMUM_WINNING_SCORE) {
            if (Player.hasPoints(playerOne.score(), 1)) {
                return Score.FIFTEEN.scorePart() + GameAnnouncer.SEPARATOR + GameAnnouncer.ALL;
            }
            if (playerOne.score() == 2) {
                return Score.THIRTY.scorePart() + GameAnnouncer.SEPARATOR + GameAnnouncer.ALL;
            }
        }

        return Score.DEUCE.scorePart();
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