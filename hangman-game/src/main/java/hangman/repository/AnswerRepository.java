package hangman.repository;

import java.util.ArrayList;
import java.util.List;

public class AnswerRepository {
	private static final AnswerRepository instance = new AnswerRepository();
	private List<String> currentTriedAnswers = new ArrayList<>();
	private String answer;

	private AnswerRepository() {

	}

	public static AnswerRepository getInstance() {
		return instance;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void save(String playerAnswer) {
		currentTriedAnswers.add(playerAnswer);
	}

	public List<String> getCurrentTriedAnswers() {
		return currentTriedAnswers;
	}

	public void clear() {
		currentTriedAnswers.clear();
	}
}
