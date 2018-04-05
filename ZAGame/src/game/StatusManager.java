package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class StatusManager {
	/*
	 *  1 DAY OF FOOD / WATER = 1440 TICKS
	 */
	public int hp, immune, food, water;
	GamePanel game;
	BufferedImage healthIcon, immuneIcon, foodIcon, waterIcon;
	Random rnd = new Random();
	
	public StatusManager(GamePanel p) {
		game = p;
		hp = 100;
		immune = 50;
		food = 5000 + rnd.nextInt(10000);
		water = 4000 + rnd.nextInt(500);
		try {
			healthIcon = ImageIO.read(this.getClass().getResourceAsStream("genImages/Health.png"));
			immuneIcon = ImageIO.read(this.getClass().getResourceAsStream("genImages/immune.png"));
			foodIcon = ImageIO.read(this.getClass().getResourceAsStream("genImages/Food.png"));
			waterIcon = ImageIO.read(this.getClass().getResourceAsStream("genImages/Water.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void draw(Graphics g) {
		g.setFont(game.classic);
		
		//HP
		g.setColor(Color.GRAY);
		g.fillRect(25, 525, 200, 25);
		g.setColor(Color.RED);
		g.fillRect(25, 525, hp*2, 25);
		g.setColor(Color.WHITE);
		g.drawString(""+hp+"", 50, 541);
		g.drawImage(healthIcon,30,530,15,15,null);
		
		//IMMUNITY
		g.setColor(Color.GRAY);
		g.fillRect(25, 490, 200, 25);
		g.setColor(Color.GREEN);
		g.fillRect(25, 490, immune*2, 25);
		g.setColor(Color.WHITE);
		g.drawString(""+immune+"", 50, 506);
		g.drawImage(immuneIcon, 30, 495, 15, 15, null);
		
		//FOOD
		g.setColor(Color.GRAY);
		g.fillRect(25, 455, 200, 25);
		g.setColor(Color.ORANGE);
		g.fillRect(25, 455, food/150, 25);
		g.setColor(Color.WHITE);
		g.drawString(getETA(food), 50, 471);
		g.drawImage(foodIcon, 30, 460, 15, 15, null);
		
		//WATER
		g.setColor(Color.GRAY);
		g.fillRect(25, 420, 200, 25);
		g.setColor(Color.BLUE);
		g.fillRect(25, 420, water/25, 25);
		g.setColor(Color.WHITE);
		g.drawString(getETA(water), 50, 436);
		g.drawImage(waterIcon, 30, 425, 15, 15, null);
	}
	void update() {
		if(hp > 100) {
			hp = 100;
		} if(hp <= 0) {
			game.cap.stop();
		} if(immune > 100) {
			immune = 100;
		} if(food > 30000) {
			food = 30000;
		} if(water > 5000) {
			water = 5000;
		}
	}
	
	void tick() {
		food--;
		water--;
		
		if(food > 5000 && water > 1500) {
			hp++;
		}
	}
	
	String getETA(int minutes) {
		if(minutes < 60) {
			return minutes+" minutes";
		} else if(minutes < 1440) {
			return (int)minutes/60 + " hours";
		} else {
			return (int)minutes/1440 + " days " + ((int)minutes/60)%24 + " hours";
		}
	}
}
