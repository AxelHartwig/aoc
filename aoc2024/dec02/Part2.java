import java.util.*;

class Part2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int result = 0;
        boolean increasing = true;

        while (scan.hasNextLine()) {
            String line[] = scan.nextLine().split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(line[0]));
            boolean count = true;
            for (int i = 1; i < line.length; i++) {
                list.add(Integer.parseInt(line[i]));
                int prev = Integer.parseInt(line[i - 1]);
                int curr = Integer.parseInt(line[i]);
                if (i == 1) {
                    increasing = prev < curr;
                }

                if (prev == curr) {
                    count = false;
                }

                if (Math.abs(prev - curr) > 3) {
                    count = false;
                }

                if (increasing && curr < prev || !increasing && curr > prev) {
                    count = false;
                }
            }

            if (count) {
                result++;
            } else {
                for (int j = 0; j < line.length; j++) {
                    int removed = list.remove(j);
                    boolean count2 = true;
                    for (int i = 1; i < list.size(); i++) {
                        int prev = list.get(i - 1);
                        int curr = list.get(i);
                        if (i == 1) {
                            increasing = prev < curr;
                        }

                        if (prev == curr) {
                            count2 = false;
                        }

                        if (Math.abs(prev - curr) > 3) {
                            count2 = false;
                        }

                        if (increasing && curr < prev || !increasing && curr > prev) {
                            count2 = false;
                        }
                    }
                    if (count2) {
                        result++;
                        break;
                    }
                    list.add(j, removed);
                }
            }
        }
        System.out.println(result);
    }
}