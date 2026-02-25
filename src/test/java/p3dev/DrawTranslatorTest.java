package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class DrawTranslatorTest {

    static Stream<Arguments> drawProvider() {
        return Stream.of(
                arguments(0, 0, "love-love"),
                arguments(1, 1, "fifteen-all"),
                arguments(2, 2, "thirty-all"),
                arguments(3, 3, "deuce")
        );
    }

    static Stream<Arguments> wrongDrawProvider() {
        return Stream.of(
                arguments(0, 1),
                arguments(1, 2),
                arguments(2, 3),
                arguments(4, 4),
                arguments(-5, -5),
                arguments(122, 1895)
        );
    }

    @ParameterizedTest
    @MethodSource("drawProvider")
    void should_return_correct_string_for_correct_draw_scores(int p1Points, int p2Points, String expected) {
        // Arrange
        DrawTranslator translator = new DrawTranslator();

        // Act
        String result = translator.translate(p1Points, p2Points);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("wrongDrawProvider")
    void should_throw_exception_for_incorrect_draw_scores(int p1Points, int p2Points) {
        // Arrange
        DrawTranslator translator = new DrawTranslator();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(p1Points, p2Points));
    }
}
