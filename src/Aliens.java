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
			} while (str.length() < 2 || str.length() > 100000);

			words[i] = str.toLowerCase();

			sc.nextLine(); // skip double print string before next scanner

			do {
				System.out.print("Input an alien sentence: ");
				str = sc.nextLine();
			} while (str.length() < 2 || str.length() > 100000 || words[i].length() > str.length());

			alienSentences[i] = str.toLowerCase();
		}

		for (int i = 0; i < numberOfTests; i++) {

			printResult(words[i], alienSentences[i]);
		}

		sc.close();

	}

	public static void printResult(String word, String sentence) {

		int index = sentence.indexOf(word);

		if (index == -1) {
			System.out.println(0);
		} else {

			int counter = 1;
			String output = "" + index;

			for (int i = index + 1; i < sentence.length(); i++) {
				index = sentence.indexOf(word, ++index);
				if (index != -1) {
					counter++;
					output += " " + index;
				} else {
					break;
				}
			}

			System.out.println(counter);
			System.out.println(output);

		}
	}

}
