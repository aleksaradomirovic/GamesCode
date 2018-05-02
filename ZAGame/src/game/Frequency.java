package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Frequency {
	Random rnd = new Random();
	String freq;
	int cooldown = 0;
	boolean broadcast = false;
	String[] message;
	int i;
	
	public Frequency() {
		freq = rnd.nextInt(8) + 121 + "." + rnd.nextInt(10) + "00";
	}
	
	void update() {
		if(message == null) {
			message = Radio.generateBroadcast(freq);
			i = 0;
			cooldown = 0;
		} else {
			cooldown++;
			if(cooldown > 300) {
				i++;
				cooldown = 0;
			}
		}
		if(i >= message.length) {
			message = null;
			i = 0;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		try {
			g.drawString(message[i], 1, 500);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
