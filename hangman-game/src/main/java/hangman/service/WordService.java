package hangman.service;

import hangman.repository.WordRepository;

import java.io.IOException;
import java.util.List;

public class WordService {
	private WordRepository wordRepository = WordRepository.getInstance();
	private String WORD_FILE_PATH = "src/main/resources/words.md";

	public String getRandomWord() throws IOException {
		List<String> words = wordRepository.getWordsInFile(WORD_FILE_PATH);
		String randomWord = generateRandomWord(words);
		return randomWord;
	}

	private String generateRandomWord(List<String> words) {
		int randomIndex = (int) (Math.random() * words.size());
		return words.get(randomIndex);
	}
}
