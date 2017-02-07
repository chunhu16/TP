package Exo3;

public class Exo5_3 {
	/**
	 * Print the mean and standard variation for 10000 experiments for each results (2 to 12).
	 * @param args Arguments.
	 */
	public static void main(String[] args) {
		PairOfDice dice = new PairOfDice();
		StatCalc stat;
		System.out.println("Dices          Average          Standard Deviation");
		for(int i = 2 ; i < 13 ; i++){
			stat = new StatCalc();
			runTenThousandTime(i, dice, stat);
			String res = "";
			try{
			res += " " + i + "            ";
			res += stat.getMean() + "               ";
			double d = stat.getStandardDeviation();
			int intPart = (int) d;
			double decimalPart = d - intPart;
			int pp = (int)(decimalPart*10000);
			double mm = (double)((double) pp /10000);
			double standardVariation = intPart + mm;
			res += standardVariation;
			} catch (Exception ex){
				res += ex.getMessage() + "\nError at total on dice ";
				res += i;
			}
			System.out.println(res);
		}
	}
	/**
	 * Fill the StatCalc with 10000 experiments of getting to the target.
	 * @param target Target to get to.
	 * @param dice Dices used for the experiment.
	 * @param stat StatCalc to fill in.
	 */
	private static void runTenThousandTime(int target, PairOfDice dice, StatCalc stat){
		for (int i = 0 ; i < 10000 ; i++){
			int count = dice.howMany(target);
			stat.enter(count);
			dice.roll();
		}
	}
}