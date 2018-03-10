package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StatusManager {
	public int hp;
	GamePanel game;
	BufferedImage healthIcon;
	public StatusManager(GamePanel p) {
		game = p;
		hp = 100;
		try {
			healthIcon = ImageIO.read(this.getClass().getResourceAsStream("genImages/Health.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(25, 525, 200, 25);
		g.setColor(Color.RED);
		g.fillRect(25, 525, hp*2, 25);
		g.setColor(Color.WHITE);
		g.setFont(game.classic);
		g.drawString(""+hp+"", 50, 541);
		g.drawImage(healthIcon,30,530,15,15,null);
	}
	void update() {
		if(hp > 100) {
			hp = 100;
		} if(hp <= 0) {
			game.cap.stop();
		}
	}
}
