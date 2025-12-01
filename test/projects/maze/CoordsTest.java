package projects.maze;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Coords    public Coords(int row, int col) {
        //TODO Auto-generated constructor stub
    }

@Test
    public void testEqualsSameValues() {
        Coords a = new Coords(0, 0);
        Coords b = new Coords(0, 0);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void testEqualsDifferentRow() {
        Coords a = new Coords(1, 2);
        Coords b = new Coords(2, 2);
        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentCol() {
        Coords a = new Coords(1, 2);
        Coords b = new Coords(1, 3);
        assertFalse(a.equals(b));
    }

    public int getRow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRow'");
    }

    public int getCol() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCol'");
    }
}