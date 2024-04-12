package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Factory.Maze;
import ca.mcmaster.se2aa4.mazerunner.Factory.Benchmarking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkingTest {

    Maze maze;

    @Test
    void testSpeedUpCalculationBFSTRE() {

        try {
            maze = new Maze("./examples/medium.maz.txt");
        } catch (Exception IOException) {
            assertTrue(false);
        }

        Benchmarking benchmarking = new Benchmarking("BFS", "tremaux");

        benchmarking.runs(maze);

        float speedUp = benchmarking.getSpeedUp();

        assertEquals(1.00f, speedUp);
    }

    @Test
    void testSpeedUpCalculationBFSRH() {

        try {
            maze = new Maze("./examples/giant.maz.txt");
        } catch (Exception IOException) {
            assertTrue(false);
        }

        Benchmarking benchmarking = new Benchmarking("BFS", "righthand");

        benchmarking.runs(maze);

        float speedUp = benchmarking.getSpeedUp();

        assertEquals(80.42, speedUp, 0.001f);
    }

    @Test
    void testSpeedUpCalculationTRERH() {

        try {
            maze = new Maze("./examples/giant.maz.txt");
        } catch (Exception IOException) {
            assertTrue(false);
        }

        Benchmarking benchmarking = new Benchmarking("tremaux", "righthand");

        benchmarking.runs(maze);

        float speedUp = benchmarking.getSpeedUp();

        assertEquals(80.42f, speedUp, 0.001f);
    }

}
