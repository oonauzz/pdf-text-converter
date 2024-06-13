//Question 1
//Import Scanner from java.util
import java.util.Scanner;

//Create a class called Triangle
public class Triangle {
	
	//Declare three private integer variables
	private int side1, side2, side3;
	
	//The constructor for Triangle class takes in three side length variables
	//It throws an InvalidSideLength exception if the side lengths for the triangle are invalid
	public Triangle(int a, int b, int c) throws InvalidSideLength{
		//If any side length variable is negative, or is greater than the sum of the other two side lengths, throw exception and prints error message
		if ((a >= b+c) || (b >= a+c) || (c >= a+b) || (a <= 0) || (b <= 0) || (c <= 0))
			throw new InvalidSideLength("Error - invalid triangle lengths.");
		//If side lengths are valid, assign them to the object attributes
		side1 = a;
		side2 = b;
		side3 = c;
	}
	
	//Getter method for side1 returns the integer value of side1
	public int getSide1() {
		return side1;
	}
	
	//Getter method for side2 returns the integer value of side2
	public int getSide2() {
		return side2;
	}
	
	//Getter method for side3 returns the integer value of side3
	public int getSide3() {
		return side3;
	}
}
