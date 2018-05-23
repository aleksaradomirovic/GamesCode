
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import game.entity.Entity;
import game.objects.Building;
import game.objects.NullObjectSpace;
import game.objects.GameObject;

public class Settlement {
	public static final int grid = 80;
	Rectangle settlementBox;
	GamePanel game;
	TerrainManager parent;
	boolean set;
	
	GameObject[][] gridLayout;
	public int x, y;
	public int w, h;
	
	public ArrayList<Entity> zombies = new ArrayList<Entity>();
	
	public Settlement(int x, int y, int height, int width, GamePanel p, TerrainManager t, boolean set) {
		parent = t;
		game = p;
		this.x = x;
		this.y = y;
		h = height;
		w = width;
		settlementBox = new Rectangle(x,y,width*grid,height*grid);
		gridLayout = new GameObject[10][10];
		this.set = set;
		
		initRoads();
		initBuildings();
		
		parent.settlements.add(this);
	}
	
	Dimension fromBoxGrid(int x, int y, int setX, int setY) {
		return new Dimension(setX+x*grid, setY+y*grid);
	}
	
	public void printLocation() {
		game.cheats.println("Settlement: ("+x+","+y+")");
	}
	
	void initRoads() {
		if(x == 0 && y == 0) {
			gridLayout[0][1] = new Road(0,1,game,this);
		}
	}
	
	void initBuildings() {
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] == null && adjacentToRoad(j,i)) {
					addHouse(2, j, i);
				}
			}
		}
		
		for(int i = 0; i < 5; i++) {
			zombies.add(new Entity(new Random().nextInt(w*grid), new Random().nextInt(h*grid),this,true,game));
		}
	}
	
	void update() {
		//System.out.println("Updated");
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] != null)
					gridLayout[j][i].update();
			}
		}
		
		for(int i = 0; i < zombies.size(); i++) {
			zombies.get(i).update();
		}
	}
	void draw(Graphics g) {
		//g.setColor(Color.BLACK);
		//g.fillRect(x, y, w*grid, h*grid);
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] != null && gridLayout[j][i] instanceof Road)
					gridLayout[j][i].draw(g);
			}
		}
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] != null && gridLayout[j][i] instanceof Building)
					gridLayout[j][i].draw(g);
			}
		}
	}
	
	void drawRoof(Graphics g) {
		for(int i = 0; i < zombies.size(); i++) {
			zombies.get(i).draw(g);
		}
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] != null && gridLayout[j][i] instanceof Building)
					gridLayout[j][i].drawRoof(g);
			}
		}
	}
	
	boolean adjacentToRoad(int x, int y) {
		if(gridLayout[x][y] == null) {
			boolean r = false;
			if(x > 0 && gridLayout[x-1][y] instanceof Road) {
				r = true;
			} if(x < w - 1 && gridLayout[x+1][y] instanceof Road) {
				r = true;
			} if(y > 0 && gridLayout[x][y-1] instanceof Road) {
				r = true;
			} if(y < h - 1 && gridLayout[x][y+1] instanceof Road) {
				r = true;
			}
			return r;
		}
		return false;
	}
	
	void extendRoad(int x, int y) {
		/*  0
		 * 1 2
		 *  3
		 */
		boolean ext = false;
		
		if(x > 0 && x < 9 && gridLayout[x-1][y] instanceof Road && gridLayout[x+1][y] == null) {
			gridLayout[x+1][y] = new Road(x+1, y, game, this);
			ext = true;
		}
		if(x > 0 && x < 9 && gridLayout[x+1][y] instanceof Road && gridLayout[x-1][y] == null) {
			gridLayout[x-1][y] = new Road(x-1, y, game, this);
			ext = true;
		}
		if(y > 0 && x < 9 && gridLayout[x][y-1] instanceof Road && gridLayout[x][y+1] == null) {
			gridLayout[x][y+1] = new Road(x, y+1, game, this);
			ext = true;
		}
		if(y > 0 && x < 9 && gridLayout[x][y+1] instanceof Road && gridLayout[x][y-1] == null) {
			gridLayout[x][y-1] = new Road(x, y-1, game, this);
			ext = true;
		}
	}
	
	void addHouse(int size,int x,int y) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(j+x < this.w && i+y < this.h) {
					if(gridLayout[j+x][i+y] == null)
						gridLayout[j+x][i+y] = new NullObjectSpace(game);
					System.out.println("Added null settlement object");
				}
			}
		}
		gridLayout[x][y] = new Building(x, y,1,game,parent,this);
	}
}
