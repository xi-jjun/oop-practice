package hangman;

import hangman.controller.FrontController;

import java.io.IOException;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) throws IOException {
		System.out.println("Test Start");
		Scanner inputReader = new Scanner(System.in);
		FrontController controller = new FrontController(inputReader);
		do {
			controller.run();
			System.out.println("Restart? Y/N");
		} while (inputReader.nextLine().equals("Y"));
		System.out.println("Game End");
	}
}
