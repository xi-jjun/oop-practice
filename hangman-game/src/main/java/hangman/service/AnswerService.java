package hangman.service;

import hangman.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;

public class AnswerService {
	private final AnswerRepository answerRepository = AnswerRepository.getInstance();
	private final String TO_BE_ANSWERED = "_";
	private final int MIN_TRY_ANSWER_LENGTH = 1;
	private String answer;

	public void addPlayerAnswer(String playerAnswer) {
		/**
		 * playerAnswer 가 answer 와 길이가 같지 않고, -> 정답을 알고 있어야 함. 어떻게? Repo에서 가져와야 함
		 * 알파벳 1개가 아닐 때 게임 진행에 있어서 부적절한 입력이다.
		 * 올바른 입력 예시)
		 * answer = iphone
		 * proper input : i, a, r, coffee, galaxy...
		 * improper input : aa, avb, fi, aaaaa, bbbbbbb, DUPLICATED_ANSWER
		 * ++ 입력 받을 때 공백 모두 제거하자!!
		 *
		 * !추가사항!
		 * 영어가 아닌 입력에 대해서 예외처리를 해줘야 함
		 */

		checkPlayerAnswer(playerAnswer);
		answerRepository.save(playerAnswer);
	}

	/**
	 * validate whether input is contained to answer
	 */
	public boolean isContainedAnswer(String input) {
		return answer.contains(input);
	}

	/**
	 * @param input
	 * @return if input is equal to answer, then true. Else return false
	 */
	public boolean isAnswer(String input) {
		return answer.equals(input);
	}

	/**
	 * init reset method. controller 에서 맨 처음으로 호출하면 될 것 같은데..?
	 *
	 * @param answer : each stage answer
	 */
	public void reset(String answer) {
		this.answer = answer;
		answerRepository.clear();
		answerRepository.setAnswer(answer);
	}

	/**
	 * @return '_ _ a _ _' formatted String
	 */
	public String getCurrentAnswerState() {
		List<String> answerState = new ArrayList<>();
		for (String answerAlphabet : answer.split("")) {
			if (isMatchedAnswer(answerAlphabet)) {
				answerState.add(answerAlphabet);
			} else {
				answerState.add(TO_BE_ANSWERED);
			}
		}

		return String.join(" ", answerState);
	}

	/**
	 * 시도한 정답 리스트를 출력하기 위한 메서드. OutputView 객체에 String 형태로 던질 수 있게 했다.
	 *
	 * @return currentTriedAnswer list joined by ", "
	 */
	public String getCurrentTriedAnswerList() {
		return String.join(", ", answerRepository.getCurrentTriedAnswers());
	}

	/**
	 * 역할
	 * 1. check duplicate answer when save
	 * 2. check duplicate character when change triedAnswer to stateAnswer
	 *
	 * @param input
	 * @return if 'input' is in getCurrentTriedAnswers, then return true. else return false
	 */
	private boolean isMatchedAnswer(String input) {
		return answerRepository.getCurrentTriedAnswers()
				.stream().anyMatch(ans -> ans.equals(input));
	}

	private boolean isInvalidAnswerLength(String playerAnswer) {
		return playerAnswer.length() != answer.length()
				&& playerAnswer.length() != MIN_TRY_ANSWER_LENGTH;
	}

	private boolean isEnglish(String input) {
		return input.chars().allMatch(Character::isLetter);
	}

	private void checkPlayerAnswer(String playerAnswer) {
		if (isInvalidAnswerLength(playerAnswer)) {
			throw new IllegalArgumentException(playerAnswer + " is Invalid Answer Length\n");
		} else if (isMatchedAnswer(playerAnswer)) {
			throw new IllegalArgumentException(playerAnswer + " is Duplicated Player Answer\n");
		} else if (!isEnglish(playerAnswer)) {
			throw new IllegalArgumentException(playerAnswer + " is not english\n");
		}
	}
}
