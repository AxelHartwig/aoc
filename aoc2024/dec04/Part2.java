import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        char[][] matrix = new char[140][line.length()];
        matrix[0] = line.toCharArray();
        int k = 1;
        while (scan.hasNextLine()) {
            matrix[k] = scan.nextLine().toCharArray();
            k++;
        }

        long result = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'A') {
                    if (i - 1 >= 0 && j - 1 >= 0 && i + 1 < matrix.length && j + 1 < matrix[0].length) {
                        if (matrix[i - 1][j - 1] == 'M' && matrix[i - 1][j + 1] == 'M' && matrix[i + 1][j - 1] == 'S'
                                && matrix[i + 1][j + 1] == 'S') {
                            result++;
                        }

                        if (matrix[i - 1][j - 1] == 'M' && matrix[i - 1][j + 1] == 'S' && matrix[i + 1][j - 1] == 'M'
                                && matrix[i + 1][j + 1] == 'S') {
                            result++;
                        }

                        if (matrix[i - 1][j - 1] == 'S' && matrix[i - 1][j + 1] == 'S' && matrix[i + 1][j - 1] == 'M'
                                && matrix[i + 1][j + 1] == 'M') {
                            result++;
                        }

                        if (matrix[i - 1][j - 1] == 'S' && matrix[i - 1][j + 1] == 'M' && matrix[i + 1][j - 1] == 'S'
                                && matrix[i + 1][j + 1] == 'M') {
                            result++;
                        }
                    }
                }
            }
        }
        System.out.println("part2: " + result);
    }
}
