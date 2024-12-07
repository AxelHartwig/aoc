import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] matrix = new char[130][130];

        long part1 = 0;
        int count = 0;
        int r = 0;
        int c = 0;
        int startR = 0;
        int startC = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            matrix[count] = line.toCharArray();
            if (line.contains("^")) {
                r = count;
                c = line.indexOf("^");
                startR = r;
                startC = c;
            }
            count++;
        }
        int direction = 0;

        while (r >= 0 && c >= 0 && r < matrix.length && c < matrix[0].length) {
            // System.out.println("r = " + r + " c = " + c + " direction = " + direction + "
            // part1 = " + part1);
            // System.out.println("matrix[r][c]");
            if (matrix[r][c] != 'X') {
                part1++;
            }

            // for (int i = 0; i < matrix.length; i++) {
            // for (int j = 0; j < matrix.length; j++) {
            // System.out.print(matrix[i][j]);
            // }
            // System.out.println();
            // }
            // System.out.println();
            // System.out.println();

            switch (direction) {
                case 0:
                    if (r - 1 < 0) {
                        r--;
                        break;
                    }
                    matrix[r][c] = 'X';
                    if (matrix[r - 1][c] == '#') {
                        direction = 1;
                        c++;
                    } else {
                        r--;
                    }
                    break;
                case 1:
                    if (c + 1 >= matrix[0].length) {
                        c++;
                        break;
                    }
                    matrix[r][c] = 'X';

                    if (matrix[r][c + 1] == '#') {
                        direction = 2;
                        r++;
                    } else {
                        c++;
                    }
                    break;
                case 2:
                    if (r + 1 >= matrix.length) {
                        r++;
                        break;
                    }
                    matrix[r][c] = 'X';

                    if (matrix[r + 1][c] == '#') {
                        direction = 3;
                        c--;
                    } else {
                        r++;
                    }
                    break;
                case 3:
                    if (c - 1 < 0) {
                        c--;
                        break;
                    }
                    matrix[r][c] = 'X';
                    if (matrix[r][c - 1] == '#') {
                        direction = 0;
                        r--;
                    } else {
                        c--;
                    }
                    break;
            }
        }

        System.out.println("part1: " + part1);
        long part2 = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // System.out.println("i = " + i + " j = " + j);
                r = startR;
                c = startC;
                direction = 0;
                if (matrix[i][j] == '#') {
                    continue;
                }
                char temp = matrix[i][j];
                matrix[i][j] = '#';
                int counter = 0;
                while (r >= 0 && c >= 0 && r < matrix.length && c < matrix[0].length) {
                    // System.out.println("r = " + r + " c = " + c + " direction = " + direction);
                    // System.out.println(r == startR && c == startC && direction == 0);
                    // System.out.println(part2);

                    if (counter++ > 100000) {
                        part2++;
                        break;
                    }
                    switch (direction) {
                        case 0:
                            if (r - 1 < 0) {
                                r--;
                                break;
                            }
                            matrix[r][c] = 'X';
                            if (matrix[r - 1][c] == '#') {
                                direction = 1;
                                // c++;
                            } else {
                                r--;
                            }
                            break;
                        case 1:
                            if (c + 1 >= matrix[0].length) {
                                c++;
                                break;
                            }
                            matrix[r][c] = 'X';

                            if (matrix[r][c + 1] == '#') {
                                direction = 2;
                                // r++;
                            } else {
                                c++;
                            }
                            break;
                        case 2:
                            if (r + 1 >= matrix.length) {
                                r++;
                                break;
                            }
                            matrix[r][c] = 'X';

                            if (matrix[r + 1][c] == '#') {
                                direction = 3;
                                // c--;
                            } else {
                                r++;
                            }
                            break;
                        case 3:
                            if (c - 1 < 0) {
                                c--;
                                break;
                            }
                            matrix[r][c] = 'X';
                            if (matrix[r][c - 1] == '#') {
                                direction = 0;
                                // r--;
                            } else {
                                c--;
                            }
                            break;
                    }
                }
                matrix[i][j] = temp;
            }

        }
        System.out.println("part2: " + part2);
    }
}