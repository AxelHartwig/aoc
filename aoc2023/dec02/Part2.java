import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {

    public static int readGame(String input) {
        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("blue", 0);
        retMap.put("green", 0);
        retMap.put("red", 0);
        ArrayList<String> clues = new ArrayList<>(Arrays.asList(input.split("[; ,]")));
        clues.removeIf(s -> s.equals(""));
        for(int i = 0; i<clues.size(); i=i+2) {
            if(retMap.get(clues.get(i+1)) < Integer.parseInt(clues.get(i))) {
                retMap.put(clues.get(i+1), Integer.parseInt(clues.get(i)));
            }
        }
        System.out.println(retMap.get("red")*retMap.get("blue")*retMap.get("green"));
        return retMap.get("red")*retMap.get("blue")*retMap.get("green");
    }



    public static void main(String[] args) {

        try {
            Scanner scan = new Scanner(new File("input2.txt"));
            int sum = 0;
            while(scan.hasNextLine()) {
                String gameString[] = scan.nextLine().split(":");
                sum += readGame(gameString[1]);
            }

            System.out.println(sum);














        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}