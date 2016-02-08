import java.util.Random;
import java.util.Scanner;

public class Aliens {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		byte minTests = 1;
		byte maxTests = 40;

		byte numberOfTests;

		do {
			System.out.print("Input number of test: ");
			numberOfTests = sc.nextByte();
		} while (numberOfTests < minTests || numberOfTests > maxTests);

		/**
		 * store English words in columns with index 0, alien text - column
		 * index 1
		 */
		String[][] data = new String[numberOfTests][2];

		String input = "";

		for (byte i = 0; i < numberOfTests; i++) {

			System.out.println("Test " + (i + 1) + ":");

			do {
				/**
				 * clear previous console input (only for manual input), comment
				 * when use auto-generate English word
				 */
				sc.nextLine();

				/**
				 * comment 2 lines below if make test with auto-generate an
				 * English word
				 */
				System.out.print("Input an English word: ");
				input = sc.next().trim().toLowerCase();

				/**
				 * uncomment next line to generate an English word
				 */
				// input = autoGenerateInput(3);

			} while (!correctInputCheck(input));

			data[i][0] = input;

			do {
				/**
				 * clear previous console input (only for manual input), comment
				 * when use auto-generate alien text
				 */
				sc.nextLine();

				/**
				 * comment 2 lines below if make test with auto-generate alien
				 * text
				 */
				System.out.print("Input an alien text: ");
				input = sc.next().trim().toLowerCase();

				/**
				 * uncomment next line to generate alien text
				 */
				// input = autoGenerateInput(90000);

			} while (!correctInputCheck(input) || !compareStringLengths(data[i][0].length(), input.length()));

			data[i][1] = input;
		}

		String[] indexes;
		int lastIndex;

		System.out.println("----------------------------------------------");
		System.out.println("		RESULT FROM TESTS");
		System.out.println("----------------------------------------------");

		for (byte i = 0; i < numberOfTests; i++) {

			indexes = searchMatches(data[i][0], data[i][1]);

			System.out.printf("Test %d\nFound matches: ", i + 1);

			if (indexes == null) {
				System.out.println(0);
				continue;
			}

			lastIndex = indexes.length;

			System.out.println(lastIndex--);
			System.out.print("Start indexes: ");

			for (int j = 0; j < lastIndex; j++) {
				System.out.print(indexes[j] + " ");
			}

			System.out.println(indexes[lastIndex]);

		}

		/**
		 * for output long strings uncheck "Limit console output" in
		 * Window->Preferences->Run/Debug->Console
		 */
		// printInput(data);

		sc.close();

	}

	/**
	 * 
	 * @param strOneLength
	 * @param strTwoLength
	 * @return true when strTwoLength is equal or bigger from strOneLength,
	 *         otherwise print proper message on console and return false.
	 */
	public static boolean compareStringLengths(int strOneLength, int strTwoLength) {

		if (strOneLength > strTwoLength) {
			System.out.println(
					"Incorrect input data! The input for alian text must not be smaller from english word in same test variant. Try again...");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param input
	 * @return true when input's length parameter is between or equal at min and
	 *         max values and each character is letter. In all other cases print
	 *         message for error and return false.
	 */
	public static boolean correctInputCheck(String input) {

		byte minLength = 2;
		int maxLength = 100000;

		int length = input.length();

		if (length < minLength || length > maxLength) {
			System.out.printf(
					"Incorrect input data! The input length must be between %d and %d characters. Try again...\n",
					minLength, maxLength);
			return false;
		}

		char c;

		for (int i = 0; i < length; i++) {

			c = input.charAt(i);

			if (c < 'a' || c > 'z') {
				System.out.println("Incorrect input data! The input must be contain only latin letters. Try again...");
				return false;
			}
		}

		return true;

	}

	/**
	 * 
	 * @param word
	 * @param text
	 * @return Returns an array of string elements with index values on found
	 *         parameter word in parameter text. Return null when ins't fount
	 *         match.
	 */
	public static String[] searchMatches(String word, String text) {

		int index = 0;

		StringBuilder output = new StringBuilder();

		while (true) {

			index = text.indexOf(word, index);

			if (index != -1) {
				output.append(index++ + " ");
			} else if (output.length() == 0) {
				return null;
			} else {
				break;
			}

		}

		return output.toString().split(" ");
	}

	/**
	 * Printing input data for test.
	 * 
	 * @param data
	 * @param label
	 */
	public static void printInput(String[][] data) {

		int lengthRow = data.length;
		int lengthCol = data[0].length;
		int len;
		char[] array;

		System.out.println("----------------------------------------------");
		System.out.println("		INPUT DATA");
		System.out.println("----------------------------------------------");

		for (int i = 0; i < lengthRow; i++) {
			for (int j = 0; j < lengthCol; j++) {

				array = data[i][j].toCharArray();
				len = array.length;

				if (j == 0) {
					System.out.println("Test " + (i + 1) + ":");
					System.out.print("English word: ");
				} else {
					System.out.print("Alien text: ");
				}

				for (int k = 0; k < len; k++) {

					if (k % 4200 == 0 && k != 0) {
						System.out.println();
					}

					System.out.print(array[k]);

				}
				System.out.print(" - (" + len + " letters long)\n");
			}

		}
	}

	/**
	 * Auto-generate random word with length from 1 to maxLength parameter.
	 * Using for testing purposes.
	 * 
	 * @param maxLength
	 * @return Returns a generated string
	 */
	public static String autoGenerateInput(int maxLength) {

		Random rand = new Random();
		String letters = "abcdefghijklmnopqrstuvwxyz";

		char[] chars = letters.toCharArray();
		int charArrayLength = letters.length();

		int index;

		StringBuilder result = new StringBuilder();
		int stringLength = rand.nextInt(maxLength) + 1;

		for (int i = 1; i < stringLength; i++) {
			index = rand.nextInt(charArrayLength);
			result.append(chars[index]);
		}

		return result.toString();
	}

}
