import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Part1 {

    public static void main(String[] args){
		try {
		Scanner scan = new Scanner(new File("input1.txt"));
		int sum = 0;
		int firstInt = 0;
		int secondInt = 0;
		while(scan.hasNextLine()) {
			String currLineString = scan.nextLine();
			char[] currLineArray = currLineString.toCharArray();

			for(int i = 0; i<currLineArray.length; i++) {
				if(Character.isDigit(currLineArray[i])) {
					firstInt = currLineArray[i] - '0';
					break;
				}
			}

			for(int i = currLineArray.length-1; i>=0; i--) {
				if(Character.isDigit(currLineArray[i])) {
					secondInt = currLineArray[i] - '0';
					break;
				}
			}

			System.out.println(firstInt * 10 + secondInt);
			sum = sum + firstInt * 10 + secondInt;
		}

		System.out.println(sum);
		
	
	} catch (Exception e) {
			// TODO: handle exception
		}

	}
}