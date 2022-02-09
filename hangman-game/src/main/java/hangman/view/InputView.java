package hangman.view;

import static hangman.constants.Constant.*;

import java.util.Scanner;

import hangman.service.AnswerService;

public class InputView {
	private final Scanner console;
	private final AnswerService answerService;
	private String playerAnswer = "";

	public InputView(Scanner console, AnswerService answerService) {
		this.console = console;
		this.answerService = answerService;
	}

	public int process() throws IllegalArgumentException {
		System.out.println("알파벳 또는 정답이라 생각하시는 단어를 적어주세요!");

		if (getPlayerAnswer()) {
			int compareResult = comparePlayerAnswerToAnswer();
			return compareResult;
		}

		return ERROR_GET_ANSWER;
	}

	private int comparePlayerAnswerToAnswer() {
		if (answerService.isAnswer(playerAnswer)) {
			return IS_ANSWER;
		} else if (answerService.isContainedAnswer(playerAnswer)) {
			/**
			 * 이게 모여서 정답이 되는 경우를 AnswerService 에서 확인해줘야 함.
			 */
			return IS_EXISTED_CHAR;
		} else {
			return IS_NOT_EXISTED_CHAR;
		}
	}

	private boolean getPlayerAnswer() throws IllegalArgumentException {
		playerAnswer = console.nextLine();
		answerService.addPlayerAnswer(playerAnswer);
		return true;
	}
}
