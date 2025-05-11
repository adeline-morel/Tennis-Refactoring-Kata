public final class GameAnnouncer {

    public static final String ADVANTAGE = "Advantage";
    public static final String WIN_FOR = "Win for";
    public static final String ALL = "All";
    public static final String SPACE = " ";
    public static final String SEPARATOR = "-";

    public static String announceLoveAll() {
        return Score.LOVE.scorePart() + SEPARATOR + ALL;
    }

    public static String announceFifteenLove() {
        return Score.FIFTEEN.scorePart() + GameAnnouncer.SEPARATOR + Score.LOVE.scorePart();
    }

    public static String announceThirtyLove() {
        return Score.THIRTY.scorePart() + GameAnnouncer.SEPARATOR + Score.LOVE.scorePart();
    }

    public static String announceFortyLove() {
        return Score.FORTY.scorePart() + GameAnnouncer.SEPARATOR + Score.LOVE.scorePart();
    }

    public static String announceLoveFifteen() {
        return Score.LOVE.scorePart() + GameAnnouncer.SEPARATOR + Score.FIFTEEN.scorePart();
    }

    public static String announceLoveThirty() {
        return Score.LOVE.scorePart() + GameAnnouncer.SEPARATOR + Score.THIRTY.scorePart();
    }

    public static String announceLoveForty() {
        return Score.LOVE.scorePart() + GameAnnouncer.SEPARATOR + Score.FORTY.scorePart();
    }
}
