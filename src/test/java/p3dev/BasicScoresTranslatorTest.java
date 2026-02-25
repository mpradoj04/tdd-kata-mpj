package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class BasicScoresTranslatorTest {

    static Stream<Arguments> basicScoresProvider() {
        return Stream.of(
                arguments(0, 1, "love-fifteen"),
                arguments(1, 0, "fifteen-love"),
                arguments(1, 2, "fifteen-thirty"),
                arguments(2, 1, "thirty-fifteen"),
                arguments(2, 3, "thirty-forty"),
                arguments(3, 2, "forty-thirty")
        );
    }

    static Stream<Arguments> wrongBasicScoresProvider() {
        return Stream.of(
                arguments(0, 0),
                arguments(1, 1),
                arguments(2, 2),
                arguments(3, 3),
                arguments(4, 3),
                arguments(-1, 2),
                arguments(-2, -3),
                arguments(5, 3),
                arguments(-5, -5),
                arguments(122, 1895)
        );
    }

    @ParameterizedTest
    @MethodSource("basicScoresProvider")
    void should_return_correct_string_for_correct_basic_scores(int p1Points, int p2Points, String expected) {
        // Arrange
        BasicScoresTranslator translator = new BasicScoresTranslator();

        // Act
        String result = translator.translate(p1Points, p2Points);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("wrongBasicScoresProvider")
    void should_throw_exception_for_incorrect_basic_scores(int p1Points, int p2Points) {
        // Arrange
        BasicScoresTranslator translator = new BasicScoresTranslator();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(p1Points, p2Points));
    }
}
