package hangman;

import java.io.IOException;
import java.util.Scanner;

import hangman.view.MainUI;

public class Application {
	public static void main(String[] args) throws IOException {
		System.out.println("Test Start");
		Scanner inputReader = new Scanner(System.in);
		MainUI main = new MainUI();
		do {
			main.process();
			System.out.println("Restart? Y/N");
		} while (inputReader.nextLine().equals("Y"));
		System.out.println("Game End");
	}
}
