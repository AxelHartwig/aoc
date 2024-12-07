import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long sum = 0;
        long sum2 = 0;
        boolean on = true;
        while (scan.findInLine("mul\\((\\d+),(\\d+)\\)|do(n't)?") != null) {
            MatchResult result = scan.match();
            if (result.group(0).equalsIgnoreCase("do")) {
                on = true;
                continue;
            } else if (result.group(0).equalsIgnoreCase("don't")) {
                on = false;
                continue;
            }
            sum += Long.parseLong(result.group(1)) * Long.parseLong(result.group(2));
            if (on) {
                sum2 += Long.parseLong(result.group(1)) * Long.parseLong(result.group(2));
            }

        }
        System.out.println("part1: " + sum);
        System.out.println("part2: " + sum2);

    }
}