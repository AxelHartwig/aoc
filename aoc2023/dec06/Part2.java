import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
       try {
            Scanner scan = new Scanner(new File("input.txt"));
            
            double time = Double.parseDouble(scan.nextLine().replaceAll(" ", "").split(":")[1]);
            double distance = Double.parseDouble(scan.nextLine().replaceAll(" ", "").split(":")[1]);

            double min = Math.floor(time / 2 - Math.sqrt(time*time / 4 - distance) + 1);
            double max = Math.ceil(time / 2 + Math.sqrt(time*time / 4 - distance));
            System.out.println(min);
            System.out.println(max);
            System.out.println(max-min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
         
    }
}
