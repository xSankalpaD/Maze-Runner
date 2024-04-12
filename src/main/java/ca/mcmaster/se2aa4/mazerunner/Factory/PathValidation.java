package ca.mcmaster.se2aa4.mazerunner.Factory;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathValidation implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private CommandLine cmd;

    public PathValidation(CommandLine cmd) {
        this.cmd = cmd;

    }

    @Override
    public void runs(Maze maze) {

        logger.info("Validating Path");
        Path path = new Path(cmd.getOptionValue("p"));
        if (maze.validatePath(path)) {
            System.out.println("path correct!");
        } else {
            System.out.println("path incorrect!");
        }
    }
}
