import java.lang.reflect.Array;
import java.util.*;

class Part2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        scan.nextLine();

        scan.nextLine();

        scan.nextLine();
        String program = scan.nextLine().replace("Program: ", "");
        String[] instr = program.split(",");
        ArrayList<Integer> instructions = new ArrayList<>();
        for (int i = 0; i < instr.length; i++) {
            instructions.add(Integer.parseInt(instr[i]));
        }

        List<Long> startVals = new ArrayList<>();
        startVals.add(0L);

        for (int i = instructions.size() - 1; i >= 0; i--) {
            ArrayList<Long> next = new ArrayList<>();
            for (long a : startVals) {
                for (int j = 0; j < 8; j++) {
                    long curr = (a << 3) + j;
                    long b = (((curr % 8) ^ 2) ^ 7) ^ (curr >> ((curr % 8) ^ 2)) % 8;
                    if (b == instructions.get(i)) {
                        next.add(curr);
                    }
                }

            }
            System.out.println(next);
            startVals = next;
        }
        startVals.sort(null);
        System.out.println(startVals.get(0));
    }

    static long part2(List<Integer> instructions, List<Integer> target) {
        long startVal = 0;
        if (target.size() == 1) {
            startVal = 0;
        } else {
            startVal = 8 * part2(instructions, target.subList(1, target.size()));

        }

        // while (checkok(instructions, runProgram(instructions, startVal,
        // target.size()), target.size())) {
        // startVal++;
        // }

        return startVal;
    }

}
