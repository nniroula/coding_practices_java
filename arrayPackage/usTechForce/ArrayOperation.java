package arrayPackage.usTechForce;

import java.util.Arrays;

class ArrayActivity{
	
	/* 1. Alternating Array SignsTask:
	Given an array of integers arr, modify it such that every element at an even 
 	index is positive and every element at an odd index is negative. 
 	If an element is 0, leave it as 0.
 	Example: [1, 2, 3, 4] -> [1, -2, 3, -4]
	 */
	public int[] modifyIntArr(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(i % 2 == 0 && arr[i] < 0) {
				arr[i] = -1 * arr[i];
			}else if(i % 2 == 1 && arr[i] > 0) {
				arr[i] = -1 * arr[i];
			}
		}
		return arr;
	}
	
	/*
	2. Count Elements Greater Than Neighbor: 
	Given an array nums, count how many elements are strictly greater than both 
	their left and right neighbors. (Ignore the first and last elements).
	Example: [1, 3, 2, 5, 4] -> 2 (since 3 and 5 fit the criteria).
	*/
	public int countElemsGreaterThanNeighbors(int[] arr) {
		int count = 0;
		for(int i = 1; i < arr.length - 1; i++) { //exclude 1st and last elems
			int element = arr[i];
			if(element > arr[i - 1] && element > arr[i + 1]) {
				count++;
			}
		}
		return count;
	}
	
	/*
	3. Array Parity Swap: 
	Given an array of integers, swap the first even element you find with the 
	last odd element you find. If either doesn't exist, return the array unchanged.
	Example: [3, 5, 2, 7, 4, 9] -> [3, 5, 9, 7, 4, 2]
	*/
	//use two pointers - first tracks 1st even elem and 2nd tracks last odd
	public int[] paritySwap(int[] arr) {
		int leftPointer = 0;
		int rightPointer = arr.length - 1;
		int firstEvenElem = 0;
		int lastOddElem = 0;
	
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] % 2 == 0) {
				firstEvenElem = arr[i];
				leftPointer = i;
				break;
			}
		}
		
		for(int j = arr.length - 1; j>= 0; j--) {
			if(arr[j] % 2 != 0) {
				lastOddElem = arr[j];
				rightPointer = j;
				break;
			}
		}
		
		arr[leftPointer] = lastOddElem;
		arr[rightPointer] = firstEvenElem;
		
		return arr;
	}
	
	/*
	4. Check If Arithmetic Progression: 
	Given an array, return true if the difference between consecutive 
	elements is the same throughout the entire array, otherwise return false.
	Example: [2, 4, 6, 8] -> true; 
	[2, 4, 7, 9] -> false
	*/
	public boolean checkIfSameDifference(int[] arr) {
		boolean result = false;
		if(arr.length == 0 || arr.length == 1 || arr.length == 2) {
			return true;
		}else{
			int difference = arr[1] - arr[0];
			
			for(int i = 1; i < arr.length - 1; i++) { // it length - 1
				if(arr[i + 1] - arr[i] != difference) {
					result = false;
					break;
				}else {
					result = true;
				}
			}
		}
		return result;
	}
	
	/*
	5. Sum of Boundaries: 
	Given an array arr, return the sum of the first element,the middle element 
	(if the length is odd, use the absolute middle; if even, use the left-middle),
	 and the last element.
	 Example: [1, 2, 3, 4, 5, 6] -> length is 6 (even), 
	 left-middle is 3. Sum = 1 + 3 + 6 = 10.
	*/
	public int getSumOfFirstMiddleLastElem(int[] arr) {
		int sum = 0;
		int arrLength = arr.length;
		int middleIndex = 0;
		if(arr.length % 2 == 0) {
			middleIndex = arrLength/2 - 1;
		}else {
			middleIndex = arrLength/2;
		}
		sum = arr[0] + arr[arr.length - 1] + arr[middleIndex];
		return sum;
	}
	
	
	/*
	6. Replace with Suffix Sum: 
	Modify an array nums such that each element at index i is replaced by 
	the sum of all elements from index i to the end of the array.
	Example: [1, 2, 3] -> [6, 5, 3]
	CodeSignal Q2 Style (Easy Implementation & Basic Simulation)
	*/
	public int[] replaceWithSuffixSum(int[] arr) {
		//declare variable to hold suffixSum
		int suffixSum = 0;
		//for i through the end, calculate the sum, assign to suffixSum and
		// update the arr elem with suffixSum
		int i = 0;
		//for(int i = 0; i < arr.length; i++) {
		while (i < arr.length) {
			int sum = 0;
			for(int j = i; j < arr.length; j++) { // j = i, j starts at i
				sum += arr[j];
			}
			arr[i] = sum;
			i++;
		}
		return arr;
	}
	
	/*
	7. Array Mutation (Classic CodeSignal Q2): 
	Given an integer n and an array a of length n, create a new array b of 
	length n where b[i] = a[i - 1] + a[i] + a[i + 1]. If an index is out of 
	bounds, treat its value as 0.
	Example: a = [4, 0, 1, -2, 3] -> b = [4, 5, -1, 2, 1]
	*/
	public int[] mutateArray(int n, int[] a) {
		int[] b = new int[n];
		// for first and last element assign
		b[0] = 0 + a[0] + a[0 + 1]; // b[i] = a[i - 1] + a[i] + a[i + 1]
		b[b.length - 1] = a[a.length - 2] + a[a.length - 1] + 0; 
		
		for(int i = 1; i < b.length - 1; i++) {
			b[i] = a[i - 1] + a[i] + a[i + 1];
		}
		return b;
	}
	
	/*
	8. Construct String from Alternating Arrays: 
	You are given two arrays of integers, a and b, of the same length. Create a 
	string by taking elements alternatingly from a and b 
	(i.e., a[0], b[0], a[1], b[1]...), but only append the number if it is 
	positive.
	Example: a = [1, -3, 5], b = [2, 4, -6] -> Result string: "1245"
	*/
	public String buildString(int[] a, int[] b) {
		//take StringBuilder and append values to it. Then convert
		//StringBuilder object to the string(use toString())
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < a.length) {
			if(a[i] > 0) {
				sb.append(a[i]);
			}
			if(b[i] > 0) {
				sb.append(b[i]);
			}
			i++;
		}
		
		return sb.toString();
	}
	
	
	/*
	9. Matrix/Array Ribbons: 
	You are given an array of integers arr representing pieces of wood. You need 
	to cut them into smaller pieces of equal integer length k. Write a function 
	that determines the maximum number of pieces of length k you can get.
	Example: arr = [4, 7, 5], k = 3 -> Cut 4 into one '3', 7 into two '3's, 5 
	into one '3'. Total pieces = 4.
	*/
	
	/*
	10. Prefix-Suffix Match: 
	Given two arrays a and b, find the longest trailing segment (suffix) of a that 
	exactly matches the leading segment (prefix) of b. Return the length of this 
	segment.
	Example: a = [1, 2, 3, 4], b = [3, 4, 5, 6] -> Output: 2 (the matching 
	subarray is [3, 4]).
	*/
	
	
	/*
	11. Maximum ZigZag Subarray: 
	A zigzag sequence is one where the elements alternate between strictly 
	increasing and strictly decreasing. Find the length of the longest 
	contiguous subarray that forms a zigzag pattern.
	Example: [1, 3, 2, 4, 3, 3] -> 5 (The segment [1, 3, 2, 4, 3] zigzags up, down, up, down).
		*/
	
	/*
	12. Merge and Filter Congruent Elements: 
	Given two arrays a and b of equal length, create a new array c where c[i] is 
	the absolute difference between a[i] and b[i]. Finally, return only the 
	elements in c that are divisible by a given integer k.
	Example: a = [10, 20, 30], b = [12, 15, 33], k = 3 -> c = [2, 5, 3]. 
	Filtered by divisible by 3 -> [3].
		*/
	
	/*
	13. Cyclic Shift Checker: 
	Given two arrays a and b of the same length, determine if b can be obtained 
	by cyclically shifting a to the right by some number of positions.
	Example: a = [1, 2, 3, 4], b = [3, 4, 1, 2] -> true (shifted by 2).
	*/
	
	/*
	14. Array Window Averages: 
	Given an array nums and an integer k, create a new array containing the floor
	average of every contiguous window of size k.
	Example: nums = [1, 3, 5, 7], k = 2 -> Windows are [1,3] (avg 2) and 
	[3,5] (avg 4) and [5,7] (avg 6) -> [2, 4, 6].
		*/
	
	/*
	15. Peak and Trough Counter: 
	An element is a peak if it is strictly greater than its neighbors, and a 
	trough if it is strictly smaller. Given an array, return an array of 2 
	elements: [peak_count, trough_count].
	Example: [1, 5, 2, 6, 3] -> Peaks are 5 and 6 (2). Trough is 2 (1). 
	Output: [2, 1].
		 */
}


//Main class
public class ArrayOperation {
	public static void main(String[] args) {
		//instantiate the class
		ArrayActivity aAObject = new ArrayActivity();
		
		//1
		int[] arr1 = {1, 2, 3, 4};  // ans: [1, -2, 3, -4]
		int result1[] = aAObject.modifyIntArr(arr1);
		//System.out.println(Arrays.toString(result1));
		
		//2
		int[] arr2 = {1, 3, 2, 5, 4};  // ans -> 2 (since 3 and 5 fit the criteria).
		int count = aAObject.countElemsGreaterThanNeighbors(arr2);
		//System.out.println(count);
		
		//3. [3, 5, 2, 7, 4, 9] ans -> [3, 5, 9, 7, 4, 2]
		int[] arr3 = {3, 5, 2, 7, 4, 9};
		int[] result2 = aAObject.paritySwap(arr3);
		//System.out.println(Arrays.toString(result2));
		
		//4.
		//int[] arr4 = {2, 4, 6, 8}; // true
		int[] arr4 = {2, 4, 7, 9}; // false
		boolean result3 = aAObject.checkIfSameDifference(arr4);
		//System.out.println(result3);
		
		//5. [1, 2, 3, 4, 5, 6] -> ans 10
		int[] arr5 = {1, 2, 3, 4, 5, 6};
		int sum = aAObject.getSumOfFirstMiddleLastElem(arr5);
		//System.out.println(sum);
		
		//6. [1, 2, 3] -> ans: [6, 5, 3]
		int[] arr6 = {1, 2, 3};
		int[] result4 = aAObject.replaceWithSuffixSum(arr6);
		//System.out.println(Arrays.toString(result4));
		
		//7. a = [4, 0, 1, -2, 3] -> b = [4, 5, -1, 2, 1]
		int[] a = {4, 0, 1, -2, 3}; //a, b and n are used as in question
		int n = 5;
		int[] b = aAObject.mutateArray(n, a); 
		//System.out.println(Arrays.toString(b));
		
		//8. Example: a = [1, -3, 5], b = [2, 4, -6] -> Result string: "1245"
		int[] a2 = {1, -3, 5};
		int[] b2 = {2, 4, -6};
		String result5 = aAObject.buildString(a2, b2);
		System.out.println(result5);
		
	}
}
