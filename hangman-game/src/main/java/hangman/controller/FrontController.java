package hangman.controller;

import hangman.repository.AnswerRepository;
import hangman.service.AnswerService;
import hangman.service.RandomWordService;

import java.io.IOException;

public class FrontController {
	private String answer;
	private final RandomWordService randomWordService;
	private AnswerService answerService;

	public FrontController(RandomWordService randomWordService) {
		this.randomWordService = randomWordService;
	}

	public void run() throws IOException {
		System.out.println("Hangman Game Start!!");

		System.out.println("단어를 고르는 중입니다...");
		answer = randomWordService.getRandomWord(); // 고르는 시점 자체를 처음으로 돌리자
		// MainController start
		// _ _ _ _ _
		// 출력할 때 맞춘 알파벳은 반영하여 출력해야 함 -> AnswerService 로 넘겨야 하나? 문자열을 _ _ _ a _ 이런식으로 넘겨버리자.
//		answerService = new AnswerRepository(answer); // repo 말고 service  로 기능 제공해야 함
		for (String ans : answer.split("")) {
			System.out.print("_ ");
		}
		System.out.println();

		// print 목숨

		// 반복 루프
//		알파벳 또는 정답이라 생각하시는 단어를 적어주세요!
//		a
//		a는 없습니다ㅜㅜ
//		_ _ _ _ _
//		시도한 알파벳이나 단어들 : a
//		남은 목숨 : 4
	}
}
