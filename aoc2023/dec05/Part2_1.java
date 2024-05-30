import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2_1 {

    static class Range implements Comparable<Range> {
        long start, end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        // returns true if Range r starts in this but doesnt end in this
        public boolean overlapRight(Range r) {
            return r.start >= start && r.start <= end && r.end > end;
        }

        // returns true if Range r ends in this but doesnt start in it
        public boolean overlapLeft(Range r) {
            return r.start < start && r.end <= end && r.end >= start;
        }

        // returns true if Range r starts and ends in this
        public boolean contains(Range r) {
            return r.start >= start && r.end <= end;
        }

        @Override
        public String toString() {
            return "(" + start + "-" + end + ")";
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

        @Override
        public int compareTo(Range o) {
            long r = start - o.start;
            if (r == 0) {
                r = end - o.end;
            }
            if (r < 0) {
                return -1;
            }
            if (r > 0) {
                return 1;
            }
            return 0;
        }

    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("test.txt"));
            String[] seedStrings = scan.nextLine().split(" ");
            TreeSet<Range> rangeSet = new TreeSet<>();
            TreeSet<Range> nextRangeSet = new TreeSet<>();
            HashSet<Range> tempSet = new HashSet<>();
            ArrayList<Range> sourcerangeSet = new ArrayList<>();
            ArrayList<Range> destinationrangeSet = new ArrayList<>();

            for (int i = 1; i < seedStrings.length; i += 2) {
                long start = Long.parseLong(seedStrings[i]);
                long range = Long.parseLong(seedStrings[i + 1]);
                rangeSet.add(new Range(start, start + range - 1));
                nextRangeSet.add(new Range(start, start + range - 1));
            }

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.isBlank()) {
                    // inget
                    continue;
                }

                if (line.contains("map") || !scan.hasNextLine()) {
                    for (int j = 0; j < sourcerangeSet.size(); j++) {
                        Range s = sourcerangeSet.get(j);
                        Range d = destinationrangeSet.get(j);
                        for (Range r : rangeSet) {
                            if (r.contains(s)) {
                                tempSet.remove(r);
                                nextRangeSet.add(d);
                                if (r.start != s.start - 1) {
                                    nextRangeSet.add(new Range(r.start, s.start - 1));
                                }
                                if (s.end + 1 != r.end) {
                                    nextRangeSet.add(new Range(s.end + 1, r.end));
                                }
                                continue;
                            } else if (r.overlapRight(s)) {
                                tempSet.remove(r);
                                nextRangeSet.add(new Range(d.start, d.start + (r.end - s.start)));
                                nextRangeSet.add(new Range(r.start, s.start - 1));
                                continue;
                            } else if (r.overlapLeft(s)) {
                                tempSet.remove(r);
                                nextRangeSet.add(new Range(s.end + 1, r.end));
                                nextRangeSet.add(new Range(d.end - (s.end - r.start), d.end));
                                continue;
                            } else {
                                tempSet.add(r);
                            }
                        }
                        nextRangeSet.addAll(tempSet);
                    }
                    System.out.println(rangeSet);
                    System.out.println(nextRangeSet);
                    rangeSet = new TreeSet<>(nextRangeSet);
                    nextRangeSet.clear();
                    System.out.println(rangeSet);
                    System.out.println(nextRangeSet);

                    continue;
                }

                sourcerangeSet.add(new Range(Long.parseLong(line.split(" ")[1]),
                        Long.parseLong(line.split(" ")[2]) + Long.parseLong(line.split(" ")[1]) - 1));
                destinationrangeSet.add(new Range(Long.parseLong(line.split(" ")[0]),
                        Long.parseLong(line.split(" ")[2]) + Long.parseLong(line.split(" ")[0]) - 1));
            }
            System.out.println(rangeSet.first());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
