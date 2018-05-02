package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.TerrainManager;

public class GameObject {
	int[] vars;
	int id;
	boolean collision;
	String name;
	BufferedImage img;
	public int x, y;
	int x_s, y_s;
	GamePanel game;
	Rectangle hitBox = new Rectangle();
	public int sizeFactor = 2;

	public GameObject(int x, int y, int id, GamePanel p, TerrainManager t) {
		game = p;
		if (t != null) {
			name = t.names[id];
			vars = t.vars[id];
			this.id = id;
			this.x = x;
			this.y = y;

			try {
				if (t.vars[id][1] != TerrainManager.type_building) {
					img = ImageIO.read(this.getClass().getResourceAsStream("images/" + name + ".png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void init() {
		if (vars[2] == 1) {
			collision = true;
			hitBox = new Rectangle();
		} else {
			collision = false;
		}
	}

	public void update() {
		// if(collision) {
		// hitBox.setBounds(x_s, y_s, img.getWidth()*sizeFactor, img.getHeight()*sizeFactor);
		//
		// if(hitBox.intersects(game.p1.hitBox)) {
		// if(game.p1.up) {
		// game.p1.y += game.p1.speed*2;
		// } else if(game.p1.down) {
		// game.p1.y -= game.p1.speed*2;
		// }
		// if(game.p1.left) {
		// game.p1.x += game.p1.speed*2;
		// } else if(game.p1.right) {
		// game.p1.x -= game.p1.speed*2;
		// }
		// }
		// }
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
		//System.out.println(x_s+", "+y_s);
		updateIndoors();
	}

	public void updateIndoors() {
	}

	public void draw(Graphics g) {
		g.drawImage(img, x_s, y_s, img.getWidth() * sizeFactor, img.getHeight() * sizeFactor, null);
	}
	
	public void drawRoof(Graphics g) {}
}
