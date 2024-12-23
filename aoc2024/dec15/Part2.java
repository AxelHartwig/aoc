import java.net.CookieStore;
import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 50;
        int r = 0;
        int c = 0;
        char[][] m = new char[n][n * 2];
        for (int i = 0; i < n; i++) {
            char[] line = scan.nextLine().toCharArray();
            int q = 0;
            for (int j = 0; j < line.length; j++) {
                if (line[j] == '.') {
                    m[i][q] = '.';
                    m[i][q + 1] = '.';
                } else if (line[j] == '#') {
                    m[i][q] = '#';
                    m[i][q + 1] = '#';
                } else if (line[j] == 'O') {
                    m[i][q] = '[';
                    m[i][q + 1] = ']';
                } else {
                    m[i][q] = '@';
                    m[i][q + 1] = '.';
                }
                q += 2;
            }
        }

        // for (int k = 0; k < m.length; k++) {
        // for (int j = 0; j < m[0].length; j++) {
        // System.out.print(m[k][j]);
        // }
        // System.out.println();
        // }
        scan.nextLine();
        char[] instructions = scan.nextLine().toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == '@') {
                    r = i;
                    c = j;
                }
            }
        }

        for (int i = 0; i < instructions.length; i++) {

            ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
            moves.add(new Coordinate(c, r));
            boolean flag = false;
            for (int cnt = 0; cnt < moves.size(); cnt++) {
                Coordinate coord = moves.get(cnt);
                int x = coord.x;
                int y = coord.y;
                // System.out.println("x = " + x + " y = " + y);

                int newx = x;
                int newy = y;
                if (instructions[i] == '^') {
                    newy--;
                } else if (instructions[i] == '>') {
                    newx++;
                } else if (instructions[i] == 'v') {
                    newy++;
                } else if (instructions[i] == '<') {
                    newx--;
                }

                // System.out.println("x = " + newx + " y = " + newy);

                if (m[newy][newx] == ']' || m[newy][newx] == '[') {
                    if (!moves.contains(new Coordinate(newx, newy))) {
                        moves.add(new Coordinate(newx, newy));
                    }
                    if (m[newy][newx] == '[') {
                        if (!moves.contains(new Coordinate(newx + 1, newy))) {
                            moves.add(new Coordinate(newx + 1, newy));
                        }
                    }

                    if (m[newy][newx] == ']') {
                        if (!moves.contains(new Coordinate(newx - 1, newy))) {
                            moves.add(new Coordinate(newx - 1, newy));
                        }
                    }
                } else if (m[newy][newx] == '#') {
                    flag = true;
                    // System.out.println("hej");
                    break;
                }
            }

            if (flag) {
                continue;
            } else {
                char[][] newm = new char[m.length][m[0].length];
                for (int k = 0; k < m.length; k++) {
                    for (int p = 0; p < m[0].length; p++) {
                        newm[k][p] = m[k][p];
                    }
                }

                for (Coordinate coord : moves) {
                    newm[coord.y][coord.x] = '.';
                }

                for (Coordinate coord : moves) {
                    if (instructions[i] == '^') {
                        newm[coord.y - 1][coord.x] = m[coord.y][coord.x];
                    } else if (instructions[i] == '>') {
                        newm[coord.y][coord.x + 1] = m[coord.y][coord.x];
                    } else if (instructions[i] == 'v') {
                        newm[coord.y + 1][coord.x] = m[coord.y][coord.x];
                    } else if (instructions[i] == '<') {
                        newm[coord.y][coord.x - 1] = m[coord.y][coord.x];
                    }
                }

                m = newm;
                if (instructions[i] == '^') {
                    r--;
                } else if (instructions[i] == '>') {
                    c++;
                } else if (instructions[i] == 'v') {
                    r++;
                } else if (instructions[i] == '<') {
                    c--;
                }

            }

            // for (int k = 0; k < m.length; k++) {
            // for (int j = 0; j < m[0].length; j++) {
            // System.out.print(m[k][j]);
            // }
            // System.out.println();
            // }
            // System.out.println(instructions[i]);
        }
        long part2 = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == '[') {
                    part2 += 100 * i + j;
                }
            }
        }
        System.out.println("part2: " + part2);
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