package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class RoadMap {
	ArrayList<Road> roads = new ArrayList<Road>();
	int x, y;
	GamePanel game;
	Rectangle hitBox;
	
	public RoadMap(int x, int y, GamePanel game) {
		this.x = x;
		this.y = y;
		this.game = game;
		init();
	}
	
	void init() {
		for(int i = 0; i < 10; i++) {
			roads.add(new Road(1,i,game,this));
		}
		hitBox = new Rectangle(x+Settlement.grid,y,Settlement.grid,Settlement.grid*10);
	}
	
	void draw(Graphics g) {
		for(int i = 0; i < roads.size(); i++) {
			roads.get(i).draw(g);
		}
		// System.out.println("Drew RoadMap");
	}
	
	void update() {
		for(int i = 0; i < roads.size(); i++) {
			roads.get(i).update();
		}
	}
}
