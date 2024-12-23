import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long part1 = 0;
        long part2 = 0;
        while (scan.hasNextLine()) {
            String l1 = scan.nextLine();
            String l2 = scan.nextLine();
            String l3 = scan.nextLine();
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
            int dxA = Integer.parseInt(l1.substring(12).split(",")[0]);
            int dxB = Integer.parseInt(l2.substring(12).split(",")[0]);
            int dyA = Integer.parseInt(l1.substring(12).split(",")[1].substring(3));
            int dyB = Integer.parseInt(l2.substring(12).split(",")[1].substring(3));
            l3 = l3.replace("Prize: X=", "");
            l3 = l3.replace(" Y=", "");
            int X = Integer.parseInt(l3.split(",")[0]);
            int Y = Integer.parseInt(l3.split(",")[1]);
            long X2 = 10000000000000L + X;
            long Y2 = 10000000000000L + Y;
            int A = (X * dyB - Y * dxB) / (dxA * dyB - dyA * dxB);
            int B = (Y * dxA - dyA * X) / (dxA * dyB - dyA * dxB);
            long A2 = (X2 * dyB - Y2 * dxB) / (dxA * dyB - dyA * dxB);
            long B2 = (Y2 * dxA - dyA * X2) / (dxA * dyB - dyA * dxB);

            if (dxA * A + dxB * B == X && dyA * A + dyB * B == Y) {
                part1 += 3 * A + B;
            }

            if (dxA * A2 + dxB * B2 == X2 && dyA * A2 + dyB * B2 == Y2) {
                part2 += 3 * A2 + B2;
            }

        }

        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);

    }
}