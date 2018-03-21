package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Chunk {
	
	public static final int chunkSize = 4;
	public static final int width = chunkSize*200;
	public static final int settlementChance = 10;
	
	Settlement chunkSettlement;
	
	public Terrain[][] chunk = new Terrain[chunkSize][chunkSize];
	GamePanel game;
	int x,y;
	int x_s, y_s;
	Rectangle renderBox = new Rectangle();
	public Chunk(GamePanel p, int x, int y) {
		this.x = x; this.y = y;
		game = p;
		initChunkRandom();
	}
	
	void initChunkRandom() {
		int biome = new Random().nextInt(2);
		for(int i = 0; i < chunkSize; i++) {
			for(int j = 0; j < chunkSize; j++) {
				chunk[j][i] = new Terrain(biome,this,j,i);
				// System.out.println(i*chunkSize+j);
			}
		}
		if(new Random().nextInt(100) < settlementChance) {
			chunkSettlement = new Settlement(x,y, 2 * new Random().nextInt(5)+2,3 + 2 * new Random().nextInt(2), game, game.terrain);
		}
	}
	
	void update() {
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
		renderBox.setBounds(x_s, y_s, chunkSize*200, chunkSize*200);
		for(int i = 0; i < chunkSize; i++) {
			for(int j = 0; j < chunkSize; j++) {
				chunk[j][i].update();
			}
		}
		if(chunkSettlement != null)
			chunkSettlement.update();
	}
	void draw(Graphics g) {
		//System.out.println("Processed Chunk");
		if(renderBox.intersects(-100,-100,1000,800)) {
			for(int i = 0; i < chunkSize; i++) {
				for(int j = 0; j < chunkSize; j++) {
					chunk[j][i].draw(g);
				}
			}
			game.terrain.generateAround(this);
		}
		if(chunkSettlement != null)
			chunkSettlement.draw(g);
	}
	void drawRoof(Graphics g) {
		if(chunkSettlement != null)
			chunkSettlement.drawRoof(g);
	}
	
	void drawFoliage(Graphics g) {
		if(renderBox.intersects(-100,-100,1000,800)) {
			for(int i = 0; i < chunkSize; i++) {
				for(int j = 0; j < chunkSize; j++) {
					chunk[j][i].drawFoliage(g);
				}
			}
		}
	}
}
