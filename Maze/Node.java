package Maze;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the Node which represent each grid on the Maze matrix
 */

public class Node {

    // the cell of the grid
    Cell cell;

    // the status of the grid, 0 as path, 1 as wall, 2/3 as start and end point, 4 as solution path
    int status;

    public Node(Cell cell, int status) {
        this.cell = cell;
        this.status = status;
    }

    /**
     * This function returns the row index of the Node.
     * @return
     */
    public int getX(){
        return cell.x;
    }

    /**
     * This function returns the column index of the Node.
     * @return
     */
    public int getY(){
        return cell.y;
    }

    /**
     * This function returns the coordinate of the Node in List formula.
     * @return
     */
    public List<Integer> getCordinate(){
        List<Integer> array = new ArrayList<>();
        array.add(getX());
        array.add(getY());
        return array;
    }
}