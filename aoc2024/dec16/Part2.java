import java.util.*;

class Part2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Node> positions = new ArrayList<>();
        int cnt = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                for (int i = 0; i < 4; i++) {
                    positions.add(new Node(c, j, cnt, i));
                }
            }
            cnt++;
        }

        Set<Node> scores = djikstra(positions, 'S', 1);

        // Backtrack to find all nodes part of any shortest path
        Set<Node> bestPathNodes = new HashSet<>();
        for (Node n : positions) {
            if (n.c == 'E' && n.dist < Integer.MAX_VALUE) {
                Node curr = n;
                while (curr != null) {
                    bestPathNodes.add(curr);
                    curr = curr.prev;
                }
            }
        }

        // Mark the tiles in the map
        char[][] resultMap = new char[cnt][];
        for (int i = 0; i < cnt; i++) {
            int row = i;
            resultMap[i] = new char[positions.stream().filter(p -> p.y == row).findFirst().get().x + 1];
            Arrays.fill(resultMap[i], '#');
        }

        for (Node n : bestPathNodes) {
            if (resultMap[n.y][n.x] == '#') {
                resultMap[n.y][n.x] = 'O';
            }
        }

        for (char[] row : resultMap) {
            System.out.println(new String(row));
        }
    }

    private static Set<Node> djikstra(ArrayList<Node> positions, char start, int direction) {
        Set<Node> scores = new HashSet<>();
        PriorityQueue<Node> q = new PriorityQueue<>();

        for (Node n : positions) {
            if (n.c == start && n.direction == direction) {
                n.visited = true;
                n.dist = 0;
                q.add(n);
            }
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();
            scores.add(curr);

            for (Node n : turns(curr, positions)) {
                int newdist = curr.dist + 1000;
                if (!n.visited && n.c != '#' && newdist < n.dist) {
                    n.dist = newdist;
                    n.visited = true;
                    n.prev = curr;
                    q.add(n);
                }
            }

            Node n = neighbour(curr, positions);
            if (n == null) {
                continue;
            }

            int newdist = curr.dist + 1;
            if (!n.visited && n.c != '#' && newdist < n.dist) {
                n.dist = newdist;
                n.visited = true;
                n.prev = curr;
                q.add(n);
            }
        }

        return scores;
    }

    static ArrayList<Node> turns(Node n, ArrayList<Node> positions) {
        ArrayList<Node> turns = new ArrayList<>();
        for (Node n1 : positions) {
            if (n1.x == n.x && n1.y == n.y) {
                switch (n.direction) {
                    case 0:
                        if (n1.direction == 1 || n1.direction == 3) {
                            turns.add(n1);
                        }
                        break;
                    case 1:
                        if (n1.direction == 0 || n1.direction == 2) {
                            turns.add(n1);
                        }
                        break;
                    case 2:
                        if (n1.direction == 1 || n1.direction == 3) {
                            turns.add(n1);
                        }
                        break;
                    case 3:
                        if (n1.direction == 0 || n1.direction == 2) {
                            turns.add(n1);
                        }
                        break;
                }
            }
        }

        return turns;
    }

    static Node neighbour(Node n, ArrayList<Node> positions) {
        for (Node n1 : positions) {
            if (n1.direction != n.direction) {
                continue;
            }
            switch (n.direction) {
                case 0:
                    if (n.x == n1.x && n.y - 1 == n1.y) {
                        return n1;
                    }
                    break;
                case 1:
                    if (n.x + 1 == n1.x && n.y == n1.y) {
                        return n1;
                    }
                    break;
                case 2:
                    if (n.x == n1.x && n.y + 1 == n1.y) {
                        return n1;
                    }
                    break;
                case 3:
                    if (n.x - 1 == n1.x && n.y == n1.y) {
                        return n1;
                    }
                    break;
            }
        }
        return null;
    }
}
