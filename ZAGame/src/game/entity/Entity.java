package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.Settlement;

public class Entity {
	Settlement parent;
	int x, y;
	int x_s, y_s;
	GamePanel game;
	Rectangle agroBox = new Rectangle();
	Rectangle hitBox = new Rectangle();
	BufferedImage zom;
	
	int type;
	
	int hitTick = 0;
	
	public static final int zombie = 0;
	
	public Entity(int x, int y, Settlement s, boolean feral, GamePanel p) {
		parent = s;
		game = p;
		
		if(feral) {
			type = zombie;
		}
		
		try {
			zom = ImageIO.read(this.getClass().getResourceAsStream("zombie_pixelart.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.x = x+s.x;
		this.y = y+s.y;
	}
	
	public void update() {
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
		agroBox.setBounds(x_s-50, y_s-50, 125, 125);
		hitBox.setBounds(x_s,y_s,25,25);
		if(type == zombie) {
			if(agroBox.intersects(game.p1.hitBox)) {
				if(x < game.p1.x+400) {
					x+=3;
				} if(x > game.p1.x+400) {
					x-=3;
				} if(y < game.p1.y+300) {
					y+=3;
				} if(y > game.p1.y+300) {
					y-=3;
				}
			}
			
			if(hitBox.intersects(game.p1.hitBox) && hitTick < 0) {
				if(game.p1.attack) {
					parent.zombies.remove(this);
				} else {
					game.status.hp -= 5;
					hitTick = 90;
				}
			} else {
				hitTick--;
			}
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(zom, x_s, y_s, 24, 35, null);
	}
}
