import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Long> list1 = new ArrayList<>();
        ArrayList<Long> list2 = new ArrayList<>();
        long result = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            list1.add(Long.parseLong(line.split("   ")[0]));
            list2.add(Long.parseLong(line.split("   ")[1]));
        }

        list1.sort((l1, l2) -> {
            return l1.compareTo(l2);
        });

        list2.sort((l1, l2) -> {
            return l1.compareTo(l2);
        });

        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i) + "  " + list2.get(i));

            result += Math.abs(list2.get(i) - list1.get(i));
        }

        System.out.println(result);
    }
}