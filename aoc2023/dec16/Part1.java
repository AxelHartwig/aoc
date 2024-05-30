import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

class Part1 {

    static final int nrows = 110;
    static final int ncols = 110;
    

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input16.txt"));
        HashSet<ArrayList<Integer>> energized = new HashSet<>();
        HashSet<ArrayList<Integer>> visited = new HashSet<>();
        char[][] matrix = new char[nrows][ncols];
        int cnt = 0;
        while(scan.hasNextLine()) {
            matrix[cnt++] = scan.nextLine().toCharArray();
        }

        laser(0,0, energized, 0, matrix, visited);
        System.out.println("part 1 = " + energized.size());
        energized.clear();
        visited.clear();
        int max = Integer.MIN_VALUE;

        for(int i = 0; i<matrix.length; i++) {
            laser(i,0, energized,3,matrix, visited);
            max = Math.max(max, energized.size());
            energized.clear();
            visited.clear();
            laser(i,matrix[0].length-1,energized, 1, matrix, visited);
            max = Math.max(max, energized.size());
            energized.clear();
            visited.clear();
            }

        for(int i = 0; i<matrix[0].length; i++) {
            laser(0,i, energized,0,matrix, visited);
            max = Math.max(max, energized.size());
            energized.clear();
            visited.clear();
            laser(matrix.length-1,i,energized, 2, matrix, visited);
            max = Math.max(max, energized.size());
            energized.clear();
            visited.clear();
            }
            System.out.println("part2 = " + max);
        }

    

    //0 = E
    //1 = N
    //2 = W
    //3 = S

    static void laser(int x, int y, HashSet<ArrayList<Integer>> energized, int startDirection, char[][] matrix, HashSet<ArrayList<Integer>> visited) {
        int direction = startDirection;
        while(x >= 0 && x < ncols && y >= 0 && y < nrows) {
            ArrayList<Integer> temp1 = new ArrayList<>();
            ArrayList<Integer> temp2 = new ArrayList<>();
            temp1.add(x);
            temp1.add(y);
            energized.add(temp1);

            temp2.add(x);
            temp2.add(y);
            temp2.add(direction);
            

            if(visited.contains(temp2)) {
                return;
            }

            visited.add(temp2);

            switch(matrix[y][x]) {
                case '.':
                    if(direction == 0) {
                        x++;
                    } else if(direction == 1) {
                        y--;
                    } else if(direction == 2) {
                        x--;
                    } else {
                        y++;
                    }
                    break;
                case '/':
                    if(direction == 0) {
                        y--;
                        direction = 1;
                    } else if(direction == 1) {
                        x++;
                        direction = 0;
                    } else if(direction == 2) {
                        y++;
                        direction = 3;
                    } else {
                        x--;
                        direction = 2;
                    }
                    break;
                case '\\':
                    if(direction == 0) {
                        y++;
                        direction = 3;
                    } else if(direction == 1) {
                        x--;
                        direction = 2;
                    } else if(direction == 2) {
                        y--;
                        direction = 1;
                    } else {
                        x++;
                        direction = 0;
                    }
                    break;
                case '-':
                    if(direction == 0) {
                        x++;
                    } else if(direction == 1) {
                        laser(x+1,y,energized, 0, matrix, visited);
                        laser(x-1,y,energized, 2, matrix, visited);
                        return;
                    } else if(direction == 2) {
                        x--;
                    } else {   
                        laser(x+1,y,energized, 0, matrix, visited);
                        laser(x-1,y,energized, 2, matrix, visited);
                        return;
                    }
                    break;
                case '|':
                    if(direction == 0) {
                        laser(x,y-1,energized, 1, matrix, visited);
                        laser(x,y+1,energized, 3, matrix, visited);
                        return;
                    } else if(direction == 1) {
                        y--;
                    } else if(direction == 2) {
                        laser(x,y-1,energized, 1, matrix, visited);
                        laser(x,y+1,energized, 3, matrix, visited);
                        return;
                    } else {
                        y++;
                    }
                    break;
                default:
                    System.out.println("hejsan");
                    return;
            }
           

        }
        return;
    }

    
}