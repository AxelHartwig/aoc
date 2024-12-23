import java.util.*;

class Part1 {
    static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 141;
        Coordinate[][] m = new Coordinate[n][n];
        int cnt = 0;

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                m[cnt][i] = new Coordinate(i, cnt, line.charAt(i));
                if (line.charAt(i) == 'E') {
                    endX = i;
                    endY = cnt;
                } else if (line.charAt(i) == 'S') {
                    startX = i;
                    startY = cnt;
                }
            }
            cnt++;
        }

        Queue<Coordinate> q = new LinkedList<>();

        Coordinate start = m[startY][startX];
        q.add(start);
        start.visited = true;

        while (!q.isEmpty()) {
            Coordinate curr = q.poll();

            for (int i = 0; i < dirs.length; i++) {
                int y = curr.y + dirs[i][0];
                int x = curr.x + dirs[i][1];
                if (inbounds(m, y, x)) {
                    if (!m[y][x].visited && m[y][x].c != '#') {
                        m[y][x].dist = curr.dist + 1;
                        m[y][x].visited = true;
                        q.add(m[y][x]);
                    }
                }
            }
        }

        long part1 = 0;
        long part2 = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j].c == '#') {
                    continue;
                }
                for (int k = 0; k < m.length; k++) {
                    for (int l = 0; l < m.length; l++) {
                        if (m[k][l].c == '#') {
                            continue;
                        }
                        int dist = Math.abs(i - k) + Math.abs(j - l);
                        if (dist == 2 && (m[k][l].dist - m[i][j].dist - dist) >= 100) {
                            part1++;
                        }

                        if (dist <= 20 && (m[k][l].dist - m[i][j].dist - dist) >= 100) {
                            part2++;
                        }
                    }
                }
            }
        }
        System.out.println(part1);
        System.out.println(part2);

    }

    static boolean inbounds(Object[][] m, int y, int x) {
        return m.length > y && m[0].length > x && x >= 0 && y >= 0;
    }
}

class Coordinate {
    char c;
    int x;
    int y;
    boolean visited;
    int dist = 0;

    public Coordinate(int x, int y, char c) {
        this.x = x;
        this.y = y;
        visited = false;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate other)) {
            return false;
        }
        return other.x == this.x && other.y == this.y && this.visited == other.visited && other.c == this.c;
    }

    @Override
    public int hashCode() {
        return 31 * x + 31 * y + 31 * c;
    }
}