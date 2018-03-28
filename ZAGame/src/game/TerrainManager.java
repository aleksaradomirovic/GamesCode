package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import game.objects.Object;

public class TerrainManager {
	
	Random rnd = new Random();
	
	//id, type, collision?
	
	public static final int objects = 2;
	public static final int type_normal = 0, type_building = 1;
	
	public String[] names = new String[]
			{"Mailbox","House1"};
	public int[][] vars = new int[objects][3];
	
	ArrayList<Object> worldObjects = new ArrayList<Object>();
	
	ArrayList<Dimension> chunkloc = new ArrayList<Dimension>();
	ArrayList<Chunk> terrain = new ArrayList<Chunk>();
	ArrayList<Settlement> settlements = new ArrayList<Settlement>();
	
	GamePanel game;
	
	public TerrainManager(GamePanel p) {
		game = p;
		init();
	}
	
	public void genChunk(int x, int y, boolean set) {
		if(!chunkloc.contains(new Dimension(x,y))) {
			game.generate = true;
			terrain.add(new Chunk(game,x,y, set));
			chunkloc.add(new Dimension(x,y));
			System.out.println("Items in terrain: "+terrain.size());
		}
		game.generate = false;
	}
	
	void init() {
		vars[0] = new int[] {0,type_normal,1};
		vars[1] = new int[] {0,type_building,0};
	}
	
	void update() {
		for(int i = 0; i < worldObjects.size(); i++) {
			worldObjects.get(i).update();
		}
		for(int i = 0; i < terrain.size(); i++) {
			terrain.get(i).update();
		}
	}
	
	void draw(Graphics g) {
		for(int i = 0; i < terrain.size(); i++) {
			terrain.get(i).draw(g);
		}
		for(int i = 0; i < worldObjects.size(); i++) {
			worldObjects.get(i).draw(g);
		}
	}
	void drawRoof(Graphics g) {
		for(int i = 0; i < terrain.size(); i++) {
			terrain.get(i).drawRoof(g);
		}
	}
	
	void drawFoliage(Graphics g) {
		for(int i = 0; i < terrain.size(); i++) {
			terrain.get(i).drawFoliage(g);
		}
	}
	
	void generateAround(Chunk c) {
		genChunk(c.x+Chunk.width, c.y, false);
		genChunk(c.x-Chunk.width, c.y, false);
		
		genChunk(c.x, c.y+Chunk.width, false);
		genChunk(c.x, c.y-Chunk.width, false);
	}
}
