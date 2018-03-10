package game.itemTypes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class Clothes extends Item {
	// ID, TYPE, TYPE2, ARMOR
	
	static final int shirt = 0, pants = 1;
	
	public BufferedImage worn;
	
	public Clothes(int x, int y, int id, GamePanel p, ItemGeneral i) {
		super(x, y, id, p, names[id-1], i);
		invContextMenu.add("Wear");
		addGeneralContext();
		
		try {
			worn = ImageIO.read(this.getClass().getResourceAsStream("image/"+id+"_wear.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleSpecialCommand(String command) {
		if(command.equals("Wear")) {
			// System.out.println("Worn");
			if(vars[id-1][2] == shirt) {
				game.p1.setShirt(this);
			} else if(vars[id-1][2] == pants) {
				game.p1.setPants(this);
			} else {
				System.out.println("Bad 'wear' call, undefined type");
			}
			invContextMenu.remove("Wear");
			invContextMenu.remove("Drop");
			invContextMenu.add("Undress");
		}
		if(command.equals("Undress")) {
			if(vars[id-1][2] == shirt) {
				game.p1.shirt = null;
			} else if(vars[id-1][2] == pants) {
				game.p1.pants = null;
			} else {
				System.out.println("Bad 'wear' call, undefined type");
			}
			invContextMenu.remove("Undress");
			invContextMenu.add("Wear");
			invContextMenu.add("Drop");
		}
	}
}
