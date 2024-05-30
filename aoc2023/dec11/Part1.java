import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
    


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input11.txt"));
        ArrayList<ArrayList<Character>> listMatrix = new ArrayList<>();
        ArrayList<ArrayList<Character>> unexpandedListMatrix = new ArrayList<>();
        ArrayList<int[]> galaxyCoords = new ArrayList<>();
        ArrayList<int[]> unexpandedGalaxyCoords = new ArrayList<>();
        
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            ArrayList<Character> l = new ArrayList<>();
            ArrayList<Character> l2 = new ArrayList<>();
            for(int i = 0; i<line.length(); i++) {
                l.add(line.charAt(i));
                l2.add(line.charAt(i));
            }
            listMatrix.add(l);
            unexpandedListMatrix.add(l2);
            if(!line.contains("#")) {
                listMatrix.add(l);
            }
        }

        for(int i = 0; i<listMatrix.get(0).size(); i++) {
            for(int j = 0; j<listMatrix.size(); j++) {
                if(listMatrix.get(j).get(i) == '#') {
                    break;
                } else if(j == listMatrix.size()-1) {
                    for(int k = 0; k<listMatrix.size(); k++) {
                        listMatrix.get(k).add(i,'.');
                    }
                    i++;
                    break;
                }
            }
        }

        for(int i = 0; i<listMatrix.size(); i++) {
            for(int j = 0; j<listMatrix.get(0).size(); j++) {
                if(listMatrix.get(i).get(j) == '#') {
                    galaxyCoords.add(new int[]{i,j});
                }
            }
        }

        for(int i = 0; i<unexpandedListMatrix.size(); i++) {
            for(int j = 0; j<unexpandedListMatrix.get(0).size(); j++) {
                if(unexpandedListMatrix.get(i).get(j) == '#') {
                    unexpandedGalaxyCoords.add(new int[]{i,j});
                }
            }
        }

        

        int part1 = 0;
        long part2 = 0;
        for(int i = 0; i<galaxyCoords.size(); i++) {
            System.out.println(galaxyCoords.get(i)[0] + " " + galaxyCoords.get(i)[1]);
            System.out.println(unexpandedGalaxyCoords.get(i)[0] + " " + unexpandedGalaxyCoords.get(i)[1]);
            System.out.println("");
            for(int j = i+1; j<galaxyCoords.size(); j++) {
                int x1 = galaxyCoords.get(i)[0];
                int y1 = galaxyCoords.get(i)[1];
                int x2 = galaxyCoords.get(j)[0];
                int y2 = galaxyCoords.get(j)[1];
                int d = Math.abs(x1-x2) + Math.abs(y1-y2);
                part1 += d;

                int ux1 = unexpandedGalaxyCoords.get(i)[0];
                int uy1 = unexpandedGalaxyCoords.get(i)[1];
                int ux2 = unexpandedGalaxyCoords.get(j)[0];
                int uy2 = unexpandedGalaxyCoords.get(j)[1];
                int ud = Math.abs(ux1-ux2) + Math.abs(uy1-uy2);

                part2 += (d-ud)*999999+ud;

            

            }
        }
        System.out.println("part1 = " + part1);
        System.out.println("part2 = " + part2);





    }
}