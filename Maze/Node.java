package Maze;
import java.util.ArrayList;
import java.util.List;

public class Node {
    Cell cell;
    int status;

    public Node(Cell cell, int status) {
        this.cell = cell;
        this.status = status;
    }

    public int getX(){
        return cell.x;
    }

    public int getY(){
        return cell.y;
    }

    public List<Integer> getCordinate(){
        List<Integer> array = new ArrayList<>();
        array.add(getX());
        array.add(getY());
        return array;
    }
}