import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, ArrayList<Integer>> pageRules = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                break;
            }
            int k = Integer.parseInt(line.split("\\|")[0]);
            // System.out.println(k);
            if (pageRules.containsKey(k)) {
                pageRules.get(k).add(Integer.parseInt(line.split("\\|")[1]));
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(Integer.parseInt(line.split("\\|")[1]));
                pageRules.put(k, temp);
            }
        }

        long part1 = 0;
        long part2 = 0;
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(",");
            ArrayList<Integer> order = new ArrayList<>();
            for (int i = 0; i < line.length; i++) {
                order.add(Integer.parseInt(line[i]));
            }
            boolean flag = true;
            int middle = 0;
            for (int i = 0; i < order.size(); i++) {
                if (i == order.size() / 2) {
                    middle = order.get(i);
                }
                int current = order.get(i);
                ArrayList<Integer> currentRule = pageRules.get(current);
                if (currentRule == null) {
                    continue;
                }
                for (int j = 0; j < currentRule.size(); j++) {
                    if (order.contains(currentRule.get(j))
                            && !(order.indexOf(current) < order.indexOf(currentRule.get(j)))) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                part1 += middle;
            } else {
                order.sort((p1, p2) -> {
                    if (!pageRules.containsKey(p1)) {
                        return 1;
                    }
                    if (pageRules.get(p1).contains(p2)) {
                        return -1;
                    } else {
                        return 1;
                    }
                });
                part2 += order.get(order.size() / 2);
            }
        }

        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);
    }

}