public enum Score {
    LOVE("Love"),
    FIFTEEN("Fifteen"),
    THIRTY("Thirty"),
    FORTY("Forty");

    private final String scorePart;

    Score(String score) {
        this.scorePart = score;
    }

    public String scorePart() {
        return scorePart;
    }
}
