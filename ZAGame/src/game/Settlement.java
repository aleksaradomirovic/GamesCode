
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
	int x, y;
	int w, h;
	
	public Settlement(int x, int y, int height,int width, GamePanel p, TerrainManager t) {
		parent = t;
		game = p;
		this.x = x;
		this.y = y;
		h = height;
		w = width;
		settlementBox = new Rectangle(x,y,width*grid,height*grid);
		gridLayout = new Object[width][height];
		
		init();
	}
	
	Dimension fromBoxGrid(int x, int y, int setX, int setY) {
		return new Dimension(setX+x*grid, setY+y*grid);
	}
	
	void init() {
		int roadY = 0, roadX = 1;
		
		for(int j = 0; j < (w-1)/2; j++) {
			roadY = 0;
			for(int i = 0; i < h; i++) {
				System.out.println(roadX +","+ roadY);
				
				gridLayout[roadX][roadY] = new Road(1,roadY,game);
				roadY++;
			}
			roadX+=2;
		}
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] == null && adjacentToRoad(j,i)) {
					addHouse(2, j, i);
				}
			}
		}
	}
	
	void update() {
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(gridLayout[j][i] != null)
					gridLayout[j][i].update();
			}
		}
	}
	void draw(Graphics g) {
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
		gridLayout[x][y] = new Building(this.x+(x*grid), this.y+(y*grid),1,game,parent);
	}
}
