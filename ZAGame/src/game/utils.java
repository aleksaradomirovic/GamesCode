package game;

import java.awt.Color;
import java.awt.Graphics;

public class utils {
	public void drawBorderedRect(int x,int y,int w,int h, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, w, h);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
	}
	
	static int[] thirty1 = new int[] {0,2,4,6,9,11};
	
	public static boolean dayPastMonth(int day, int month) {
		if(arrayContains(thirty1, month)) {
			if(day > 31) {
				return true;
			}
		} else if(month == 1) {
			if(day > 28) {
				return true;
			}
		} else {
			if(day > 30) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean arrayContains(int[] array, int obj) {
		boolean r = false;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == obj) {
				r = true;
			}
		}
		return r;
	}
	
	public static String stringTime(int min, int hr) {
		String rHR, rMN;
		if(min < 10) {
			rMN = "0"+min;
		} else {
			rMN = ""+min;
		}
		
		if(hr < 10) {
			rHR = "0"+hr;
		} else {
			rHR = ""+hr;
		}
		
		return rHR+":"+rMN;
	}
}