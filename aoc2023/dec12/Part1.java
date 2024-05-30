import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    static HashMap<Combination, Long> cache = new HashMap<>();


    public static long permutations(String state, List<Integer> groupings) {
        Combination in = new Combination(state, groupings);
        if(cache.containsKey(in)) {
            return cache.get(in);
        }


        if(state.isBlank()) {
            if(groupings.size() == 0) {
                return 1;
            }
            return 0;
        }


        long count = 0;
        if(state.charAt(0) == '.') { 
            count = permutations(state.substring(1), groupings);
        } else if(state.charAt(0) == '?') { //räkna permutationer på att byta ut med # respektive .
            count += permutations("#" + state.substring(1), groupings) + permutations("." + state.substring(1), groupings);
        } else {
            if(groupings.size() == 0) {
                count = 0;
            } else {
                int damaged = groupings.get(0);
                
            
            if(state.length() >= damaged) {
                for(int i = 0; i<damaged; i++) {
                    if(state.charAt(i) == '.') {
                        return 0;
                    }
                }
                List<Integer> newGroupings = groupings.subList(1, groupings.size());

                if(damaged == state.length()) {
                    if(newGroupings.isEmpty()) {
                        count = 1;
                    } else {
                        count = 0;
                    }
                } else if(state.charAt(damaged) == '.') {
                    count = permutations(state.substring(damaged+1), newGroupings);
                } else if(state.charAt(damaged) == '?') {
                    count = permutations("." + state.substring(damaged+1), newGroupings);
                } else {
                    count = 0;
                }
            } else {
                count = 0;
            }
            }
        }
        cache.put(in, count);
        return count;
    }



    public static void main(String[] args) throws FileNotFoundException {
        Part1 p1 = new Part1();
        long t0 = System.currentTimeMillis(); 
        Scanner scan = new Scanner(new File("input12.txt"));
        long part1 = 0;
        long part2 = 0;
        int count = 0;
        while(scan.hasNextLine()) {
            String[] line = scan.nextLine().split(" ");
            String left = line[0];
            int[] right = Arrays.stream(line[1].split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
            String unfoldedLeft = line[0] + "?" + line[0] + "?" + line[0] + "?" + line[0] + "?" + line[0];
            ArrayList<Integer> groupings = new ArrayList<>();
            ArrayList<Integer> unfoldedGroupings = new ArrayList<>();
            for(int i = 0; i<5; i++) {
                for(int j = 0; j<right.length; j++) {
                    unfoldedGroupings.add(right[j]);
                }
            }
            for(int i = 0; i<right.length; i++) {
                groupings.add(right[i]);
            }
            //System.out.println(++count);
            part1 += permutations(left, groupings);
            part2 += permutations(unfoldedLeft, unfoldedGroupings);
            cache.clear();
        }
        System.out.println("part 1 = " + part1);
        System.out.println("part 2 = " + part2);
        System.out.println("total time = " + (System.currentTimeMillis()-t0) + "ms");
    }
}