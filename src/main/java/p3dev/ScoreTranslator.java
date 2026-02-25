package p3dev;

public class ScoreTranslator {

    private final IScoreTranslator[] translators;

    public ScoreTranslator() {
        translators = new IScoreTranslator[]{
                new BasicScoresTranslator(),
                new DrawTranslator(),
                new AdvantageTranslator(),
                new VictoryTranslator()
        };
    }

    public String translate(int scoreP1, int scoreP2) {

        for (IScoreTranslator translator : translators) {
            if (translator.applies(scoreP1, scoreP2)) {
                return translator.translate(scoreP1, scoreP2);
            }
        }
        throw new IllegalStateException(
                String.format("No translator found for score %d-%d", scoreP1, scoreP2));
    }
}
