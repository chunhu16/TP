package Exo3;

import java.util.Random;

public class PairOfDice {
	
	private int dice1;
	private int dice2;
	private Random rand;
	/**
	 * Constructor for PairOfDice.
	 */
	public PairOfDice(){
		rand = new Random();
		roll();
	}
	/**
	 * Getter of the dices' values.
	 * @return An array of the value of the 2 dices.
	 */
	public int[] getDices(){
		int[] res = new int[] {dice1 , dice2};
		return res;
	}

	/**
	 * Get a summary of the instance.
	 * @return A summary of the instance.
	 */
	@Override 
	public String toString(){
		String res = "First dice value : ";
		res += dice1 + "\n";
		res +="Second dice value : ";
		res += dice2 + "\n";
		return res;
	}
	/**
	 * Roll the dices.
	 */
	public void roll(){
		dice1 = rand.nextInt(6) + 1;
		dice2 = rand.nextInt(6) + 1;
	}
	/**
	 * Print the number of tries necessary to get a snake eyes.
	 */
	public void snakeEyes(){
		int counter = 1;
		while(dice1 + dice2 != 2){
			roll();
			counter++;
		}
		String res = "To get a snake eyes, it took ";
		if(counter == 1){
			res += "only one try!";
		}else{
			res += " : " + counter + " tries.";
		}
		System.out.println(res);
	}
	/**
	 * Get the number of tries to get to the param i.
	 * @param i Target.
	 * @return The number of tries to get to the param i.
	 */
	public int howMany(int i){
		int counter = 1;
		while(dice1 + dice2 != i){
			roll();
			counter++;
		}
		return counter;
	}
}
