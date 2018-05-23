package game;

import java.awt.Graphics;
import java.util.Random;

public class Radio {
	Random rnd = new Random();
	Frequency f1 = new Frequency("89.5");
	Frequency m1 = new Frequency("129.5");
	Frequency c1 = new Frequency("9"+rnd.nextInt(10)+"."+((rnd.nextInt(5)*2)+1));
	Frequency c2 = new Frequency("9"+rnd.nextInt(10)+"."+((rnd.nextInt(5)*2)+1));
	
	void draw(Graphics g) {
		g.drawString(f1.band+rnd.nextInt(10)+rnd.nextInt(10), 126, 123);
		g.drawString(c1.band+rnd.nextInt(10)+rnd.nextInt(10), 126, 135);
		g.drawString(c2.band+rnd.nextInt(10)+rnd.nextInt(10), 126, 147);
	}
}
