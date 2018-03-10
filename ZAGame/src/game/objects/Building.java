package game.objects;

import javax.imageio.ImageIO;
import game.GamePanel;
import game.TerrainManager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Building extends Object {
	
	BufferedImage floor;
	BufferedImage roof;
	
	boolean indoors;
	
	public Building(int x, int y, int id, GamePanel p, TerrainManager t) {
		super(x, y, id, p, t);
		
		sizeFactor = 4;
		try {
			floor = ImageIO.read(this.getClass().getResourceAsStream("images/"+name+"_floor.png"));
			roof = ImageIO.read(this.getClass().getResourceAsStream("images/"+name+"_roof.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateIndoors() {
		hitBox.setBounds(x_s, y_s, floor.getWidth()*sizeFactor, floor.getHeight()*sizeFactor);
		
		if(this.hitBox.intersects(game.p1.hitBox)) {
			indoors = true;
		} else { indoors = false; }
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(floor, x_s,y_s,floor.getWidth()*sizeFactor, floor.getHeight()*sizeFactor, null);
		
		if(!indoors) {
			g.drawImage(roof, x_s,y_s,roof.getWidth()*sizeFactor, roof.getHeight()*sizeFactor, null);
		}
	}
}
