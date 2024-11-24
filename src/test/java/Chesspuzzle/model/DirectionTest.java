package Chesspuzzle.model;

import ChessPuzzle.model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectionTest {
    @Test
    void of() {

        assertSame(Direction.K_UP, Direction.of(-1, 0));
        assertSame(Direction.K_DOWN, Direction.of(1, 0));
        assertSame(Direction.K_LEFT, Direction.of(0, -1));
        assertSame(Direction.K_RIGHT, Direction.of(0, 1));
        assertSame(Direction.K_UP_LEFT, Direction.of(-1, -1));
        assertSame(Direction.K_UP_RIGHT, Direction.of(-1, 1));
        assertSame(Direction.K_DOWN_LEFT, Direction.of(1, -1));
        assertSame(Direction.K_DOWN_RIGHT, Direction.of(1, 1));


        assertSame(Direction.L_UP_LEFT, Direction.of(-2, -1));
        assertSame(Direction.L_UP_RIGHT, Direction.of(-2, 1));
        assertSame(Direction.L_DOWN_LEFT, Direction.of(2, -1));
        assertSame(Direction.L_DOWN_RIGHT, Direction.of(2, 1));
        assertSame(Direction.L_LEFT_UP, Direction.of(-1, -2));
        assertSame(Direction.L_LEFT_DOWN, Direction.of(1, -2));
        assertSame(Direction.L_RIGHT_UP, Direction.of(-1, 2));
        assertSame(Direction.L_RIGHT_DOWN, Direction.of(1, 2));
    }

    @Test
    void of_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Direction.of(0, 0));
    }
}
