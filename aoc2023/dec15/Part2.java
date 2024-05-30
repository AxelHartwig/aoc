import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Part2 {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input15.txt"));
        String[] seq = scan.nextLine().split(",");
        ArrayList<ArrayList<String>> hashMap = new ArrayList<>();
        for(int i = 0; i<256; i++) {
            hashMap.add(new ArrayList<>()); 
        }
        int curr = 0;
        int sum = 0;
        int j;
        for(int i = 0; i<seq.length; i++) {
            curr = 0;
            StringBuilder sb = new StringBuilder();
            for(j = 0; j<seq[i].length(); j++) {
                if(seq[i].charAt(j) == '=' || seq[i].charAt(j) == '-') {
                    break;
                }
                sb.append(seq[i].charAt(j));
                curr += (int) seq[i].charAt(j);
                curr *= 17;
                curr = curr % 256;
            }
            boolean changed = false;
            if(seq[i].charAt(j) == '=') {
                for(int k = 0; k<hashMap.get(curr).size(); k++) {
                    if(hashMap.get(curr).get(k).contains(sb.toString())) {
                        hashMap.get(curr).set(k, sb.append(" " + seq[i].charAt(j+1)).toString());
                        changed = true;
                        break;
                    }
                }
                if(!changed) {
                    hashMap.get(curr).add(sb.append(" " + seq[i].charAt(j+1)).toString());
                }
            } else {
                for(int k = 0; k<hashMap.get(curr).size(); k++) {
                    if(hashMap.get(curr).get(k).contains(sb.toString())) {
                        hashMap.get(curr).remove(k);
                        break;
                    }
                }
            }
            
        }

        for(int i = 0; i<hashMap.size(); i++) {
            for(j = 0; j<hashMap.get(i).size(); j++) {
                sum += (i+1)*(j+1)*Integer.parseInt(hashMap.get(i).get(j).split(" ")[1]);
            }
        }
        System.out.println(sum);
    }
}