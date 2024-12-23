import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 50;
        int r = 0;
        int c = 0;
        char[][] m = new char[n][n];
        for (int i = 0; i < n; i++) {
            m[i] = scan.nextLine().toCharArray();
        }
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
        int cnt = 0;
        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i] == '^') {
                if (m[r - 1][c] == '.') {
                    m[r][c] = '.';
                    m[r - 1][c] = '@';
                    r -= 1;
                } else if (m[r - 1][c] == 'O') {
                    cnt = r - 1;
                    while (m[cnt][c] == 'O') {
                        cnt--;
                    }
                    if (m[cnt][c] == '#') {
                        // do nothing
                    } else if (m[cnt][c] == '.') {
                        m[r][c] = '.';
                        m[r - 1][c] = '@';
                        m[cnt][c] = 'O';
                        r--;
                    }
                }
            } else if (instructions[i] == '>') {
                if (m[r][c + 1] == '.') {
                    m[r][c] = '.';
                    m[r][c + 1] = '@';
                    c += 1;
                } else if (m[r][c + 1] == 'O') {
                    cnt = c + 1;
                    while (m[r][cnt] == 'O') {
                        cnt++;
                    }

                    if (m[r][cnt] == '#') {
                        // do nothing
                    } else if (m[r][cnt] == '.') {
                        m[r][c] = '.';
                        m[r][c + 1] = '@';
                        m[r][cnt] = 'O';
                        c++;
                    }
                }
            } else if (instructions[i] == 'v') {
                if (m[r + 1][c] == '.') {
                    m[r][c] = '.';
                    m[r + 1][c] = '@';
                    r += 1;
                } else if (m[r + 1][c] == 'O') {
                    cnt = r + 1;
                    while (m[cnt][c] == 'O') {
                        cnt++;
                    }
                    if (m[cnt][c] == '#') {
                        // do nothing
                    } else if (m[cnt][c] == '.') {
                        m[r][c] = '.';
                        m[r + 1][c] = '@';
                        m[cnt][c] = 'O';
                        r++;
                    }
                }
            } else if (instructions[i] == '<') {
                if (m[r][c - 1] == '.') {
                    m[r][c] = '.';
                    m[r][c - 1] = '@';
                    c -= 1;
                } else if (m[r][c - 1] == 'O') {
                    cnt = c - 1;
                    while (m[r][cnt] == 'O') {
                        cnt--;
                    }
                    if (m[r][cnt] == '#') {
                        // do nothing
                    } else if (m[r][cnt] == '.') {
                        m[r][c] = '.';
                        m[r][c - 1] = '@';
                        m[r][cnt] = 'O';
                        c--;
                    }
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
        long part1 = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 'O') {
                    part1 += 100 * i + j;
                }
            }
        }
        System.out.println("part1: " + part1);
    }

}