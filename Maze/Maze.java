package Maze;
import java.util.List;

/** This class holds the Maze game */

public class Maze {

    public Node startNode;
    public Node endNode;
    public List<Node> path;

    public Maze(int[][] rawMaze){

        Node[][] maze = createMaze(rawMaze);

        Lee leeAlgorithm = new Lee(maze, startNode, endNode);

        path = leeAlgorithm.getPath();


    }

    public Node[][] createMaze(int[][] rawMaze){

        int rowNumber = rawMaze.length;
        int colNumber = rawMaze[0].length;

        Node[][] maze = new Node[rowNumber][colNumber];

        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < colNumber; j++){
                Cell cell = new Cell(i,j);
                int dist = rawMaze[i][j];
                Node grid = new Node(cell, dist);
                maze[i][j] = grid;

                if(dist == 2){
                    startNode = grid;
                }else if(dist == 3){
                    endNode = grid;
                }
            }
        }
        return maze;
    }

    public void printPath(){
        if(path.equals(null)){
            System.out.println("NO PATH OUT!");
        }else{
            for(Node grid : path){
                System.out.println("Next");
                System.out.println(grid.getCordinate());
            }
        }
        
    }

    public static void main(String[] args) {
        // int[][] rawMaze = {{2,1,1},
        //                     {0,1,0},
        //                     {0,0,3},
        //                     {1,1,1}};

        int[][] rawMaze = {
            {1,2,0,1},
            {0,0,1,3},
            {1,0,1,0},
            {1,0,0,0},
            {1,1,1,0},
        };

        Maze maze = new Maze(rawMaze);

        maze.printPath();

    }
    
}