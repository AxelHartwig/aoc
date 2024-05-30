import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class Part1 {
    // public static final int rows = 131;
    // public static final int cols = 131;
    public static final int size = 131;
    public static char[][] matrix = new char[size][size];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input21.txt"));
        int c = 0;
        while (scan.hasNextLine()) {
            matrix[c++] = scan.nextLine().toCharArray();
        }
        part1();
        part2();
    }

    private static void part2() {
        long t0 = System.currentTimeMillis();
        long part2;
        long odd = countPlots(0, 0, 2 * 131 + 1);
        long even = countPlots(0, 0, 2 * 131);
        long n = countPlots(130, 65, 130);
        long e = countPlots(65, 0, 130);
        long s = countPlots(0, 65, 130);
        long w = countPlots(65, 130, 130);
        long neEdgeSmall = countPlots(130, 0, 64);
        long seEdgeSmall = countPlots(0, 0, 64);
        long swEdgeSmall = countPlots(0, 130, 64);
        long nwEdgeSmall = countPlots(130, 130, 64);
        long neEdgeLarge = countPlots(130, 0, 195);
        long seEdgeLarge = countPlots(0, 0, 195);
        long swEdgeLarge = countPlots(0, 130, 195);
        long nwEdgeLarge = countPlots(130, 130, 195);

        part2 = odd * (202300 - 1) * (202300 - 1) + even * 202300 * 202300 + n + e + s + w + (neEdgeSmall +
                seEdgeSmall + swEdgeSmall + nwEdgeSmall) * (202300) + (neEdgeLarge + seEdgeLarge +
                        swEdgeLarge + nwEdgeLarge) * (202300 - 1);

        System.out.println("part2 = " + part2 + ", time: " + (System.currentTimeMillis() - t0));
    }

    private static void part1() {
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        System.out.println("part1 = " + countPlots(startRow, startCol, 64));
    }

    private static long countPlots(int startRow, int startCol, int remainingSteps) {
        List<Integer> start = new ArrayList<>(List.of(startRow, startCol));
        List<Integer> qStart = new ArrayList<>(List.of(startRow, startCol, remainingSteps));

        int[] dx = new int[] { 1, -1, 0, 0 };
        int[] dy = new int[] { 0, 0, 1, -1 };
        HashSet<List<Integer>> endPlots = new HashSet<>();
        HashSet<List<Integer>> visited = new HashSet<>();
        ArrayDeque<List<Integer>> q = new ArrayDeque<>();
        visited.add(start);
        q.offer(qStart);

        while (!q.isEmpty()) {
            List<Integer> curr = q.poll();

            if (curr.get(2) % 2 == 0) {
                endPlots.add(curr);
            } else if (curr.get(2) <= 0) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curr.get(0) + dy[i];
                int nextCol = curr.get(1) + dx[i];
                List<Integer> next = new ArrayList<Integer>(List.of(nextRow, nextCol));
                List<Integer> nextQ = new ArrayList<Integer>(List.of(nextRow, nextCol, curr.get(2) - 1));
                if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size || matrix[nextRow][nextCol] == '#'
                        || visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                q.add(nextQ);
            }
        }
        return (long) endPlots.size();
    }
}