package Chesspuzzle.model;
import ChessPuzzle.model.Direction;
import ChessPuzzle.model.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParameterizedPositionTest {
    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.row()),
                () -> assertEquals(expectedCol, position.col())
        );
    }

    static Stream<Position> positionProvider() {
        return Stream.of(
                new Position(5, 1),
                new Position(5, 2),
                new Position(7, 6));
    }
    @ParameterizedTest
    @MethodSource("positionProvider")
    void move(Position position) {

        assertPosition(position.row() - 1, position.col(), position.move(Direction.K_UP));
        assertPosition(position.row(), position.col() + 1, position.move(Direction.K_RIGHT));
        assertPosition(position.row() + 1, position.col(), position.move(Direction.K_DOWN));
        assertPosition(position.row(), position.col() - 1, position.move(Direction.K_LEFT));
        assertPosition(position.row() - 1, position.col() - 1, position.move(Direction.K_UP_LEFT));
        assertPosition(position.row() - 1, position.col() + 1, position.move(Direction.K_UP_RIGHT));
        assertPosition(position.row() + 1, position.col() - 1, position.move(Direction.K_DOWN_LEFT));
        assertPosition(position.row() + 1, position.col() + 1, position.move(Direction.K_DOWN_RIGHT));

        assertPosition(position.row() - 2, position.col() - 1, position.move(Direction.L_UP_LEFT));
        assertPosition(position.row() - 2, position.col() + 1, position.move(Direction.L_UP_RIGHT));
        assertPosition(position.row() + 2, position.col() - 1, position.move(Direction.L_DOWN_LEFT));
        assertPosition(position.row() + 2, position.col() + 1, position.move(Direction.L_DOWN_RIGHT));
        assertPosition(position.row() - 1, position.col() - 2, position.move(Direction.L_LEFT_UP));
        assertPosition(position.row() + 1, position.col() - 2, position.move(Direction.L_LEFT_DOWN));
        assertPosition(position.row() - 1, position.col() + 2, position.move(Direction.L_RIGHT_UP));
        assertPosition(position.row() + 1, position.col() + 2, position.move(Direction.L_RIGHT_DOWN));
    }



    @ParameterizedTest
    @MethodSource("positionProvider")
    void testToString(Position position) {
        assertEquals(String.format("(%d,%d)", position.row(), position.col()), position.toString());
    }
}
