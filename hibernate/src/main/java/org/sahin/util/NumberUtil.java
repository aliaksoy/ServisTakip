package org.sahin.util;

import java.util.Random;

public class NumberUtil {
	private static Random random=new Random();
	
	
	public static Random getRandom(int num){
		return new Random(num);
	}
	public static int getRandomInt(int num){
		return (int) (random.nextDouble()*num);
	}
}
