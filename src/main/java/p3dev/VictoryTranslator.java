package p3dev;

public class VictoryTranslator implements IScoreTranslator{
    @Override
    public boolean applies(int scoreP1, int scoreP2) {
        boolean validWin = scoreP2 == 5 || scoreP1 == 5;
        boolean notDraw = scoreP2 != scoreP1;
        boolean isValid = scoreP1 >= 0 && scoreP1 <= 5 && scoreP2 >= 0 && scoreP2 <= 5;
        return validWin && notDraw && isValid;
    }

    @Override
    public String translate(int scoreP1, int scoreP2) {
        if (!applies(scoreP1, scoreP2)) throw new IllegalArgumentException("Numbers must a valid victory score");

        int diff = scoreP1 - scoreP2;

        if (diff >= 2) return "Player 1 wins";
        return "Player 2 wins";
    }
}
