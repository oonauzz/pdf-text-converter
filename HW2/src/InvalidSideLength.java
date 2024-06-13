//Question 1
//Write an InvalidSideLength class that extends the Exception class in Java;

public class InvalidSideLength extends Exception{
	
	//Class constructor is calling the parent class constructor with super();
	public InvalidSideLength(String message) {
		//It takes in a message parameter to provide additional information about the Exception;
		super(message);
	}

}
