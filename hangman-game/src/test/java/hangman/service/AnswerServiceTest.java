package hangman.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerServiceTest {
	private final AnswerService answerService = new AnswerService();

	@BeforeEach
	void init() {
		// set answer
		answerService.reset("hello");

		// set user input data : tried answer list
		answerService.addPlayerAnswer("a");
		answerService.addPlayerAnswer("b");
		answerService.addPlayerAnswer("e");
	}

	@Test
	@DisplayName("저장하기")
	void save() {
		// given
		String playerInput = "c";

		// when
		answerService.addPlayerAnswer(playerInput);

		// then
		String currentTriedAnswerList = answerService.getCurrentTriedAnswerList();
		assertTrue(currentTriedAnswerList.contains(playerInput));
	}

	@Test
	@DisplayName("이미 입력했던 정답을 입력할 경우 IllegalArgumentException 발생")
	void duplicatedSave() {
		assertThrows(IllegalArgumentException.class, () -> {
			answerService.addPlayerAnswer("a");
		});
	}

	@Test
	@DisplayName("적절하지 않은 길이의 입력이 들어왔을 때 IllegalArgumentException")
	void invalidLengthPlayerAnswer() {
		String playerInput = "ab"; // hello 는 5글자. 따라서 입력은 무조건 1 or 5 글자여야 함.
		assertThrows(IllegalArgumentException.class, () -> {
			answerService.addPlayerAnswer(playerInput);
		});
	}

	@Test
	@DisplayName("현재까지 맞춘 정답의 상태를 문자열 형태로 반환하는 테스트")
	void showCurrentAnswerState() {
		String currentAnswerState = answerService.getCurrentAnswerState();

		System.out.print("현재까지 맞춘 정답의 상태 : ");
		assertEquals("_ e _ _ _", currentAnswerState);
		System.out.println(currentAnswerState);
	}

	@Test
	@DisplayName("현재까지 입력했던 정답 리스트 확인하는 테스트")
	void showCurrentTriedAnswerList() {
		String triedAnswerList = answerService.getCurrentTriedAnswerList();

		assertEquals("a, b, e", triedAnswerList);
	}
}
