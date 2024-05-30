import java.io.File;
import java.util.*;

public class Part2 {

    static class Range {
        long start;
        long end;
        long size;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Range other = (Range) obj;
            if (start != other.start)
                return false;
            if (end != other.end)
                return false;
            return true;
        }

        boolean contains(Range r) {
            return start <= r.start && end >= r.end;
        }

        public boolean inRange(long n) {
            return n >= start && n <= end;
        }

        public String toString() {
            return ("start = " + start + " end = " + end);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (start ^ (start >>> 32));
            result = prime * result + (int) (end ^ (end >>> 32));
            return result;
        }

        public static void main(String[] args) {
            try {
                Scanner scan = new Scanner(new File("input5.txt"));
                String[] seedStrings = scan.nextLine().split(" ");
                ArrayDeque<Range> seeds = new ArrayDeque<>();
                HashMap<Range, Range> ranges = new HashMap<>();
                HashSet<Range> next = new HashSet<>();

                for (int i = 1; i < seedStrings.length; i += 2) {
                    long start = Long.parseLong(seedStrings[i]);
                    long range = Long.parseLong(seedStrings[i + 1]);
                    seeds.add(new Range(start, start + range - 1));
                }
                int j = 0;
                scan.nextLine();
                scan.nextLine();
                do {
                    String line = scan.nextLine();

                    if (line.isBlank()) {
                        continue;
                    }
                    if (line.contains("map") || !scan.hasNextLine()) {
                        next.clear();

                        while (seeds.size() > 0) {
                            Range r = seeds.pop();
                            for (Range s : ranges.keySet()) {
                                Range d = ranges.get(s);
                                Range overlap = new Range(Math.max(r.start, s.start), Math.min(r.end, s.end));
                                if (overlap.start < overlap.end) {
                                    next.remove(r);
                                    next.add(new Range(overlap.start - s.start + d.start,
                                            overlap.end - s.start + d.start));
                                    if (overlap.start > r.start) {
                                        seeds.add(new Range(r.start, overlap.start - 1));
                                    }
                                    if (overlap.end < r.end) {
                                        seeds.add(new Range(overlap.end + 1, r.end));
                                    }
                                    break;
                                } else {
                                    next.add(r);
                                }
                            }

                        }

                        // System.out.println(next);
                        seeds = new ArrayDeque<>(next);
                        ranges.clear();

                        continue;
                    }
                    Range source = new Range(Long.parseLong(line.split(" ")[1]),
                            Long.parseLong(line.split(" ")[1]) + Long.parseLong(line.split(" ")[2]) - 1);
                    Range destination = new Range(Long.parseLong(line.split(" ")[0]),
                            Long.parseLong(line.split(" ")[0]) + Long.parseLong(line.split(" ")[2]) - 1);
                    ranges.put(source, destination);

                } while (scan.hasNextLine());
                System.out.println(seeds);
                long min = Long.MAX_VALUE;
                while (seeds.size() > 0) {
                    long temp = seeds.pop().start;
                    if (temp < min) {
                        min = temp;
                    }
                }

                System.out.println(min);
            } catch (Exception e) {
                System.out.println("hej");
                e.printStackTrace();
            }

        }
    }
}
