package Maze;
import java.util.ArrayList;
import java.util.List;

public class Node {
    Cell cell;
    int dist;

    public Node(Cell cell, int dist) {
        this.cell = cell;
        this.dist = dist;
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