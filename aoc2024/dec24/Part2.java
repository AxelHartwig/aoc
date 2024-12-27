import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Part2 {
        public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Boolean> wires = new HashMap<>();
        TreeSet<String> swapped = new TreeSet<>();
        
        long x = 0; 
        long y = 0;

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.isBlank()) {
                break;
            }
            if(line.split(": ")[1].equals("1")) {
                wires.put(line.split(": ")[0], true);
            } else{
                wires.put(line.split(": ")[0], false);
            }

            if(line.split(": ")[0].startsWith("y")) {
                System.out.print(line.split(": ")[1]);
            }

        }
        System.out.println();
        


        ArrayList<String> in1 = new ArrayList<>();
        ArrayList<String> in2 = new ArrayList<>();
        ArrayList<String> op = new ArrayList<>();
        ArrayList<String> out = new ArrayList<>();
        while(scan.hasNextLine()) {
            String[] line = scan.nextLine().replace(" -> ", " ").split(" ");
            in1.add(line[0]);
            in2.add(line[2]);
            op.add(line[1]);
            out.add(line[3]);
        }
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;

        for(int i = 0; i<in1.size(); i++) {
            String i1 = in1.get(i);
            String i2 = in2.get(i);
            String oper = op.get(i);
            String output = out.get(i);


            if(output.startsWith("z") && !oper.equals("XOR") && Integer.parseInt(output.replace("z", "")) != 45) {
                if(!swapped.contains(output)) {
                    one++;
                }
                swapped.add(output);
            }

            if(oper.equals("XOR") 
                && !(output.startsWith("z") || output.startsWith("x") || output.startsWith("y"))
                && !(i1.startsWith("z") || i1.startsWith("x") || i1.startsWith("y"))
                && !(i2.startsWith("z") || i2.startsWith("x") || i2.startsWith("y"))
                ) {
                    if(!swapped.contains(output)) {
                        two++;
                    }
                    swapped.add(output);
            }

            if(oper.equals("XOR")) {
                for(int j = 0; j<in1.size(); j++) {
                    if((output.equals(in1.get(j)) || output.equals(in2.get(j))) && op.get(j).equals("OR")) {
                        if(!swapped.contains(output)) {
                            three++;
                        } 
                        swapped.add(output);
                       
                    }
                }
            }
            
            if(oper.equals("AND") && !i1.equals("x00") && !i2.equals("x00")) {
                for(int j = 0; j<in1.size(); j++) {
                    if((output.equals(in1.get(j)) || output.equals(in2.get(j)))&& !op.get(j).equals("OR")) {
                        if(!swapped.contains(output)) {
                            four++;
                        }
                        swapped.add(output);
                    }
                }
            }

                
        }

        //System.out.println(swapped.size());
        //System.out.println("one " + one);
        //System.out.println("two " + two);
//
        //System.out.println("three " + three);
        //System.out.println("four " + four);

        for(String s : swapped) {
            System.out.print(s + ",");
        }
        System.out.println();
//  
  
    }


}
