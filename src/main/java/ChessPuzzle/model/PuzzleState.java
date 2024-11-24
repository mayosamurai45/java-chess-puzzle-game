package ChessPuzzle.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import org.tinylog.Logger;
import puzzle.State;


import java.util.*;

import static ChessPuzzle.model.Direction.getDirectionsForPiece;

/**
 * Represents the state of the puzzle.
 */
public class PuzzleState implements State<Direction> {

    /**
     * The size of the board.
     */
    public static final int BOARD_SIZE = 8;

    /**
     * The index of the king.
     */
    public static final int KING = 0;

    /**
     * The index of the knight.
     */
    public static final int KNIGHT = 1;

    /**
     * The index of the target.
     */
    public static final int TARGET = 2;


    private boolean failed=false;

    private final ReadOnlyObjectWrapper<Position>[] positions;

    private IntegerProperty ACTIVE_PIECE;


    /**
     * Creates a {@code PuzzleState} object that corresponds to the original
     * initial state of the puzzle.
     */
    public PuzzleState() {
        this(   new Position(5, 1),
                new Position(5, 2),
                new Position(7, 6)
        );
    }

    /**
     * Creates a {@code PuzzleState} object initializing the positions of the
     * pieces with the positions specified. The constructor expects an array of
     * three {@code Position} objects or three {@code Position} objects.
     *
     * @param positions the initial positions of the pieces.
     */
    public PuzzleState(Position... positions) {
        checkPositions(positions);
        this.positions = new ReadOnlyObjectWrapper[3];
        for (var i = 0; i < 3; i++) {
            this.positions[i] = new ReadOnlyObjectWrapper<>(positions[i]);
        }
        ACTIVE_PIECE= new SimpleIntegerProperty();
        ACTIVE_PIECE.set(-1);
        decideActivePiece();


    }

    private void checkPositions(Position[] positions) {
        if (positions.length != 3) {
            throw new IllegalArgumentException();
        }
        for (var position : positions) {
            if (!isOnBoard(position)) {
                throw new IllegalArgumentException();
            }
        }
        if (positions[KNIGHT].equals(positions[KING])) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return a copy of the position of the piece specified
     *
     * @param index the index of a piece or the target.
     */
    public Position getPosition(int index) {
        return positions[index].get();
    }

    /**
     *
     * @param index the index of a piece or the target.
     * @return a ReadOnly coordinate of the target or piece specified with the index
     */
    public ReadOnlyObjectProperty<Position> positionProperty(int index) {
        return positions[index].getReadOnlyProperty();
    }

    /**
     * {@return whether the puzzle is solved.}
     */
    public boolean isSolved() {
        if (this.positions[KING].get().equals(this.positions[TARGET].get())){

            return true;


        }
        if (this.positions[KNIGHT].get().equals(this.positions[TARGET].get())){
            return true;

        }
        return false;
    }

    /**
     * {@return whether the puzzle has failed.}
     */
    public boolean isfailed() {
        return failed;
    }


    /**
     * Sets the currently active piece.
     * @param activePiece decides which piece we are setting the active to be.
     */
    public void setActivePiece(int activePiece) {
        this.ACTIVE_PIECE.set(activePiece);
    }

    /**
     * {@return the currently active piece.}
     */
    public int getActivePiece(){return this.ACTIVE_PIECE.get();}

    /**
     * {@return whether the active piece can be moved to the direction specified.}
     *
     * @param direction a direction to which the active piece is intended to be moved.
     */

    public boolean isLegalMove(Direction direction) {
        if (this.ACTIVE_PIECE.get()==-1){
            return false;
        }
        if (this.ACTIVE_PIECE.get() == KING) {
            return switch (direction) {
                case K_UP -> kingCanMoveUp();
                case K_RIGHT -> kingCanMoveRight();
                case K_DOWN -> kingCanMoveDown();
                case K_LEFT -> kingCanMoveLeft();
                case K_UP_LEFT -> kingCanMoveUpLeft();
                case K_UP_RIGHT -> kingCanMoveUpRight();
                case K_DOWN_LEFT -> kingCanMoveDownLeft();
                case K_DOWN_RIGHT -> kingCanMoveDownRight();
                default -> false;
            };
        } else if (this.ACTIVE_PIECE.get() == KNIGHT) {
            return switch (direction) {
                case L_UP_LEFT -> knightCanMoveUpLeft();
                case L_UP_RIGHT -> knightCanMoveUpRight();
                case L_DOWN_LEFT -> knightCanMoveDownLeft();
                case L_DOWN_RIGHT -> knightCanMoveDownRight();
                case L_LEFT_UP -> knightCanMoveLeftUp();
                case L_LEFT_DOWN -> knightCanMoveLeftDown();
                case L_RIGHT_UP -> knightCanMoveRightUp();
                case L_RIGHT_DOWN -> knightCanMoveRightDown();
                default -> false;
            };
        }

        return false;

    }
    private boolean kingCanMoveUp() {
        return getPosition(KING).row() > 0;
    }
    private boolean kingCanMoveDown() {
        return getPosition(KING).row() < BOARD_SIZE - 1;
    }
    private boolean kingCanMoveLeft() {
        return getPosition(KING).col() > 0 ;
    }
    private boolean kingCanMoveRight() {
        return getPosition(KING).col() < BOARD_SIZE - 1 ;
    }
    private boolean kingCanMoveUpLeft() {
        return getPosition(KING).row() > 0 && getPosition(KING).col() > 0 ;
    }
    private boolean kingCanMoveUpRight() {return getPosition(KING).row() > 0 && getPosition(KING).col() < BOARD_SIZE - 1 ;}
    private boolean kingCanMoveDownLeft() {return getPosition(KING).row() < BOARD_SIZE - 1 && getPosition(KING).col() > 0;}
    private boolean kingCanMoveDownRight() {return getPosition(KING).row() < BOARD_SIZE - 1 && getPosition(KING).col() < BOARD_SIZE - 1;}
    private boolean knightCanMoveUpLeft() {
        return getPosition(KNIGHT).row() > 1 && getPosition(KNIGHT).col() > 0 ;
    }
    private boolean knightCanMoveUpRight() {return getPosition(KNIGHT).row() > 1 && getPosition(KNIGHT).col() < BOARD_SIZE - 1 ;}
    private boolean knightCanMoveRightUp() {return getPosition(KNIGHT).row() > 0 && getPosition(KNIGHT).col() < BOARD_SIZE - 2;}
    private boolean knightCanMoveRightDown() {return getPosition(KNIGHT).row() < BOARD_SIZE - 1 && getPosition(KNIGHT).col() < BOARD_SIZE - 2 ;}
    private boolean knightCanMoveDownLeft() {return getPosition(KNIGHT).row() < BOARD_SIZE - 2 && getPosition(KNIGHT).col() > 0 ;}
    private boolean knightCanMoveDownRight() {return getPosition(KNIGHT).row() < BOARD_SIZE - 2 && getPosition(KNIGHT).col() < BOARD_SIZE - 1 ;}
    private boolean knightCanMoveLeftUp() {
        return getPosition(KNIGHT).row() > 0 && getPosition(KNIGHT).col() > 1 ;
    }
    private boolean knightCanMoveLeftDown() {return getPosition(KNIGHT).row() < BOARD_SIZE - 1 && getPosition(KNIGHT).col() > 1 ;}


    /**
     * Decides which piece should be currently active. If the answer is neither king nor knight then the puzzle fails.
     * It uses {@code getDirectionsForPiece()} to iterate through the moves belonging to a certain piece.
     */
    public void decideActivePiece(){
        List<Direction> kingpossiblemoves=getDirectionsForPiece(KING);
        for (var possiblemove : kingpossiblemoves){
            if(!isEmpty(getPosition(KING).move(possiblemove)))
            {
              setActivePiece(1);
              return;
            }
        }

        List<Direction> knightpossiblemoves = getDirectionsForPiece(KNIGHT);
        for (var possiblemove : knightpossiblemoves){
                if (!isEmpty(getPosition(KNIGHT).move(possiblemove))){
                    setActivePiece(0);
                    return;
                }
            }

        if (!isSolved() || ACTIVE_PIECE.get()==-1){failed=true;}

    }


    /**
     * Moves the active piece to the direction specified.
     *
     * @param direction the direction to which the active piece is moved.
     */
    public void makeMove(Direction direction) {

            switch (direction)
            {
            case K_UP -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_UP);
            case K_DOWN -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_DOWN);
            case K_LEFT -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_LEFT);
            case K_RIGHT -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_RIGHT);
            case K_UP_LEFT -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_UP_LEFT);
            case K_UP_RIGHT -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_UP_RIGHT);
            case K_DOWN_LEFT -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_DOWN_LEFT);
            case K_DOWN_RIGHT -> movePiece(this.ACTIVE_PIECE.get(), Direction.K_DOWN_RIGHT);
            }
            switch (direction)
            {
                case L_UP_LEFT -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_UP_LEFT);
                case L_UP_RIGHT -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_UP_RIGHT);
                case L_DOWN_LEFT -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_DOWN_LEFT);
                case L_DOWN_RIGHT -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_DOWN_RIGHT);
                case L_LEFT_UP -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_LEFT_UP);
                case L_LEFT_DOWN -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_LEFT_DOWN);
                case L_RIGHT_UP -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_RIGHT_UP);
                case L_RIGHT_DOWN -> movePiece(this.ACTIVE_PIECE.get(), Direction.L_RIGHT_DOWN);
            }
        decideActivePiece();
    }

    private void movePiece(int index, Direction direction) {
        if (index<0)
        {
            return;
        }
        var newPosition = getPosition(index).move(direction);
        positions[index].set(newPosition);
    }

    /**
     * {@return the set of directions to which the active piece can be moved.}
     */
    public Set<Direction> getLegalMoves() {
        var legalMoves = EnumSet.noneOf(Direction.class);
        for (var direction : Direction.values()) {
            if (isLegalMove(direction)) {
                legalMoves.add(direction);
            }
        }
        return legalMoves;
    }


    private boolean isOnBoard(Position position) {
        return position.row() >= 0 && position.row() < BOARD_SIZE &&
                position.col() >= 0 && position.col() < BOARD_SIZE;
    }


    private boolean isEmpty(Position position) {

        if(position.equals(positions[KING].get())){
            return false;
        }
        if (position.equals(positions[KNIGHT].get())){
            return  false;
        }
        return true;

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof PuzzleState other)
                && getPosition(KING).equals(other.getPosition(KING))
                && getPosition(KNIGHT).equals(other.getPosition(KNIGHT))
                && getPosition(TARGET).equals(other.getPosition(TARGET));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(KING), getPosition(KNIGHT),
                getPosition(TARGET));
    }

    @Override
    public PuzzleState clone() {
        return new PuzzleState(getPosition(KING), getPosition(KNIGHT),
                getPosition(TARGET));
    }

    @Override
    public String toString() {
        var sj = new StringJoiner(",", "[", "]");
        for (var position : positions) {
            sj.add(position.get().toString());
        }
        return sj.toString();
    }

}
