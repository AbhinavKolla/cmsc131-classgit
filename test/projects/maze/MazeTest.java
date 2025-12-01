package projects.maze;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeTest {
    
    @Test
    public void addNullCell() {
        Maze maze = new Maze(10);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> maze.addCell(null)
        );
        assertEquals("cell cannot be null", ex.getMessage());
    }

    @Test
    public void addNullCellCoords() {
        Maze maze = new Maze(10);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> maze.addCell(new Cell(null, null))
        );
        assertEquals("cell.coords cannot be null", ex.getMessage());
    }

    @Test
    public void addCellExistingAtCoords() {
        Maze maze = new Maze(10);
        maze.addCell(new Cell(new Coords(0, 0),null));
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> maze.addCell(new Cell(new Coords(0, 0), null))
        );
        assertEquals("A cell already exists at coords", ex.getMessage());
    }

    @Test void serializeMaze() throws FileNotFoundException {
        Maze maze = new Maze(10);
        maze.addCell(new Cell(new Coords(0, 0), CellStatus.S));
        maze.addCell(new Cell(new Coords(1, 0), CellStatus.E));
        maze.addCell(new Cell(new Coords(0, 1), CellStatus.P));
        maze.addCell(new Cell(new Coords(1, 1), CellStatus.P));
        maze.serialize("test/projects/maze/testMaze.txt");
        // Verify the contents of the file
        Scanner scanner = new Scanner(new File("test/projects/maze/testMaze.txt"));
        String line = scanner.nextLine();
        assertEquals("S,P", line);
        line = scanner.nextLine();
        assertEquals("E,P", line);
        scanner.close();
    }

}
