import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Part1 {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input10.txt"));
            boolean[][] boolMatrix = new boolean[140][140];
            char[][] matrix = new char[140][140];
            int cnt = 0;
            int startY = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.contains("S")) {
                    startY = cnt;
                }
                matrix[cnt] = line.toCharArray();
                cnt++;
            }
            int startX = 0;
            for (int i = 0; i < matrix[startY].length; i++) {
                if (matrix[startY][i] == 'S') {
                    startX = i;
                    break;
                }
            }

            boolMatrix[startX][startY] = true;
            System.out.println(matrix[startY][startX]);
            int x1 = startX;
            int y1 = startY;
            cnt = 0;
            System.out.println("x1 = " + x1 + " y1 = " + y1);

            x1 = startX - 1;
            y1 = startY;

            boolMatrix[y1][x1] = true;

            cnt++;

            while (matrix[y1][x1] != 'S') {
                System.out.println(matrix[y1][x1]);
                System.out.println("x1 = " + x1 + " y1 = " + y1);
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
                        System.out.println("default, ej förväntat");
                }
                boolMatrix[y1][x1] = true;

                cnt++;
            }
            System.out.println(cnt / 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}