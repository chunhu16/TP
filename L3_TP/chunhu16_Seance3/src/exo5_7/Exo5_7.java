package exo5_7;

import java.util.Random;
import java.util.Scanner;

public class Exo5_7 {
	
	public static void main(String[] args) {
		IntQuestion[] questions = init();
		int[] answer = ask(questions);
		summary(questions, answer);
	}
	/**
	 * Give the summary, and result of the quizz.
	 * @param questions Questions asked.
	 * @param answer Answers from the user.
	 */
	private static void summary(IntQuestion[] questions, int[] answer) {
		int i = 0;
		int count = 0;
		System.out.println("\n\nSummary :");
		for(IntQuestion question : questions){
			int j = i+1;
			System.out.print(j+ ") " + question.getQuestion() + " = " + question.getCorrectAnswer());
			if(answer[i] == question.getCorrectAnswer()){
				System.out.println("  Correct!");
				count += 10;
			} else {
				System.out.println("  Not " + answer[i] + "!");
			}
			i++;
		}
		System.out.println("\nScore : "+count + "/100");
	}
	/**
	 * Ask the questions.
	 * @param questions The questions to ask.
	 * @return The answer from the user.
	 */
	private static int[] ask(IntQuestion[] questions) {
		int[] answer =  new int[questions.length];
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Here's the quizz :");
		int i = 1;
		for(IntQuestion question : questions){
			boolean ok = false;
			do{
				try{
					System.out.println(i + ") "+ question.getQuestion());
					
					int ans = scan.nextInt();
					answer[i-1] = ans;
					i++;
					ok = true;
				} catch (Exception ex){
					System.out.println("Please enter an integer.");
					scan = new Scanner(System.in);
				} 
			} while(!ok);
		}
		scan.close();
		return answer;
	}
	/**
	 * Initiate the questions.
	 * @return The questions.
	 */
	private static IntQuestion[] init() {
		IntQuestion bigQuestion = new IntQuestion() {
		    public String getQuestion() {
		        return "What is the answer to the ultimate question " +
		                 " of life, the universe, and everything?";
		    }
		    public int getCorrectAnswer() {
		        return 42;
		    }
		};
		
		IntQuestion[] questions = new IntQuestion[10];
		questions[9] = bigQuestion;
		
		Random rand = new Random();
		for(int i = 0 ; i < 9 ; i++){
			boolean isAddQuestion = rand.nextBoolean();
			if(isAddQuestion){
				questions[i] = new AdditionQuestion();
			}else{
				questions[i] = new SubstractionQuestion();
			}
		}
		return questions;
	}
}