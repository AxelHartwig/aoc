import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {


    public static int findNumber(ArrayList<ArrayList<Character>> charMatrix, int r, int c) {
        if(r >= charMatrix.size() || r < 0 || c >= charMatrix.get(0).size() || c < 0 || !Character.isDigit(charMatrix.get(r).get(c))) {
            return 0;
        }
        

        //Gå längst till vänster i siffran
        while(c > 0 && Character.isDigit(charMatrix.get(r).get(c-1))) {
            System.out.println(c);
            c--;
        }
       

        int number = Character.getNumericValue(charMatrix.get(r).get(c));
        while(c+1 < charMatrix.size() && Character.isDigit(charMatrix.get(r).get(c+1))) {
            number = number*10 + Character.getNumericValue(charMatrix.get(r).get(c+1));
            c++;
        }

        return number;
    }

    public static void main(String[] args) {
        try {
            int sum = 0;
            Scanner scan = new Scanner(new File("input3.txt"));
            ArrayList<ArrayList<Character>> charMatrix = new ArrayList<>();
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                ArrayList<Character> temp = new ArrayList<>();
                for(int i = 0; i<line.length(); i++) {
                    temp.add(line.charAt(i));
                }
                charMatrix.add(temp);
            }


            for(int i = 0; i<charMatrix.size(); i++) {
                for(int j = 0; j<charMatrix.get(i).size(); j++) {
                    if(charMatrix.get(i).get(j) == '*') {
                        ArrayList<Integer> adjNumberList = new ArrayList<>();

                        //SOUTH
                        int sw = findNumber(charMatrix,i+1,j-1);
                        int s = findNumber(charMatrix, i+1, j);
                        int se = findNumber(charMatrix, i+1, j+1);

                        if(s != 0) {
                            adjNumberList.add(s);
                        } else if(sw != 0 && se != 0) {
                            adjNumberList.add(sw);
                            adjNumberList.add(se);
                        } else if(sw != 0) {
                            adjNumberList.add(sw);
                        } else if(se != 0) {
                            adjNumberList.add(se);
                        }


                        
                        
                        
                        //NORTH
                        int nw = findNumber(charMatrix, i-1, j-1);
                        int n = findNumber(charMatrix, i-1, j);
                        int ne = findNumber(charMatrix, i-1, j+1);


                        if(n != 0) {
                            adjNumberList.add(n);
                        } else if(nw != 0 && ne != 0) {
                            adjNumberList.add(nw);
                            adjNumberList.add(ne);
                        } else if(nw != 0) {
                            adjNumberList.add(nw);
                        } else if(ne != 0) {
                            adjNumberList.add(ne);
                        }

                
                        //WEST AND EAST
                        

                        int w = findNumber(charMatrix, i, j-1);
                        int e = findNumber(charMatrix, i, j+1);

                        if(w != 0) {
                            adjNumberList.add(w);
                        }

                        if(e != 0) {
                            adjNumberList.add(e);
                        }

                        if(adjNumberList.size() == 2) {
                            sum = sum + adjNumberList.get(0)*adjNumberList.get(1);
                        }
                    }  
                }
            }
            System.out.println(sum);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
