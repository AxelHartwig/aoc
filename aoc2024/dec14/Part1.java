import java.util.*;

class Part1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Robot> robots = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            line = line.replaceAll("p=", "");
            line = line.replaceAll("v=", "");
            int x = Integer.parseInt(line.split(" ")[0].split(",")[0]);
            int y = Integer.parseInt(line.split(" ")[0].split(",")[1]);
            int dx = Integer.parseInt(line.split(" ")[1].split(",")[0]);
            int dy = Integer.parseInt(line.split(" ")[1].split(",")[1]);
            robots.add(new Robot(x, y, dx, dy));
        }
        long part1 = 0;
        long quad1 = 0;
        long quad2 = 0;
        long quad3 = 0;
        long quad4 = 0;
        for (int i = 1; i <= 100000; i++) {
            for (Robot r : robots) {
                r.move();
            }
            boolean flag = true;
            for (Robot r1 : robots) {
                for (Robot r2 : robots) {
                    if (r1.x == r2.x && r1.y == r2.y && r1 != r2) {
                        flag = false;
                    }
                }
            }

            if (i == 100) {
                for (Robot r : robots) {

                    if (r.x == 101 / 2 && r.y == 103 / 2) {
                        continue;
                    }

                    if (r.x < 101 / 2 && r.y < 103 / 2) {
                        quad1++;
                    }

                    if (r.x > 101 / 2 && r.y < 103 / 2) {
                        quad2++;
                    }

                    if (r.x < 101 / 2 && r.y > 103 / 2) {
                        quad3++;
                    }
                    if (r.x > 101 / 2 && r.y > 103 / 2) {
                        quad4++;
                    }
                }
            }

            if (flag) {
                System.out.println(i);
                break;
            }

            // System.out.println();
        }

        for (int i = 0; i < 103; i++) {
            for (int j = 0; j < 101; j++) {
                for (Robot r : robots) {
                    if (r.x == j && r.y == i) {
                        System.out.print("#");
                        break;
                    }
                }
                System.out.print(" ");

            }
            System.out.println();
        }

        part1 = quad1 * quad2 * quad3 * quad4;
        // System.out.println(quad1 + " " + quad2 + " " + quad3 + " " + quad4);
        System.out.println("part1: " + part1);

    }
}

class Robot {
    int x;
    int y;
    int dx;
    int dy;

    Robot(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dy = dy;
        this.dx = dx;
    }

    void move() {
        x += dx;
        y += dy;
        x = Math.floorMod(x, 101);
        y = Math.floorMod(y, 103);
    }
}