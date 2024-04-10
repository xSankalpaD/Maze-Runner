package ca.mcmaster.se2aa4.mazerunner.Factory;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.MazeBFSSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.TremauxSolver;

public class Benchmarking implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private String method;
    private String baseline;
    private Maze maze;

    public Benchmarking(String method, String baseline) {
        this.method = method;
        this.baseline = baseline;
        // this.maze = maze;

    }

    @Override
    public void runs(Maze maze) {

        MazeSolver solveMethod, solveBase;
        Path pathMethod, pathBase;

        long startTimeMethod, endTimeMethod, startTimeBase, endTimeBase;
        float methodTime, baseTime;
        float speedUp;

        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand method chosen.");
                solveMethod = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux method chosen.");
                solveMethod = new TremauxSolver();
            }

            case "BFS" -> {
                logger.debug("BFS method chosen.");
                solveMethod = new MazeBFSSolver();
            }
            default -> {
                throw new RuntimeException("Maze solving method '" + method + "' not supported.");
            }
        }

        switch (baseline) {
            case "righthand" -> {
                logger.debug("RightHand method chosen.");
                solveBase = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux method chosen.");
                solveBase = new TremauxSolver();
            }

            case "BFS" -> {
                logger.debug("BFS method chosen.");
                solveBase = new MazeBFSSolver();
            }
            default -> {
                throw new RuntimeException("Maze solving baseline '" + baseline + "' not supported.");
            }
        }

        System.out.println("Start Benchmarking...");

        startTimeMethod = System.nanoTime();
        pathMethod = solveMethod.solve(maze);
        endTimeMethod = System.nanoTime();

        methodTime = (endTimeMethod - startTimeMethod) / 1000000f;
        System.out.println("Runtime for " + method + ": " + String.format("%.2f", methodTime) + " ms");

        startTimeBase = System.nanoTime();
        pathBase = solveBase.solve(maze);
        endTimeBase = System.nanoTime();
        baseTime = (endTimeBase - startTimeBase) / 1000000f;
        System.out.println("Runtime for " + baseline + ": " + String.format("%.2f", baseTime) + " ms");

        speedUp = (pathBase.getCanonicalForm().length()) * 1f / (pathMethod.getCanonicalForm().length());
        System.out.println("Runtime for Speedup : " + String.format("%.2f", speedUp));
    }
}
