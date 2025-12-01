package projects.maze;

public class Grid {

    private final Cell[] cells;
    private int cellCount;

    /* Constructor for Grid class
     * @param maxCells the maximum number of cells the grid can hold
     */
    public Grid(int maxCells) {
        cells = new Cell[maxCells];
        cellCount = 0;
    }

    /*
     * Inserts a new cell into the grid.
     * @param cell the Cell to insert
     * @return true if the cell was inserted successfully, false if the grid is full
     */
    public boolean insertCell(Cell cell) {
        if (cellCount < cells.length) {
            cells[cellCount] = cell;
            cellCount++;
            return true;
        }
        return false;
    }

    /**
     * Retrieves a cell from the grid by its coordinates.
     * @param vh the coordinates of the cell to retrieve
     * @return the Cell with the matching coordinates, or null if not found
     */
    public Cell getCell(Coords vh) {
        for (int idx = 0; idx < cellCount; idx++) {
            if (cells[idx].getCoords().equals(vh) ) {
                return cells[idx];
            }
        }
        return null;
    }

    public int getCellCount() {
        return cellCount;
    }

    /**
     * Retrieves all cells in the grid.
     * @return an array of all Cells in the grid
     */
    public Cell[] getAllCells() {
        Cell[] allCells = new Cell[cellCount];
        for (int idx = 0; idx < cellCount; idx++) {
            allCells[idx] = cells[idx];
        }
        return allCells;
    }

}
