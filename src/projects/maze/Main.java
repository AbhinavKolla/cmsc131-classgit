package projects.maze;

public class Main {

    static void phase1() {
        Maze maze = MazeReader.load( "data/sample_maze3.txt" );
        System.out.println("Maze successfully loaded!");
        if(maze.leadsToExit())
            maze.serialize("data/sample_maze_phase1.txt");
    }

    public static void main(String[] args) {
        phase1();
    }

}
