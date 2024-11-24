package ChessPuzzle.game;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import org.tinylog.Logger;

import ChessPuzzle.model.Direction;
import ChessPuzzle.model.PuzzleState;
import util.javafx.ImageStorage;
import util.OrdinalImageStorage;

import java.util.Optional;

/**
 * The GameController class handles the game logic and user interactions
 *  for the Chess puzzle application.
 */
public class GameController {
    private static final ImageStorage<Integer> imageStorage = new OrdinalImageStorage(GameController.class,
            "king.png",
            "knight.png",
            "target.png");

    @FXML
    private GridPane grid;

    @FXML
    private TextField numberOfMovesField;

    @FXML
    private TextField activepieceText;


    private PuzzleState state;

    private final IntegerProperty numberOfMoves = new SimpleIntegerProperty(0);

    @FXML
    private void initialize() {
        bindNumberOfMoves();
        restartGame();
        setActivepieceText();
    }

    private void bindNumberOfMoves() {
        numberOfMovesField.textProperty().bind(numberOfMoves.asString());
    }

    private void setActivepieceText(){
        if(this.state.getActivePiece()==0){ activepieceText.setText("King");}
        else if (this.state.getActivePiece()==1) {activepieceText.setText("Knight");}
        else{activepieceText.setText("NONE");}
    }

    private void restartGame() {
        createState();
        numberOfMoves.set(0);
        clearAndPopulateGrid();

    }

    private void createState() {
        state = new PuzzleState();
        getDetailsOfCurrentState();

    }

    private void getDetailsOfCurrentState()
    {
        this.state.decideActivePiece();
        if(this.state.isSolved()){
            Platform.runLater(this::showSolvedAlert);

        }
        else if(this.state.isfailed()){
            Platform.runLater(this::showFailedAlert);

        };
    }


    private void showSolvedAlert() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Congratulations!");
        alert.setContentText("You have solved the puzzle!");
        alert.showAndWait();
        restartGame();
    }
    private void showFailedAlert() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Game Over");
        alert.setContentText("Unfortunately, you have failed the puzzle!");
        alert.showAndWait();
        restartGame();
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var source = (Node) event.getSource();
        var row = GridPane.getRowIndex(source);
        var col = GridPane.getColumnIndex(source);
        Logger.debug("Click on square ({},{})", row, col);
        getDirectionFromClick(row, col).ifPresentOrElse(this::makeMoveIfLegal,
                () -> Logger.warn("Click does not correspond to any of the directions"));
    }

    private void makeMoveIfLegal(Direction direction) {
        if (state.isLegalMove(direction)) {
            Logger.info("Moving {}", direction);
            state.makeMove(direction);
            Logger.trace("New state after move: {}", state);
            numberOfMoves.set(numberOfMoves.get() + 1);
        } else {
            Logger.warn("Illegal move: {}", direction);
        }
        getDetailsOfCurrentState();
        setActivepieceText();
    }


    private void clearAndPopulateGrid() {
        grid.getChildren().clear();
        for (var row = 0; row < grid.getRowCount(); row++) {
            for (var col = 0; col < grid.getColumnCount(); col++) {
                var square = createSquare(row, col);
                grid.add(square, col, row);
            }
        }
    }

    private StackPane createSquare(int row, int col) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.getStyleClass().add((row + col) % 2 == 0 ? "light": "dark");
        for (var i = 0; i < 3; i++) {
            var imageView = createImageViewForPieceOnPosition(i, row, col);
            square.getChildren().add(imageView);
        }
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private ImageView createImageViewForPieceOnPosition(int index, int row, int col) {
        var imageView = new ImageView(imageStorage.get(index).orElseThrow());
        imageView.visibleProperty().bind(createBindingToCheckPieceIsOnPosition(index, row, col));
        return imageView;
    }

    private BooleanBinding createBindingToCheckPieceIsOnPosition(int index, int row, int col) {
        return new BooleanBinding() {
            {
                super.bind(state.positionProperty(index));
            }
            @Override
            protected boolean computeValue() {
                var position = state.getPosition(index);
                return position.row() == row && position.col() == col;
            }
        };
    }


    private Optional<Direction> getDirectionFromClick(int row, int col) {
        int activePiece = state.getActivePiece();
        var positionofActivePiece = state.getPosition(activePiece);
        try {
            return Optional.of(Direction.of(row - positionofActivePiece.row(), col - positionofActivePiece.col()));
        } catch (IllegalArgumentException e) {
            // The click does not correspond to any of the four directions
        }
        return Optional.empty();
    }
}
