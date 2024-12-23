import java.io.SequenceInputStream;
import java.lang.reflect.Array;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

class Part1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long part1 = 0;
        long part2 = 0;
        HashMap<List<Long>, Long> res = new HashMap<>();
        ArrayList<Integer> targetseq = new ArrayList<>();
        targetseq.add(-2);
        targetseq.add(1);
        targetseq.add(-1);
        targetseq.add(3);

        while (scan.hasNextLine()) {
            ArrayList<Long> prices = new ArrayList<>();
            HashSet<List<Long>> seen = new HashSet<>();
            Long start = Long.parseLong(scan.nextLine());
            long price = start % 10;
            for (int i = 0; i < 2000; i++) {
                prices.add(price);
                start = (start ^ (start * 64)) % 16777216;
                start = (start ^ (start / 32)) % 16777216;
                start = (start ^ (start * 2048)) % 16777216;
                price = start % 10;
            }

            prices.add(price);
            part1 += start;
            ArrayList<Long> diff = new ArrayList<>();

            for (int i = 0; i < 2000; i++) {
                diff.add(i, prices.get(i + 1) - prices.get(i));
            }
            for (int i = 3; i < diff.size(); i++) {
                ArrayList<Long> sequence = new ArrayList<>();
                sequence.add(diff.get(i - 3));
                sequence.add(diff.get(i - 2));
                sequence.add(diff.get(i - 1));
                sequence.add(diff.get(i));
                if (!seen.contains(sequence)) {
                    seen.add(sequence);
                    if (res.containsKey(sequence)) {
                        res.put(sequence, res.get(sequence) + prices.get(i + 1));

                    } else {
                        res.put(sequence, prices.get(i + 1));
                    }
                }
            }
        }
        System.out.println(part1);
        long max = 0;
        for (Long l : res.values()) {
            if (l > max) {
                max = l;
            }
        }

        System.out.println(max);

    }
}

class Sequence {
    LinkedList<Long> last4;
    long price;

    Sequence(LinkedList<Long> last4, long price) {
        this.last4 = last4;
        this.price = price;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (Long l : last4) {
            sum += 31 * l;
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sequence other)) {
            return false;
        }
        return this.last4.equals(other.last4);
    }
}