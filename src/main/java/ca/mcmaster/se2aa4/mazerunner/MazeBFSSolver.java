package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;

public class MazeBFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private boolean[][] marked;

    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        marked = new boolean[maze.getSizeY()][maze.getSizeX()];
        logger.debug("Marking entrances...");
        // markEntrances();
        logger.debug("Tracing path...");
        // return tracePath();
        return null;
    }

    private boolean isInBounds(Position position, int sizeX, int sizeY) {
        return position.x() >= 0 && position.x() < sizeX && position.y() >= 0 && position.y() < sizeY;
    }

    private void traversal() {
        Direction currentDir = Direction.RIGHT;
        Position currentCell = maze.getStart();
        Position exit = maze.getEnd();

        Queue<Position> nodes = new ArrayDeque<Position>();
        Queue<Direction> dir = new ArrayDeque<Direction>();

        nodes.add(currentCell);
        dir.add(currentDir);

        while (!currentCell.equals(exit)) {
            currentCell = nodes.remove();
            currentDir = dir.remove();

            if (marked[currentCell.y()][currentCell.x()] != true) {

                marked[currentCell.y()][currentCell.x()] = true;

                Position right = currentCell.move(currentDir.turnRight());
                Position left = currentCell.move(currentDir.turnLeft());
                Position forward = currentCell.move(currentDir);

                int x = maze.getSizeX();
                int y = maze.getSizeY();

                if (isInBounds(right, x, y) && marked[right.y()][right.x()] != true && !maze.isWall(right)) {
                    nodes.add(right);
                    dir.add(currentDir.turnRight());

                }
                if (isInBounds(left, x, y) && marked[left.y()][left.x()] != true && !maze.isWall(left)) {
                    nodes.add(left);
                    dir.add(currentDir.turnLeft());

                }
                if (isInBounds(forward, x, y) && marked[forward.y()][forward.x()] != true && !maze.isWall(forward)) {
                    nodes.add(forward);
                    dir.add(currentDir);

                }

            }
        }

    }

    private void tracing() {

    }
}