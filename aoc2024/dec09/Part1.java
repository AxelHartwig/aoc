import java.util.*;

class Part1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] l = s.nextLine().toCharArray();
        int curPos = 0;
        ArrayList<Block> blocks = new ArrayList<>();

        ArrayList<Block> files = new ArrayList<>();
        Block[] empty = new Block[1000000];

        long part1 = 0;
        long part2 = 0;

        for (int i = 0; i < l.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < l[i] - '0'; j++) {
                    blocks.add(new Block(i / 2, curPos, 0));

                }
                files.add(new Block(i / 2, curPos, 0, l[i] - '0'));
                curPos += l[i] - '0';
            } else {
                blocks.getLast().space = l[i] - '0';
                empty[curPos] = new Block(-1, curPos, 0, l[i] - '0');
                curPos += l[i] - '0';
            }
        }

        Block last = new Block(0, 0, 0);
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).space == 0) {
                continue;
            } else {
                try {
                    Block b = blocks.get(i);
                    last = blocks.removeLast();
                    last.space = b.space - 1;
                    blocks.add(i + 1, last);
                } catch (Exception e) {
                    blocks.addLast(last);
                }
            }
        }

        for (int i = 0; i < blocks.size(); i++) {
            part1 += blocks.get(i).id * i;
        }

        System.out.println("part1: " + part1);

        for (Block f : files.reversed()) {
            for (int i = 0; i < empty.length; i++) {
                Block e = empty[i];
                if (e == null) {
                    continue;
                }
                if (f.size > e.size) {
                    continue;
                } else if (f.size == e.size) {
                    f.startPos = e.startPos;
                    empty[i] = null;
                    empty[f.startPos] = new Block(-1, f.startPos, 0, f.size);
                } else if (f.size < e.size) {
                    f.startPos = e.startPos;
                    e.startPos += f.size;
                    e.size -= f.size;
                    empty[f.startPos] = new Block(-1, f.startPos, 0, f.size);
                }
                break;
            }
        }
        for (Block b : files) {
            for (int i = 0; i < b.size; i++) {
                part2 += (b.startPos + i) * b.id;
            }
            // System.out.println(b.space);
            // System.out.println(b.id);
            // for (int i = 0; i < b.space; i++) {
            // System.out.print(".");
            // }
        }
        System.out.println("part2: " + part2);

    }
}

class Block {

    int id;
    int startPos;
    int space;
    int size;

    public Block(int id, int pos, int space) {
        this.id = id;
        this.startPos = pos;
        this.space = space;
        size = 1;
    }

    public Block(int id, int pos, int space, int size) {
        this.id = id;
        this.startPos = pos;
        this.space = space;
        this.size = size;
    }
}
