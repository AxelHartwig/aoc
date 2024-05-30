import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
    
    static boolean equalsWithSmudge(String s1, String s2) {
        boolean one = true;
        for(int i = 0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(one) {
                    one = false;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input13.txt"));
        ArrayList<String> pattern = new ArrayList<>();
        ArrayList<String> transPattern = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        boolean row = false;
        boolean col = false;

        while(scan.hasNextLine()) {
            row = false;
            col = false;
            String line = scan.nextLine();
            if(line.isBlank()) {

                for(int i = 0; i<pattern.size()-1; i++) {
                    if(pattern.get(i).equals(pattern.get(i+1))) {
                        int j = i;
                        int k = i+1;
                        row = true;
                        while(j >= 0 && k < pattern.size() && row) {
                            row &= pattern.get(j).equals(pattern.get(k));                    
                            j--;
                            k++;
                        }

                        if(row){
                            sum += (i+1)*100;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
  
                if(!row) {

                    //bygger transponerad matris
                    for(int i = 0; i<pattern.get(0).length(); i++) {
                        sb = new StringBuilder();
                        for(int j = 0; j<pattern.size(); j++) {
                            sb.append(pattern.get(j).charAt(i));
                        }
                        transPattern.add(sb.toString());
                    }
            

                    for(int i = 0; i<transPattern.size()-1; i++) {
                        if(transPattern.get(i).equals(transPattern.get(i+1))) {
                            int j = i;
                            int k = i+1;
                            col = true;
                            while(j >= 0 && k < transPattern.size() && col) {
                                col &= transPattern.get(j).equals(transPattern.get(k));
                                j--;
                                k++;
                            }

                            if(col){
                                sum += (i+1);
                                break;
                            } else {
                                continue;
                            }
                        }
                    }
                }
                pattern.clear();
                transPattern.clear();
            } else {
                pattern.add(line);
            }
        }

        System.out.println(sum);
    }
}