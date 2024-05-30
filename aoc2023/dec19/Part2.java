import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

class Part2 {

    static class Range {
        int start;
        int end;
        int size;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
            this.size = end + 1 - start;
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
            return Objects.hash(start, end);
        }
    }

    static long countCombinations(HashMap<Character, Range> values, String workflow,
            HashMap<String, ArrayList<String>> workflows) {
        long sum = 0;
        ArrayList<String> instructions = workflows.get(workflow);
        if (workflow.equals("A")) {
            long temp = 1;
            for (Range r : values.values()) {
                temp *= r.size;
            }
            return temp;
        } else if (workflow.equals("R")) {
            return 0;
        }

        for (int i = 0; i < instructions.size(); i++) {
            String s = instructions.get(i);

            if (i == instructions.size() - 1) {
                sum += countCombinations(values, instructions.get(i), workflows);
                break;
            }

            char part = s.charAt(0);
            char comp = s.charAt(1);
            int compValue = Integer.parseInt(s.split("[<>:]")[1]);
            String tempState = s.split(":")[1];
            HashMap<Character, Range> newValues = new HashMap<>(values);
            Range r = values.get(part);

            if (comp == '<') {
                newValues.put(part, new Range(r.start, Math.min(r.end, compValue - 1)));
                values.put(part, new Range(Math.max(r.start, compValue), r.end));
                sum += countCombinations(newValues, tempState, workflows);
            } else if (comp == '>') {
                newValues.put(part, new Range(Math.max(r.start, compValue + 1), r.end));
                values.put(part, new Range(r.start, Math.min(r.end, compValue)));
                sum += countCombinations(newValues, tempState, workflows);
            }

        }
        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input19.txt"));
        HashMap<String, ArrayList<String>> workflows = new HashMap<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isBlank()) {
                break;
            }
            String[] temp = line.split("[{,}]");
            ArrayList<String> tempList = new ArrayList<>();
            for (int i = 1; i < temp.length; i++) {
                tempList.add(temp[i]);
            }
            workflows.put(temp[0], tempList);
        }

        HashMap<Character, Range> values = new HashMap<>();
        values.put('x', new Range(1, 4000));
        values.put('m', new Range(1, 4000));
        values.put('a', new Range(1, 4000));
        values.put('s', new Range(1, 4000));
        long part2 = countCombinations(values, "in", workflows);
        System.out.println(part2);

    }
}