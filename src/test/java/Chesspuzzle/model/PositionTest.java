package Chesspuzzle.model;

import ChessPuzzle.model.Direction;
import ChessPuzzle.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PositionTest {
    Position position;

    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.row()),
                () -> assertEquals(expectedCol, position.col())
        );
    }

    @BeforeEach
    void init() {
        position = new Position(0, 0);}

    @Test
    void move() {

        assertPosition(-1, 0, position.move(Direction.K_UP));
        assertPosition(1, 0, position.move(Direction.K_DOWN));
        assertPosition(0, -1, position.move(Direction.K_LEFT));
        assertPosition(0, 1, position.move(Direction.K_RIGHT));
        assertPosition(-1, -1, position.move(Direction.K_UP_LEFT));
        assertPosition(-1, 1, position.move(Direction.K_UP_RIGHT));
        assertPosition(1, -1, position.move(Direction.K_DOWN_LEFT));
        assertPosition(1, 1, position.move(Direction.K_DOWN_RIGHT));

        assertPosition(-2, -1, position.move(Direction.L_UP_LEFT));
        assertPosition(-2, 1, position.move(Direction.L_UP_RIGHT));
        assertPosition(2, -1, position.move(Direction.L_DOWN_LEFT));
        assertPosition(2, 1, position.move(Direction.L_DOWN_RIGHT));
        assertPosition(-1, -2, position.move(Direction.L_LEFT_UP));
        assertPosition(1, -2, position.move(Direction.L_LEFT_DOWN));
        assertPosition(-1, 2, position.move(Direction.L_RIGHT_UP));
        assertPosition(1, 2, position.move(Direction.L_RIGHT_DOWN));
    }

    @Test
    void testToString() {
        assertEquals("(0,0)", position.toString());
    }

}
