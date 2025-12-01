package projects.maze;

public class Coords {

    private final int row;
    private final int col;

    /**
     * Constructs a Coords object representing a location in a grid or maze.
     *
     * @param r the row index of the location
     * @param c the column index of the location
     */
    public Coords(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Determines whether this Coords is equal to the specified Coords.

     * @param other the Coords to compare against; must not be null
     * @return true if this Coords and {@code other} have the same row and column, false otherwise
     */
    public boolean equals(Coords other) {
        return (
            getRow() == other.getRow()
            && getCol() == other.getCol()
        );
    }

}
