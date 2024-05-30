import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input18.txt"));
        String[][] matrix = new String[670][3];
        int c = 0;
        while (scan.hasNextLine()) {
            matrix[c++] = scan.nextLine().split(" ");
        }
        int x = 0;
        int y = 0;
        long area = 0;
        int edgepoints = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0].equals("U")) {
                area += x * (y + Integer.parseInt(matrix[i][1])) - x * y;
                y = y + Integer.parseInt(matrix[i][1]);
            } else if (matrix[i][0].equals("R")) {
                area += x * y - (x + Integer.parseInt(matrix[i][1])) * y;
                x = x + Integer.parseInt(matrix[i][1]);
            } else if (matrix[i][0].equals("D")) {
                area += x * (y - Integer.parseInt(matrix[i][1])) - x * y;
                y = y - Integer.parseInt(matrix[i][1]);
            } else if (matrix[i][0].equals("L")) {
                area += x * y - (x - Integer.parseInt(matrix[i][1])) * y;
                x = x - Integer.parseInt(matrix[i][1]);
            }
            edgepoints += Integer.parseInt(matrix[i][1]);
        }
        area = Math.abs(area) / 2;
        System.out.println(area);

        System.out.println(area + edgepoints / 2 + 1);
    }
}