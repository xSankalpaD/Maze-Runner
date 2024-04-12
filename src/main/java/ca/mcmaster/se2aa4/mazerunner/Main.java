package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Factory.Benchmarking;
import ca.mcmaster.se2aa4.mazerunner.Factory.Maze;
import ca.mcmaster.se2aa4.mazerunner.Factory.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Factory.PathCalculator;
import ca.mcmaster.se2aa4.mazerunner.Factory.PathValidation;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');
            Maze maze = new Maze(filePath);
            MazeExplorer explorer = decisionMaker(cmd, maze);

            explorer.runs(maze);
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    public static MazeExplorer decisionMaker(CommandLine cmd, Maze maze) {

        if (cmd.getOptionValue("baseline") != null) {
            return (new Benchmarking(cmd.getOptionValue("method"), cmd.getOptionValue("baseline")));
        } else if (cmd.getOptionValue("p") != null) {
            return (new PathValidation(cmd));
        } else {
            return (new PathCalculator(cmd));
        }

    }

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Compare with Method and given algorithms will be used"));
        return options;
    }
}
