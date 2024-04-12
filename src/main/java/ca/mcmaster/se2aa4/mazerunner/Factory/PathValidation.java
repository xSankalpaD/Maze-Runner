package ca.mcmaster.se2aa4.mazerunner.Factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathValidation implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();

    private String newPath;

    public PathValidation(String newPath) {
        this.newPath = newPath;

    }

    @Override
    public void runs(Maze maze) {

        logger.info("Validating Path");
        Path path = new Path(newPath);
        if (maze.validatePath(path)) {
            System.out.println("path correct!");
        } else {
            System.out.println("path incorrect!");
        }
    }
}
