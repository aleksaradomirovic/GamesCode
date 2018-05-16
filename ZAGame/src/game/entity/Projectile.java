package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.GamePanel;

public class Projectile {
	int damage; // DAMAGE OF THE PROJECTILE
	int velocity; // SPEED BASED ON PIXELS/SECOND (1 METER ~ 25 PIXELS)
	int range; // SECONDS BEFORE PROJECTILE DROPS OFF
	double angle;
	
	GamePanel game;
	
	int x, y;
	int x_s, y_s;
	
	public Projectile(int dmg, int speed, int distance, int x, int y, int direction, GamePanel p) {
		damage = dmg;
		velocity = (int)speed/60;
		range = distance;
		angle = Math.toRadians(direction);
		game = p;
	}
	
	public void update() {
		x += Math.cos(angle) * velocity;
		y -= Math.sin(angle) * velocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
		g.drawLine(x_s, y_s, x_s += Math.cos(angle) * 20, y_s -= Math.sin(angle) * 20);
	}
}
