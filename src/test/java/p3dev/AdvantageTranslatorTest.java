package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class AdvantageTranslatorTest {

    static Stream<Arguments> advantageAndVictoryProvider() {
        return Stream.of(
                arguments(4, 3, "Advantage Player 1"),
                arguments(3, 4, "Advantage Player 2")
        );
    }

    static Stream<Arguments> wrongAdvantageAndVictoryProvider() {
        return Stream.of(
                arguments(0, 0),
                arguments(1, 1),
                arguments(2, 2),
                arguments(3, 3),
                arguments(4, 4),
                arguments(5, 2),
                arguments(1, 5),
                arguments(1, 3),
                arguments(1, 4),
                arguments(2, 3),
                arguments(-1, 2),
                arguments(-2, -3),
                arguments(5, 5),
                arguments(-5, -5),
                arguments(122, 1895)
        );
    }

    @ParameterizedTest
    @MethodSource("advantageAndVictoryProvider")
    void should_return_correct_string_for_correct_advantage_and_victory_scores(int p1Points, int p2Points, String expected) {
        // Arrange
        AdvantageTranslator translator = new AdvantageTranslator();

        // Act
        String result = translator.translate(p1Points, p2Points);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("wrongAdvantageAndVictoryProvider")
    void should_throw_exception_for_incorrect_advantage_and_victory_scores(int p1Points, int p2Points) {
        // Arrange
        AdvantageTranslator translator = new AdvantageTranslator();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(p1Points, p2Points));
    }
}
