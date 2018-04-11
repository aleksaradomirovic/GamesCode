package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Foliage {
	public static final int normal = 0;
	int x, y;
	BufferedImage img;
	String[] normalFoliageNames = new String[] { "Tree" };
	String name;
	Terrain parent;
	int xv, yv;
	Rectangle hitbox;

	public Foliage(Terrain t, int type, int id) {
		parent = t;

		xv = new Random().nextInt(200);
		yv = new Random().nextInt(200);

		if (type == normal) {
			name = normalFoliageNames[id];
		}

		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("terrain/foliage/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		hitbox = new Rectangle(parent.parent.x + parent.xpos + xv, parent.parent.y + parent.ypos + yv, img.getWidth() * 5, img.getHeight() * 5);
	}

	void update() {
		x = parent.parent.x_s + parent.xpos + xv;
		y = parent.parent.y_s + parent.ypos + yv;
	}

	void draw(Graphics g) {
		ArrayList<Settlement> settlements = parent.parent.game.terrain.settlements;
		
		boolean d = true;
		for(int i = 0; i < settlements.size(); i++) {
			if(hitbox.intersects(settlements.get(i).settlementBox)) {
				d = false;
			}
		}
		
		for(int i = 0; i < parent.parent.game.terrain.terrain.size(); i++) {
			RoadMap selectedRoadMap = parent.parent.game.terrain.terrain.get(i).roads;
			if(selectedRoadMap != null && hitbox.intersects(selectedRoadMap.hitBox)) {
				d = false;
			}
		}
		
		if(d)
			g.drawImage(img, x,y,img.getWidth()*5,img.getHeight()*5,null);
	}
}
