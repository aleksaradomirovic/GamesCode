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
}