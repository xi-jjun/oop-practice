package hangman.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomWordService {
	private String WORD_FILE_PATH = "src/main/resources/words.md";
	private List<String> words = new ArrayList<>();

	public String getRandomWord() throws IOException {
		List<String> words = getWordsInFile(WORD_FILE_PATH);
		String randomWord = generateRandomWord(words);
		return randomWord;
	}

	private String generateRandomWord(List<String> words) {
		int randomIndex = (int) (Math.random() * words.size());
		return words.get(randomIndex);
	}

	private List<String> getWordsInFile(String filePath) throws IOException {
		if (!words.isEmpty()) {
			return words;
		}

		BufferedReader wordReader = new BufferedReader(new FileReader(filePath));
		addWords(wordReader);
		wordReader.close();

		return words;
	}

	private void addWords(BufferedReader wordReader) throws IOException {
		String word;
		while ((word = wordReader.readLine()) != null) {
			words.add(word);
		}
	}
}
