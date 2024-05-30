import java.io.File;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        try {
            Scanner scan = new Scanner(new File("input10.txt"));
            boolean[][] boolMatrix = new boolean[140][140];
            char[][] matrix = new char[140][140];
            int cnt = 0;
            int startY = 0;
            int startX = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.contains("S")) {
                    startY = cnt;
                }
                matrix[cnt] = line.toCharArray();
                cnt++;
            }

            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[startY][i] == 'S') {
                    startX = i;
                    break;
                }
            }

            boolMatrix[startY][startX] = true;
            int x1 = startX;
            int y1 = startY;

            x1 = startX;
            y1 = startY - 1;

            boolMatrix[y1][x1] = true;

            cnt++;
            while (matrix[y1][x1] != 'S') {
                System.out.println(matrix[y1][x1]);
                switch (matrix[y1][x1]) {
                    case '|':
                        if (boolMatrix[y1 - 1][x1]) {
                            y1++;
                        } else {
                            y1--;
                        }
                        break;
                    case '-':
                        if (boolMatrix[y1][x1 - 1]) {
                            x1++;
                        } else {
                            x1--;
                        }
                        break;
                    case 'L':
                        if (boolMatrix[y1][x1 + 1]) {
                            y1--;
                        } else {
                            x1++;
                        }
                        break;
                    case 'J':
                        if (boolMatrix[y1][x1 - 1]) {
                            y1--;
                        } else {
                            x1--;
                        }
                        break;
                    case '7':
                        if (boolMatrix[y1][x1 - 1]) {
                            y1++;
                        } else {
                            x1--;
                        }
                        break;
                    case 'F':
                        if (boolMatrix[y1][x1 + 1]) {
                            y1++;
                        } else {
                            x1++;
                        }
                        break;
                    default:
                        System.out.println("hej");
                }
                boolMatrix[y1][x1] = true;
            }

            // pga min input
            matrix[startY][startX] = 'J';

            boolean inside = false;
            cnt = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    char c = matrix[i][j];
                    if ((c == '|' || c == 'L' || c == 'J') && boolMatrix[i][j]) {
                        inside = !inside;
                        continue;
                    }
                    if (inside && !boolMatrix[i][j]) {
                        matrix[i][j] = '1';
                        cnt++;
                    } else if (!boolMatrix[i][j]) {
                        matrix[i][j] = '0';
                    }
                }
                inside = false;
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    char c = matrix[i][j];
                    if (c == '1') {
                        System.out.print(ANSI_RED + matrix[i][j] + ANSI_RESET + "  ");
                    } else if (c == '0') {
                        System.out.print(ANSI_GREEN + matrix[i][j] + ANSI_RESET + "  ");
                    } else {
                        System.out.print(ANSI_BLUE + matrix[i][j] + ANSI_RESET + "  ");
                    }
                }
                System.out.println("");
            }

            System.out.println(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
