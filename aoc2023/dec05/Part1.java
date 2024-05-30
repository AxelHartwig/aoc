import java.io.File;
import java.util.*;

public class Part1 {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input5.txt"));
            String[] seedStrings = scan.nextLine().split(" ");
            List<Long> curr = new ArrayList<>();
            List<Long> next = new ArrayList<>();
            for (int i = 1; i < seedStrings.length; i++) {
                next.add(Long.parseLong(seedStrings[i]));
                curr.add(Long.parseLong(seedStrings[i]));
            }

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("")) {
                    //
                } else if (line.contains("map")) {
                    for (int i = 0; i < 20; i++) {
                        curr.set(i, next.get(i));
                    }
                } else {
                    long destinationStart = Long.parseLong(line.split(" ")[0]);
                    long sourceStart = Long.parseLong(line.split(" ")[1]);
                    long sourceEnd = sourceStart + Long.parseLong(line.split(" ")[2]) - 1;
                    for (int i = 0; i < 20; i++) {
                        long l = curr.get(i);
                        if (l >= sourceStart && l <= sourceEnd) {
                            next.set(i, curr.get(i) - sourceStart + destinationStart);
                        }

                    }

                }
            }
            next.sort(null);
            System.out.println(next.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}