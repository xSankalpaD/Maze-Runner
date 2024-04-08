package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Benchmarking {
    private static final Logger logger = LogManager.getLogger();

    public Benchmarking(String method, String baseline, Maze maze) {
        MazeSolver solver1;
        MazeSolver solver2;
        Path path1;
        Path path2;

        long mTimeStart;
        long mTimeEnd;
        float methodTime;

        long bTimeStart;
        long bTimeEnd;
        float baselineTime;

        float Speedup;

        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand method chosen.");
                solver1 = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux method chosen.");
                solver1 = new TremauxSolver();
            }

            case "bfs" -> {
                logger.debug("Breadth First Search method chosen.");
                solver1 = new MazeBFSSolver();
            }
            default -> {
                throw new RuntimeException("Maze solving method '" + method + "' not supported.");
            }
        }

        switch (baseline) {
            case "righthand" -> {
                logger.debug("RightHand method chosen.");
                solver2 = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux method chosen.");
                solver2 = new TremauxSolver();
            }

            case "bfs" -> {
                logger.debug("Breadth First Search method chosen.");
                solver2 = new MazeBFSSolver();
            }
            default -> {
                throw new RuntimeException("Maze solving baseline '" + baseline + "' not supported.");
            }
        }

        logger.info("Benchmarking");

        mTimeStart = System.nanoTime();
        path1 = solver1.solve(maze);
        mTimeEnd = System.nanoTime();
        methodTime = (mTimeEnd - mTimeStart) / 1000000f;
        System.out.printf("Runtime %s = %.2f ms\n", method, methodTime);

        bTimeStart = System.nanoTime();
        path2 = solver2.solve(maze);
        bTimeEnd = System.nanoTime();
        baselineTime = (bTimeEnd - bTimeStart) / 1000000f;
        System.out.printf("Runtime %s = %.2f ms\n", baseline, baselineTime);

        Speedup = (path2.getCanonicalForm().length()) * 1f / (path1.getCanonicalForm().length());
        System.out.printf("Runtime Speedup = %.2f\n", Speedup);
    }

}
