
public class AdditionQuestion implements IntQuestion {

	private int a;
	private int b;
	/**
	 * Constructor of an AdditionQuestion.
	 */
    public AdditionQuestion() {
        a = (int)(Math.random() * 50 + 1);
        b = (int)(Math.random() * 50);
    }

	@Override
	public String getQuestion() {
		String str = a + " + " + b + " = ";
		return str;
	}

	@Override
	public int getCorrectAnswer() {
		return a + b ;
	}

}
