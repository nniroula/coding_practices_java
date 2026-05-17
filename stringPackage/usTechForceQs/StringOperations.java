package stringPackage.usTechForceQs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Implementation{
	/*
	 * 1. Parity Transformation:
	 Given an array of strings, if a string's length is even, reverse it.
	 If it is odd, convert it to uppercase.
	 NOTE: use StringBuilder sb, sb.reverse() and then .toString() to convert
	 StringBuilder object back to the string.
	*/
	public String[] checkParity(String[] strs) {
		for(int i = 0; i < strs.length; i++) {
			StringBuilder sb = new StringBuilder();
			if(strs[i].length() % 2 == 0) {
				sb.append(strs[i]);
				String newString = sb.reverse().toString(); // Know this
				strs[i] = newString;
			}else {
				strs[i] = strs[i].toUpperCase();
			}
		}
		return strs;
	}

	/* 2. Digital Sanitizer:
	Given a string containing alphanumeric characters, return a new string
	containing only the digits, in their original order.
	Focus: Character.isDigit(char) and StringBuilder.append().
	Example: "US-Gov-2026" to "2026".
	 */
	public String getNumericString(String str) {
		String newString = " ";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		newString = sb.toString();
		return newString;
	}

	/*
	 * 3. Vowel-Consonant Pattern Matching:
		You are given a pattern string (e.g., "010") where 0 represents a
		vowel and 1 represents a consonant. Return true if a given word
		matches the pattern.
		Focus: Conditional logic within a loop and
		String.indexOf() for vowels "aeiou".
		Example: Pattern "010", Word "cat" -> false.
	 */
	public boolean matchVowelConsonantPattern(String pattern, String word) {
		boolean result = false;
		String vowels = "aeiou";
		//Pattern "010", Word "cat" -> false.
		for(int i = 0; i < word.length(); i++) {
			String ch = Character.toString(word.charAt(i));
			if(pattern.charAt(i) == '0' && vowels.contains(ch)){
				result = true;
				//System.out.println("if result: " + result);
			}else if(pattern.charAt(i) == '1' && !vowels.contains(ch)) {
				result = true;
			}else {
				result = false;
			}
		}
		return result;
	}

	//4 Sentence Case Normalizer:
	/*
		Given a sentence in all lowercase, capitalize only the first letter
		of every word.
		Focus: String.split(" ") and String.substring().
		Example: "us tech force" -> "Us Tech Force".
	 */
	public String capitalizeFirstLetter(String str){
		//split the string at a blank space
		String [] strArr = str.split(" "); // need space in between ""

		for(int i = 0;  i < strArr.length; i++) {
			StringBuilder sb = new StringBuilder(); //create SB object
			String arrItem = strArr[i]; // take array element
			String subStr = arrItem.substring(0, 1); // subsring first letter
			subStr = subStr.toUpperCase(); // make it upper case
			sb.append(subStr).append(arrItem.substring(1)); //append both substrings to sb
			strArr[i] = sb.toString(); // update array item
		}
		//joint the array elements back into the string
		str = String.join(" ", strArr);

		return str;
	}

	//5 String Compression
	/*
		Given a string, replace every sequence of the same character with
		the character followed by the count (only if the count is > 1).
		Focus: Single-pass loop logic and boundary checking (i + 1 < length).
		Example: "aaabbc" -> "a3b2c".
	 */
	public String charCount(String str) {

		Map<Character, Integer> hm = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if(hm.containsKey(str.charAt(i))) {
				hm.replace(str.charAt(i), hm.getOrDefault(str.charAt(i), 0) + 1);
			}else {
				hm.put(str.charAt(i), 1);
			}
		}
		// get all keys from the hashmap
		//HashSet<Character> keys = (HashSet<Character>)hm.keySet();

		for(char key: hm.keySet()) {
			int val = hm.get(key);
			if(val > 1) {
				sb.append(key).append(val);
			}else {
				sb.append(key);
			}
		}
		str = sb.toString();

		return str;
	}

	//6. Circular Shift Check
	/*
		Given two strings s1 and s2, return true if s2 is a rotation of s1.
		Focus: The "Double String" trick: check if s2 is a substring of
		(s1 + s1).
		Example: s1="abcde", s2="cdeab" -> true.
		Steps: s1 + s1 = abcde + abcde = abcdeabcde is s2 substring of this - yes.
	 */
	public boolean isS1RotationOfS2(String str1, String str2) {
		boolean result = false;
		// concatenate str1 with with itself and check if s2 is then a substring
		String concatStr = str1 + str1;
		result = concatStr.contains(str2);
		return result;
	}

	//7. First Unique Character:
	/*
		Find the first character in a string that does not repeat and
		return its index. If all repeat, return -1.
		Focus: Using a frequency array int[26] or a
		HashMap<Character, Integer>.
		Example: "codesignal" -> 0 (for 'c').
	 */
	public int getFirstNonRepeatingChar(String str) {
		int index = -1;
		// put it in hashmap
		// from the hashmap, grab ones with frequency of 1
		// find which one's index comes early in the string - return that
		Map<Character, Integer> hm = new HashMap<>();
		for(int i = 0; i < str.length(); i++) {
			if(hm.containsKey(str.charAt(i))) {
				//update the value
				int value = hm.getOrDefault(str.charAt(i), 0);
				hm.replace(str.charAt(i), value + 1);
			}else {
				hm.put(str.charAt(i), 1);
			}
		}
		//from hm grab ones with freq 1
		int indexInStr = Integer.MAX_VALUE;
		for(Character key: hm.keySet()) { // provides list of all keys
			if(hm.get(key) == 1) { // if freq is 1, find index in str and assign it to index
				int newIndex = str.indexOf(key);
				if(newIndex < indexInStr) {
					indexInStr = newIndex;
				}
			}
		}

		index = indexInStr;

		return index;
	}

	//8. Domain Extractor:
	/*
		Given a list of government emails, return a list of unique agency
		names (the part between @ and .gov).
		Focus: String.indexOf("@") and String.substring().
		Example:["j.doe@nasa.gov", "a.smith@irs.gov"] -> ["nasa", "irs"].
	 */
	public List<String> getAgencyNames(List<String> emailList) {
		// find index of @ and index of . from each email
		// put them in a list and return that list
		List<String> al = new ArrayList<>();
		for(int i = 0; i < emailList.size(); i++) {
			String listElem = emailList.get(i);
			// grab index of @ and make a substring.
			// Then grab index of . from that substring and extract the substring in between
			int indexOfAt = listElem.indexOf("@");
			String subStrStartAtAt = listElem.substring(indexOfAt + 1);
			int indexOfDot = subStrStartAtAt.indexOf(".");
			// create a substring from the substring and add it to the arraylist
			String agencyName = subStrStartAtAt.substring(0, indexOfDot);
			al.add(agencyName);

		}
		return al;
	}

	//9. Palindrome with "Noise":
	/*
		Determine if a string is a palindrome, ignoring all non-alphanumeric
		characters and case sensitivity.
		Focus: Two-pointer technique (left and right indices).
		Example: "A man, a plan, a canal: Panama" -> true.
	 */
	public boolean isPalindrome(String str) {
		boolean result = false;
		// make a new string keeping nums and alphabets
		String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			Character ch = str.charAt(i); // To use .toString() use Wrapper class type for char
			if(Character.isDigit(ch) || alphabets.contains(ch.toString())){
				sb.append(ch);
			}
		}
		//convert string builder to a string. then convert all letters to lower cases
		String sbToStr = sb.toString();
		sbToStr = sbToStr.toLowerCase();

		int leftPointer = 0;
		int rightPointer = sb.length() - 1; // OR sbToStr.length()

		while(leftPointer <= rightPointer) {
			if(sbToStr.charAt(leftPointer) == sbToStr.charAt(rightPointer)) {
				result = true;
				leftPointer++;
				rightPointer--;
			}else {
				result = false;
				break;
			}
		}
		return result;
	}

	//10.
	/*
	 * Given a multi-word agency name, return its acronym in uppercase.
	 * Skip words like "and", "of", or "the".
	 * Focus: String.split(), List.contains(), and String.charAt(0).
	 * Example: "Department of Veterans Affairs" -> "DVA".
	 */
	public String retrunAcronym(String str) {
		StringBuilder sb = new StringBuilder();
		String[] strArr = str.split(" ");
		//System.out.println(Arrays.toString(strArr));
		for(String elem: strArr ) {
			if(!(elem.contains("of") || elem.contains("the") || elem.contains("the"))) {
				char ch = Character.toUpperCase(elem.charAt(0));
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}


public class StringOperations {
	public static void main(String[] args) {
		//Instantiate the class
		Implementation implObj = new Implementation();

		/* call methods */

		//1. Parity Check
		String[] langs = {"Java", "Python", "JavaScript", "cpp"};
		String[] result1 = implObj.checkParity(langs);
		//System.out.println(Arrays.toString(result1));

		//2
		String str1 = "US-Gov-2026"; // ans = "2026"
		String result2 = implObj.getNumericString(str1);
		//System.out.println("Numeric String: " + result2);

		//3.
		//Pattern "010", Word "cat" -> false.
		String pattern1 = "010";
		String word1 = "cat";
		String word2 = "aba";
		boolean result3 = implObj.matchVowelConsonantPattern(pattern1, word2);
		//System.out.println("Result 3: " + result3);

		//4.
		String str2 = "us tech force"; //ans -> "Us Tech Force".
		String result4 = implObj.capitalizeFirstLetter(str2);
		//System.out.println(result4);

		//5. Example: "aaabbc" -> "a3b2c".
		String str3 = "aaabbc";
		String result5 = implObj.charCount(str3);
		//System.out.println(result5);

		//6
		String s1="abcde", s2= "cdeab"; // -> true
		//String s1="a", s2= "cd"; // -> false
		boolean result6 = implObj.isS1RotationOfS2(s1, s2);
		//System.out.println(result6);

		//7
		String str4 = "codesignal"; // ans -> 0 (for 'c')
		int result7 = implObj.getFirstNonRepeatingChar(str4);
		//System.out.println(result7);

		//8. // ans:["nasa", "irs"].
		List<String> list = Arrays.asList("j.doe@nasa.gov", "a.smith@irs.gov");
		List<String> agencyList = implObj.getAgencyNames(list);
		//System.out.println(agencyList);

		//9.
		String palindromStr =  "A man, a plan, a canal: Panama"; //ans: true
		boolean result8 = implObj.isPalindrome(palindromStr);
		//System.out.println(result8);

		//10.
		String deptName = "Department of Veterans Affairs"; //ans: "DVA"
		String accronym = implObj.retrunAcronym(deptName);
		System.out.println(accronym);
	}
}
