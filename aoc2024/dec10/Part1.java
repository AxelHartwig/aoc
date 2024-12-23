import java.util.*;

class Part1 {
    static int n = 53;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] m = new int[n][n];
        char[][] temp = new char[n][n];
        int c = 0;

        while (scan.hasNextLine()) {
            char[] line = scan.nextLine().toCharArray();
            for (int i = 0; i < line.length; i++) {
                m[c][i] = line[i] - '0';
                temp[c][i] = '.';
            }
            c++;
        }

        long part1 = 0;
        long part2 = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    part1 += calcTrailScore(m, new Coordinate(j, i), new HashSet<>());
                    part2 += calcTrailRating(m, new Coordinate(j, i), new HashSet<>());
                }

            }

        }

        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);

    }

    static int calcTrailScore(int[][] m, Coordinate c, Set<Coordinate> path) {
        path.add(c);
        int result = 0;
        if (m[c.y][c.x] == 9) {
            return 1;
        }
        Coordinate[] coords = { new Coordinate(c.x + 1, c.y), new Coordinate(c.x - 1, c.y),
                new Coordinate(c.x, c.y + 1), new Coordinate(c.x, c.y - 1) };

        for (int i = 0; i < 4; i++) {
            Coordinate next = coords[i];
            if (next.inbounds(m) && !path.contains(next) && (m[next.y][next.x] == m[c.y][c.x] + 1)) {
                result += calcTrailScore(m, next, path);
            }
        }

        return result;
    }

    static int calcTrailRating(int[][] m, Coordinate c, Set<Coordinate> path) {
        // path.add(c);
        int result = 0;
        if (m[c.y][c.x] == 9) {
            return 1;
        }

        Coordinate[] coords = { new Coordinate(c.x + 1, c.y), new Coordinate(c.x - 1, c.y),
                new Coordinate(c.x, c.y + 1), new Coordinate(c.x, c.y - 1) };

        for (int i = 0; i < 4; i++) {
            Coordinate next = coords[i];
            if (next.inbounds(m) && !path.contains(next) && (m[next.y][next.x] == m[c.y][c.x] + 1)) {
                result += calcTrailRating(m, next, path);
            }
        }

        return result;
    }
}

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate c)) {
            return false;
        }
        return c.x == this.x && c.y == this.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + 31 * y;
    }

    public boolean inbounds(int[][] m) {
        return m.length > y && m[0].length > x && x >= 0 && y >= 0;
    }
}