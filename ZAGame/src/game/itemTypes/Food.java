package game.itemTypes;

import game.GamePanel;

public class Food extends Item {
	
	/*
	 * ID, TYPE, TYPE2, HPHEAL
	 */
	
	String type;
	
	public static final int food = 0, water = 1, other = 2;
	
	public Food(int x, int y, int id, GamePanel p, ItemGeneral i) {
		super(x, y, id, p, names[id-1], i);
		addGeneralContext();
		
		if(vars[id-1][2] == 0) {
			type = "Eat";
		} else if(vars[id-1][2] == 1) {
			type = "Drink";
		} else {
			type = "Use";
		}
		
		invContextMenu.add(type);
	}
	
	@Override
	public void handleSpecialCommand(String c) {
		if(c.equals(type)) {
			game.status.hp += vars[id-1][3];
			game.p1.inventory.remove(this);
		}
	}
}
