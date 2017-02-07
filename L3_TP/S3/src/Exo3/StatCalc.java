package Exo3;

public class StatCalc {
	
	private int length;
	private int sum;
	private int squareSum;
	private int max;
	private int min;
	/**
	 * Constructor of StatCalc.
	 */
	public StatCalc(){
		length = 0;
		sum = 0;
		squareSum = 0;
	}
	/**
	 * Enter the informations oh the param i.
	 * @param i Interger to enter in the data set.
	 */
	public void enter(int i){
		if(length == 0){
			min = i;
			max = i;
		} else {
			if(i > max)
				max = i;
			if(i < min)
				min = i;
		}
		length++;
		sum += i;
		squareSum += i*i;
	}
	/**
	 * Getter of the length of the data set.
	 * @return
	 */
	public int getCount(){
		return length;
	}
	/**
	 * Getter of the sum of the data set.
	 * @return
	 */
	public int getSum(){
		return sum;
	}
	/**
	 * Getter of the mean of the data set.
	 * @return The mean of the data set.
	 * @throws Exception In case of empty data set.
	 */
	public double getMean() throws Exception{
		if(length == 0){
			throw new Exception("DataSet empty.");
		}
		return (double) sum/length;
	}
	/**
	 * Getter of the standard deviation of the data set.
	 * @return The standard deviation of the data set.
	 * @throws Exception In case of empty data set.
	 */
	public double getStandardDeviation() throws Exception{
		if(length == 0){
			throw new Exception("DataSet empty.");
		}
		double mean = getMean();
		double res = (double) Math.sqrt(squareSum/length - mean * mean);
		return res;
	}
	/**
	 * Get the max value of the data set.
	 * @return The max value of the data set.
	 * @throws Exception In case of empty data set.
	 */
	public int getMax() throws Exception{
		if(length == 0){
			throw new Exception("DataSet empty.");
		}
		return max;
	}
	/**
	 * Get the min value of the data set.
	 * @return The min value of the data set.
	 * @throws Exception In case of empty data set.
	 */
	public int getMin() throws Exception{
		if(length == 0){
			throw new Exception("DataSet empty.");
		}
		return min;
	}
}
