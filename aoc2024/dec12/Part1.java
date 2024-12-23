import java.util.*;

class Part1 {

    static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 140;
        Coordinate[][] m = new Coordinate[n][n];
        int count = 0;
        while (scan.hasNextLine()) {
            char[] l = scan.nextLine().toCharArray();
            for (int i = 0; i < l.length; i++) {
                m[count][i] = new Coordinate(i, count, l[i]);
            }
            count++;
        }

        long part1 = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (!m[i][j].visited) {
                    part1 += fill(m, i, j);
                }
            }
        }
        System.out.println("part1: " + part1);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j].visited = false;
            }
        }

        long part2 = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (!m[i][j].visited) {
                    part2 += fill2(m, i, j);
                }
            }
        }

        System.out.println("part2: " + part2);

    }

    static int fill(Coordinate m[][], int r, int c) {
        Queue<Coordinate> q = new LinkedList<>();
        Coordinate start = m[r][c];
        q.add(start);
        start.visited = true;
        int area = 0;
        int perimiter = 0;

        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            area++;
            // System.out.println("hej " + q.size());
            for (int i = 0; i < dirs.length; i++) {
                int y = curr.y + dirs[i][0];
                int x = curr.x + dirs[i][1];
                if (inbounds(m, y, x)) {
                    if (!m[y][x].visited && curr.c == m[y][x].c) {
                        m[y][x].visited = true;
                        q.add(m[y][x]);
                    }

                    if (curr.c != m[y][x].c) {
                        perimiter++;
                    }
                } else {
                    perimiter++;
                }
            }

        }
        return area * perimiter;
    }

    static int fill2(Coordinate m[][], int r, int c) {
        Queue<Coordinate> q = new LinkedList<>();
        Coordinate start = m[r][c];
        q.add(start);
        start.visited = true;
        int area = 0;
        int perimiter = 0;

        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            area++;
            // System.out.println("hej " + q.size());
            for (int i = 0; i < dirs.length; i++) {
                int y = curr.y + dirs[i][0];
                int x = curr.x + dirs[i][1];
                if (inbounds(m, y, x)) {
                    if (!m[y][x].visited && curr.c == m[y][x].c) {
                        m[y][x].visited = true;
                        q.add(m[y][x]);
                    }
                }
            }
            // up right
            if ((!inbounds(m, curr.y - 1, curr.x) || m[curr.y - 1][curr.x].c != curr.c)
                    && (!inbounds(m, curr.y, curr.x + 1) || m[curr.y][curr.x + 1].c != curr.c)) {
                perimiter++;
            }
            // down left
            if ((!inbounds(m, curr.y + 1, curr.x) || m[curr.y + 1][curr.x].c != curr.c)
                    && (!inbounds(m, curr.y, curr.x - 1) || m[curr.y][curr.x - 1].c != curr.c)) {
                perimiter++;
            }
            // down right
            if ((!inbounds(m, curr.y + 1, curr.x) || m[curr.y + 1][curr.x].c != curr.c)
                    && (!inbounds(m, curr.y, curr.x + 1) || m[curr.y][curr.x + 1].c != curr.c)) {
                perimiter++;
            }
            // up left
            if ((!inbounds(m, curr.y - 1, curr.x) || m[curr.y - 1][curr.x].c != curr.c)
                    && (!inbounds(m, curr.y, curr.x - 1) || m[curr.y][curr.x - 1].c != curr.c)) {
                perimiter++;
            }

            if ((!inbounds(m, curr.y - 1, curr.x - 1) || m[curr.y - 1][curr.x - 1].c != curr.c)
                    && inbounds(m, curr.y - 1, curr.x) && m[curr.y - 1][curr.x].c == curr.c
                    && inbounds(m, curr.y, curr.x - 1) && m[curr.y][curr.x - 1].c == curr.c) {
                perimiter++;
            }

            if ((!inbounds(m, curr.y - 1, curr.x + 1) || m[curr.y - 1][curr.x + 1].c != curr.c)
                    && inbounds(m, curr.y - 1, curr.x) && m[curr.y - 1][curr.x].c == curr.c
                    && inbounds(m, curr.y, curr.x + 1) && m[curr.y][curr.x + 1].c == curr.c) {
                perimiter++;
            }

            if ((!inbounds(m, curr.y + 1, curr.x - 1) || m[curr.y + 1][curr.x - 1].c != curr.c)
                    && inbounds(m, curr.y + 1, curr.x) && m[curr.y + 1][curr.x].c == curr.c
                    && inbounds(m, curr.y, curr.x - 1) && m[curr.y][curr.x - 1].c == curr.c) {
                perimiter++;
            }

            if ((!inbounds(m, curr.y + 1, curr.x + 1) || m[curr.y + 1][curr.x + 1].c != curr.c)
                    && inbounds(m, curr.y + 1, curr.x) && m[curr.y + 1][curr.x].c == curr.c
                    && inbounds(m, curr.y, curr.x + 1) && m[curr.y][curr.x + 1].c == curr.c) {
                perimiter++;
            }

        }
        return area * perimiter;
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