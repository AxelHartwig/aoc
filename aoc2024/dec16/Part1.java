import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Node> positions = new ArrayList<>();
        int cnt = 0;
        char[][] m = new char[15][15];
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            m[cnt] = line.toCharArray();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                for (int i = 0; i < 4; i++) {
                    positions.add(new Node(c, j, cnt, i));
                }
            }
            cnt++;
        }

        ArrayList<Node> positions1 = new ArrayList<>();
        ArrayList<Node> positions2 = new ArrayList<>();
        ArrayList<Node> positions3 = new ArrayList<>();
        ArrayList<Node> positions4 = new ArrayList<>();

        for (Node n : positions) {
            positions1.add(new Node(n.c, n.x, n.y, n.direction));
            positions2.add(new Node(n.c, n.x, n.y, n.direction));
            positions3.add(new Node(n.c, n.x, n.y, n.direction));
            positions4.add(new Node(n.c, n.x, n.y, n.direction));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();

        djikstra(positions, 'S', 1, m);
        int part1 = Integer.MAX_VALUE;
        for (Node n : positions) {
            if (n.c == 'E') {
                // System.out.println(n.dist);
                if (n.dist < part1) {
                    part1 = n.dist;
                }
            }
        }
        cnt = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
        System.out.println(part1);

        // 0 = N, 1 = E, 2 = S, 3 = W

    }

    private static void djikstra(ArrayList<Node> positions, char start, int direction, char[][] m) {
        PriorityQueue<Node> q = new PriorityQueue<>();

        for (Node n : positions) {
            if (n.c == start && n.direction == direction) {
                n.visited = true;
                n.dist = 0;
                q.add(n);
            }
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();
            m[curr.y][curr.x] = 'O';
            for (Node n : turns(curr, positions)) {
                int newdist = curr.dist + 1000;
                if (!n.visited && n.c != '#' && newdist < n.dist) {
                    n.dist = newdist;
                    n.visited = true;
                    n.prev = curr;
                    q.add(n);
                }
            }

            Node n = neighbour(curr, positions);
            if (n == null) {
                continue;
            }

            int newdist = curr.dist + 1;
            if (!n.visited && n.c != '#' && newdist < n.dist) {
                n.dist = newdist;
                n.visited = true;
                n.prev = curr;
                q.add(n);
            }

        }

    }

    static Set<Node> part2(Node curr, Node start, Node end, Set<Node> path) {
        path.add(curr);
        if (curr.equals(start)) {
            return path;
        } else {
            ArrayList<Node> next = new ArrayList<>();
        }
    }

    static ArrayList<Node> turns(Node n, ArrayList<Node> positions) {
        ArrayList<Node> turns = new ArrayList<>();
        for (Node n1 : positions) {
            if (n1.x == n.x && n1.y == n.y) {
                switch (n.direction) {
                    case 0:
                        if (n1.direction == 1 || n1.direction == 3) {
                            turns.add(n1);
                        }
                        break;
                    case 1:
                        if (n1.direction == 0 || n1.direction == 2) {
                            turns.add(n1);
                        }
                        break;
                    case 2:
                        if (n1.direction == 1 || n1.direction == 3) {
                            turns.add(n1);
                        }
                        break;
                    case 3:
                        if (n1.direction == 0 || n1.direction == 2) {
                            turns.add(n1);
                        }
                        break;
                }
            }
        }

        return turns;
    }

    static Node neighbour(Node n, ArrayList<Node> positions) {
        for (Node n1 : positions) {
            if (n1.direction != n.direction) {
                continue;
            }
            switch (n.direction) {
                case 0:
                    if (n.x == n1.x && n.y - 1 == n1.y) {
                        return n1;
                    }
                    break;
                case 1:
                    if (n.x + 1 == n1.x && n.y == n1.y) {
                        return n1;
                    }
                    break;
                case 2:
                    if (n.x == n1.x && n.y + 1 == n1.y) {
                        return n1;
                    }
                    break;
                case 3:
                    if (n.x - 1 == n1.x && n.y == n1.y) {
                        return n1;
                    }
                    break;
            }
        }
        return null;
    }

}

class Node implements Comparable<Node> {
    char c;
    int x;
    int y;
    int dist;
    boolean visited;
    int direction;
    Node prev;

    public Node(char c, int x, int y, int direction) {
        dist = Integer.MAX_VALUE;
        visited = false;
        this.c = c;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    // public boolean equals(Object obj) {
    // if (!(obj instanceof Node other)) {
    // return false;
    // }
    // return this.visited == other.visited && other.c == this.c && this.x ==
    // other.x && this.y == other.y
    // && this.direction == other.direction && this.dist == other.dist;
    // }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node other)) {
            return false;
        }

        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * c + 31 * x + 31 * y;
    }

    @Override
    public int compareTo(Node o) {
        return this.dist - o.dist;
    }
}
