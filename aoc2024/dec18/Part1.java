import java.util.*;

class Part1 {
    static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int size = 71;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Node[][] m = new Node[size][size];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = new Node('.', j, i);
            }
        }

        for (int k = 0; k < 1024; k++) {
            String[] line = scan.nextLine().split(",");
            m[Integer.parseInt(line[1])][Integer.parseInt(line[0])].c = '#';
        }

        System.out.println(dfs(m, 0, 0));
        int x = 0;
        int y = 0;
        while (scan.hasNextLine()) {
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++) {
                    m[i][j].dist = 0;
                    m[i][j].visited = false;
                }
            }
            String[] line = scan.nextLine().split(",");
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);
            m[y][x].c = '#';
            if (dfs(m, 0, 0) == -1) {
                break;
            }
        }
        System.out.println(x + "," + y);

    }

    static int dfs(Node[][] m, int row, int col) {
        Queue<Node> q = new LinkedList<>();
        Node start = m[row][col];
        start.dist = 0;
        start.visited = true;
        q.add(start);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.x == size - 1 && curr.y == size - 1) {
                return curr.dist;
            }

            for (int i = 0; i < dirs.length; i++) {

                int y = curr.y + dirs[i][0];
                int x = curr.x + dirs[i][1];
                if (inbounds(m, y, x)) {
                    Node next = m[y][x];
                    if (!m[y][x].visited && m[y][x].c != '#') {
                        m[y][x].visited = true;
                        m[y][x].dist = curr.dist + 1;
                        q.add(m[y][x]);
                    }
                }
            }
        }

        return -1;
    }

    static boolean inbounds(Object[][] m, int y, int x) {
        return m.length > y && m[0].length > x && x >= 0 && y >= 0;
    }
}

class Node {
    char c;
    int x;
    int y;
    int dist;
    boolean visited;

    public Node(char c, int x, int y) {
        dist = 0;
        visited = false;
        this.c = c;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node other)) {
            return false;
        }
        return this.visited == other.visited && other.c == this.c && this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * c;
    }
}