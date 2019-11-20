package CyrilleLingaiJonathanGrant_06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * The Integer Sorting Program.
 * 
 * @author Cyrille Lingai, Grant Jonathan.
 * @version 11/20/19.
 */

public class CyrilleLingaiJonathanGrant_06 {
	
	final static String inputFileName = "2050 Project 06_Input.txt";
									// The file of random integers.
	
	/**
	 * Main execution of program.
	 * 
	 * @param		args.
	 * @throws		FileNotFoundException 	If the file is not found.
	 * @throws		IOException				If there is not to a writable file.		
	 */
	public static void main(String[] args) {
		
		// Declaring local variables.
		
		int lineNumber = 0;			// Track the line an expression is scanned from.
		int fileNumber = 1; 		// The file number to identify and scan files.
		final int sequenceLength = 100;	// The length of the list of integers.
	
		int[] quickSortedArray = new int[sequenceLength];
									// The quick sorted integers to write to file.
		int[] shellSortedArray = new int[sequenceLength];	
									// The shell sorted integers to write to file.
		
		
		String outputFileName = "2050 Project 06_Output0X.txt";
									// The file of corresponding sorted integers.
		
		File inputFile = new File(inputFileName);	// The tool for openning files.
		FileWriter fileWriter = null;				// The tool for writing to files.
		Scanner fileScanner = null;					// The tool to read file.
		
		// Get input from user and find the corresponding file.
		
		try {
			
		    fileScanner = new Scanner(inputFile); // Use the reading tool on the file.
		    
		    // While loop till file has line
		    while (fileScanner.hasNextInt()) {
		    	quickSortedArray[lineNumber] = fileScanner.nextInt(); // Read the line
		    	shellSortedArray[lineNumber] = quickSortedArray[lineNumber];
		    	lineNumber++;
			} // End while.
			 
			fileScanner.close();
			    
			quickSort(quickSortedArray, 0, sequenceLength - 1);
			shellSort(shellSortedArray);
			
		} // End try.

		catch (FileNotFoundException e) {
			System.err.println(e);
		} // End catch.
		
		// Attempt to find pre-existing file or create new one of specified name,
		// and write the postfix expressions to file.
		
		try {
			
			outputFileName = outputFileName.replace("X", Integer.toString(fileNumber));
			fileWriter = new FileWriter(outputFileName);
			fileWriter.write("Quick Sorted Array\n\n");
			
			writeToFile(quickSortedArray, fileWriter);
			
			fileWriter.close();
			
			outputFileName = outputFileName.replace(
					Integer.toString(fileNumber++), Integer.toString(fileNumber));
			fileWriter = new FileWriter(outputFileName);
			
			fileWriter.write("Shell Sorted Array\n");
			
			fileWriter.write("Currently Not Operable\nCase Example of Expected"
					+ " Output Below:\n\n"); // Remove lines 95-96 when finished.
			
			writeToFile(shellSortedArray, fileWriter);
			
			fileWriter.close();
			
		} // End try.
		
		catch (IOException e) {
			System.err.println(e.getMessage());
		}  // End catch.

	} // End main method.

// ****************************************************************************	

	/**
	 * Writes sorted lists of integers to file with 10 integers per line.
	 * 
	 * @param sortedArray	The sorted list of integers.
	 * @param fileWriter	The tool used to write values to file.
	 */
	private static void writeToFile(int[] sortedArray, FileWriter fileWriter) {
		
		try {
					
			for (int i = 0; i < sortedArray.length; ) {
				
				for (int j = 0; j < 10; j++, i++) {
					fileWriter.write(sortedArray[i] + " ");
				} // End for.
				
				fileWriter.write("\n");
				
			} // End for.
			
		} // End try.
		catch (IOException e) {
					e.printStackTrace();
				} // End catch.
		
	} // End writeToFile method.
	
// ****************************************************************************
	
	/**
	 * 
	 * @param 	presortSequence
	 * @return 	String
	 */
	private static void shellSort(int[] presortSequence) {
		
		// Placeholder code; replace with shellSort() algorithm.
		for (int i = 0; i < presortSequence.length; i++) {
			presortSequence[i] = i;
		} // End for.
		
	} // End shellSort method.
	
// ****************************************************************************
	
	
	
// ****************************************************************************
	
	/**
	 * Quicksort implements the textbook case of quicksort to efficient sort the
	 * array by finding a pivot, swapping values to the correct side of the pivot,
	 * splitting each side of the pivot into subarray, and repeating until each
	 * subarray has less than three elements.
	 * 
	 * @param 	anArray
	 * @return	String
	 */
	private static void quickSort(int[] anArray, int firstIndex, int lastIndex) {
		
		// Declaring Local Variables.
		
		int pivotIndex = 0;
		final int MIN_SIZE = 3;
		
		// Sort the subarrays.
		
		if ( firstIndex - lastIndex + 1 < MIN_SIZE) {
			
			insertionSort( anArray, firstIndex, lastIndex );
			
		} // End if.
		
		else {
			
			pivotIndex = divideArray(anArray, firstIndex, lastIndex);
			
			quickSort(anArray, firstIndex, pivotIndex - 1);
			quickSort(anArray, pivotIndex + 1, lastIndex);
			
		} // End else.
		
		
	} // End quickSort method.

// ****************************************************************************
	
	/**
	 * Sort the subarray by insertion and ordering values.
	 * 
	 * @param anArray
	 * @param firstIndex
	 * @param lastIndex
	 */
	private static void insertionSort(int[] anArray, int firstIndex, int lastIndex) {
		
		// Sort the subarray in order.
		
		if ( firstIndex < lastIndex ) {
			
			insertionSort( anArray, firstIndex, lastIndex - 1 );
			
			insertInOrder( anArray[lastIndex], anArray, firstIndex, lastIndex - 1 );
			
		} // End if.
		
	} // End insertionSort method.
	
// ****************************************************************************
	
	/**
	 * 
	 * Insert the entry in the correct position relative to the values of the
	 * first and last indices of the subarray.
	 * 
	 * @param anEntry
	 * @param anArray
	 * @param firstIndex
	 * @param lastIndex
	 */
	private static void insertInOrder( int anEntry, int[] anArray, int firstIndex, int lastIndex ) {
		
		// Order the elements of the subarray.
		
		if (anEntry >= anArray[lastIndex] ) {
			anArray[lastIndex + 1] = anEntry;
		} // End if.
		
		else if (firstIndex < lastIndex) {
			anArray[lastIndex + 1] = anArray[lastIndex];
			insertInOrder(anEntry, anArray, firstIndex, lastIndex - 1);
		} // End else if.
		
		else {
			anArray[lastIndex + 1] = anArray[lastIndex];
			anArray[lastIndex] = anEntry;
		} // End else.
		
	} // End insertInOrder method.
	
// ****************************************************************************
	/**
	 * Returns the pivot index of the newly partitioned array.
	 * 
	 * @param anArray
	 * @param firstIndex
	 * @param lastIndex
	 * @return
	 */
	private static int divideArray(int[] anArray, int firstIndex, int lastIndex) {
		
		// Declaring Local Variables.
		
		int temp = 0;
		int midIndex = (lastIndex - firstIndex) / 2;
		int leftIndex = 0;
		int rightIndex = 0;
		int pivotIndex = 0;
		int pivotValue = 0;
		boolean isDivided = false;
		
		// Exchange values of middle and last indices.
		
		temp = anArray[midIndex];
		anArray[midIndex] = anArray[lastIndex - 1];
		anArray[lastIndex - 1] = temp;
		
		// Find the new pivot.
		
		pivotIndex = lastIndex - 1;
		pivotValue = anArray[pivotIndex];
		
		// Set the range of the sub array.
		
		leftIndex = firstIndex + 1;
		rightIndex = lastIndex - 2;
		
		// Process the array from left to right and exchange left and right indices.
		
		while (!isDivided) {
			
			for (leftIndex++; anArray[leftIndex] < pivotValue;) {} // End for.
			
			for (rightIndex--; anArray[rightIndex] > pivotValue;) {} // End for.
			
			if (leftIndex < rightIndex) {
				
				temp = anArray[leftIndex];
				anArray[leftIndex] = anArray[rightIndex];
				anArray[rightIndex] = temp;
				
			} // End if.
			
			else {
				isDivided = true;
			} // End else.
			
		} // End while.
		
		// Exchange the pivot and the left index.
		
		temp = anArray[pivotIndex];
		anArray[pivotIndex] = anArray[leftIndex];
		anArray[leftIndex] = temp;
		pivotIndex = leftIndex;
		
		return pivotIndex;
		
	} // End divideArray method.
	
} // End class.