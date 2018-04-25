package game;

import java.awt.Color;
import java.awt.Graphics;

import game.itemTypes.Item;

public class Inventory {
	GamePanel game;
	
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
			invY = 100 + i*12 - setback*12;
			
			if(inv[i] != 0) {
				if(i == game.inv_Sel) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				
				g.drawRect(125, invY, 100, 12);
				g.setColor(Color.BLACK);
				g.drawString(Item.names[i]+"(x"+inv[i]+")", 126, invY+10);
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
}
