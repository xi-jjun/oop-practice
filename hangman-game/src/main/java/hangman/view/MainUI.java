package hangman.view;

import static hangman.constants.Constant.*;

import hangman.service.AnswerService;
import hangman.service.RandomWordService;

import java.io.IOException;
import java.util.Scanner;

public class MainUI {
	private final int INIT_LIFE = 5;
	private final Scanner console = new Scanner(System.in);
	private final AnswerService answerService = new AnswerService();
	private final RandomWordService randomWordService = new RandomWordService();
	private final OutputView outputView = new OutputView();
	private final InputView inputView = new InputView(console, answerService);
	private int life;
	private boolean gameOver;
	private String answer;
	private String playerAnswer;
	private int processResult;

	public void process() throws IOException {
		init();

		String currentAnswerState = answerService.getCurrentAnswerState();
		outputView.viewCurrentAnswerState(currentAnswerState);
		outputView.viewCurrentLife(life);

		do {
			processResult = inputView.process();

			if (processResult == ERROR_GET_ANSWER) {
				System.out.println("다시 입력해주세요!");
				continue;
			} else if (processResult == IS_ANSWER) {
				System.out.println("정답입니다!");
			} else if (processResult == IS_EXISTED_CHAR) {
				System.out.println(playerAnswer + "는 있습니다!");
			} else if (processResult == IS_NOT_EXISTED_CHAR) {
				System.out.println(playerAnswer + "는 없습니다ㅜ");
				decreaseLifeCount();
				if (gameOver) {
					return;
				}
			}

			showStageResult();
		} while (processResult != IS_ANSWER);
	}

	private void showStageResult() {
		String currentAnswerState = answerService.getCurrentAnswerState();
		String currentTriedAnswerList = answerService.getCurrentTriedAnswerList();
		outputView.viewEachStageResult(currentAnswerState, life, currentTriedAnswerList);
	}

	private void decreaseLifeCount() {
		if (--life <= 0) {
			System.out.println("Game Over");
			gameOver = true;
		}
	}

	private void init() throws IOException {
		System.out.println("Hangman Game Start!!");
		life = INIT_LIFE; // life reset
		gameOver = false;

		System.out.println("단어를 고르는 중입니다...");
		answer = randomWordService.getRandomWord();
		answerService.reset(answer); // set answer in AnswerRepo
	}

}
