import java.util.ArrayList;
import java.util.Scanner;

public class Armstrong {
	private ArrayList<Long> resultList = new ArrayList<Long>();
	
	//only for statistic
	private long programmTime;	
	
	public void calculate(int power) {	
		// set power list 0 1 8 27 etc.
		long[] powerList = new long[10];
		for (int i = 0; i < 10; i++)
			powerList[i] = (long) Math.pow(i, power);
		
		// counter 0-9 to break off the limit
		int period = 10;
		
		int numList[] = new int[power];
		for (int i = 0; i < power; i++)
			numList[i] = 9;
		
		long result = 0;
		long minValue = (long) Math.pow(10,power-1) - 1;
		long maxValue = (long) Math.pow(10,power) - 1;
		for (long checkNum = maxValue; checkNum > minValue; checkNum--) {	
			
			// set skip period to 0 each 10 numbers
			period--;
			if (period == 0) period = 10;

			// set result pro digit-power
			result = 0;
			for (int k = 0; k < power; k++) {
				result+= powerList[numList[k]];
				if(result > checkNum) break; // break if already too big
			}
			
			
			// transform 867 --> 8-6-7
			for (int i = power - 1; i >= 0; i--){
				if (numList[i] == 0) numList[i] = 9;
				else{
					numList[i] -= 1;
					break;
				}
			}
			
			// skip if too low
			if (result < checkNum && period < 10){
				numList[power - 1] = 0;
				
				checkNum -= (period - 1);
				period = 11;
			}
			else if (result == checkNum) resultList.add(checkNum);
			
			
		}
				
	}
 
	public void calculateAll() {
		
		Scanner keyRead = new Scanner(System.in);
		System.out.print("Enter N: ");
		int power = keyRead.nextInt();
		keyRead.close();
		
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