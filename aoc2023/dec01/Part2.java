import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args){
        Map<String, Integer> intMap = new HashMap<>();
        intMap.put("one", 1);
        intMap.put("one", 1);
        intMap.put("two", 2);
        intMap.put("three", 3);
        intMap.put("four", 4);
        intMap.put("five", 5);
        intMap.put("six", 6);
        intMap.put("seven", 7);
        intMap.put("eight", 8);
        intMap.put("nine", 9);

        try {
            Scanner scan = new Scanner(new File("input1.txt"));
		    int sum = 0;
		    int firstInt = 0;
		    int lastInt = 0;
            List<Integer> intList = new ArrayList<>();
            while(scan.hasNextLine()) {
                String in = scan.nextLine();
                intList = findInt(in, intMap);
                firstInt = intList.get(0);
                lastInt = intList.get(intList.size()-1);
                System.out.println(firstInt*10 + lastInt);
                sum = sum + firstInt*10 + lastInt;
            }
            System.out.println(sum);
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    public static List<Integer> findInt(String in, Map<String, Integer> intMap) {

        List<Integer> intList = new ArrayList<>();
        char[] charArray = in.toCharArray();

        for(int i = 0;i<charArray.length; i++) {
            if(i < charArray.length - 2) {
                String subString = in.substring(i,i+3);

                if(intMap.containsKey(subString)) {
                    intList.add(intMap.get(subString));
                }
            }

            if(i < charArray.length - 3) {
                String subString = in.substring(i,i+4);
                if(intMap.containsKey(subString)) {
                    intList.add(intMap.get(subString));
                }
            }

            if(i < charArray.length - 4) {
                String subString = in.substring(i,i+5);
                if(intMap.containsKey(subString)) {
                    intList.add(intMap.get(subString));
                }
            }

            if(Character.isDigit(charArray[i])) {
                intList.add(charArray[i] - '0');
            }
        }
        return intList;



    }
}
