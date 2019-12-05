package CyrilleLingaiJonathanGrant_06;

/*
 * ComputerScience_02_01
 * Sort lists of integers by shell sorting and quick sorting algorithms.
 * JonathanGrant & CyrilleLingai
 * Integer Lists Sorting Algorithms Program_06
 * Windows 10 Eclipse IDE JRE 1.8
 * 
 * Interlude: a temporary amusement or source of entertainment that contrasts with 
 * 				what goes before or after.
 * 
 * "Computer Science is no more about computers than astronomy is about
 *  telescopes."
 * Edsger W. Dijkstra, born March 11, 1930. died August 6, 2002.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Read lists of integers for sorting and write the sorted lists to files.
 * 
 * @author Cyrille Lingai, Grant Jonathan.
 * @version 12/05/19.
 */

public class CyrilleLingaiJonathanGrant_06 {
	
	// Declaring class constants.
	
	private final static String INPUT_FILENAME = "2050 Project 06_Input.txt";
									// The file of random integers.
	private final static int LIST_LENGTH = 100;
									// The length of the list of integers.
	
	/**
	 * Main execution of program to scan input files, sort lists of integers, 
	 * and write those lists to independent files.
	 * 
	 * @param		args					The IO streams for reading and writing files.
	 * @throws		FileNotFoundException 	If the file is not found.
	 * @throws		IOException				If there is not to a writable file.		
	 */
	
	public static void main(String[] args) {
		
		// Declaring local variables.
		
		int lineNumber = 0;			// Track the line an expression is scanned from.
		int fileNumber = 1; 		// The file number to identify and scan files.
		
		int[] quickSortedArray = new int[LIST_LENGTH];
									// The quick sorted integers to write to file.
		int[] shellSortedArray = new int[LIST_LENGTH];	
									// The shell sorted integers to write to file.
		
		String outputFileName = "2050 Project 06_OutputX.txt";
									// The file of corresponding sorted integers.
		
		File inputFile = new File(INPUT_FILENAME);	// The tool for openning files.
		FileWriter fileWriter = null;				// The tool for writing to files.
		Scanner fileScanner = null;					// The tool to read file.
		
		// Get input from user and find the corresponding file.
		
		try {
			
		    fileScanner = new Scanner(inputFile); // Use the reading tool on the file.
		    
		    // Scan the input file for all integers and push eac integer to two lists.
		    
		    while (fileScanner.hasNextInt()) {
		    	quickSortedArray[lineNumber] = fileScanner.nextInt(); // Read the line
		    	shellSortedArray[lineNumber] = quickSortedArray[lineNumber++];
			} // End while.
			 
			fileScanner.close();
			
		} // End try.
		catch (FileNotFoundException e) {
			System.err.println(e);
		} // End catch.
		
		// Sort both lists of integers.
		
		shellSort(shellSortedArray);
		quickSort(quickSortedArray, 0, LIST_LENGTH - 1);
		
		// Attempt to create new files of the specified names,
		// and write the sorted lists to these files.
		
		try {
			
			// Write the shell sorted array to file.
			
			outputFileName = outputFileName.replace("X", Integer.toString(fileNumber));
			fileWriter = new FileWriter(outputFileName);
			fileWriter.write("Shell Sorted Array\n\n");
			fileWriter.write(arrayToString(shellSortedArray));
			fileWriter.close();
			
			// Open a new file to write the quick sorted array.
			
			outputFileName = outputFileName.replace(
					Integer.toString(fileNumber++), Integer.toString(fileNumber));
			
			// Write the quick sorted array to file.
			
			fileWriter = new FileWriter(outputFileName);
			fileWriter.write("Quick Sorted Array\n\n");
			fileWriter.write(arrayToString(quickSortedArray));;
			fileWriter.close();
			
		} // End try.
		catch (IOException e) {
			System.err.println(e.getMessage());
		}  // End catch.

	} // End main method.

// ****************************************************************************
	
	/**
	 * Convert a sorted list of integers to a string with 10 integers per line
	 * for writing to file.
	 * 
	 * @param	sortedArray	The sorted list of integers.
	 * @return 	arrayString	The list of sorted integers for writing to file.
	 */
	
	private static String arrayToString(int[] sortedArray) {
		
		// Declaring local variables.
		
		String arrayString = "";
		
		// Pushing each integer of the sorted list to a string for the output file.
		
		for (int i = 0; i < sortedArray.length;) {
			
			for (int j = 0; j < 10; j++, i++) {
				arrayString += sortedArray[i] + " ";
			} // End for.
			
			arrayString += "\n";
			
		} // End for.
		
		return arrayString;
		
	} // End arrayToString method.
	
// ****************************************************************************
	
	/**
	 * Sort integers using the shell sorting algorithm.
	 * 
	 * @param	unsortedList	The list of unsorted integers to sort.
	 */
	
	private static void shellSort(int[] unsortedList) {
		
		// Declaring local variables.
		
		int nextInteger;	// The next integer in the sublist.
		int index = 0;		// The current sublist of integers.
		
		// Iterate the list at intevals, dividing by 2 each time.
		
		for (int space = unsortedList.length/2; space > 0; space /= 2) {
	 
			// Scan the sublist by each interval.
			
			for (int i = space; i < unsortedList.length; i++) {
     
				// Sort the sublist.
				
				nextInteger = unsortedList[i];
				
				for (index = i; index >= space 
						&& unsortedList[index - space] > nextInteger; 
						index -= space) {
					
					unsortedList[index] = unsortedList[index - space];
					
				} // End for.
				
				unsortedList[index] = nextInteger;
				
			}	// End for.
			
		} // End for.
		
	} // End shellSort method.
	
// ****************************************************************************
	
	/**
	 * Quicksort implements the textbook case of quicksort to efficiently sort the
	 * list by finding a pivot, swapping integers to the correct side of the pivot,
	 * splitting each side of the pivot into sublists, and repeating until each
	 * sublist has less than four elements. Short sublists of less than four
	 * integers are sorted using shell sort: an improved insertion sort.
	 * 
	 * @param 	sortingList	The list or sublist of integers being recursively sorted.
	 */
	
	private static void quickSort(int[] sortingList, int firstIndex, int lastIndex) {
		
		// Declaring local constants.
		
		final int MIN_SIZE = 4;			// The minimum size of the list.
		
		// Declaring local variables.
		
		int pivotIndex = 0;				// The pivot of the sublist.
		int tempIndex = 0;				// Placeholder to swap two integers.
		int midIndex = (lastIndex - firstIndex) / 2; 
										// Find the middle integer of the list.		
		int leftIndex = firstIndex + 1;	// Leftmost integer of the sublist.
		int rightIndex = lastIndex - 2;	// Rightmostmost integer of the sublist.
		int pivotValue = 0;				// The integer that seperates two sublists.
		
		// Sort the sublist using insertion(shell) sort if the length is less than four,
		// otherwise, sort the sublist by the quick sort algorithm.
		
		if (lastIndex - firstIndex < MIN_SIZE) {
			
			shellSort(sortingList); 
			
		} // End if.
		else {
			
			// Exchange integers of middle and last indices of the sublist.
			
			tempIndex = sortingList[midIndex];
			sortingList[midIndex] = sortingList[lastIndex - 1];
			sortingList[lastIndex - 1] = tempIndex;
			
			// Find the new pivot of sublist.
			
			pivotIndex = lastIndex - 1;
			pivotValue = sortingList[pivotIndex];
			
			// Scan the sublist and swap integers.
				
			while (sortingList[leftIndex] < pivotValue) {
				leftIndex++;
			} // End while.
			
			while (sortingList[rightIndex] > pivotValue) {
				rightIndex++;
			} // End while.
			
			// If the left index overlaps the right index, swap them.
			
			if (leftIndex < rightIndex) {
				
				tempIndex = sortingList[leftIndex];
				sortingList[leftIndex] = sortingList[rightIndex];
				sortingList[rightIndex] = tempIndex;
				
			} // End if.
			
			// Swap the pivot and leftmost integer of the sublist.
			
			tempIndex = sortingList[pivotIndex];
			sortingList[pivotIndex] = sortingList[leftIndex];
			sortingList[leftIndex] = tempIndex;
			pivotIndex = leftIndex;
			
			// Recursively quick sort the new sublists.
			
			quickSort(sortingList, firstIndex, pivotIndex - 1);
			quickSort(sortingList, pivotIndex + 1, lastIndex);
			
		} // End else.
		
	} // End quickSort method.
	
} // End class.