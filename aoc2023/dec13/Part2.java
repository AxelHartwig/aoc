import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
    
    static int diff(String s1, String s2) {
        int diffs = 0;
        for(int i = 0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                diffs++;
            }
        }
        return diffs;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input13.txt"));
        ArrayList<String> pattern = new ArrayList<>();
        ArrayList<String> transPattern = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        boolean row = false;
        int differences = 0;

        while(scan.hasNextLine()) {
            row = false;
            String line = scan.nextLine();
            if(line.isBlank()) {

                for(int i = 0; i<pattern.size()-1; i++) {
                    differences = 0;
                    int j = i;
                    int k = i+1;
                    while(differences <= 1 && j >= 0 && k < pattern.size()) {
                        differences += diff(pattern.get(j), pattern.get(k));
                        j--;
                        k++;
                    }

                    if(differences <= 1) {
                        sum += (i+1)*100;
                        row = true;
                        break;
                    }
                }

                if(row) {   
                    pattern.clear();
                    transPattern.clear();
                    continue;
                }
                
                //bygger transponerad matris
                for(int i = 0; i<pattern.get(0).length(); i++) {
                    sb = new StringBuilder();
                    for(int j = 0; j<pattern.size(); j++) {
                        sb.append(pattern.get(j).charAt(i));
                    }
                    transPattern.add(sb.toString());
                }
            

                for(int i = 0; i<transPattern.size()-1; i++) {
                    differences = 0;
                    int j = i;
                    int k = i+1;
                    while(differences <= 1 && j >= 0 && k < transPattern.size()) {
                        differences += diff(transPattern.get(j), transPattern.get(k));
                        j--;
                        k++;
                    }

                    if(differences <= 1) {
                        sum += (i+1);
                        break;
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
