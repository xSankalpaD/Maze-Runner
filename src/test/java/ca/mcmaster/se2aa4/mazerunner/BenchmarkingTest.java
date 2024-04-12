package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Factory.Maze;
import ca.mcmaster.se2aa4.mazerunner.Factory.Benchmarking;
import ca.mcmaster.se2aa4.mazerunner.Path;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BenchmarkingTest {
    Maze maze;

    @Test
    void testSpeedUpCalculation() {
        // Create a mock maze for testing
        Maze mockMaze = maze;

        // Create a Benchmarking instance with BFS as the method and baseline
        Benchmarking benchmarking = new Benchmarking("BFS", "BFS");

        // Run the benchmarking on the mock maze
        benchmarking.runs(mockMaze);

        // Get the calculated speedup value
        float speedUp = benchmarking.getSpeedUp();

        assertEquals(1.00f, speedUp, 0.01f);
    }
}
