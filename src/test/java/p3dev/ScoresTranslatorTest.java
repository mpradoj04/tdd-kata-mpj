package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTranslatorTest {

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

    @ParameterizedTest
    @MethodSource("testCasesProvider")
    void should_return_expected_tennis_score(TennisTestCase testCase) {
        TennisGameTranslator translator = new TennisGameTranslator();

        // Act
        String result = translator.translate(testCase.p1, testCase.p2);

        // Assert
        assertEquals(testCase.expectedResult, result);
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
}