import java.util.Scanner;

public class Aliens {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int numberOfTests;

		do {
			System.out.print("Input number of test: ");
			numberOfTests = sc.nextInt();
		} while (numberOfTests < 1 || numberOfTests > 40);

		String[] words = new String[numberOfTests];
		String[] alienSentences = new String[numberOfTests];

		String str = "";

		for (int i = 0; i < numberOfTests; i++) {

			do {
				System.out.print("Input a english word: ");
				str = sc.next();
			} while (!inputLengthCheck(str));

			words[i] = str.toLowerCase();

			sc.nextLine(); // skip double print string before next scanner

			do {
				System.out.print("Input an alien sentence: ");
				str = sc.nextLine();
			} while (!inputLengthCheck(str) || words[i].length() > str.length());

			alienSentences[i] = str.toLowerCase();
		}

		for (int i = 0; i < numberOfTests; i++) {

			printResult(words[i], alienSentences[i]);
		}

		sc.close();

	}
	
	public static boolean inputLengthCheck(String s) {
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
