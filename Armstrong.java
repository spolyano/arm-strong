import java.util.ArrayList;

public class Armstrong {
	private ArrayList<Long> resultList = new ArrayList<Long>();
	
	//only for statistic
	private long programmTime;	
	
	public void calculate(int power) {	
		// set power list 0 1 8 27 etc.
		long[] powerList = new long[10];
		for (int i = 0; i < 10; i++)
			powerList[i] = (int) Math.pow(i, power);
		
		// counter 0-9 to break off the limit
		int period = 10;
		
		long result = 0;
		long temp = 0;
		long minValue = (long) Math.pow(10,power-1) - 1;
		long maxValue = (long) Math.pow(10,power) - 1;
		for (long checkNum = maxValue; checkNum > minValue; checkNum--) {	
			
			// set period to 0 each 10 numbers
			period--;
			if (period == 0) period = 10;
			
			// transform 867 --> 8-6-7
			// set result pro digit-power
			result = 0;
			temp = checkNum;
			for (int k = 0; k < power; k++) {
				result+= powerList[(int) (temp % 10)];
				if(result > checkNum) break; // break if already too big
				temp /= 10;
			}
			
			if (result < checkNum && period < 10) {
				//break if too small, it's 11 because first step period--
				checkNum -= (period - 1);
				period = 11;
			}
			else if (result == checkNum) resultList.add(checkNum);	
		}
				
	}
 
	public void calculateAll(int power) {
		programmTime = System.currentTimeMillis();
		for (int i = 1; i <= power; i++) calculate(i);
		programmTime = System.currentTimeMillis() - programmTime;
	}
	
	public void printResultList() {
		for (int i = 0; i < resultList.size(); i++)
			System.out.print(i + ": " + resultList.get(i) + "\n");
		System.out.println("time: "+ programmTime + "ms");
	}
	
}