import java.util.ArrayList;


public class Exo3_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exo();
	}

	private static void exo(){
		int min = 0+1;
		int max = 10000;
		int maxDivisors = 0;
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = min ; i < max ; i++){
			int nbDivs = nb_divisors(i);
			if(maxDivisors < nbDivs){
				values = new ArrayList<Integer>();
				values.add((Integer)i);
				maxDivisors = nbDivs;
			} 
			else if(maxDivisors == nbDivs){
				values.add((Integer)i);
			}
		}
		System.out.println("Among integers between 1 and 10000,");
		System.out.println("The maximum number of divisors was "+maxDivisors);
		System.out.println("Numbers with that many divisors include:");
		for(Integer i : values){
			System.out.println(i.toString());
		}
	}
	
	private static int nb_divisors(int n){
		int count = 1;
		for(int i = 1 ; i < n ; i++){
			if(n%i == 0){
				count++;
			}
		}
		return count;
	}
}
