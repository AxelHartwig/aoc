import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Boolean> wires = new HashMap<>();


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
        }


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
      
        while(in1.size() > 0) {
            for(int i = 0; i<in1.size(); i++) {
                String i1 = in1.get(i);
                String i2 = in2.get(i);
                if(wires.containsKey(i1) && wires.containsKey(i2)) {
                    wires.put(out.get(i), res(wires.get(i1), wires.get(i2), op.get(i)));
                    in1.remove(i);
                    in2.remove(i);
                    out.remove(i);
                    op.remove(i);
                    i--;
                }
            }
        }

            
        int cnt = 0;
        for(String s : wires.keySet()) {
            if(s.startsWith("z")) {
                cnt++;
            }
        }
        boolean[] result = new boolean[cnt];
        for(String s : wires.keySet()) {
            if(s.startsWith("z")) {
                result[Integer.parseInt(s.replace("z", ""))] =  wires.get(s);
            }
        }

        for(int i = result.length-1; i>= 0; i--) {
            if(result[i]) {
                System.out.print(1);
            } else if(!result[i]) {
                System.out.print(0);
            } else {
                System.out.println("hej");
            }
        }
        System.out.println();
    }

    static boolean res(boolean i1, boolean i2, String op) {
        switch (op) {
            case "XOR":
                return i1 ^ i2;    
            case "OR":
                return i1 || i2;
            case "AND":
                return i1 && i2;
            default:
                System.out.println("hej");
                return false;
        }
    }
}