
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.objects.Building;
import game.objects.NullObjectSpace;
import game.objects.Object;

public class Settlement {
	public static final int grid = 80;
	Rectangle settlementBox;
	GamePanel game;
	TerrainManager parent;
	
	Object[][] gridLayout;
	int size;
	int x, y;
	
	public Settlement(int x, int y, int size, GamePanel p, TerrainManager t) {
		parent = t;
		game = p;
		this.x = x;
		this.y = y;
		this.size = size;
		settlementBox = new Rectangle(x,y,size*grid,size*grid);
		gridLayout = new Object[size][size];
		
		init();
	}
	
	Dimension fromBoxGrid(int x, int y, int setX, int setY) {
		return new Dimension(setX+x*grid, setY+y*grid);
	}
	
	void init() {
		int roadY = 0;
		
		for(int i = 0; i < size; i++) {
			gridLayout[size/2][roadY] = new Road(size/2,roadY,game);
			roadY++;
		}
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(gridLayout[j][i] == null && adjacentToRoad(j,i)) {
					addHouse(2, j, i);
				}
			}
		}
	}
	
	void update() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(gridLayout[j][i] != null)
					gridLayout[j][i].update();
			}
		}
	}
	void draw(Graphics g) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(gridLayout[j][i] != null && gridLayout[j][i] instanceof Road)
					gridLayout[j][i].draw(g);
			}
		}
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(gridLayout[j][i] != null && gridLayout[j][i] instanceof Building)
					gridLayout[j][i].draw(g);
			}
		}
	}
	
	boolean adjacentToRoad(int x, int y) {
		if(gridLayout[x][y] == null) {
			boolean r = false;
			if(x > 0 && gridLayout[x-1][y] instanceof Road) {
				r = true;
			} if(x < size - 1 && gridLayout[x+1][y] instanceof Road) {
				r = true;
			} if(y > 0 && gridLayout[x][y-1] instanceof Road) {
				r = true;
			} if(y < size - 1 && gridLayout[x][y+1] instanceof Road) {
				r = true;
			}
			return r;
		}
		return false;
	}
	
	void addHouse(int size,int x,int y) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(j+x < this.size && i+y < this.size) {
					if(gridLayout[j+x][i+y] == null)
						gridLayout[j+x][i+y] = new NullObjectSpace(game);
					System.out.println("Added null settlement object");
				}
			}
		}
		gridLayout[x][y] = new Building(this.x+(x*grid), this.y+(y*grid),1,game,parent);
	}
}
