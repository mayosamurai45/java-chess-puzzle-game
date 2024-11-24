package ChessPuzzle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the eight direction the king can move in
 * and the eight direction the knight can move in.
 * pieceType 0 represents the king while 1 represents the knight
 * K_UP is meant to be read as KING up 1 while L_UP_LEFT is KNIGHT up 2 left 1
 */
public enum Direction {
    /**
     * King move up.
     */
    K_UP(-1, 0, 0),
    /**
     * King move down.
     */
    K_DOWN(1, 0, 0),
    /**
     * King move left.
     */
    K_LEFT(0, -1, 0),
    /**
     * King move right.
     */
    K_RIGHT(0, 1, 0),
    /**
     * King move up left.
     */
    K_UP_LEFT(-1, -1, 0),
    /**
     * King move up right.
     */
    K_UP_RIGHT(-1, 1, 0),
    /**
     * King move down left.
     */
    K_DOWN_LEFT(1, -1, 0),
    /**
     * King move down right.
     */
    K_DOWN_RIGHT(1, 1, 0),


    /**
     * Knight move up 2 left 1.
     */
    L_UP_LEFT(-2, -1, 1),
    /**
     * Knight move up 2 right 1.
     */
    L_UP_RIGHT(-2, 1, 1),
    /**
     * Knight move down 2 left 1.
     */
    L_DOWN_LEFT(2, -1, 1),
    /**
     * Knight move down 2 right 1.
     */
    L_DOWN_RIGHT(2, 1, 1),
    /**
     * Knight move left 2 up 1.
     */
    L_LEFT_UP(-1, -2, 1),
    /**
     * Knight move left 2 down 1.
     */
    L_LEFT_DOWN(1, -2, 1),
    /**
     * Knight move right 2 up 1.
     */
    L_RIGHT_UP(-1, 2, 1),
    /**
     * Knight move right 2 down 1.
     */
    L_RIGHT_DOWN(1, 2, 1);

    
    private final int rowChange;
    private final int colChange;

    private final int pieceType;

    Direction(int rowChange, int colChange,int pieceType) {
        this.rowChange = rowChange;
        this.colChange = colChange;
        this.pieceType = pieceType;
    }

    /**
     * {@return the change in the row coordinate when moving to the direction.}
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * {@return the change in the column coordinate when moving to the direction.}
     */
    public int getColChange() {
        return colChange;
    }

    /**
     * {@return which piece the move belongs to.}
     */
    public int getPieceType() { return pieceType; }

    /**
     * {@return the direction that corresponds to the coordinate changes specified.}
     *
     * @param rowChange the change in the row coordinate.
     * @param colChange the change in the column coordinate.
     */
    public static Direction of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * {@return  a Direction type ArrayList containing the directions associated with knight or king.}
     * @param pieceType the number that determines which piece type we want the directions for.( 0 is King 1 is Knight).
     */
    public static List<Direction> getDirectionsForPiece(int pieceType) {
        List<Direction> directions = new ArrayList<>();
        for (Direction direction : values()) {
            if (direction.getPieceType() == pieceType) {
                directions.add(direction);
            }
        }
        return directions;
    }

}