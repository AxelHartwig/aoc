import java.util.*;

import javax.print.attribute.HashAttributeSet;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Node> graph = new HashMap<>();
        HashSet<ArrayList<Node>> connections = new HashSet<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String n1 = line.split("-")[0];
            String n2 = line.split("-")[1];
            if (graph.containsKey(n1)) {
                if (graph.containsKey(n2)) {
                    graph.get(n1).addNeighbour(graph.get(n2));
                    graph.get(n2).addNeighbour(graph.get(n1));
                } else {
                    Node temp = new Node(n2);
                    graph.get(n1).addNeighbour(temp);
                    temp.addNeighbour(graph.get(n1));
                    graph.put(n2, temp);
                }
            } else {
                if (graph.containsKey(n2)) {
                    Node temp = new Node(n1);
                    graph.get(n2).addNeighbour(temp);
                    temp.addNeighbour(graph.get(n2));
                    graph.put(n1, temp);
                } else {
                    Node temp1 = new Node(n1);
                    Node temp2 = new Node(n2);
                    temp1.addNeighbour(temp2);
                    temp2.addNeighbour(temp1);
                    graph.put(n1, temp1);
                    graph.put(n2, temp2);
                }
            }
        }
        int sum = 0;
        for (Node n1 : graph.values()) {
            // för varje nod n1 kolla dess adj
            for (Node n2 : n1.adj) {
                for (Node n3 : n2.adj) {
                    if (n1.adj.contains(n3)) {
                        if (n1.id.startsWith("t") || n2.id.startsWith("t") || n3.id.startsWith("t")) {
                            sum++;
                        }
                    }
                }
            }
        }
        // divide by 6 because we count every thing 6 times.
        System.out.println(sum / 6);

        TreeSet<Node> maxClique = new TreeSet<>();
        for (Node n : graph.values()) {
            TreeSet<Node> clique = new TreeSet<>((n1, n2) -> n1.id.compareTo(n2.id));
            clique.add(n);
            for (Node n1 : graph.values()) {
                if (n1.equals(n)) {
                    continue;
                }

                boolean flag = true;
                for (Node other : clique) {
                    if (!other.adj.contains(n1)) {
                        flag = false;
                    }
                }

                if (flag) {
                    clique.add(n1);
                }
            }

            if (clique.size() > maxClique.size()) {
                maxClique = clique;
            }
        }

        for (Node n : maxClique) {
            System.out.print(n.id + ",");
        }

    }
}

class Node {
    String id;
    ArrayList<Node> adj;

    public Node(String id) {
        this.id = id;
        adj = new ArrayList<>();
    }

    void addNeighbour(Node n) {
        if (!adj.contains(n)) {
            adj.add(n);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node n)) {
            return false;
        } else {
            return this.id.equals(n.id);
        }
    }
}