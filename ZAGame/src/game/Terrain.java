package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Terrain {
	//Default terrain texture size is 50
	public static final int grass = 0, snow = 1;
	public static final String[] typeNames = new String[]
			{"grass","snow"};
	
	final int foliageDensity = 2;
	BufferedImage img;
	int type;
	String typeName;
	Chunk parent;
	int xpos, ypos;
	ArrayList<Foliage> foliage = new ArrayList<Foliage>();
	
	public Terrain(int type, Chunk c, int xpos, int ypos) {
		this.type = type;
		this.typeName = typeNames[type];
		parent = c;
		this.xpos = xpos*200;
		this.ypos = ypos*200;
		
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("terrain/"+typeName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < foliageDensity; i++) {
			foliage.add(new Foliage(this,0,grass));
		}
	}
	
	void update() {
		for(int i = 0; i < foliage.size(); i++) {
			foliage.get(i).update();
		}
	}
	void draw(Graphics g) {
		//System.out.println("Drawn Terrain");
		g.drawImage(img, parent.x_s+xpos, parent.y_s+ypos,200,200,null);
	}
	void drawFoliage(Graphics g) {
		for(int i = 0; i < foliage.size(); i++) {
			foliage.get(i).draw(g);
		}
	}
}
