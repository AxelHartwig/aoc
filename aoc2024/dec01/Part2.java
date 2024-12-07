import java.util.*;

class Part2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Long> list1 = new ArrayList<>();
        HashMap<Long, Long> list2 = new HashMap<>();
        long result = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            list1.add(Long.parseLong(line.split("   ")[0]));
            long list2in = Long.parseLong(line.split("   ")[1]);
            if (list2.containsKey(list2in)) {
                list2.put(list2in, list2.get(list2in) + 1);
            } else {
                list2.put(list2in, (long) 1);
            }
        }

        for (int i = 0; i < list1.size(); i++) {
            if (list2.containsKey(list1.get(i))) {
                result += list1.get(i) * list2.get(list1.get(i));
            }
        }

        System.out.println(result);
    }
}