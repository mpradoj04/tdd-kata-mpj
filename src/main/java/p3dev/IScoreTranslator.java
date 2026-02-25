package p3dev;

public interface IScoreTranslator {

    boolean applies(int scoreP1, int scoreP2);

    String translate(int scoreP1, int scoreP2);
}
