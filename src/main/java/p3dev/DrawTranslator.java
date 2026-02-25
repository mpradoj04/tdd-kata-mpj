package p3dev;

public class DrawTranslator implements IScoreTranslator {


    @Override
    public boolean applies(int scoreP1, int scoreP2) {
        return scoreP1 == scoreP2 && scoreP1 <= 3 && scoreP1 >= 0;
    }

    @Override
    public String translate(int scoreP1, int scoreP2) {
        if (!applies(scoreP1, scoreP2)) throw new IllegalArgumentException("Numbers must a valid draw");
        return switch(scoreP1){
            case 0 -> "love-love";
            case 1 -> "fifteen-all";
            case 2 -> "thirty-all";
            case 3 -> "deuce";
            default -> throw new IllegalArgumentException("Unexpected draw score: " + scoreP1);
        };
    }
}
