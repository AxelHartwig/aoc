import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int result = 0;
        boolean increasing = true;

        while (scan.hasNextLine()) {
            String line[] = scan.nextLine().split(" ");
            boolean count = true;
            for (int i = 1; i < line.length; i++) {
                int prev = Integer.parseInt(line[i - 1]);
                int curr = Integer.parseInt(line[i]);
                if (i == 1) {
                    increasing = prev < curr;
                }

                if (prev == curr) {
                    count = false;
                }

                if (Math.abs(prev - curr) > 3) {
                    count = false;
                }

                if (increasing && curr < prev || !increasing && curr > prev) {
                    count = false;
                }
            }

            if (count) {
                result++;
            }
        }
        System.out.println(result);

    }

}