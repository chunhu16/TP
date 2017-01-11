
import java.util.Scanner;

public class Exo3_4 {

	public static void main(String[] args) {
		print_lines_from_input();
	}
	
	private static void print_lines_from_input(){
		Scanner scan = new Scanner(System.in);
		System.out.println("What's the sentence?");
		String sen = scan.nextLine();
		String[] lines = sen.split(" ");
		int size = lines.length;
		for(int i = 0; i < size ; i++){
			System.out.println(lines[i]);
		}
	}

}
