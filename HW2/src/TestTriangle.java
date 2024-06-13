//Question 1
//Import Scanner from java.util
import java.util.Scanner;

//TestTriangle is the class that test if the triangle has valid side lengths
public class TestTriangle {
	
	public static void main(String[] args) {
		//Declares a Scanner object in the main class
		Scanner sc = new Scanner(System.in);
		
		//Use a try-catch to input the lengths of the triangle and test if there are valid
		try{
			System.out.print("Enter the length of three sides of the triangle, separate by space: ");
			//The input variable takes the input of the three lengths and String[] s splits the input by white spaces
			String input = sc.nextLine();
			String[] s = input.split(" ");
			
			//Convert the three String variables in s into integer variables
			int l1 = Integer.parseInt(s[0]);
			int l2 = Integer.parseInt(s[1]);
			int l3 = Integer.parseInt(s[2]);
			
			//Create an instance t of the Triangle class and initialize with three integer parameters. Print message for user.
			Triangle t = new Triangle(l1,l2,l3);
			System.out.println("You entered a valid triangle!");
		}//If the above message ran into error, try to catch and handle an InvalidSideLength exception e and print out the stack trace.
		catch(InvalidSideLength e) {
			e.printStackTrace();
		}
		//Close the scanner
		sc.close();
		
	}
}
