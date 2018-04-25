package game;

import java.awt.Color;
import java.awt.Graphics;

import game.itemTypes.Item;

public class Inventory {
	GamePanel game;
	Item selectedItem;
	
	public Inventory(GamePanel p) {
		game = p;
	}
	
	public int[] inv = new int[Item.items];
	
	void draw(Graphics g) {
		g.setFont(GamePanel.classic);
		//update
		for(int i = 0; i < inv.length; i++) {
			if(inv[i] < 0) {
				inv[i] = 0;
			}
		}
		
		//draw
		int setback = 0;
		int invY;
		
		g.setColor(Color.GRAY);
		g.fillRect(100, 100, 600, 400);
		g.setColor(Color.BLACK);
		g.drawRect(100, 100, 600, 400);
		
		for(int i = 0; i < inv.length; i++) {
			invY = 113 + i*12 - setback*12;
			
			if(inv[i] != 0) {
				if(i == game.inv_Sel) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				
				g.drawRect(125, invY, 100, 12);
				g.setColor(Color.BLACK);
				g.drawString(Item.names[i]+"(x"+inv[i]+")", 126, invY+10);
				
				if(i == game.inv_Sel && game.invContext) {
					selectedItem = game.items.spawnItem(i+1, false);
					int contextSize = selectedItem.invContextMenu.size();
					
					int contY = invY-10 - contextSize*8;
					
					//draw inventory context
					for(int j = 0; j < contextSize; j++) {
						contY+=16;
						
						utils.drawBorderedRect(225, contY, 100, 16, g);
						
						if(game.invContext_Sel == j)
							g.setColor(Color.RED);
						
						g.drawString(selectedItem.invContextMenu.get(j), 230, contY + 12);
					}
					
//					if(game.enterContext) {
//						selectedItem.handleCommand(selectedItem.invContextMenu.get(game.invContext_Sel));
//						game.enterContext = false;
//						game.invContext = false;
//						System.out.println("Handled item");
//						game.inv_Sel = lowestValue();
//					}
				}
			} else 
				setback++;
		}
	}
	
	public void add(int id) {
		inv[id]++;
	}
	public void add(int id, int amount) {
		inv[id]+=amount;
	}
	
	public void remove(int id) {
		inv[id]--;
	}
	public void remove(int id, int amount) {
		inv[id]-=amount;
	}
	
	public boolean has(int id) {
		if(inv[id] > 0) {
			return true;
		}
		return false;
	}
	
	public int size() {
		return inv.length;
	}
	
	public int lowestValue() {
		int r = 0;
		while(r < inv.length && !has(r)) {
			r++;
		}
		if(!(r < inv.length)) {
			r = 0;
		}
		return r;
	}
	
	public int highestValue() {
		int r = inv.length - 1;
		while(r >= 0 && !has(r)) {
			r--;
		}
		if(r < 0) {
			r = 0;
		}
		return r;
	}
	
	void enterHandler() {
		if(game.inv && game.invContext) {
			selectedItem.handleCommand(selectedItem.invContextMenu.get(game.invContext_Sel));
			game.enterContext = false;
			game.invContext = false;
			System.out.println("Handled item");
			game.inv_Sel = lowestValue();
		}
	}
}
