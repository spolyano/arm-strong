import java.util.ArrayList;

public class Armstrong {
	private ArrayList<Long> resultList = new ArrayList<Long>();
	
	//only for statistic
	private long programmTime;	
	private long programmTimeAll;	

	public void calculate(int power) {
		programmTime = System.currentTimeMillis();	
		
		// set power list 0 1 8 27 etc.
		int[] powerList = new int[10];
		for (int i = 0; i < 10; i++)
			powerList[i] = (int) Math.pow(i, power);
		
		// counter 0-9 to break off the limit
		int period = -1;
		
		long result = 0;
		
		long minValue = (long) Math.pow(10,power-1);
		long maxValue = (long) Math.pow(10,power);
		
		period = 10;
		for (long checkNum = maxValue - 1; checkNum > minValue - 1; checkNum--) {	
			
			// set period to 0 each 10 numbers
			period--;
			if (period == 0) period = 10;
			
			// transform 867 --> 8-6-7
			// set result pro digit-power
			result = 0;
			long temp = checkNum;
			for (int k = 0; k < power; k++) {
				result+= powerList[(int) (temp % 10)];
				temp /= 10;
			}
			
			if (result < checkNum) {
				checkNum-= period;
				period = 10;
			}
			else if (result == checkNum) resultList.add(checkNum);	
		}
		
		programmTime = System.currentTimeMillis() - programmTime;
		programmTimeAll += programmTime;
		
	}
	
	public void calculateAll(int power) {
		for (int i = 0; i <= power; i++) calculate(i);	
	}
	
	public void printResultList() {
		for (int i = 0; i < resultList.size(); i++)
			System.out.print(i + ": " + resultList.get(i) + "\n");
		System.out.println("time: "+ programmTimeAll + "ms");
	}
	
}
