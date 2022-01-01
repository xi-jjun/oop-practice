package hangman.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class WordServiceTest {
	private WordService wordService = new WordService();
	@Test
	void 랜덤한_단어_생성() throws IOException {
		String randomWord = wordService.getRandomWord();
		System.out.println("randomWord = " + randomWord);
	}
}
