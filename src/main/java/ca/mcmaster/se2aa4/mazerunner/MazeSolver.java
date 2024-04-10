package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Factory.Maze;

public interface MazeSolver {
    /**
     * Solve maze and return path through maze.
     *
     * @param maze Maze to solve
     * @return Path that solves the provided maze
     */
    Path solve(Maze maze);
}
