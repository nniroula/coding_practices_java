package interviewAskedQsPackage;

//1.
/*
 * For an integer array, the discount is minimum of all preceding terms until
 * the index i item. Calculate the total sale price.
 * Example 1 Input: nums = [10, 5, 8, 2, 6] output = 17
 * Input 2: nums = [3, 3, 3] and output is 3.
 */

//2. Variant of Q1
/*
* Every time you calculate the sale price update the array with that price. 
* Then for next calculation, use updated values values.
* Example 1 Input: nums = [10, 5, 8, 2, 6]
* 	updated arr = [10; 0; 8, 2, 6] output = 26
* Input 2: nums = [3, 3, 3] and output is 3.
* 	updatedArr = [3, 0, 3] output = 6
* Solution create another using a for loop as above. Then calculate sum of that array
*/
class SalePrice{
	public long calcSalePrice(int[] arr) {
		//calculate discount price = running minimum
		//calculate selling price = price - running minimum
		// Keep adding selling prices to running sum
		int runningSaleSum = arr[0]; //total selling price so far
		int minValue = arr[0]; //upto i-1, min value will be already calculated
		//So, compare min val with arr[i] and decide min val
		int sellingPriceAtI = 0;
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] < minValue) {
				minValue = arr[i];
			}
			sellingPriceAtI = arr[i] - minValue;
			// if negative value, then make it 0
			if(sellingPriceAtI < 0) {
				sellingPriceAtI = 0;
			}
			runningSaleSum += sellingPriceAtI;
		}
		return runningSaleSum;
	}
	
	// Example 1 Input: nums = [10, 5, 8, 2, 6]
	//	updated arr = [10; 0; 8, 2, 6] output = 26
	public long updatedSum(int[] arr) {
		// need running sum, min value so far, sum at I, new array
		long runningSum = arr[0];
		int minValue = arr[0];
		int priceAtI = 0;
		
		if(arr.length == 0) {
			return 0;
		}
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] < minValue) {
				minValue = arr[i];
			}
			priceAtI = arr[i] - minValue;
			//update the array
			arr[i] = priceAtI;
			runningSum += priceAtI;
		}
		return runningSum;
	}
}

public class VisaAsked{
	public static void main(String[] args) {
		// instantiate the class
		SalePrice spObj = new SalePrice();
		
		//call method
		int[] arr1 = {3, 3, 3};  // output is 3.
		long result1 = spObj.calcSalePrice(arr1);
		System.out.println("result1: " + result1);
		
		int[] arr2 = {10, 5, 8, 2, 6}; // output = 17
		long result2 = spObj.calcSalePrice(arr2);
		System.out.println("result2: " + result2);
		
		//updatedSum method
		int[] arr3 = {2, 4, 2};
		int[] arr4 = {1, 3, 4};
		System.out.println("Updated Sum1: " + spObj.updatedSum(arr3));
		System.out.println("Updated Sum2: " + spObj.updatedSum(arr4));
		
	}
}