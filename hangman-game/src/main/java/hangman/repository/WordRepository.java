package hangman.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {
	private static WordRepository instance = new WordRepository();

	private List<String> words = new ArrayList<>();

	private WordRepository() {

	}

	public static WordRepository getInstance() {
		if (instance == null) {
			return new WordRepository();
		}
		return instance;
	}

	public List<String> getWordsInFile(String filePath) throws IOException {
		if (!words.isEmpty()) {
			return words;
		}

		BufferedReader wordReader = new BufferedReader(new FileReader(filePath));
		addWords(wordReader);
		wordReader.close();

		return words;
	}

	public List<String> getWords() {
		return words;
	}

	private void addWords(BufferedReader wordReader) throws IOException {
		String word;
		while ((word = wordReader.readLine()) != null) {
			words.add(word);
		}
	}
}
