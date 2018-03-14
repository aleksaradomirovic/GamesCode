package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.objects.Object;

public class Road extends Object{
	BufferedImage img;
	int x_s, y_s;
	int x,y;
	GamePanel game;
	public Road(int x, int y, GamePanel p) {
		super(x, y, 0, p, null);
		this.x = x*80;
		this.y = y*80;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("terrain/roads/2lane_1.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		game = p;
	}
	
	@Override
	public void update() {
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x_s,y_s,(int)(img.getWidth()*7.5),img.getHeight()*8,null);
	}
}
