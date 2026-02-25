package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoresTranslatorTest {

    static Stream<TennisTestCase> testCasesProvider() {
        return Stream.of(
                new TennisTestCase(0, 0, "love-love"),
                new TennisTestCase(1, 1, "fifteen-all"),
                new TennisTestCase(2, 2, "thirty-all"),
                new TennisTestCase(3, 3, "deuce"),

                new TennisTestCase(1, 0, "fifteen-love"),
                new TennisTestCase(0, 2, "love-thirty"),
                new TennisTestCase(3, 1, "forty-fifteen"),

                new TennisTestCase(4, 3, "Advantage Player 1"),
                new TennisTestCase(3, 4, "Advantage Player 2"),

                new TennisTestCase(5, 0, "Player 1 wins"),
                new TennisTestCase(1, 5, "Player 2 wins"),
                new TennisTestCase(5, 3, "Player 1 wins"),
                new TennisTestCase(2, 5, "Player 2 wins")
        );
    }

    static Stream<InvalidScoreCase> invalidScoresProvider() {
        return Stream.of(
                new InvalidScoreCase(-1, 0),
                new InvalidScoreCase(0, -5),
                new InvalidScoreCase(10, 2),
                new InvalidScoreCase(2, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesProvider")
    void should_return_expected_tennis_score(TennisTestCase testCase) {
        ScoreTranslator translator = new ScoreTranslator();

        // Act
        String result = translator.translate(testCase.p1, testCase.p2);

        // Assert
        assertEquals(testCase.expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("invalidScoresProvider")
    void should_throw_exception_for_unhandled_scores(InvalidScoreCase invalidCase) {
        ScoreTranslator translator = new ScoreTranslator();

        assertThrows(IllegalStateException.class, () ->
                translator.translate(invalidCase.p1, invalidCase.p2)
        );
    }

    static class TennisTestCase {
        int p1;
        int p2;
        String expectedResult;

        TennisTestCase(int p1, int p2, String expectedResult) {
            this.p1 = p1;
            this.p2 = p2;
            this.expectedResult = expectedResult;
        }

        @Override
        public String toString() {
            return String.format("Score %d-%d should return \"%s\"", p1, p2, expectedResult);
        }
    }

    static class InvalidScoreCase {
        int p1, p2;
        InvalidScoreCase(int p1, int p2) {
            this.p1 = p1; this.p2 = p2;
        }
        @Override
        public String toString() { return String.format("%d-%d", p1, p2); }
    }
}