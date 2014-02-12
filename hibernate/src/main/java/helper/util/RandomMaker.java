package helper.util;

import java.util.Random;

public class RandomMaker {
	
	static Random random=new Random();
	
	public static int getRandom(int num){
		
		
		
		int result=1;
		return result=(int) (random.nextDouble()*num);
	}

public static int getPositiveOrNegativeRandom(int num){
		
		
		
		int result=1;
		result=(int) (random.nextDouble()*10);
		if(result<5)
			result=((int) (random.nextDouble()*num))*-1;
		else
			
		 result=(int) (random.nextDouble()*num);
		
		return result;
	}
	
}
