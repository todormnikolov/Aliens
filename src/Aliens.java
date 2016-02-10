import java.util.Random;
import java.util.Scanner;

public class Aliens {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		byte numberOfTests = getNumberOfTests(sc, "number of tests");

		/**
		 * store English words in columns with index 0, alien text - column
		 * index 1
		 */
		String[][] data = new String[numberOfTests][2];

		for (byte i = 0; i < numberOfTests; i++) {

			System.out.println("Test " + (i + 1) + ":");

			/**
			 * comment 2 lines below if use auto-generation input
			 */
			data[i][0] = getWord(sc, "an English word");
			data[i][1] = getText(sc, "an alien text", data[i][0].length());

			/**
			 * uncomment 2 lines below for auto-generation input
			 */
			// data[i][0] = getAutoGenerateWord(3);
			// data[i][1] = getAutoGenerateText(90000, data[i][0].length());

		}

		printTitle("Result from tests");
		printResult(data, numberOfTests);

		/**
		 * for output long strings uncheck "Limit console output" in
		 * Window->Preferences->Run/Debug->Console
		 */
		printInput(data);

		sc.close();

	}

	/**
	 * 
	 * @param sc
	 * @param message
	 * @return Get integer from console, check min and max boundaries and return
	 *         number of tests.
	 */
	public static byte getNumberOfTests(Scanner sc, String message) {

		byte minTests = 1;
		byte maxTests = 40;

		int numberOfTests;

		do {
			System.out.printf("Input %s: ", message);
			numberOfTests = sc.nextInt();
		} while (numberOfTests < minTests || numberOfTests > maxTests);

		return (byte) numberOfTests;
	}

	/**
	 * 
	 * @param sc
	 * @param message
	 * @return Get a string for word from console, when it pass checks return
	 *         that string.
	 */
	public static String getWord(Scanner sc, String message) {

		String input;

		do {
			System.out.printf("Input %s: ", message);
			input = sc.next();
		} while (!correctInputCheck(input));

		return input.toLowerCase();
	}

	/**
	 * 
	 * @param sc
	 * @param message
	 * @param wordLength
	 * @return Get a string for text from console, when it pass checks return
	 *         that string.
	 */
	public static String getText(Scanner sc, String message, int wordLength) {

		String text = getWord(sc, message);

		while (!compareStringLengths(wordLength, text.length())) {
			text = getText(sc, message, wordLength);
		}

		return text;
	}

	/**
	 * 
	 * @param maxLength
	 * @return Returns auto-generate string for word with maximum possible
	 *         length pass as parameter maxLength.
	 */
	public static String getAutoGenerateWord(int maxLength) {

		String input;

		do {
			input = autoGenerateInput(maxLength);
		} while (!correctInputCheck(input));

		return input.toLowerCase();
	}

	/**
	 * 
	 * @param maxLength
	 * @param wordLength
	 * @return Returns auto-generate string for text with maximum possible
	 *         length pass as parameter maxLength, after check that length with
	 *         word's length.
	 */
	public static String getAutoGenerateText(int maxLength, int wordLength) {

		String text = getAutoGenerateWord(maxLength);

		while (!compareStringLengths(wordLength, text.length())) {
			text = getAutoGenerateText(maxLength, wordLength);
		}

		return text;
	}

	/**
	 * 
	 * @param wordLength
	 * @param textLength
	 * @return true when textLength is equal or bigger from wordLength,
	 *         otherwise print proper message on console and return false.
	 */
	public static boolean compareStringLengths(int wordLength, int textLength) {

		if (wordLength > textLength) {
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

			if (c < 65 || c > 122) {
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
			} else {
				break;
			}

		}

		if (output.length() == 0) {
			return null;
		}

		return output.toString().split(" ");
	}

	/**
	 * Print formatted string for section title
	 * 
	 * @param title
	 */
	public static void printTitle(String title) {
		System.out.println("----------------------------------------------");
		System.out.printf("		%s\n", title.toUpperCase());
		System.out.println("----------------------------------------------");

	}

	/**
	 * Print the result: number of matches and each start index
	 * 
	 * @param data
	 * @param numberOfTests
	 */
	public static void printResult(String[][] data, byte numberOfTests) {

		String[] indexes;

		for (byte i = 0; i < numberOfTests; i++) {

			indexes = searchMatches(data[i][0], data[i][1]);

			System.out.printf("Test %d\nFound matches: ", i + 1);

			if (indexes == null) {
				System.out.println(0);
				continue;
			}

			int lastIndex = indexes.length;

			System.out.println(lastIndex--);
			System.out.print("Start indexes: ");

			for (int j = 0; j < lastIndex; j++) {
				System.out.print(indexes[j] + " ");
			}

			System.out.println(indexes[lastIndex]);
		}
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

		printTitle("Input data");

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

		int index;

		StringBuilder result = new StringBuilder();
		int stringLength = rand.nextInt(maxLength) + 1;

		for (int i = 1; i < stringLength; i++) {
			index = rand.nextInt(26) + 97;
			result.append((char) index);
		}

		return result.toString();
	}

}
