import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long part1 = 0;
        long part2 = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            long testVal = Long.parseLong(line.split(": ")[0]);

            String[] temp = line.split(": ")[1].split(" ");
            long[] equation = new long[temp.length];
            for (int i = 0; i < equation.length; i++) {
                equation[i] = Long.parseLong(temp[i]);
            }

            if (isValid(testVal, equation, 0, equation[0])) {
                part1 += testVal;
            }

            if (isValid2(testVal, equation, 0, equation[0])) {
                part2 += testVal;
            }
        }
        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);
    }

    static boolean isValid(long testval, long[] equation, int i, long res) {
        if (i >= equation.length - 1 || res > testval) {
            return res == testval;
        } else {
            return isValid(testval, equation, i + 1, res * equation[i + 1])
                    || isValid(testval, equation, i + 1, res + equation[i + 1]);
        }
    }

    static boolean isValid2(long testval, long[] equation, int i, long res) {
        if (i >= equation.length - 1) {
            return res == testval;
        } else {
            return isValid2(testval, equation, i + 1, res * equation[i + 1])
                    || isValid2(testval, equation, i + 1, res + equation[i + 1])
                    || isValid2(testval, equation, i + 1,
                            Long.parseLong(Long.toString(res) + Long.toString(equation[i + 1])));
        }
    }
}