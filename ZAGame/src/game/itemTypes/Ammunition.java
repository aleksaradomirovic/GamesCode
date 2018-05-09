package game.itemTypes;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class Ammunition extends Item {

	public Ammunition(int x, int y, int id, GamePanel p, ItemGeneral i) {
		super(x, y, id, p, names[id-1], i);
		addGeneralContext();
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("image/Ammo1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleSpecialCommand(String c) {
		x = game.p1.x + 400;
		y = game.p1.y + 300;

		game.items.items.add(this);
		game.p1.inventory.remove(id-1, info.vars[id-1][3]);
	}
}
