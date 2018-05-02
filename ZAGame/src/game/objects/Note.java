package game.objects;

import java.awt.Color;
import java.awt.Graphics;

import game.GamePanel;
import game.TerrainManager;

public class Note extends GameObject {
	
	boolean read = false;

	public Note(int x, int y, int id, GamePanel p, TerrainManager t) {
		super(x, y, id, p, t);
	}
	
	@Override
	public void updateIndoors() {
		if(!game.interact && hitBox.intersects(game.p1.hitBox)) {
			game.interact = true;
			game.interactable = this;
		}
	}
	
	@Override
	public void drawRoof(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(100, 100, 600, 400);
		g.setColor(Color.GRAY);
	}
}