import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part1 {

    public static Map<String, Integer> readGame(String input) {
        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("blue", 0);
        retMap.put("green", 0);
        retMap.put("red", 0);
        ArrayList<String> clues = new ArrayList<>(Arrays.asList(input.split("[; ,]")));
        clues.removeIf(s -> s.equals(""));

        for(String s : clues) {
            System.out.println(s);
        }
        for(int i = 0; i<clues.size(); i=i+2) {
            if(retMap.get(clues.get(i+1)) < Integer.parseInt(clues.get(i))) {
                retMap.put(clues.get(i+1), Integer.parseInt(clues.get(i)));
            }
        }
        //System.out.println(retMap.entrySet());
        return retMap;
    }



    public static void main(String[] args) {
        Map<String, Integer> maxMap = new HashMap<>();
        Map<String, Integer> compMap;

        maxMap.put("red", 12);
        maxMap.put("green", 13);
        maxMap.put("blue", 14);
        try {
            Scanner scan = new Scanner(new File("input2.txt"));
            int sum = 0;
            while(scan.hasNextLine()) {
                String gameString[] = scan.nextLine().split(":");
                compMap = readGame(gameString[1]);
                if(maxMap.get("red") >= compMap.get("red") && maxMap.get("blue") >= compMap.get("blue") && maxMap.get("green") >= compMap.get("green")) {
                    sum += Integer.parseInt(gameString[0].split(" ")[1]);
                }
            }

            System.out.println(sum);














        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}