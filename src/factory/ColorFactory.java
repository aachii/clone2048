package factory;

import java.util.Random;

public class ColorFactory {
	private String[] colors;
	
	public ColorFactory() {
		final int ARR_SIZE = 8192;
		Random rand = new Random();
        colors = new String[ARR_SIZE+1];
        for (int i = 1; i <= ARR_SIZE; i++) {
        	String str = Integer.toHexString(rand.nextInt(16777215)); // #FFFFFF is 16777214
        	colors[i] = "#"+String.format("%6s", str).replace(' ', '0');
        }
        colors[0] = "#e2e2e2";
	}
	
	public String getColor(int num) {
		return colors[num];
	}
}
