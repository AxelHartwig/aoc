import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input19.txt"));
        HashMap<String, ArrayList<String>> procMap = new HashMap<>();
        int part1 = 0;

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
            procMap.put(temp[0], tempList);
        }

        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split("[{},=]");
            HashMap<Character, Integer> values = new HashMap<>();
            for (int i = 1; i < line.length - 1; i += 2) {
                values.put(line[i].toCharArray()[0], Integer.parseInt(line[i + 1]));
            }
            String state = "in";
            while (!(state.equals("A") || state.equals("R"))) {
                ArrayList<String> temp = procMap.get(state);
                for (int i = 0; i < temp.size(); i++) {
                    String s = temp.get(i);
                    if (i == temp.size() - 1) {
                        state = s;
                        continue;
                    }
                    char part = s.charAt(0);
                    char comp = s.charAt(1);
                    int compValue = Integer.parseInt(s.split("[<>:]")[1]);
                    String tempState = s.split(":")[1];

                    if (comp == '<') {
                        if (values.get(part) < compValue) {
                            state = tempState;
                            break;
                        }
                        continue;
                    } else {
                        if (values.get(part) > compValue) {
                            state = tempState;
                            break;
                        }
                        continue;
                    }
                }
            }

            if (state.equals("A")) {
                for (char c : values.keySet()) {
                    part1 += values.get(c);
                }
            }
        }

        System.out.println(part1);

    }
}