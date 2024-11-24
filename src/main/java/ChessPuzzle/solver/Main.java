package ChessPuzzle.solver;

import puzzle.solver.BreadthFirstSearch;
import ChessPuzzle.model.Direction;
import ChessPuzzle.model.PuzzleState;


public class Main {
    public static void main(String[] args) {
        var bfs = new BreadthFirstSearch<Direction>();
        bfs.solveAndPrintSolution(new PuzzleState());
    }
}
