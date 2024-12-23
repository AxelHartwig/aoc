import java.util.*;

class Part1 {

    static HashMap<Character, int[]> dirs = new HashMap<>();
    static HashMap<ArrayList<Integer>, Long> cache = new HashMap<>();

    static char[][] num = { { '7', '8', '9' }, { '4', '5', '6' }, { '1', '2', '3' }, { 'X', '0', 'A' } };
    static int[][] dirsarr = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    static int totiters = 3;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long part1 = 0;
        char[] numpad = { '7', '8', '9', '4', '5', '6', '1', '2', '3', 'X', '0', 'A' };
        while (scan.hasNextLine()) {
            String in = scan.nextLine();
            int r1 = 3;
            int c1 = 2;
            long res = 0;
            for (int i = 0; i < in.length(); i++) {
                for (int r2 = 0; r2 < 4; r2++) {
                    for (int c2 = 0; c2 < 3; c2++) {
                        if (numpad[r2 * 3 + c2] == in.charAt(i)) {

                            res += solve(r1, c1, r2, c2);
                            r1 = r2;
                            c1 = c2;

                        }
                    }
                }
            }

            int n = Integer.parseInt(in.replace("A", ""));
            part1 += n * res;
        }
        System.out.println(part1);
        System.out.println(Long.MAX_VALUE);
    }

    static long solve(int r1, int c1, int r2, int c2) {

        long res = Long.MAX_VALUE;
        Queue<Coordinate> q = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        q.add(new Coordinate(r1, c1, ""));

        while (!q.isEmpty()) {
            Coordinate curr = q.poll();

            if (curr.y == r2 && curr.x == c2) {

                long ans = minRobot(curr.press + "A", 3);
                res = Math.min(ans, res);
                continue;
            }

            if (curr.y == 3 && curr.x == 0) {
                continue;
            }

            if (curr.y < r2) {
                Coordinate temp = new Coordinate(curr.y + 1, curr.x, curr.press + "v");
                if (!visited.contains(temp)) {
                    q.add(temp);
                    visited.add(temp);
                }
            } else if (curr.y > r2) {
                Coordinate temp = new Coordinate(curr.y - 1, curr.x, curr.press + "^");
                if (!visited.contains(temp)) {
                    q.add(temp);
                    visited.add(temp);
                }
            } else if (curr.x < c2) {
                Coordinate temp = new Coordinate(curr.y, curr.x + 1, curr.press + ">");
                if (!visited.contains(temp)) {
                    visited.add(temp);
                    q.add(temp);
                }

            } else if (curr.x > c2) {
                Coordinate temp = new Coordinate(curr.y, curr.x - 1, curr.press + "<");
                if (!visited.contains(temp)) {
                    q.add(temp);
                    visited.add(temp);
                }

            }
        }
        return res;

    }

    static long minPath(int r1, int c1, int r2, int c2, int iters) {
        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.add(r1);
        tempList.add(c1);
        tempList.add(r2);
        tempList.add(c2);
        tempList.add(iters);
        if (cache.containsKey(tempList)) {
            return cache.get(tempList);
        }

        long res = Long.MAX_VALUE;
        Queue<Coordinate> q = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();

        q.add(new Coordinate(r1, c1, ""));
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            if (curr.y == r2 && curr.x == c2) {
                long ans = minRobot(curr.press + "A", iters - 1);
                res = Math.min(ans, res);
                continue;
            }

            if (curr.y == 0 && curr.x == 0) {
                continue;
            }

            if (curr.y < r2) {
                Coordinate temp = new Coordinate(curr.y + 1, curr.x, curr.press + "v");
                if (!visited.contains(temp)) {
                    q.add(temp);
                    visited.add(temp);
                }
            } else if (curr.y > r2) {
                Coordinate temp = new Coordinate(curr.y - 1, curr.x, curr.press + "^");
                if (!visited.contains(temp)) {
                    q.add(temp);
                    visited.add(temp);
                }
            } else if (curr.x < c2) {
                Coordinate temp = new Coordinate(curr.y, curr.x + 1, curr.press + ">");
                if (!visited.contains(temp)) {
                    visited.add(temp);
                    q.add(temp);
                }

            } else if (curr.x > c2) {
                Coordinate temp = new Coordinate(curr.y, curr.x - 1, curr.press + "<");
                if (!visited.contains(temp)) {
                    q.add(temp);
                    visited.add(temp);
                }

            }
        }
        cache.put(tempList, res);
        return res;

    }

    static long minRobot(String press, int iters) {
        if (iters == 1) {
            System.out.println(press);
            return press.length();
        }

        char[] keypad = { 'X', '^', 'A', '<', 'v', '>' };
        long res = 0;
        int r1 = 0;
        int c1 = 2;

        for (int i = 0; i < press.length(); i++) {
            for (int r2 = 0; r2 < 2; r2++) {
                for (int c2 = 0; c2 < 3; c2++) {
                    if (keypad[r2 * 3 + c2] == press.charAt(i)) {
                        res += minPath(r1, c1, r2, c2, iters);
                        r1 = r2;
                        c1 = c2;
                    }
                }
            }
        }
        return res;
    }

}

class Coordinate {
    int x;
    int y;
    String press;

    public Coordinate(int x, int y, String press) {
        this.x = x;
        this.y = y;
        this.press = press;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate other)) {
            return false;
        }
        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + 31 * y;
    }

}
