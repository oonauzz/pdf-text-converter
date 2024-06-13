//Question 2
//Import HashMap and Scanner from Java - to ensure the running time is O(n)
import java.util.HashMap;
import java.util.Scanner;

//Write a TestFindTarget class to find the solution to the target from a list of numbers
public class TestFindTarget {
	
	//The main method
	public static void main(String[] args) {
		
		//Declare a scanner to take input of array of String
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a list of integers, separate them by space: ");
		String input = sc.nextLine();
		//Split the String 'input' variable by whitespace, and store them into an array of Strings called 'enter'
		String[] enter = input.split(" ");
		//Declare an new array of integers 'arr1' that has the same size as the array of 'enter'
		int[] arr1 = new int[enter.length];
		
		//Use a for loop to iterate through the indices of 'enter'
		for (int i = 0; i<enter.length; i++) {
			//Change the String variable of 'enter' to an integer variable and add to 'arr1' at the index of 'i'
			arr1[i] = Integer.parseInt(enter[i]);
		}
		
		//Ask for input of an integer and store it in 'target' variable
		System.out.print("Enter an integer target (make sure there is only one solution from the list): ");
		int target = sc.nextInt();
		
        //The int[] pair stores the returned solution of calling the findIndices method on arr1 and target
        int[] pair = findIndices(arr1, target);
        
        //If the int[] pair is not null, print out the variables of pair as the solutions. Pint a statement for explanation.
        if (pair != null) {
            System.out.println("\nSolution: [" + pair[0] + ", " + pair[1] + "]");
            System.out.println("Explanation: List " + pair[0] + " = " + arr1[pair[0]] + ", List " + pair[1] + " = " + arr1[pair[1]] + ". Their sum is "+ target + ".");
        }
	}
	
	//the findIndices method takes two parameters and returns an array of integers
	public static int[] findIndices(int[] arr, int target) {
		//Declare a new HashMap object indices to store the element and its index, both are integers
		HashMap<Integer, Integer> indices = new HashMap<>();
		
		//Declare an integer variable i and a for loop that iterates the index of the 'arr'
		for(int i=0; i<arr.length;i++) {
			//The other variable equals to target minus the current element in the 'arr' 
			int other = target - arr[i];
			//Check if the 'indices' HashMap contains the key of 'other'. 
			if (indices.containsKey(other)){
				//If true, return a new array of integers of the current index, and the index of the 'other' variable from the 'indices' HashMap
				return new int[] {indices.get(other),i};
			}
			//If indices HashMap doesn't contain other, add the current element and its index to 'indices' HashMap
			indices.put(arr[i], i);
		}
		
		//If no pair that satisfy the condition is found after the for loop, return null
		return null;
	}
}
