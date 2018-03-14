package game.objects;

import java.awt.Graphics;

import game.GamePanel;

public class NullObjectSpace extends Object {

	public NullObjectSpace(GamePanel p) {
		super(0, 0, 0, p, null);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
}
