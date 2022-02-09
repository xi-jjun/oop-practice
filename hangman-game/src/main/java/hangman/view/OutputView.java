package hangman.view;

public class OutputView {
	public void viewCurrentAnswerState(String answerState) {
		System.out.println(answerState);
	}

	public void viewCurrentLife(int life) {
		System.out.println("남은 목숨 : " + life);
	}

	public void viewTriedAnswerList(String currentTriedAnswerList) {
		System.out.println("시도한 알파벳이나 단어들 : " + currentTriedAnswerList);
	}

	public void viewEachStageResult(String currentAnswerState, int life, String currentTriedAnswerList) {
		viewCurrentAnswerState(currentAnswerState);
		viewTriedAnswerList(currentTriedAnswerList);
		viewCurrentLife(life);
		System.out.println("\n====================\n");
	}
}
