import java.util.Scanner;

public class Aliens {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		byte numberOfTests;

		do {
			System.out.print("Input number of test: ");
			numberOfTests = sc.nextByte();
		} while (numberOfTests < 1 || numberOfTests > 40);

		String[] words = new String[numberOfTests];
		String[] alienSentences = new String[numberOfTests];

		String str = "";

		for (byte i = 0; i < numberOfTests; i++) {

			do {
				System.out.print("Input a english word: ");
				str = formatInput(sc.next());
			} while (!inputLengthRangeCheck(str) || !inputStringCheck(str));

			words[i] = str;

			sc.nextLine(); // skip double print string before next scanner

			do {
				System.out.print("Input an alien sentence: ");
				str = replaceIntervals(formatInput(sc.nextLine()));
			} while (!inputLengthRangeCheck(str)  || !inputStringCheck(str) || words[i].length() > str.length());

			alienSentences[i] = str;
			System.out.println(alienSentences[i]);
		}

		for (byte i = 0; i < numberOfTests; i++) {

			printResult(words[i], alienSentences[i]);
		}

		sc.close();

	}

	public static String formatInput(String s) {
		return s.toLowerCase();
	}
	
	public static String replaceIntervals(String s){
		return s.replaceAll(" ", "");
	}

	public static boolean inputStringCheck(String s) {

		char c;

		for (int i = 0; i < s.length(); i++) {

			c = s.charAt(i);

			if (c < 'a' || c > 'z') {
				return false;
			}
		}

		return true;

	}

	public static boolean inputLengthRangeCheck(String s) {
		return (s.length() >= 2 && s.length() <= 100000) ? true : false;
	}

	public static void printResult(String word, String sentence) {

		int index = 0;
		int counter = 0;
		String output = "";

		while (index != -1) {
			index = sentence.indexOf(word, index);
			if (index != -1) {
				counter++;
				output += index++ + " ";
			}
		}

		System.out.println(counter);

		if (counter != 0) {
			// print indexes without last interval in string
			System.out.println(output.substring(0, output.length() - 1));
		}

	}

}
