import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part1 {
    
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input8.txt"));
            HashMap<String, String[]> map = new HashMap<>();
            char[] instructions = scan.nextLine().toCharArray();
            for(int i = 0; i<instructions.length; i++) {
                System.out.println(instructions[i]);
            }
          
            scan.nextLine(); //get rid of blank
            while(scan.hasNext()) {
                String[] line = scan.nextLine().split(" = ");
                map.put(line[0], line[1].replace("(", "").replace(")", "").split(", "));
            }

            int counter = 0;
            int incr = -1;
            String s = "AAA";
            while(incr < instructions.length) {
                counter++;
                incr++;

                if(incr == instructions.length) {
                    incr = -1;
                    counter--;
                    continue;
                }

                if(instructions[incr] == 'R') {
                    s = map.get(s)[1];
                } else if(instructions[incr] == 'L'){
                    s = map.get(s)[0];
                }
            
                if(s.equals("ZZZ")) {
                    System.out.println(counter);
                    break;
                }

            } 
        System.out.println("hej");
        System.out.println(counter);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}