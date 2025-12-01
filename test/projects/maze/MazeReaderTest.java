package projects.maze;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeReaderTest {

    @Test
    public void testReadSuccessful() throws FileNotFoundException {
        Maze maze = MazeReader.load("test/projects/maze/testMaze.txt");
        assertNotNull(maze);
        assertEquals(CellStatus.S, maze.getCell(new Coords(0, 0)).getStatus());
        assertEquals(CellStatus.E, maze.getCell(new Coords(1, 0)).getStatus());
        assertEquals(CellStatus.P, maze.getCell(new Coords(0, 1)).getStatus());
        assertEquals(CellStatus.P, maze.getCell(new Coords(1, 1)).getStatus());
    }

}
