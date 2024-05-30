import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {

  
    

    static class Hand implements Comparable {
        int[] cards = new int[5];
        int bet;
        char[] ranks1 = new char[]{'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'}; //for part1
        char[] ranks2 = new char[]{'A', 'K', 'Q','T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'}; //for part2
        int[] freq = new int[13];   
        int score;

        public Hand(String in, int bet) {
            this.bet = bet;

            int jokers = 0;
            char[] temp = in.toCharArray();
            for(int i = 0; i<temp.length; i++) {
                for(int j = 0; j<ranks2.length; j++) {
                    if(temp[i] == ranks2[j]) {
                        if(temp[i] != 'J') {
                            freq[j]++;
                        }

                        if(temp[i] == 'J') {
                            jokers++;
                        }

                        cards[i] = j;
                        
                    }
                    
                }
              
            }
            
            Arrays.sort(freq);
            freq[freq.length-1] += jokers;

            //x2 för plats
            score = 2*freq[freq.length-1];

            //2par eller kåk
            if(freq[freq.length-2] == 2) {
                score++;
            }
               
        }


        @Override
        public int compareTo(Object o) {
            Hand oHand = (Hand) o;
            if(score != oHand.score) {
                return score-oHand.score;
            }

            for(int i = 0; i<cards.length; i++) {
                if(cards[i] != oHand.cards[i]) {
                    return oHand.cards[i]-cards[i];
                }
            }

            return 0;
        }
        
    }
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input7.txt"));
            ArrayList<Hand> hands = new ArrayList<>();
            

            while(scan.hasNextLine()) {
                String[] in = scan.nextLine().split(" ");
                hands.add(new Hand(in[0], Integer.parseInt(in[1])));
            }

            hands.sort(null);
            int sum = 0;
            for(int i = 1; i<hands.size()+1; i++) {
                System.out.println(hands.get(i-1).bet);
                sum += i*hands.get(i-1).bet;
            }

            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}