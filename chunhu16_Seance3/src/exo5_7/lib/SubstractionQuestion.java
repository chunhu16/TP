package exo5_7.lib;

import exo5_7_interf.IntQuestion;


public class SubstractionQuestion implements IntQuestion {

	private int a;
	private int b;
	/**
	 * Constructor oh a SubstractionQuestion.
	 */
    public SubstractionQuestion() {
    	do{
    		a = (int)(Math.random() * 50 + 1);
    		b = (int)(Math.random() * 50);
    	} while(a - b < 0);
    }
    
	@Override
	public String getQuestion() {
		String str = a + " - " + b + " = ";
		return str;
	}

	@Override
	public int getCorrectAnswer() {
		return a - b ;
	}


}
