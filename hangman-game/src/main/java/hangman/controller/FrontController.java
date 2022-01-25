package hangman.controller;

import hangman.repository.AnswerRepository;
import hangman.service.AnswerService;
import hangman.service.RandomWordService;

import java.io.IOException;
import java.util.Scanner;

public class FrontController {
	private final RandomWordService randomWordService = new RandomWordService();
	private final Scanner console;
	private String answer;
	private int life;
	private String playerTriedAnswer = "";
	private AnswerService answerService;

	public FrontController(Scanner scanner) {
		this.console = scanner;
		this.life = 5;
		answerService = new AnswerService();
	}

	public void run() throws IOException {
		System.out.println("Hangman Game Start!!");
		life = 5; // life reset

		System.out.println("단어를 고르는 중입니다...");
		answer = randomWordService.getRandomWord(); // 고르는 시점 자체를 처음으로 돌리자
		answerService.reset(answer); // set answer in AnswerRepo
		// MainController start
		// _ _ _ _ _
		String currentAnswerState = answerService.getCurrentAnswerState();
		System.out.println(currentAnswerState);
		// 출력할 때 맞춘 알파벳은 반영하여 출력해야 함 -> AnswerService 로 넘겨야 하나? 문자열을 _ _ _ a _ 이런식으로 넘겨버리자.

		// print 목숨
		System.out.println("life = " + life);

		// 반복 루프
		/**
		 * 알파벳 또는 정답이라 생각하시는 단어를 적어주세요!
		 * a
		 * a는 없습니다ㅜㅜ
		 * _ _ _ _ _
		 * 시도한 알파벳이나 단어들 : a
		 * 남은 목숨 : 4
		 */
		do {
			System.out.println("알파벳 또는 정답이라 생각하시는 단어를 적어주세요!");
			try {
				playerTriedAnswer = console.nextLine(); // a
				answerService.addPlayerAnswer(playerTriedAnswer);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			if (answerService.isAnswer(playerTriedAnswer)) {
				// answer!
				System.out.println("정답입니다!");
				break;
			} else if (answerService.isContainedAnswer(playerTriedAnswer)) {
				// playerAnswer is contained to answer
				System.out.println(playerTriedAnswer + "는 있습니다!");
			} else {
				// wrong
				System.out.println(playerTriedAnswer + "는 없습니다ㅜ");
				if (--life == 0) {
					System.out.println("Game Over");
					break;
				}
			}

			currentAnswerState = answerService.getCurrentAnswerState();
			System.out.println(currentAnswerState); // _ _ _ _ _

			String currentTriedAnswerList = answerService.getCurrentTriedAnswerList();
			System.out.println("시도한 알파벳이나 단어들 : " + currentTriedAnswerList);

			System.out.println("남은 목숨 : " + life);
			System.out.println("\n====================\n");
		} while (!answerService.isAnswer(playerTriedAnswer));
	}
}
