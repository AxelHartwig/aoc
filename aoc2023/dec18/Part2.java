import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input18.txt"));
        String[][] matrix = new String[670][2];
        int c = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine().split(" ")[2];
            matrix[c][0] = line.substring(2, 7);
            matrix[c][1] = line.substring(7, 8);
            c++;
        }

        long x = 0;
        long y = 0;
        long area = 0;
        long edgepoints = 0;
        for (int i = 0; i < matrix.length; i++) {
            int direction = Integer.parseInt(matrix[i][1]);
            long distance = Long.parseLong(matrix[i][0], 16);
            if (direction == 3) {
                area += x * (y + distance) - x * y;
                y = y + distance;
            } else if (direction == 0) {
                area += x * y - (x + distance) * y;
                x = x + distance;
            } else if (direction == 1) {
                area += x * (y - distance) - x * y;
                y = y - distance;
            } else if (direction == 2) {
                area += x * y - (x - distance) * y;
                x = x - distance;
            }
            edgepoints += distance;
        }
        area = Math.abs(area) / 2;
        System.out.println(area + edgepoints / 2 + 1);
    }
}