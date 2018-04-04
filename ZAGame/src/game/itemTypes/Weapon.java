package game.itemTypes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GamePanel;

//ID,TYPE,TYPE2,DMG,COOLDOWN

public class Weapon extends Item {
	
	public boolean equipped = false, attack = false;
	
	int cooldown = 0;

	public Weapon(int x, int y, int id, GamePanel p, ItemGeneral i) {
		super(x, y, id, p, names[id-1], i);
		
		addGeneralContext();
		invContextMenu.add("Equip");
	}
	
	@Override
	public void drawIfEquipped(Graphics g) {
		if(equipped)
			g.drawImage(img,402,293,img.getWidth()*2,img.getHeight()*2,null);
	}
	
	@Override
	public void update() {
		hitBox.setBounds(x_s, y_s, img.getWidth() * 2, img.getHeight() * 2);
		if (hitBox.intersects(game.p1.hitBox)) {
			game.ds = true;
			if (game.pickup) {
				game.p1.inventory.add(this);
				game.items.items.remove(this);
			}
		}
		
//		cooldown--;
//		if(cooldown < 60) {
//			attack = false;
//		}
	}
	
	@Override
	public void handleSpecialCommand(String c) {
		if(c.equals("Equip")) {
			game.p1.setWeapon(this);
			equipped = true;
			
			invContextMenu.remove("Equip");
			invContextMenu.add("Dequip");
			invContextMenu.remove("Drop");
			
			cooldown = info.vars[id-1][4];
		}
		if(c.equals("Dequip")) {
			equipped = false;
			
			invContextMenu.add("Equip");
			invContextMenu.remove("Dequip");
			invContextMenu.add("Drop");
		}
	}	
}
