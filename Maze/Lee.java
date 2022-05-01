package Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**This class contains the Lee's algorithm */

public class Lee {

    // the input Maze as an 2D-array of Nodes
    public Node[][] maze;

    // The solution path of the Maze 
    public List<Node> path;

    // list of visited Nodes to keep track
    List<Node> visited = new ArrayList<>();

    // the map of each Node and its predecessor
    Map<Node, Node> nodeMap = new HashMap<>();

    // startNode and endNode
    Node startNode;
    Node endNode;

    // Number of rows and columns of the Maze
    int rowNumber;
    int colNumber;

    public Lee(Node [][] maze, Node startNode, Node endNode){
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;

        rowNumber = maze.length;
        colNumber = maze[0].length;

        nodeMap = BFS();

        // if endNode not reachable, set path = null.
        if(nodeMap == null){
            path = null;
        }
        else {
            path = findpath(nodeMap);
        }       
        
    }

    /**
     * This function is an implementation of the Breadth First Search(BFS) Algorithm.
     * @return return the map of nodes and their predecessors if found the path, return null otherwise.
     */
    public Map<Node, Node> BFS(){
        
        Map<Node, Node> predecessor = new HashMap<>();

        Queue<Node> queue = new ArrayDeque<>();

        visited.add(startNode);
        queue.add(startNode);

        while(queue.size()!=0){

            Node current = queue.poll();

            for(Node n : findNeighbors(current)){

                // put Node n and its parent to the parent map
                predecessor.put(n,current); 

                // add Node n into the queue of Nodes that are going to be check later
                queue.add(n);

                // Mark Node n as visited
                visited.add(n);

                // if found the endNode, end while loop and return the parent map
                if(n.equals(endNode)){
                    queue.clear();
                    return predecessor;   
                }
            }
        }
        return null;
    }

    /**
     * This function takes an input Node and return the list of non-visited neighbors of that node. 
     * For an Node with row index x and column index y, its neighbors are [x-1,y],[x+1,y],[x,y-1],[x,y+1]
     * @param current
     * @return
     */
    public List<Node> findNeighbors(Node current){
        
        List<Node> neighbors = new ArrayList<>();

        int x = current.getX();

        int y = current.getY();

        for(int i = -1; i <= 1; i+=2){
            int corX = x+i;
            if(corX >= 0 & corX < rowNumber){
                Node grid = maze[x+i][y];
                if(!visited.contains(grid)){
                    if(grid.status!=1){
                        neighbors.add(grid);
                    }    
                }
            }
            
        }

        for(int j = -1; j <= 1; j+=2){
            int corY = y+j;
            if(corY >= 0 & corY < colNumber){
                Node grid = maze[x][y+j];
                if(!visited.contains(grid)){
                    if(grid.status!=1){
                        neighbors.add(grid);
                    }
                }
            }
            
        }  

        return neighbors;

    }

    /**
     * This function is an backtracking algorithm that will find the path based on the Map of predecessors.
     * @param nodeMap
     * @return
     */
    public List<Node> findpath(Map<Node,Node> nodeMap){
        List<Node> path = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node previous = endNode;

        while(!previous.equals(startNode)){
            stack.push(previous);
            //path.add(previous);
            previous = nodeMap.get(previous);
        }

        path.add(startNode);

        while(!stack.empty()){
            path.add(stack.pop());
        }

        return path;
    }

    /**
     * This function return the solution path of the Maze.
     * @return
     */
    public List<Node> getPath(){
        return path;
    }

    
    
}
