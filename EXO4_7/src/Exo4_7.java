import java.util.Random;
import java.util.Scanner;

public class Exo4_7 {

	public static void main(String[] args) {
		init();
		ask();
		end();
	}
	/**
	 * Array of the first set of int for the sum.
	 */
	private static int[] sum1;
	/**
	 * Array of the second set of int for the sum.
	 */
	private static int[] sum2;
	/**
	 * Array of the answers of the user.
	 */
	private static int[] answer;
	/**
	 * Initiate the 3 arrays for quizz.
	 */
	private static void init(){
		Random random = new Random();
		int range = 100 - 1 + 1;
		sum1 = new int[10];
		sum2 = new int[10];
		answer = new int[10];
		for(int i = 0 ; i < 10 ; i++){
			int f = (int)( 1 + range * random.nextDouble());
			int s = (int)( 1 + range * random.nextDouble());
			sum1[i] = f;
			sum2[i] = s;
		}
	}
	/**
	 * Ask the 10 questions to the user.
	 */
	private static void ask(){
		Scanner scan = new Scanner(System.in);
		for(int i = 0 ; i < 10 ; i++){
			int answeri;
			int num = i+1;
			System.out.print(num+")  "+sum1[i]+" + "+sum2[i]+ " = ");
			try{
				answeri = scan.nextInt();
			}
			catch(Exception e){
				scan.close();
				throw new IllegalArgumentException("Not a number");
			}
			answer[i] = answeri;
		}
		scan.close();
	}
	/**
	 * Print the summary of the quizz.
	 */
	private static void end(){
		System.out.println("Summary :");
		int count = 0;
		for(int i = 0; i < 10 ; i++){
			int result = sum1[i]+sum2[i];
			int num = i+1;
			System.out.print(num+") "+sum1[i]+" + "+sum2[i]+" = "+result);
			if(answer[i] == result){
				System.out.println(" good!");
				count++;
			}else{
				System.out.println("  not "+answer[i]+" !");
			}
		}
		int score = count*10;
		System.out.println("\nYour score is : "+score+"/100");
	}
}
