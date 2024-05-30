import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {

    static class MgmCalc {

        static long mgm(long nbr1, long nbr2) {
            if (nbr1 == 0 || nbr2 == 0) {
                return 0;
            }
            long higher = Math.max(nbr1, nbr2);
            long lower = Math.min(nbr1, nbr2);
            long lcm = higher;
            while (lcm % lower != 0) {
                lcm += higher;
            }
            return lcm;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input8.txt"));
            HashMap<String, String[]> map = new HashMap<>();
            char[] instructions = scan.nextLine().toCharArray();
            ArrayList<String> starts = new ArrayList<>();

            scan.nextLine(); // get rid of blank
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split(" = ");
                map.put(line[0], line[1].replace("(", "").replace(")", "").split(", "));
                if (line[0].endsWith("A")) {
                    starts.add(line[0]);
                }
            }
            long[] cycleLengths = new long[starts.size()];

            for (int i = 0; i < starts.size(); i++) {
                long counter = 0;
                int incr = -1;
                String s = starts.get(i);
                while (incr < instructions.length) {
                    counter++;
                    incr++;
                    if (incr == instructions.length) {
                        incr = -1;
                        counter--;
                        continue;
                    }

                    if (instructions[incr] == 'R') {
                        s = map.get(s)[1];
                    } else if (instructions[incr] == 'L') {
                        s = map.get(s)[0];
                    }

                    if (s.endsWith("Z")) {
                        cycleLengths[i] = counter;
                        break;
                    }
                }
            }
            long total = 1;
            for (int i = 0; i < cycleLengths.length; i++) {
                total = MgmCalc.mgm(total, cycleLengths[i]);
            }

            System.out.println(total);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}