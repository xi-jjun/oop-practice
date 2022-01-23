package hangman.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class RandomWordServiceTest {
	private final RandomWordService randomWordService = new RandomWordService();
	private String WORD_FILE_PATH = "src/main/resources/words.md";

	@Test
	void getWordList() throws IOException {
		List<String> wordList = randomWordService.getWordsInFile(WORD_FILE_PATH);
		wordList.forEach(System.out::println);
	}

	@Test
	void getAnswer() throws IOException {
		String answer = randomWordService.getRandomWord();
		System.out.println("answer = " + answer);
	}

}
