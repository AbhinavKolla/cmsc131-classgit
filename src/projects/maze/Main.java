package projects.maze;

public class Main {

    static void phase1() {
        Maze maze = MazeReader.load( "data/sample_maze3.txt" );
        System.out.println("Maze successfully loaded!");
        if(maze.leadsToExit())
            maze.serialize("data/sample_maze_phase1.txt");
    }

    static void phase2() {
        Maze maze = MazeReader.load("data/hard_maze.txt");
        if (maze.leadsToExit()) {
            maze.serialize("data/hard_maze.out");
        }
        maze = MazeReader.load("data/hard_maze_nosol.txt");
        if (maze.leadsToExit()) {
            maze.serialize("data/hard_maze_nosol.out");
        }
    }

    public static void main(String[] args) {
        phase1();
        phase2();
    }

}
