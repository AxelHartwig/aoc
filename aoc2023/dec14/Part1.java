import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input14.txt"));
        ArrayList<String> in = new ArrayList<>();        
        while(scan.hasNextLine()) {
            in.add(scan.nextLine());
        }
        part1(in);
    }

    private static void part1(ArrayList<String> in) {
        ArrayList<String> cols;
        ArrayList<String> tiltedCols;
        cols = turn(in);
        tiltedCols = tilt(cols);
        
    
        int part1 = 0;
        for(int i = 0; i<tiltedCols.size(); i++) {
            for(int j = 0; j<tiltedCols.get(0).length(); j++) {
                if(tiltedCols.get(i).charAt(j) == 'O') {
                    part1 += tiltedCols.get(0).length()-j;
                }
            }
            
        }
        System.out.println("part1 = " + part1);
    }

    private static void part2(ArrayList<String> in) {
        int cycleLength = 0;
        ArrayList<String> temp = in;
        while(true) {
            in = turn(in);
            in = tilt(in);
            for(int i = 0; i<temp.size(); i++) {
            }
        }
    }

    private static ArrayList<String> tilt(ArrayList<String> rocks) {
        ArrayList<String> tilted = new ArrayList<>();
        for(int k = 0; k<rocks.size(); k++) {
            tilted.add("");
            String[] split = rocks.get(k).split("#");

  
            for(int i = 0; i<split.length; i++) {
                char[] sorted = split[i].toCharArray();
                Arrays.sort(sorted);
                for(int j = sorted.length-1; j >= 0; j--) {
                    tilted.set(k, tilted.get(k).concat(Character.toString(sorted[j])));
                }
                tilted.set(k, tilted.get(k).concat("#"));
            }
            while(tilted.get(k).length() < rocks.get(k).length()) {
                tilted.set(k, tilted.get(k).concat("#"));
            }

            if(tilted.get(k).length() > rocks.get(k).length()) {
                tilted.set(k,tilted.get(k).substring(0,tilted.get(k).length()-1));
            }
        }
        return tilted;
    }

    private static ArrayList<String> turn(ArrayList<String> rocks) {
        ArrayList<String> turned = new ArrayList<>();
        for(int i = 0; i<rocks.size(); i++) {
            turned.add("");
        }
        for(String s : rocks) {
            for(int i = 0; i<s.length(); i++) {
                turned.set(i, turned.get(i).concat(Character.toString(s.charAt(i))));
            }
        }
        return turned;
    }
}
