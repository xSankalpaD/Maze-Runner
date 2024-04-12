package ca.mcmaster.se2aa4.mazerunner.Factory;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.MazeBFSSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.MazeAlgorithms.TremauxSolver;

public class PathCalculator implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private CommandLine cmd;

    public PathCalculator(CommandLine cmd) {
        this.cmd = cmd;

    }

    @Override
    public void runs(Maze maze) throws RuntimeException {

        String method = cmd.getOptionValue("method", "righthand");
        Path path = solveMaze(method, maze);
        System.out.println(path.getFactorizedForm());
    }

    public static Path solveMaze(String method, Maze maze) throws RuntimeException {
        MazeSolver solver = null;
        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "BFS" -> {
                logger.debug("BFS Algorithm chosen.");
                solver = new MazeBFSSolver();

            }
            default -> {
                throw new RuntimeException("Maze solving method '" + method + "' not supported.");
            }
        }

        logger.info("Computing path");
        return solver.solve(maze);
    }
}
