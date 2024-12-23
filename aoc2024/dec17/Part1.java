import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long rega = Integer.parseInt(scan.nextLine().replace("Register A: ", ""));
        long regb = Integer.parseInt(scan.nextLine().replace("Register B: ", ""));
        long regc = Integer.parseInt(scan.nextLine().replace("Register C: ", ""));
        rega = 190615597431823L;

        scan.nextLine();
        String program = scan.nextLine().replace("Program: ", "");
        String[] instr = program.split(",");
        HashMap<Integer, Long> combo = new HashMap<>();
        combo.put(0, 0L);
        combo.put(1, 1L);
        combo.put(2, 2L);
        combo.put(3, 3L);
        combo.put(4, rega);
        combo.put(5, regb);
        combo.put(6, regc);
        combo.put(7, -1L);
        boolean prev = false;

        extracted(rega, regb, regc, instr, combo, prev);
        System.out.println();
        long part2 = 0;
        ArrayList<String> res = new ArrayList<>();

    }

    private static void extracted(long rega, long regb, long regc, String[] instr, HashMap<Integer, Long> combo,
            boolean prev) {
        for (int i = 0; i < instr.length; i += 2) {
            int l = Integer.parseInt(instr[i + 1]);
            long c = combo.get(Integer.parseInt(instr[i + 1]));
            switch (Integer.parseInt(instr[i])) {
                case 0:
                    rega = rega / (int) Math.pow(2, c);
                    combo.put(4, rega);
                    break;
                case 1:
                    regb = regb ^ l;
                    combo.put(5, regb);
                    break;
                case 2:
                    regb = c % 8;
                    combo.put(5, regb);
                    break;
                case 3:
                    if (rega != 0) {
                        i = l - 2;
                    }
                    break;
                case 4:
                    regb = regb ^ regc;
                    combo.put(5, regb);
                    break;
                case 5:
                    if (prev) {
                        System.out.print(",");
                    }
                    System.out.print(c % 8);
                    prev = true;
                    break;
                case 6:
                    regb = rega / (int) Math.pow(2, c);
                    combo.put(5, regb);
                    break;
                case 7:
                    regc = rega / (int) Math.pow(2, c);
                    combo.put(6, regb);
                    break;
                case 8:
                    break;
            }
        }
    }

}
