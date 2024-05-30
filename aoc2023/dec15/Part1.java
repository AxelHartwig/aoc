import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Part1 {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input15.txt"));
        String[] seq = scan.nextLine().split(",");
        int curr = 0;
        int sum = 0;
        for(int i = 0; i<seq.length; i++) {
            curr = 0;
            for(int j = 0; j<seq[i].length(); j++) {
                curr += (int) seq[i].charAt(j);
                curr *= 17;
                curr = curr % 256;
            }
            sum += curr;
        }
        System.out.println(sum);
    }
}