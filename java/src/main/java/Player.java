public final class Player {
    private final String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String name() {
        return name;
    }

    public int score() {
        return score;
    }

    public static boolean hasPoints(int scorePlayer, int numberOfPoints) {
        return scorePlayer == numberOfPoints;
    }

    public void giveOnePoint() {
        score++;
    }
}
