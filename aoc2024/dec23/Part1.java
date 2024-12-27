import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Node> graph = new HashMap<>();
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String n1 = line.split("-")[0];
            String n2 = line.split("-")[1];
            
        }
    }
}

class Node {
    ArrayList<Node> adj;

    public Node() {
        adj = new ArrayList<>();
    }

    void addNeighbour(Node n) {
        if(!adj.contains(n)) {
            adj.add(n);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node n)) {
            return false;
        } else {
            return this.adj.equals(n.adj);
        }
    }
}