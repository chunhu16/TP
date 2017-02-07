import java.util.Scanner;

public class Exo5_2 {
	/**
	 * Ask the user to fill in a StatCalc to get all results possible.
	 * @param args Arguments.
	 */
	public static void main(String[] args) {
		StatCalc stat = new StatCalc();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the numbers one by one (to stop enter 0)");
		int entry = scan.nextInt();
		while(entry != 0){
			stat.enter(entry);
			entry = scan.nextInt();
		}
		String res = "Length : "+ stat.getCount();
		try{
			res += "\nSum : " + stat.getSum();
			res += "\nMean : " + stat.getMean();
			double sv = stat.getStandardDeviation();
			res += "\nStandard Deviation Variation : " + sv;
			res += "\nMax : " + stat.getMax();
			res += "\nMin : " + stat.getMin();
		} catch (Exception ex){
			res = ex.getMessage();
		}
		System.out.println(res);
		scan.close();
	}

}