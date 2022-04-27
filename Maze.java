
/** This class holds the Maze game */

public class Maze {

    public Node startNode;
    public Node endNode;

    public Maze(int[][] rawMaze){

        Node[][] maze = createMaze(rawMaze);

        Lee leeAlgorithm = new Lee(maze, startNode, endNode);


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
    
}
