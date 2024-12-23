import java.util.*;

class Part1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Long> stones = new ArrayList<>();
        long part1 = 0, part2 = 0;
        String line = scan.nextLine();
        for (int i = 0; i < line.split(" ").length; i++) {
            part1 += blink(new Stone(Long.parseLong(line.split(" ")[i]), 25), new HashMap<Stone, Long>());
            part2 += blink(new Stone(Long.parseLong(line.split(" ")[i]), 75), new HashMap<Stone, Long>());
        }

        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);

    }

    public static long blink(Stone s, Map<Stone, Long> cache) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        if (s.n == 0) {
            cache.put(s, (long) 1);
            return 1;
        }

        if (s.value == 0) {
            long result = blink(new Stone(1, s.n - 1), cache);
            cache.put(s, result);
            return result;
        } else if (String.valueOf(s.value).length() % 2 == 0) {
            int digits = String.valueOf(s.value).length();
            long left = (long) (s.value / Math.pow(10, digits / 2));
            long right = (long) (s.value % Math.pow(10, digits / 2));
            long result = blink(new Stone(left, s.n - 1), cache) + blink(new Stone(right, s.n - 1), cache);
            cache.put(s, result);
            return result;
        } else {
            long result = blink(new Stone(s.value * 2024, s.n - 1), cache);
            cache.put(s, result);
            return result;
        }

    }
}

class Stone {
    long value;
    int n;

    Stone(long value, int n) {
        this.value = value;
        this.n = n;
    }

    @Override
    public int hashCode() {
        return (int) value * 31 + n * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Stone s)) {
            return false;
        }

        return this.value == s.value && this.n == s.n;
    }
}
