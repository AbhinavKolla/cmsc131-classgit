package projects.maze;

/** `Cell` class additions

Extra attributes

- `neighbors` (Coords[]) - coordinates of neighbors
    - needs accessor/mutator

- `explored` (boolean) - traversal flag (defaults to false)
    - needs accessor/mutator

- `status` (CellStatus enum) - cell's role/state
    - Enum values    
        - `S` maze entrance
        - `E` maze exit  
        - `O` open cell
        - `P` part of solution path
    - Needs accessor/mutator

A cell will be constructed with a coordinates and a status. Decide for yourself what are sensible default values (if any) for the other attributes.
 */

public class Cell {

    private final Coords coords;
    private final CellStatus status;
    // TODO add neighbors, explored. see class repo
    
    /**
     * Creates a new Cell with the given coordinates and status.
     *
     * @param c the coordinates of this cell
     * @param status the initial status of this cell
     */
    public Cell(Coords c, CellStatus status) {
        coords = c;
        this.status = status;
    }

    public Coords getCoords() {
        return coords;
    }

    public CellStatus getStatus() {
        return status;
    }

}
