package Chesspuzzle.model;

import ChessPuzzle.model.Direction;
import ChessPuzzle.model.Position;
import ChessPuzzle.model.PuzzleState;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

import static ChessPuzzle.model.PuzzleState.*;

public class PuzzleStateTest {
    PuzzleState state1 = new PuzzleState(); // the original initial state

    PuzzleState state2 = new PuzzleState(
            new Position(7, 6),
            new Position(4, 7),
            new Position(7, 6)); // a goal state

    PuzzleState state3 = new PuzzleState(
            new Position(5, 1),
            new Position(4, 0),
            new Position(7, 6)); // a non-goal state

    PuzzleState state4 = new PuzzleState(
            new Position(5, 1),
            new Position(4, 4),
            new Position(7, 6)); // a dead-end state with no legal moves
    PuzzleState state5 = new PuzzleState(
            new Position(5, 1),
            new Position(3, 2),
            new Position(7, 6)); // king free to move in any direction state

    PuzzleState state6 = new PuzzleState(
            new Position(7, 0),
            new Position(5, 1),
            new Position(7, 6)); // king is in left down corner

    PuzzleState state7 = new PuzzleState(
            new Position(0, 7),
            new Position(2, 6),
            new Position(7, 6)); // king is in right up corner

    @Test
    void constructor() {
        var positions = new Position[] {
                new Position(5, 1),
                new Position(5, 2),
                new Position(7, 6)
        };
        PuzzleState state = new PuzzleState(positions);
        for (var i = 0; i < 3; i++) {
            assertEquals(positions[i], state.getPosition(i));
        }
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PuzzleState(
                new Position(0, 0)));
        assertThrows(IllegalArgumentException.class, () -> new PuzzleState(
                new Position(0, 0),
                new Position(1, 1),
                new Position(8, 8)));
        assertThrows(IllegalArgumentException.class, () -> new PuzzleState(
                new Position(1, 1),
                new Position(1, 1),
                new Position(1, 1)));
    }

    @Test
    void isSolved() {
        assertFalse(state1.isSolved());
        assertTrue(state2.isSolved());
        assertFalse(state3.isSolved());
        assertFalse(state4.isSolved());
    }

    @Test
    void isFailed() {
        assertFalse(state1.isfailed());
        assertTrue(state2.isfailed());
        assertFalse(state3.isfailed());
        assertTrue(state4.isfailed());
    }

    //!!!
    @Test
    void isLegalMove_state1() {
        assertFalse(state1.isLegalMove(Direction.K_UP));
        assertFalse(state1.isLegalMove(Direction.K_DOWN));
        assertFalse(state1.isLegalMove(Direction.K_LEFT));
        assertFalse(state1.isLegalMove(Direction.K_RIGHT));
        assertFalse(state1.isLegalMove(Direction.K_UP_LEFT));
        assertFalse(state1.isLegalMove(Direction.K_UP_RIGHT));
        assertFalse(state1.isLegalMove(Direction.K_DOWN_LEFT));
        assertFalse(state1.isLegalMove(Direction.K_DOWN_RIGHT));
        assertTrue(state1.isLegalMove(Direction.L_UP_LEFT));
        assertTrue(state1.isLegalMove(Direction.L_UP_RIGHT));
        assertTrue(state1.isLegalMove(Direction.L_DOWN_LEFT));
        assertTrue(state1.isLegalMove(Direction.L_DOWN_RIGHT));
        assertTrue(state1.isLegalMove(Direction.L_LEFT_UP));
        assertTrue(state1.isLegalMove(Direction.L_LEFT_DOWN));
        assertTrue(state1.isLegalMove(Direction.L_RIGHT_UP));
        assertTrue(state1.isLegalMove(Direction.L_RIGHT_DOWN));
    }


    @Test
    void isLegalMove_state2() {
        assertFalse(state2.isLegalMove(Direction.K_UP));
        assertFalse(state2.isLegalMove(Direction.K_DOWN));
        assertFalse(state2.isLegalMove(Direction.K_LEFT));
        assertFalse(state2.isLegalMove(Direction.K_RIGHT));
        assertFalse(state2.isLegalMove(Direction.K_UP_LEFT));
        assertFalse(state2.isLegalMove(Direction.K_UP_RIGHT));
        assertFalse(state2.isLegalMove(Direction.K_DOWN_LEFT));
        assertFalse(state2.isLegalMove(Direction.K_DOWN_RIGHT));
        assertFalse(state2.isLegalMove(Direction.L_UP_LEFT));
        assertFalse(state2.isLegalMove(Direction.L_UP_RIGHT));
        assertFalse(state2.isLegalMove(Direction.L_DOWN_LEFT));
        assertFalse(state2.isLegalMove(Direction.L_DOWN_RIGHT));
        assertFalse(state2.isLegalMove(Direction.L_LEFT_UP));
        assertFalse(state2.isLegalMove(Direction.L_LEFT_DOWN));
        assertFalse(state2.isLegalMove(Direction.L_RIGHT_UP));
        assertFalse(state2.isLegalMove(Direction.L_RIGHT_DOWN));
    }
    @Test
    void isLegalMove_state3() {
        assertFalse(state3.isLegalMove(Direction.K_UP));
        assertFalse(state3.isLegalMove(Direction.K_DOWN));
        assertFalse(state3.isLegalMove(Direction.K_LEFT));
        assertFalse(state3.isLegalMove(Direction.K_RIGHT));
        assertFalse(state3.isLegalMove(Direction.K_UP_LEFT));
        assertFalse(state3.isLegalMove(Direction.K_UP_RIGHT));
        assertFalse(state3.isLegalMove(Direction.K_DOWN_LEFT));
        assertFalse(state3.isLegalMove(Direction.K_DOWN_RIGHT));
        assertFalse(state3.isLegalMove(Direction.L_UP_LEFT));
        assertTrue(state3.isLegalMove(Direction.L_UP_RIGHT));
        assertFalse(state3.isLegalMove(Direction.L_DOWN_LEFT));
        assertTrue(state3.isLegalMove(Direction.L_DOWN_RIGHT));
        assertFalse(state3.isLegalMove(Direction.L_LEFT_UP));
        assertFalse(state3.isLegalMove(Direction.L_LEFT_DOWN));
        assertTrue(state3.isLegalMove(Direction.L_RIGHT_UP));
        assertTrue(state3.isLegalMove(Direction.L_RIGHT_DOWN));
    }
    @Test
    void isLegalMove_state4() {
        assertFalse(state4.isLegalMove(Direction.K_UP));
        assertFalse(state4.isLegalMove(Direction.K_DOWN));
        assertFalse(state4.isLegalMove(Direction.K_LEFT));
        assertFalse(state4.isLegalMove(Direction.K_RIGHT));
        assertFalse(state4.isLegalMove(Direction.K_UP_LEFT));
        assertFalse(state4.isLegalMove(Direction.K_UP_RIGHT));
        assertFalse(state4.isLegalMove(Direction.K_DOWN_LEFT));
        assertFalse(state4.isLegalMove(Direction.K_DOWN_RIGHT));
        assertFalse(state4.isLegalMove(Direction.L_UP_LEFT));
        assertFalse(state4.isLegalMove(Direction.L_UP_RIGHT));
        assertFalse(state4.isLegalMove(Direction.L_DOWN_LEFT));
        assertFalse(state4.isLegalMove(Direction.L_DOWN_RIGHT));
        assertFalse(state4.isLegalMove(Direction.L_LEFT_UP));
        assertFalse(state4.isLegalMove(Direction.L_LEFT_DOWN));
        assertFalse(state4.isLegalMove(Direction.L_RIGHT_UP));
        assertFalse(state4.isLegalMove(Direction.L_RIGHT_DOWN));
    }



@Test
void getLegalMoves() {
    assertEquals(EnumSet.of(Direction.L_UP_RIGHT,Direction.L_UP_LEFT,Direction.L_DOWN_LEFT,Direction.L_DOWN_RIGHT,Direction.L_LEFT_UP,Direction.L_LEFT_DOWN,Direction.L_RIGHT_UP,Direction.L_RIGHT_DOWN), state1.getLegalMoves());
    assertEquals(EnumSet.noneOf(Direction.class), state2.getLegalMoves());
    assertEquals(EnumSet.of(Direction.L_UP_RIGHT,Direction.L_DOWN_RIGHT,Direction.L_RIGHT_UP,Direction.L_RIGHT_DOWN), state3.getLegalMoves());
    assertEquals(EnumSet.noneOf(Direction.class), state4.getLegalMoves());
    assertEquals(EnumSet.of(Direction.K_UP,Direction.K_DOWN, Direction.K_LEFT, Direction.K_RIGHT, Direction.K_UP_LEFT, Direction.K_UP_RIGHT, Direction.K_DOWN_LEFT,Direction.K_DOWN_RIGHT), state5.getLegalMoves());
    assertEquals(EnumSet.of(Direction.K_UP,Direction.K_RIGHT,Direction.K_UP_RIGHT), state6.getLegalMoves());
    assertEquals(EnumSet.of(Direction.K_DOWN,Direction.K_LEFT,Direction.K_DOWN_LEFT), state7.getLegalMoves());
}



    @Test
    void testEquals() {
        assertTrue(state1.equals(state1));

        var clone = state1.clone();
        clone.makeMove(Direction.L_RIGHT_UP);
        assertFalse(clone.equals(state1));

        assertFalse(state1.equals(null));
        assertFalse(state1.equals("Hello, World!"));
        assertFalse(state1.equals(state2));
    }

    @Test
    void testHashCode() {
        assertTrue(state1.hashCode() == state1.hashCode());
        assertTrue(state1.hashCode() == state1.clone().hashCode());
    }

    @Test
    void testClone() {
        var clone = state1.clone();
        assertTrue(clone.equals(state1));
        assertNotSame(clone, state1);
    }

    @Test
    void testToString() {
        assertEquals("[(5,1),(5,2),(7,6)]", state1.toString());
        assertEquals("[(7,6),(4,7),(7,6)]", state2.toString());
        assertEquals("[(5,1),(4,0),(7,6)]", state3.toString());
        assertEquals("[(5,1),(4,4),(7,6)]", state4.toString());
    }
}

