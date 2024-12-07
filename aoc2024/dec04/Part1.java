import java.util.*;

class Part1 {
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

            System.out.println();
            for (int j = 0; j < matrix[0].length; j++) {
                // left right
                // System.out.print(matrix[i][j]);
                if (matrix[i][j] == 'X') {
                    if (j + 1 < matrix[0].length && matrix[i][j + 1] == 'M') {
                        if (j + 2 < matrix[0].length && matrix[i][j + 2] == 'A') {
                            if (j + 3 < matrix[0].length && matrix[i][j + 3] == 'S') {
                                result++;
                                System.out.println("hej1");
                            }
                        }
                    }
                }
                // right lef
                if (matrix[i][j] == 'X') {
                    if (j - 1 >= 0 && matrix[i][j - 1] == 'M') {
                        if (j - 2 >= 0 && matrix[i][j - 2] == 'A') {
                            if (j - 3 >= 0 && matrix[i][j - 3] == 'S') {
                                result++;
                                System.out.println("hej2");
                            }

                        }
                    }
                }
                // up down
                if (matrix[i][j] == 'X') {
                    if (i + 1 < matrix.length && matrix[i + 1][j] == 'M') {
                        if (i + 2 < matrix.length && matrix[i + 2][j] == 'A') {
                            if (i + 3 < matrix.length && matrix[i + 3][j] == 'S') {
                                result++;
                                System.out.println("hej3");
                            }
                        }
                    }
                }

                // down up
                if (matrix[i][j] == 'X') {
                    if (i - 1 >= 0 && matrix[i - 1][j] == 'M') {
                        if (i - 2 >= 0 && matrix[i - 2][j] == 'A') {
                            if (i - 3 >= 0 && matrix[i - 3][j] == 'S') {
                                result++;
                                System.out.println("hej4");
                            }

                        }
                    }
                }

                // diag LRUD
                if (matrix[i][j] == 'X') {
                    if (i + 1 < matrix.length && j + 1 < matrix[0].length && matrix[i + 1][j + 1] == 'M') {
                        if (i + 2 < matrix.length && j + 2 < matrix[0].length && matrix[i + 2][j + 2] == 'A') {
                            if (i + 3 < matrix.length && j + 3 < matrix[0].length && matrix[i + 3][j + 3] == 'S') {
                                result++;
                                System.out.println("hej5");
                            }
                        }
                    }
                }

                // diag RLDU
                if (matrix[i][j] == 'X') {
                    if (i + 1 < matrix.length && j - 1 >= 0 && matrix[i + 1][j - 1] == 'M') {
                        if (i + 2 < matrix.length && j - 2 >= 0 && matrix[i + 2][j - 2] == 'A') {
                            if (i + 3 < matrix.length && j - 3 >= 0 && matrix[i + 3][j - 3] == 'S') {
                                result++;
                                System.out.println("hej6");
                            }
                        }
                    }
                }

                // diag RLUD
                if (matrix[i][j] == 'X') {
                    if (i - 1 >= 0 && j + 1 < matrix[0].length && matrix[i - 1][j + 1] == 'M') {
                        if (i - 2 >= 0 && j + 2 < matrix[0].length && matrix[i - 2][j + 2] == 'A') {
                            if (i - 3 >= 0 && j + 3 < matrix[0].length && matrix[i - 3][j + 3] == 'S') {
                                result++;
                                System.out.println("hej7");
                            }
                        }
                    }
                }

                // diag LRDU
                if (matrix[i][j] == 'X') {
                    if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] == 'M') {
                        if (i - 2 >= 0 && j - 2 >= 0 && matrix[i - 2][j - 2] == 'A') {
                            if (i - 3 >= 0 && j - 3 >= 0 && matrix[i - 3][j - 3] == 'S') {
                                result++;
                                System.out.println("hej8");
                                System.out.println("i = " + i);
                                System.out.println("j = " + j);

                            }
                        }
                    }
                }
            }
        }
        System.out.println("part1: " + result);
    }

}