package ca.mcmaster.se2aa4.mazerunner.Factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.MazeBFSSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.TremauxSolver;

public class Benchmarking implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private String method;
    private String baseline;
    private Path pathMethod;
    private Path pathBase;
    private float speedUp;

    public Benchmarking(String method, String baseline) {
        this.method = method;
        this.baseline = baseline;
    }

    @Override
    public void runs(Maze maze) {

        MazeSolver solveMethod;
        MazeSolver solveBase;

        switch (method) {
            case "righthand":
                logger.debug("RightHand method chosen.");
                solveMethod = new RightHandSolver();
                break;
            case "tremaux":
                logger.debug("Tremaux method chosen.");
                solveMethod = new TremauxSolver();
                break;
            case "BFS":
                logger.debug("BFS method chosen.");
                solveMethod = new MazeBFSSolver();
                break;
            default:
                throw new RuntimeException("Maze solving method '" + method + "' not supported.");
        }

        switch (baseline) {
            case "righthand":
                logger.debug("RightHand method chosen.");
                solveBase = new RightHandSolver();
                break;
            case "tremaux":
                logger.debug("Tremaux method chosen.");
                solveBase = new TremauxSolver();
                break;
            case "BFS":
                logger.debug("BFS method chosen.");
                solveBase = new MazeBFSSolver();
                break;
            default:
                throw new RuntimeException("Maze solving baseline '" + baseline + "' not supported.");
        }

        logger.debug("Start Benchmarking...");

        long startTimeMethod = System.nanoTime();
        pathMethod = solveMethod.solve(maze);
        long endTimeMethod = System.nanoTime();

        float methodTime = (endTimeMethod - startTimeMethod) / 1000000f;
        System.out.println("Runtime for " + method + ": " + String.format("%.2f", methodTime) + " ms");

        long startTimeBase = System.nanoTime();
        pathBase = solveBase.solve(maze);
        long endTimeBase = System.nanoTime();

        float baseTime = (endTimeBase - startTimeBase) / 1000000f;
        System.out.println("Runtime for " + baseline + ": " + String.format("%.2f", baseTime) + " ms");

        speedUp = (pathBase.getCanonicalForm().length() * 1f) / (pathMethod.getCanonicalForm().length());
        System.out.println("Runtime for Speedup : " + String.format("%.2f", speedUp));
    }

    public Path getPathBase() {
        return pathBase;
    }

    public float getSpeedUp() {
        return speedUp;
    }
}
