import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> keys = new ArrayList<>();
        ArrayList<ArrayList<Integer>> locks = new ArrayList<>();

        while (scan.hasNextLine()) {
            char[][] in = new char[7][5];
            boolean key = true;
            for (int i = 0; i < 7; i++) {
                String line = scan.nextLine();
                if (i == 0) {
                    if (line.contains("#")) {
                        key = false;
                    }
                }
                in[i] = line.toCharArray();
            }

            ArrayList<Integer> heights = new ArrayList<>();
            for (int i = 0; i < in[0].length; i++) {
                int height = -1;
                for (int j = 0; j < in.length; j++) {
                    if (in[j][i] == '#') {
                        height++;
                    }
                }
                heights.add(height);
            }

            if (key) {
                keys.add(heights);
            } else {
                locks.add(heights);
            }
            // blank
            if (scan.hasNextLine()) {
                scan.nextLine();
                continue;
            }

        }
        long part1 = 0;
        for (ArrayList<Integer> key : keys) {
            for (ArrayList<Integer> lock : locks) {
                boolean flag = true;
                for (int i = 0; i < lock.size(); i++) {
                    if (lock.get(i) + key.get(i) >= 6) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    part1++;
                }
            }
        }
        System.out.println(part1);

    }

}