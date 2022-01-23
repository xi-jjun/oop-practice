package hangman.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnswerRepositoryTest {
	private final String TEST_ANSWER = "thisIsAnswer";
	private final AnswerRepository answerRepository = AnswerRepository.getInstance();

	@BeforeEach
	void setData() {
		answerRepository.setAnswer(TEST_ANSWER);
		answerRepository.save("a");
		answerRepository.save("b");
		answerRepository.save("iphone");
	}

	@Test
	void save() {
		answerRepository.save("myAnswer");
		List<String> triedAnswers = answerRepository.getCurrentTriedAnswers();

		List<String> expected = Arrays.asList("a", "b", "iphone", "myAnswer");

		Assertions.assertEquals(expected, triedAnswers);
	}

	@Test
	void getAnswer() {
		String answer = answerRepository.getAnswer();

		Assertions.assertEquals(TEST_ANSWER, answer);
	}

	@Test
	void clear() {
		answerRepository.clear();
		List<String> triedAnswers = answerRepository.getCurrentTriedAnswers();

		List<String> expected = new ArrayList<>();

		Assertions.assertEquals(expected, triedAnswers);
	}

}
