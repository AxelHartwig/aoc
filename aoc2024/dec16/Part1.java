import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = 141;
        Node[][][] nodes = new Node[size][size][4];
        char[][] m = new char[size][size];
        int cnt = 0;
        while (scan.hasNextLine()) {
            m[cnt] = scan.nextLine().toCharArray();
            cnt++;
        }

        int startR = 0;
        int startC = 0;
        int endR = 0;
        int endC = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                for (int k = 0; k < 4; k++) {
                    nodes[i][j][k] = new Node(m[i][j], j, i, k);
                }

                if (m[i][j] == 'S') {
                    startR = i;
                    startC = j;
                }

                if (m[i][j] == 'E') {
                    endR = i;
                    endC = j;
                }
            }
        }

        Queue<Node> q = new PriorityQueue<>();
        HashSet<Node> paths = new HashSet<>();
        Node start = nodes[startR][startC][1];
        start.dist = 0;
        // start.parent.add()
        q.add(start);
        // 0 = N, 1 = E, 2 = S, 3 = W
        int[][] dirs = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
        int lowestscore = -1;
        while (!q.isEmpty()) {
            Node curr = q.poll();

            // walk straight
            int nextx = curr.x + dirs[curr.direction][0];
            int nexty = curr.y + dirs[curr.direction][1];
            if (nextx >= size || nextx < 0 || nexty >= size || nexty < 0) {
                continue;
            }
            int newdist = 0;
            if (m[nexty][nextx] != '#') {
                newdist = curr.dist + 1;
                Node walk = nodes[nexty][nextx][curr.direction];
                if (newdist < walk.dist) {
                    q.add(walk);
                    walk.parent.clear();
                    walk.parent.add(curr);
                    walk.dist = newdist;
                } else if (newdist == walk.dist) {
                    walk.parent.add(curr);
                }
            }

            // turn cost
            newdist = curr.dist + 1000;

            // Turn right
            Node right = nodes[curr.y][curr.x][Math.floorMod((curr.direction + 1), 4)];
            if (newdist < right.dist) {
                q.add(right);
                right.parent.clear();
                right.parent.add(curr);
                right.dist = newdist;
            } else if (newdist == right.dist) {
                right.parent.add(curr);
            }

            // Turn left
            Node left = nodes[curr.y][curr.x][Math.floorMod((curr.direction - 1), 4)];
            if (newdist < left.dist) {
                q.add(left);
                left.parent.clear();
                left.parent.add(curr);
                left.dist = newdist;
            } else if (newdist == left.dist) {
                left.parent.add(curr);
            }
        }

        int part1 = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (part1 > nodes[1][size - 2][i].dist) {
                part1 = nodes[1][size - 2][i].dist;
            }
        }

        int part2 = find_paths(nodes[1][size - 2][3], new HashSet<>(), new HashSet<>());

        System.out.println(part1);
        System.out.println(part2);

    }

    static int find_paths(Node curr, HashSet<Node> nodes, HashSet<Node> path) {
        if (curr.parent.size() == 0) {
            nodes.addAll(path);
        }

        for (Node parentNode : curr.parent) {
            path.add(curr);

            find_paths(parentNode, nodes, path);

            path.remove(curr);
        }

        return nodes.size();
    }
}

class Node implements Comparable<Node> {
    char c;
    int x;
    int y;
    int direction;
    int dist = Integer.MAX_VALUE;
    ArrayList<Node> parent = new ArrayList<>();

    public Node(char c, int x, int y, int direction) {
        this.c = c;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

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
