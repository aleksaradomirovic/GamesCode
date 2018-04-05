package game.itemTypes;

import java.awt.image.BufferedImage;
import game.GamePanel;

public class Tool extends Item {
	// ID, TYPE, TYPE2, ARMOR
	
	static final int shirt = 0, pants = 1;
	
	public BufferedImage worn;
	
	public Tool(int x, int y, int id, GamePanel p, ItemGeneral i) {
		super(x, y, id, p, names[id-1], i);
		addGeneralContext();
	}
}
