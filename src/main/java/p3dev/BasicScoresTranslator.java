package p3dev;

public class BasicScoresTranslator implements IScoreTranslator{
    @Override
    public boolean applies(int scoreP1, int scoreP2) {
        return scoreP2 != scoreP1 && scoreP1 >= 0 && scoreP1 <= 3 && scoreP2 >= 0 && scoreP2 <= 3;
    }

    @Override
    public String translate(int scoreP1, int scoreP2) {
        if (!applies(scoreP1, scoreP2)) throw new IllegalArgumentException("Numbers must a valid basic score");
        return mapScore(scoreP1) + "-" + mapScore(scoreP2);
    }

    private String mapScore(int score) {
        return switch (score) {
            case 0 -> "love";
            case 1 -> "fifteen";
            case 2 -> "thirty";
            case 3 -> "forty";
            default -> throw new IllegalArgumentException("Unknown score: " + score);
        };
    }
}
