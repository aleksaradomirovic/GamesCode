package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import game.objects.Object;

public class Settlement {
	public static final int grid = 50;
	Rectangle settlementBox;
	GamePanel game;
	
	Object[][] gridLayout;
	int size;
	
	public Settlement(int x, int y, int size, GamePanel p) {
		game = p;
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
		
		for(int i = 0; i < size; i++, roadY++) {
			gridLayout[(int)(size/2)][0] = new Road((int)(size/2),roadY,game);
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
				if(gridLayout[j][i] != null)
					gridLayout[j][i].draw(g);
			}
		}
	}
}
