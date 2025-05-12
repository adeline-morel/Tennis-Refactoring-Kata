public final class GameAnnouncer {

    public static final String ADVANTAGE = "Advantage";
    public static final String WIN_FOR = "Win for";
    public static final String ALL = "All";
    public static final String SPACE = " ";
    public static final String SEPARATOR = "-";

    public static String announceLoveAll() {
        return Score.LOVE.scorePart() + SEPARATOR + ALL;
    }

    public static String announceGameState(Score leftPart, Score rightPart) {
        return leftPart.scorePart() + GameAnnouncer.SEPARATOR + rightPart.scorePart();
    }
}
