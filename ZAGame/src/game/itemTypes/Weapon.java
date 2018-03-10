package game.itemTypes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GamePanel;

//ID,TYPE,TYPE2,DMG

public class Weapon extends Item implements MouseListener{
	
	boolean equipped = false;

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
	public void handleSpecialCommand(String c) {
		if(c.equals("Equip")) {
			equipped = true;
			
			invContextMenu.remove("Equip");
			invContextMenu.add("Dequip");
			invContextMenu.remove("Drop");
		}
		if(c.equals("Dequip")) {
			equipped = false;
			
			invContextMenu.add("Equip");
			invContextMenu.remove("Dequip");
			invContextMenu.add("Drop");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
