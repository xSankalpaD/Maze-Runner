package ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Node;
import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.Factory.Maze;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class MazeBFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private boolean[][] visited;

    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        visited = new boolean[maze.getSizeY()][maze.getSizeX()];
        logger.debug("Searching through the maze...");

        Node exitNode = traversal();

        logger.debug("Tracing path...");

        return findPath(exitNode);
    }

    private Node traversal() {
        Direction currentDir = Direction.RIGHT;
        Position currentCell = maze.getStart();
        Position exit = maze.getEnd();
        Node root = new Node(currentCell, currentDir, 'S'); // S for strating pos
        Queue<Node> queue = new ArrayDeque<Node>();

        queue.add(root);

        Node currentNode = root;
        while (!currentCell.equals(exit) && !queue.isEmpty()) {
            currentNode = queue.remove();

            currentCell = currentNode.getPosition();
            currentDir = currentNode.getDirection();

            if (visited[currentCell.y()][currentCell.x()] != true) {

                visited[currentCell.y()][currentCell.x()] = true;

                Position right = currentCell.move(currentDir.turnRight());
                Position left = currentCell.move(currentDir.turnLeft());
                Position forward = currentCell.move(currentDir);

                int x = maze.getSizeX();
                int y = maze.getSizeY();

                if (isInBounds(right, x, y) && visited[right.y()][right.x()] != true && !maze.isWall(right)) {

                    Node rightNode = new Node(right, currentDir.turnRight(), 'R');
                    queue.add(rightNode);
                    currentNode.addRightNode(rightNode);

                }
                if (isInBounds(left, x, y) && visited[left.y()][left.x()] != true && !maze.isWall(left)) {

                    Node leftNode = new Node(left, currentDir.turnLeft(), 'L');
                    queue.add(leftNode);
                    currentNode.addLeftNode(leftNode);

                }
                if (isInBounds(forward, x, y) && visited[forward.y()][forward.x()] != true && !maze.isWall(forward)) {

                    Node forwardNode = new Node(forward, currentDir, 'F');
                    queue.add(forwardNode);
                    currentNode.addForwardNode(forwardNode);
                }

            }
        }
        if (!currentCell.equals(exit)) {
            throw new RuntimeException("Maze is Invalid!");
        }

        return currentNode;

    }

    private Path findPath(Node exitNode) {
        Stack<Character> path = new Stack<Character>();
        Path finalPath = new Path();
        Node runner = exitNode;

        while (runner.getMovement() != 'S') {
            path.add('F');
            if (runner.getMovement() == 'L' || runner.getMovement() == 'R') {
                path.add(runner.getMovement());
            }

            runner = runner.getRoot();

        }
        while (!path.isEmpty())
            finalPath.addStep(path.pop());

        return finalPath;
    }

    private boolean isInBounds(Position position, int sizeX, int sizeY) {
        return position.x() >= 0 && position.x() < sizeX && position.y() >= 0 && position.y() < sizeY;
    }

}