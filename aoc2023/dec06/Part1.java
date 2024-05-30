import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input.txt"));
            
            double[] times = Arrays.stream(scan.nextLine().split(" "))
                  .filter(c -> c.matches("\\d+"))
                  .mapToDouble(c -> Integer.parseInt(c))
                  .toArray();

            double[] distances = Arrays.stream(scan.nextLine().split(" "))
                  .filter(c -> c.matches("\\d+"))
                  .mapToDouble(c -> Integer.parseInt(c))
                  .toArray();

            int product = 1;
            for(int i = 0; i<times.length; i++) {
                double min = Math.floor(times[i] / 2 - Math.sqrt(times[i]*times[i] / 4 - distances[i]) + 1);
                double max = Math.ceil(times[i] / 2 + Math.sqrt(times[i]*times[i] / 4 - distances[i]));
                product *= max-min;
            }
            System.out.println(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
}