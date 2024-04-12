package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Factory.Maze;

import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.*;

public class PathCalculatorTest {
    Maze myMaze;

    @Test
    void RHTest() {
        try {
            myMaze = new Maze("./examples/medium.maz.txt");
        } catch (Exception IOException) {
            assertFalse(true);
        }

        pathTester(new RightHandSolver());

    }

    @Test
    void BFSTest() {
        try {
            myMaze = new Maze("./examples/medium.maz.txt");
        } catch (Exception IOException) {
            assertFalse(true);
        }
        pathTester(new MazeBFSSolver());

    }

    @Test
    void TremauxTest() {
        try {
            myMaze = new Maze("./examples/medium.maz.txt");
        } catch (Exception IOException) {
            assertFalse(true);
        }

        pathTester(new TremauxSolver());

    }

    private void pathTester(MazeSolver solver) {
        Path myPath = solver.solve(myMaze);

        assertTrue(myMaze.validatePath(myPath));

    }

}
