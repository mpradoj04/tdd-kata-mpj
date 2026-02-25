package p3dev;

public class AdvantageTranslator implements IScoreTranslator{
    @Override
    public boolean applies(int scoreP1, int scoreP2) {
        boolean someoneHasAdvantage = scoreP1 == 4 || scoreP2 == 4;
        boolean notDraw = scoreP1 != scoreP2;
        boolean isValidAdvantage = scoreP1 >= 3 && scoreP1 <= 4 && scoreP2 >= 3 && scoreP2 <= 4;
        return someoneHasAdvantage && notDraw && isValidAdvantage;
    }

    @Override
    public String translate(int scoreP1, int scoreP2) {
        if (!applies(scoreP1, scoreP2)) throw new IllegalArgumentException("Numbers must a valid advantage or victory score score");

        int diff = scoreP1 - scoreP2;

        return switch (diff) {
            case 1 -> "Advantage Player 1";
            case -1 -> "Advantage Player 2";
            default -> throw new IllegalArgumentException("Unexpected advantage score: " + scoreP1);
        };
    }
}
