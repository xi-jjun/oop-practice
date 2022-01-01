package hangman.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class WordRepositoryTest {
	private String WORD_FILE_PATH = "src/main/resources/words.md";

	@Test
	void fileReadTest() throws IOException {
		WordRepository wordRepository = WordRepository.getInstance();
		List<String> words = wordRepository.getWordsInFile(WORD_FILE_PATH);

		words.stream().forEach(System.out::println);
	}
}
