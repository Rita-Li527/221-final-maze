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
                int status = rawMaze[i][j];
                Node grid = new Node(cell, status);
                maze[i][j] = grid;

                if(status == 2){
                    startNode = grid;
                }else if(status == 3){
                    endNode = grid;
                }
            }
        }
        return maze;
    }

    public void printPath(int[][] rawMaze){
        if(path ==null){
            System.out.println("NO PATH OUT!");
        }else{
            for(int i =0; i< path.size()-2; i++){
                rawMaze[path.get(i+1).getX()][path.get(i+1).getY()] = 4;
            }
            System.out.println("Here is the shortest path: \n ■ = walls, □ = route, x = shortest path, o = start/finish");
            System.out.println("--------");
            for (int i = 0; i<rawMaze.length; i++) {
                String strToPrint = "";
                for (int j =0; j< rawMaze[0].length; j++) {
                    if (rawMaze[i][j] == 1) {
                        strToPrint += "■ ";
                    }
                    else if (rawMaze[i][j] == 2 | rawMaze[i][j] == 3) {
                        strToPrint += "o ";
                    }
                    else if (rawMaze[i][j] == 4) {
                        strToPrint += "x ";
                    }
                    else strToPrint += "□ ";
                }
    
                System.out.println(strToPrint);
            }
            System.out.println("--------");
        }
       
        
    }

    public static void main(String[] args) {
        // int[][] rawMaze = {{2,1,1},
        //                     {0,1,0},
        //                     {0,0,3},
        //                     {1,1,1}};

        // int[][] rawMaze = {
        //     {1,2,0,1,0,1,1,1,1,1},
        //     {0,0,1,0,0,1,1,1,1,1},
        //     {1,0,1,0,0,0,1,1,1,0},
        //     {1,0,0,0,1,0,1,1,1,0},
        //     {1,0,1,1,1,1,1,1,1,0},
        //     {1,0,0,0,0,1,0,1,1,1},
        //     {0,0,1,0,1,0,0,1,1,3},
        //     {1,0,1,0,0,0,1,0,0,1},
        //     {1,0,0,0,1,0,1,0,1,0},
        //     {1,1,1,1,1,0,0,0,1,0}
        // };

        int[][] rawMaze = {
            {1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
            {1,0,1,0,0,1,1,0,0,0,1,1,0,1,1,1,1,1,0},
            {1,0,1,1,1,1,1,1,1,0,1,0,0,1,0,0,0,0,0},
            {1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1},
            {1,0,0,0,1,1,1,0,0,0,1,0,0,0,0,0,0,0,3},
            {1,0,1,1,1,0,1,0,0,0,0,0,0,0,1,1,1,0,0},
            {1,0,1,0,1,0,0,0,1,0,0,1,1,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,1,0,0,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,1,0,0,1,1,1,1,0,0,0,1},
            {1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,1,1,1,1,1,0,1,0,1},
            {1,1,1,0,1,1,1,1,1,0,1,0,0,0,1,0,1,0,1},
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1},
            {0,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,0,1},
            {0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,0,1},
            {1,1,1,1,0,1,0,1,0,1,0,0,1,1,1,1,0,0,1},
            {1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,1},
            {1,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1},
            {1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1},
            {1,2,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        Maze maze = new Maze(rawMaze);

        maze.printPath(rawMaze);

    }
    
}
