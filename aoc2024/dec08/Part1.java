import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Pos> l = new ArrayList<>();
        int c = 0;
        int n = 50;
        char m[][] = new char[n][n];
        HashSet<Pos> antinodes = new HashSet<>();

        while (s.hasNextLine()) {
            char[] line = s.nextLine().toCharArray();
            m[c] = line;
            for (int i = 0; i < line.length; i++) {
                if (line[i] != '.') {
                    l.add(new Pos(i, c, line[i]));
                }

            }
            c++;
        }

        for (int i = 0; i < l.size(); i++) {
            Pos curr = l.get(i);
            for (int j = 0; j < l.size(); j++) {
                if (i == j) {
                    continue;
                }
                Pos comp = l.get(j);
                if (curr.freq != comp.freq) {
                    continue;
                }

                int x1, x2, y1, y2;
                x1 = 2 * curr.x - comp.x;
                y1 = 2 * curr.y - comp.y;

                x2 = 2 * comp.x - curr.x;
                y2 = 2 * comp.y - curr.y;

                if (x1 >= 0 && y1 >= 0 && x1 < m[0].length && y1 < m.length) {
                    antinodes.add(new Pos(x1, y1, '#'));
                }

                if (x2 >= 0 && y2 >= 0 && x2 < m[0].length && y2 < m.length) {
                    antinodes.add(new Pos(x2, y2, '#'));
                }
            }
        }

        System.out.println("part1: " + antinodes.size());
        antinodes.clear();
        for (int i = 0; i < l.size(); i++) {
            Pos curr = l.get(i);
            for (int j = 0; j < l.size(); j++) {
                if (i == j) {
                    continue;
                }
                Pos comp = l.get(j);
                if (curr.freq != comp.freq) {
                    continue;
                }

                int dx = curr.x - comp.x;
                int dy = curr.y - comp.y;

                int x = curr.x;
                int y = curr.y;
                for (;;) {
                    x = x - dx;
                    y = y - dy;
                    if (x >= 0 && y >= 0 && x < m[0].length && y < m.length) {
                        antinodes.add(new Pos(x, y, '#'));
                    } else {
                        break;
                    }
                }
                x = comp.x;
                y = comp.y;
                for (;;) {
                    x = x + dx;
                    y = y + dy;
                    if (x >= 0 && y >= 0 && x < m[0].length && y < m.length) {
                        antinodes.add(new Pos(x, y, '#'));
                    } else {
                        break;
                    }
                }

            }
        }
        System.out.println("part2: " + antinodes.size());
    }
}

class Pos {
    int x;
    int y;
    char freq;

    public Pos(int x, int y, char freq) {
        this.x = x;
        this.y = y;
        this.freq = freq;
    }

    @Override
    public int hashCode() {
        return 31 * x + 31 * y + 31 * freq;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pos)) {
            return false;
        }

        Pos pos = (Pos) obj;

        return pos.x == x && pos.y == y && pos.freq == freq;

    }
}