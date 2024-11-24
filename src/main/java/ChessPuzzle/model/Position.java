package ChessPuzzle.model;

/**
 * Represents a 2D position.
 * @param row the index of row.
 * @param col the index of column.
 */
public record Position(int row, int col) {

    /**
     * {@return the position whose vertical and horizontal distances from this position are equal to the coordinate changes of the direction given.}
     *
     * @param direction a direction that specifies a change in the coordinates.
     */
    public Position move(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }


    /**
     * {@return a string that contains a position in the following format (row,col).}
     */
    @Override
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}