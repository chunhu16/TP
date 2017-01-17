import java.util.Scanner;
import java.lang.IllegalArgumentException;
import java.util.Random;

/**
 * Exercice 4.3 class.
 * @author Hugo
 *
 */
public class Exo4_3 {
	/**
	 * main method launch when Exo4_3 is running.
	 * @param args argument from the main.
	 */
	public static void main(String[] args) {
		// To launch the asked program uncomment the following line
		//program();
		
		// The following line launch the program to have the snake eyes
		throw_dices(2);
	}
	/**
	 * Program that ask a target and gives the number of try that was
	 * necessary to have the sum of two random numbers from a dice equals to this target.
	 */
	public static void program(){
		get_target();
	}
	/**
	 * Ask the user the target and gives the number of try that was
	 * necessary to have the sum of two random numbers from a dice equals to this target.
	 */
	public static void get_target() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What's the target? (the target must be in [2;12])");
		int target = 0;
		try{
			target = scan.nextInt();
		}
		catch(Exception e){
			scan.close();
			throw new IllegalArgumentException("Not a number");
		}
		if(target<2 || target >12){
			scan.close();
			throw new IllegalArgumentException("Target not in [2;12]");
		}
		throw_dices(target);
		scan.close();
	}
	/**
	 * Give the result of the throw of a dice.
	 * @return the result of the result of a dice.
	 */
	public static int dice(){
		Random random = new Random();
		int range = 6 - 1 + 1 ;
		int res = 1 + (int)(range * random.nextDouble());
		return res;
	}
	/**
	 * Depending of the target, gives the number of try that was
	 * necessary to have the sum of two random numbers from a dice equals to this target.
	 * @param target Target to get to.
	 */
	public static void throw_dices(int target){
		int count = 0;
		int total = 0;
		while(total != target){
			total = dice() + dice();
			count++;
		}
		if(target != 2){
			System.out.println("It took "+count+" try to get to "+target+".");
		} else {
			System.out.println("It took "+count+" try to get the snake eyes.");
		}
	}
}

