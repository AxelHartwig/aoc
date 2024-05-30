import java.util.ArrayList;
import java.util.Objects;

public class Node implements Comparable<Node> {
    int x;
    int y;
    int dx;
    int dy;
    int counter;
    int loss;
    Node lastNode;

    public Node(int x, int y, int loss, int dx, int dy, int counter) {
        this.loss = loss;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.counter = counter;
    }

    @Override
    public int compareTo(Node n) {
        return this.loss - n.loss;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node n = (Node) obj;
        return this.x == n.x && this.y == n.y && this.dx == n.dx && this.dy == n.dy && this.counter == n.counter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, dx, dy, counter);
    }

}
