//Question 3
//The libraries of pdfbox, fontbox and commons-logging have been downloaded and added to the Referenced Libraries folder
//Import needed classes from Java and Apache PDFBox
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

//The PDFReader class takes a pdf file as input and write to a txt file as output, also includes methods to analyze the pdf text
public class PDFReader {
	//The Main Method
	public static void main(String[] args) throws Exception {
		
		//Names of the 40 pdfs are store in 'names' - an array of Strings
		String[] names = {"291", "306", "309", "319", "322", "602", "666", "668", "953",
		                  "961", "990", "1095", "1136", "1206", "1942", "2091", "2714",
		                  "2966", "3329", "3441", "3453", "3476", "3477", "3480", "3536",
		                  "3546", "3578", "3853", "4694", "4727", "64", "621", "656",
		                  "3321", "3339", "3458", "3474", "3478", "3531", "3575"};
		
		//Titles of the 40 pdfs are store in 'titles' - an array of Strings
		String[] titles = {"Investigation of the Nickel-Hydrogen Anomalous Heat Effect", 
				"A SEARCH FOR THE EMISSION OF X-RAYS FROM ELECTROLYTICALLY CHARGED P ALLADIUM-DEUTERIUM",
				"Calorimetry of Energy-Efficient Glow Discharge – Apparatus Design and Calibration",
				"ANALYSIS OF TRITIUM AND HEAT EXCESS IN ELECTROCHEMICAL CELLS WITH Pd CATHODES",
				"Study of Deuterium Charging in Palladium by the Electrolysis of Heavy Water: Heat Excess Production",
				"LENR and NASA", "MEASUREMENT OF D-D AND D-6LI NUCLEAR REACTIONS AT VERY LOW ENERGIES",
				"ENERGETIC CHARGED PARTICLES FROM DEUTERIUM METAL SYSTEMS", 
				"Variation of Resistance with Composition in the beta-Phase of the H-Pd System at 298 K",
				"Enhancement of the electron screening effect for d + d fusion reactions in metallic environments",
				"Intensification Of Low Energy Nuclear Reactions Using Superwave Excitation",
				"Notes from the 12th International Conference on Condensed Matter Nuclear Sciences",
				"Hydrogen triggered exothermal reaction in uranium metal",
				"Abnormal excess heat observed during Mizuno-type experiments",
				"SPORADIC OBSERVATION OF THE FLEISCHMANNPONS HEAT EFFECT",
				"Theory of Low-Energy Deuterium Fusion in Micro/Nano-Scale Metal Grains and Particles",
				"ISOTHERMAL FLOW CALORIMETRIC INVESTIGATIONS OF THE D/Pd SYSTEM",
				"Generation of Heat and Products During Plasma Electrolysis",
				"“THEYWILL ONLY LAUGH AT YOU”: Cold Fusion",
				"TRITIUM GENERATION DURING ELECTROLYSIS EXPERIMENT",
				"SEARCH FOR NUCLEAR FUSION IN GAS PHASE DEUTERIDING OF TITANIUM METAL",
				"Reduced radioactivity of tritium in small titanium particles",
				"Some Experiments on the Decrease of the Radioactivity of Tritium Sorbed by Titanium",
				"Cold Fusion and Decrease of Tritium Radioactivity",
				"METHOD AND APPARATUS FOR CARRYING OUT NICKEL AND HYDROGEN EXOTHERMAL REACTIONS",
				"Introduction to the Cold Fusion Experiments of Melvin Miles",
				"More about why cold fusion will lower the cost of energy",
				"NEW ELECTROLYTIC PROCEDURE FOR THE OBTAINMENT OF VERY HIGH HlPd LOADING RATIOS. PRELIMINARY ATTEMPTS FOR ITS APPLICATION TO THE D-Pd SYSTEM",
				"ELECTROCHEMICAL EFFECTS ON THE RESISTANCE MEASUREMENTS OF PD|H ELECTRODE",
				"BETHE'S CALCULATION FOR SOLAR ENERGY AND SELECTIVE RESONANT TUNNELING",
				"ELECTROLYSIS OF D2O WITH A PALLADIUM CATHODE COMPARED WITH ELECTROLYSIS OF H20O WITH A PLATINUM ELECTRODE: PROCEDURE AND EXPERIMENTAL DETAILS.",
				"OVERVIEW OF H-NI SYSTEMS: OLD EXPERIMENTS AND NEW SETUP",
				"CALORIMETRIC MEASUREMENTS DURING Pd-Ni THIN FILM–CATHODES ELECTROLYSIS IN Li2SO4/H2O SOLUTION",
				"Agreements and Disagreements with Storms",
				"METHOD FOR PRODUCING ENERGY AND APPARATUS THEREFOR",
				"Generation of Excess Energy with (Ni+Pd) +H2/D2 system",
				"Cold Fusion: What Do We Know? What Do We Think?",
				"SOME EXPERIMENTS ON THE DECREASE OF TRITIUM RADIOACTIVITY",
				"TRITIUM GENERATION FROM THE INTERACTION OF A GLOW DISCHARGE PLASMA WITH METALS AND WITH A MAGNETIC FIELD",
				"フライシュマンとポンスの熱量測定"};
		
		//Declare a scanner object and ask for the name of pdf file to be processed
		Scanner search = new Scanner(System.in);
		System.out.print("Type the name of the pdf that you want to open and covert"
				+ "\nSelect from the PDFFiles folder please. Enter only the numerical part: ");
		int num = search.nextInt();
		
		//Declare a new File object 'file' as the pdf file
		File file = new File("PDFFiles/" + num + ".pdf");
		//String fileName = file.getName();
        //int index = fileName.lastIndexOf(".pdf");
		//fileName = fileName.substring(0,index);
		
		//Declare a FileInputStream object 'fis' and open 'file'
		FileInputStream fis = new FileInputStream(file);
		
		//String 'text' stores the returned String output after applying the ReadPDFFile method on 'file' and 'fis'
		String text = ReadPDFFile(file,fis);
		//Split String 'text' by empty lines and store them into an array of Strings 'lines'
		String[] lines = text.split("\n");
		
		//Apply the writeLinesToFile method to the 'lines' arrays and assign its name as 'FileName'. Print a successful message
		String FileName = num + ".txt";
        writeLinesToFile(lines, FileName);
        System.out.println("Pdf has successfully been converted to a txt file!");
        
        //Start the analysis of the text.
		System.out.print("\nApplication: Analyze the Text.\n1- Type the word you want to look up in the pdf: ");
		//Ask for the input of a String 'w' to search in the pdf text
		Scanner input = new Scanner(System.in);
		String w = input.nextLine();
		
		//Apply the searchWord method to 'text' and 'w'. If true, prints that the word exists; if false, prints it doesn't
		if (searchWord(text,w)) {
			System.out.println("Yes, the word exists.");
		} else{
			System.out.println("Sorry, the word doesn't exist.");
		}
		
		//Ask for the input of a section String 'section' to search in the pdf text
		System.out.print("\n2- Type the section you want to look up in the pdf (E.g. Introduction, Summary, Abstract, Conclusion...): ");
		Scanner in = new Scanner(System.in);
		String section = input.nextLine();
		
		//Apply the containsSection method to 'text' and 'w'. If true, prints that the section exists; if false, prints it doesn't
		if (containsSection(text, section)) {
			System.out.println("Yes, this section exists.");
		} else{
			System.out.println("Sorry, this section doesn't exist.");
		}
		
		System.out.println("\n3- Funfact: the top ten most common words in this pdf are these: ");
		//Apply the topTenWords method to 'text', and print out the ten most common words in the pdf, separate by "|"
		for (String s:topTenWords(text)) {
			System.out.print(s + " | ");
		}
		
		//Ask for the input of a  String 'keyword' to search the 'titles' array
		Scanner sc = new Scanner(System.in);
		System.out.println("\n");
		System.out.println("4- You can also search for the articles with keywords in titles. \nThe more meaningful the word the better (e.g. Energy, Efficient...) \nEnter the keyword you would like to search in titles: ");
		String keyword = sc.nextLine();
		//Decalre an integer variable 'count' for the printing output
		int count = 1;
		System.out.println("Here are the list of titles (and their names) that contain the keyword (case insensitive).");
		//Use a for loop to iterate through the index of 'titles'
		for (int i = 0; i<titles.length; i++) {
			//String 'lower' and 'keywordLower' are: the string in titles[] and the inputed keyword converted to lower case
			String lower = titles[i].toLowerCase();
			String keywordLower = keyword.toLowerCase();
			//If the current string in 'titles' contains keywordLower, print out the title and its name. Increment the count variable
			if(lower.contains(keywordLower)){
				System.out.println(count + ") " + titles[i] + " - " + names[i] + ".pdf");
				count++;
			}
		}
		sc.close();
		in.close();
		input.close();
		search.close();
	}	
	
	//The writeToFile method takes paraments of lines and fileName and write content line by line to the txt file
	public static void writeLinesToFile(String[] lines, String fileName) {
        //Declare a boolean variable to find the Introduction in the pdf, and a lastLine String variable
		boolean isIntroduction = false;
        String lastLine = null;
        
        //Use a try-catch to declare a new BufferedWriter object that takes the parameter of a new FileWriter object of 'fileName'
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        	//Use a for loop to process every String element in 'lines'
        	for (String line : lines) {
        		//If the current line contains 'Introduction' with any of the following format, start writing to the file, and change the 'isIntroduction' variable to true
                if ((line.startsWith("Introduction"))||(line.startsWith("INTRODUCTION"))||(line.startsWith("Introduction."))||(line.startsWith("1. INTRODUCTION"))||(line.startsWith("1.INTRODUCTION"))||(line.startsWith("1- INTRODUCTION"))||(line.startsWith("1. Introduction")||(line.startsWith("1.  INTRODUCTION")))) {
                	writer.write(line);
                    isIntroduction = true;
                } 
                //If 'isIntroduction' is true, keep processing and writing the following Strings
                else if (isIntroduction) {
                	//Apply the allUpperCase and startsWithNotation method to the current line.
                	//If any of the two is true, it means that this line is usually a Section title. 
                	//Add 2 empty lines before, and then write the current line.
                	if ((allUpperCase(line)==true)||(startsWithNotaion(line)==true)){
                        writer.newLine(); 
                        writer.newLine(); 
                        writer.newLine();  
                        writer.write(line);
                	}
                	//Apply the newParagraph method to the current line.
                	//If the 'lastLine' variable is not null, and that the newParagraph method return is true. It means that this line is usually a new paragraph . 
                	//Add 1 empty lines before, and then write the current line.
                	else if (lastLine != null && newParagraph(line, lastLine)) {
                		writer.newLine(); 
                		writer.newLine(); 
                        writer.write(line);
                	}
                	//If everything is normal, the line is not a new Section or Paragraph. Write the line as it is to the file
                	else {
                        writer.write(line);
                	}
                }
                //Before the new iteration, store the current line to the 'lastLine' variable, for t=helping the next iteration.
                lastLine = line;
            }
            //Sometimes, even if 'isIntroduction' is false, the pdf file is till normal and needs to be converted to a txt file
        	 if (isIntroduction==false) {
        		 //Repeat the for loop as above without searching for the 'Introduction' section
        		 //Start processing and writing as it is.
        		 for (String l : lines) {
                    if ((allUpperCase(l)==true)||(startsWithNotaion(l)==true)){
                    	writer.newLine(); 
                        writer.newLine(); 
                        writer.newLine();  
                        writer.write(l);
                    }
                    else if (lastLine != null && newParagraph(l, lastLine)) {
                     	writer.newLine(); 
                     	writer.newLine(); 
                        writer.write(l);
                    }
                    else {
                        writer.write(l);
                    }
                    lastLine = l;
                 }
             }
        	 
        }//Catch for an IOException when writing to the txt file 
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//The startsWithNotation method checks if the line matches a specific pattern
	private static boolean startsWithNotaion(String line) {
		//The pattern is: a single digit + hyphen/space/period + space + Uppercase Letter + any other letter
		//This is normally one format of a Section title.
		//Return true if the line matches; false otherwise.
		return line.matches("^\\d[-.] [A-Z].*");
	}
	
	//The allUpperCase method checks if the line is allUpperCase. This is normally another format of Section title
	private static boolean allUpperCase(String str) {
		//Loop through the character after converting the string to an array of characters
		for (char c : str.toCharArray()) {
			//Check if the character is a character and not Uppercase - return false if so
	        if (Character.isLetter(c) && !Character.isUpperCase(c)) {
	            return false; 
	        }
	    }
	    return true; 
	}
	
	//The newParagraph method checks if the line is a new paragraph. Pass into two parameters to check the current and last lines
	public static boolean newParagraph(String Line, String lastLine) {
		//If 'Line' is null or empty, return false
	    if (Line == null || Line.isEmpty()) {
	        return false;
	    }
	    //Declare a 'firstChar' variable for the first character in 'Line'
	    char firstChar = Line.charAt(0);
	    //Declare two boolean variables and check if first character is Uppercase, and the 'lastLine' character ended with a period
	    boolean startsWithUpper = Character.isUpperCase(firstChar);
	    boolean endedWithPeriod = lastLine.trim().endsWith(".");
	    //Return true if one of the following two statements is true
		return ((startsWithUpper && endedWithPeriod) || Character.isUpperCase(firstChar));
	}
	
	//The searchWord method checks if the word exists in the text
	public static boolean searchWord(String text, String word) {
		if (text == null || word == null) {
	        return false; // Handle null input
	    }
	    Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b");
	    Matcher matcher = pattern.matcher(text);
	    return matcher.find();
	}
	
	//The searchSection method checks if the Section exists in the text
	public static boolean containsSection(String text, String section) {
		//Return false with null inputs
		if (text == null || section == null) {
	        return false;
	    }
		//Convert both 'text' and 'section' String to lower case and return if 'text' contains 'section'
		text = text.toLowerCase();
	    section = section.toLowerCase();
		return text.contains(section);
	}
	
	//The topTenWords method returns a list of ten Strings that are most common in the pdf
	public static List<String> topTenWords(String text) {
		// Handle null or empty input
		if (text == null || text.isEmpty()) {
	        return Collections.emptyList(); 
	    }
	   
	    // Convert the text to lowercase, and split by one or more whitespaces
	    text = text.toLowerCase();
	    String[] words = text.split("\\s+"); 

	    // Create HashMap object wordFrequency that keeps track of the word and times it exists in the text
	    HashMap<String, Integer> wordFrequency = new HashMap<>();
	    for (String word : words) {
	    	//If the word exists in map, increment its frequency by 1; if not, add the word and initialize the frequency to 1
	        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
	    }
	    
	    //Initialize a new list of String 'topWords' with the information from the HashMap
	    List<String> topWords = new ArrayList<>(wordFrequency.keySet());
	    //The lambda expression compares two words and sorts them in descending order of frequency. 
	    //It subtracts the frequency of word1 from the frequency of word2, which orders the words by their frequencies in descending order.
	    topWords.sort((word1, word2) -> wordFrequency.get(word2) - wordFrequency.get(word1));

	    //Get the top 10 most common words
	    int topCount = Math.min(10, topWords.size());
	    //Returns a sublist of the topWords list in descending order, index from 0 to topCount-1
	    return topWords.subList(0, topCount);
	}
	
	//The ReadPDFFile takes in File and FileInputStream parameters and throws an Exception if error exists
	public static String ReadPDFFile(File file, FileInputStream fis) throws Exception {
		//Declare a new PDDocument variable and takes the FileInputStream as parameter
		PDDocument pdfDoc = PDDocument.load(fis);
		//Declare a new PDFTextStripper variable, use the getText method on the PDDocument and store the String in 'docText'
		PDFTextStripper pdfTestStripper = new PDFTextStripper();
		String docText = pdfTestStripper.getText(pdfDoc);
		//Return the stripped text from pdf
		return docText;
	} 
}
