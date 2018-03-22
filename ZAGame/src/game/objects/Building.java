package game.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.ItemTable;
import game.Settlement;
import game.TerrainManager;

public class Building extends Object {

	public BufferedImage floor, roof;

	ItemTable table;

	boolean indoors;

	public Building(int x, int y, int id, GamePanel p, TerrainManager t, Settlement s) {
		super(x, y, id, p, t);
		
		this.x = x*Settlement.grid + s.x;
		this.y = y*Settlement.grid + s.y;

		sizeFactor = 4;
		try {
			floor = ImageIO.read(this.getClass().getResourceAsStream("images/" + name + "_floor.png"));
			roof = ImageIO.read(this.getClass().getResourceAsStream("images/" + name + "_roof.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(id == 1) {
			table = new ItemTable(0, game, this);
		}
	}

	@Override
	public void updateIndoors() {
		hitBox.setBounds(x_s, y_s, floor.getWidth() * sizeFactor, floor.getHeight() * sizeFactor);

		if (this.hitBox.intersects(game.p1.hitBox)) {
			indoors = true;
		} else {
			indoors = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("drew house: "+x+","+y);
		g.drawImage(floor, x_s, y_s, floor.getWidth() * sizeFactor, floor.getHeight() * sizeFactor, null);
	}
	
	@Override
	public void drawRoof(Graphics g) {
		if (!indoors) {
			g.drawImage(roof, x_s, y_s, roof.getWidth() * sizeFactor, roof.getHeight() * sizeFactor, null);
		}
	}
}
