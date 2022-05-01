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

    public Node[][] maze;
    public List<Node> visited = new ArrayList<>();
    public Node startNode;
    public Node endNode;
    public Map<Node, Node> nodeMap = new HashMap<>();
    public List<Node> path;
    public int rowNumber;
    public int colNumber;

    public Lee(Node [][] maze, Node startNode, Node endNode){
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;

        rowNumber = maze.length;
        colNumber = maze[0].length;


        nodeMap = BFS();

        if(nodeMap == null){
            path = null;
        }
        else {
            path = findpath(nodeMap);
        }

        
        

    }

    public Map<Node, Node> BFS(){
        
        Map<Node, Node> predecessor = new HashMap<>();

        Queue<Node> queue = new ArrayDeque<>();

        visited.add(startNode);
        queue.add(startNode);


        while(queue.size()!=0){

            Node current = queue.poll();

            
            for(Node n : findNeighbors(current)){


                predecessor.put(n,current);

                queue.add(n);

                visited.add(n);

                // System.out.println(n.getCordinate());


                if(n.equals(endNode)){
                    queue.clear();
                    return predecessor;   
                }
            }
            
        }

        return null;

        

    }

    

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

    public List<Node> getPath(){
        return path;
    }

    
    
}
