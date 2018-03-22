package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.GamePanel;
import game.Settlement;

public class Entity {
	Settlement parent;
	int x, y;
	int x_s, y_s;
	GamePanel game;
	Rectangle agroBox = new Rectangle();
	
	int type;
	
	public static final int zombie = 0, npc = 1;
	
	public Entity(int x, int y, Settlement s, boolean feral, GamePanel p) {
		parent = s;
		game = p;
		
		if(feral) {
			type = zombie;
		}
		
		this.x = x+s.x;
		this.y = y+s.y;
	}
	
	public void update() {
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
		agroBox.setBounds(x_s-50, y_s-50, 125, 125);
		if(type == zombie) {
			if(agroBox.intersects(game.p1.hitBox)) {
				if(x < game.p1.x-400) {
					x+=3;
				} else if(x > game.p1.x-400) {
					x-=3;
				} if(y < game.p1.y-300) {
					y+=3;
				} else if(y > game.p1.y-300) {
					y-=3;
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x_s, y_s, 25, 25);
		g.setColor(Color.RED);
		g.drawRect(x_s-50, y_s-50, 125, 125);
	}
}