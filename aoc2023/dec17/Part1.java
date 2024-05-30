import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

class Part1 {

    final static int rows = 141;
    final static int cols = 141;
    final static boolean part2 = false;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input17.txt"));
        int[][] matrix = new int[rows][cols];
        int c = 0;
        while (scan.hasNextLine()) {
            matrix[c++] = Arrays.stream(scan.nextLine().split("")).mapToInt(s -> Integer.parseInt(s)).toArray();
        }
        PriorityQueue<Node> queue = new PriorityQueue<>();
        HashSet<Node> visited = new HashSet<>();
        int[] dx = new int[] { 1, -1, 0, 0 };
        int[] dy = new int[] { 0, 0, 1, -1 };

        if (part2) {
            queue.add(new Node(0, 0, 0, 1, 0, 0));
            queue.add(new Node(0, 0, 0, 0, 1, 0));
        } else {
            queue.add(new Node(0, 0, 0, 0, 0, 0));

        }

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.x == matrix[0].length - 1 && curr.y == matrix.length - 1 && (curr.counter >= 4 || !part2)) {
                System.out.println(curr.loss);
                break;
            }

            if (visited.contains(curr)) {
                continue;
            }

            visited.add(curr);

            for (int i = 0; i < 4; i++) {
                int nextX = curr.x + dx[i];
                int nextY = curr.y + dy[i];
                if (nextX < 0 || nextX >= matrix[0].length || nextY < 0 || nextY >= matrix.length) {
                    continue; // utanför
                }

                if (dx[i] == -curr.dx && dy[i] == -curr.dy) {
                    continue; // baklänges
                }

                if (dx[i] == curr.dx && dy[i] == curr.dy) {
                    if ((curr.counter < 10 || !part2) && (curr.counter < 3 || part2)) {
                        queue.offer(new Node(nextX, nextY, curr.loss + matrix[nextY][nextX], dx[i], dy[i],
                                curr.counter + 1));
                    } else {
                        continue;
                    }
                } else if (curr.counter >= 4 || !part2) {
                    queue.offer(new Node(nextX, nextY, curr.loss + matrix[nextY][nextX], dx[i], dy[i], 1));
                }
            }
        }

    }

}
