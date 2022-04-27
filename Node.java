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
}