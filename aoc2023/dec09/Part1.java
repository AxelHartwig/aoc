import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        try {
            boolean part1 = false;
            Scanner scan = new Scanner(new File("input9.txt"));
            int sum = 0;
            while (scan.hasNextLine()) {
                boolean allZeroes = false;
                int counter = 0;
                ArrayList<ArrayList<Integer>> arrayListList = new ArrayList<>();
                int[] line = Arrays.stream(scan.nextLine().split(" ")).mapToInt(c -> Integer.parseInt(c)).toArray();
                arrayListList.add(new ArrayList<>());

                if (part1) {
                    for (int i = 0; i < line.length; i++) {
                        arrayListList.get(0).add(line[i]);
                    }
                }

                if (!part1) {
                    for (int i = line.length - 1; i >= 0; i--) {
                        arrayListList.get(0).add(line[i]);
                    }
                }

                while (!allZeroes) {
                    arrayListList.add(new ArrayList<>());
                    int current = arrayListList.get(counter).size() - 1;
                    counter++;
                    for (int i = 0; i < current; i++) {
                        arrayListList.get(counter)
                                .add(arrayListList.get(counter - 1).get(i + 1) - arrayListList.get(counter - 1).get(i));
                    }
                    for (int i = 0; i < arrayListList.get(counter).size(); i++) {
                        allZeroes = true;
                        if (arrayListList.get(counter).get(i) != 0) {
                            allZeroes = false;
                            break;
                        }
                    }
                }

                arrayListList.get(arrayListList.size() - 1).add(0);
                for (int i = arrayListList.size() - 2; i >= 0; i--) {
                    arrayListList.get(i).add(arrayListList.get(i).get(arrayListList.get(i).size() - 1)
                            + arrayListList.get(i + 1).get(arrayListList.get(i + 1).size() - 1));
                }
                sum += arrayListList.get(0).get(arrayListList.get(0).size() - 1);

            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
