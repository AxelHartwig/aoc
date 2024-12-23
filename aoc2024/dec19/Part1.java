import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> towels = new ArrayList<>();
        String[] l = scan.nextLine().split(", ");
        for (int i = 0; i < l.length; i++) {
            towels.add(l[i]);
        }

        scan.nextLine();
        int part1 = 0;
        long part2 = 0;
        HashMap<String, Boolean> cache = new HashMap<>();
        HashMap<String, Long> cache2 = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (rec(line, towels, cache)) {
                part1++;
            }
            part2 += rec2(line, towels, cache2);

        }
        System.out.println(part1);
        System.out.println(part2);

    }

    static boolean rec(String line, ArrayList<String> towels, HashMap<String, Boolean> cache) {
        if (line.isEmpty()) {
            return true;
        }

        if (cache.containsKey(line)) {
            return cache.get(line);
        }

        boolean possible = false;
        for (String s : towels) {
            if (line.startsWith(s)) {
                possible = possible || rec(line.replaceFirst(s, ""), towels, cache);
            }
        }
        cache.put(line, possible);
        return possible;

    }

    static long rec2(String line, ArrayList<String> towels, HashMap<String, Long> cache) {
        if (line.isEmpty()) {
            return 1;
        }

        if (cache.containsKey(line)) {
            return cache.get(line);
        }

        long possible = 0;
        for (String s : towels) {
            if (line.startsWith(s)) {
                possible += rec2(line.replaceFirst(s, ""), towels, cache);
            }
        }
        cache.put(line, possible);
        return possible;

    }
}