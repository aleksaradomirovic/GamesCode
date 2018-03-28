package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.itemTypes.Clothes;
import game.itemTypes.Item;
import game.itemTypes.Weapon;

public class Player {
	BufferedImage body, legs, head;
	public Rectangle hitBox = new Rectangle(389,285,22,30);
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public int x = 400,y = 300;
	public boolean up, down, left, right;
	public Weapon weapon;
	public Clothes shirt, pants;
	public int speed = 3;
	GamePanel game;
	utils u = new utils();
	public Player(GamePanel p) {
		try {
			body = ImageIO.read(this.getClass().getResourceAsStream("genImages/Body.png"));
			legs = ImageIO.read(this.getClass().getResourceAsStream("genImages/Legs.png"));
			head = ImageIO.read(this.getClass().getResourceAsStream("genImages/Head.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		game = p;
	}
	
	void initPlayer() {
		//TODO Add any pre-startup commands here (inventory gear, etc)
		inventory.add(game.items.spawnItem(6));
		inventory.add(game.items.spawnItem(5));
	}
	
	void update() {
		if(up) {
			y-= speed;
		} if(down) {
			y+= speed;
		} if(left) {
			x-= speed;
		} if(right) {
			x+= speed;
		}
	}
	
	void draw(Graphics g) {
		//draw body
		g.drawImage(head,395,286,10,8,null);
		g.drawImage(legs,393,304,14,12,null);
		g.drawImage(body,389,294,22,14,null);
		if(shirt != null) {
			g.drawImage(shirt.worn,389,294,22,14,null);
		}
		if(pants != null) {
			g.drawImage(pants.worn,393,304,14,12,null);
		}
		
		for(int i = 0; i < inventory.size(); i++) {
			inventory.get(i).drawIfEquipped(g);
		}
	}
	
	void drawInventory(Graphics g, Font f) {
		if(game.inv) {
			g.setColor(Color.GRAY);
			g.setFont(f);
			g.fillRect(100, 50, 600, 500);
			g.setColor(Color.BLACK);
			g.drawRect(100, 50, 600, 500);
			
			g.drawString("Shirt:", 400, 100);
			g.drawString("Pants:", 500, 100);
			
			int invY = 100;
			int equippedSetback = 0;
			boolean equipList;
			for(int i = 0; i < inventory.size(); i++) {
				equipList = false;
				
				invY = 100 + i*12 - equippedSetback*12;
				
				if(i == game.inv_Sel) 
					g.setColor(Color.RED);
				else
					g.setColor(Color.BLACK);
				
				if(inventory.get(i) != shirt && inventory.get(i) != pants) {
					g.drawString(inventory.get(i).name, 125, invY);
				} else {
					equipList = true;
					if(inventory.get(i) == shirt) {
						g.drawString(inventory.get(i).name, 400, 112);
					}
					if(inventory.get(i) == pants) {
						g.drawString(inventory.get(i).name, 500, 112);
					}
					equippedSetback++;
				}
				
				if(game.invContext && game.inv_Sel == i) {
					int contY = invY - (inventory.get(i).invContextMenu.size())*10;
					int contX = 200;
					
					if(equipList) {
						contY = 112 - inventory.get(i).invContextMenu.size()*10;
						if(inventory.get(i) == shirt) {
							contX = 450;
						} if(inventory.get(i) == pants) {
							contX = 550;
						}
					}
					
					for(int j = 0; j < inventory.get(i).invContextMenu.size(); j++) {
						u.drawBorderedRect(contX, contY, 100, 16, g);
						if(game.invContext_Sel == j) {
							g.setColor(Color.RED);
						} else {
							g.setColor(Color.BLACK);
						}
						
						g.drawString(inventory.get(i).invContextMenu.get(j), contX + 5, contY + 12);
						
						contY+=16;
					}
					
					if(game.enterContext) {
						inventory.get(i).handleCommand(inventory.get(i).invContextMenu.get(game.invContext_Sel));
						game.invContext = false;
						System.out.println("Handled item");
						game.enterContext = false;
					}
				}
				
			}
		}
	}
	
	public void testInventory() {
		System.out.println("Inventory Test Handler Trace:");
		for(int i = 0; i < inventory.size(); i++) {
			System.out.println("Item: "+inventory.get(i).name);
		}
	}
	
	public void setShirt(Clothes c) {
		if(shirt != null)
			shirt.handleCommand("Undress");
		shirt = c;
	}
	public void setPants(Clothes c) {
		if(pants != null)
			pants.handleCommand("Undress");
		pants = c;
	}
	public void setWeapon(Weapon w) {
		if(weapon != null) {
			game.frame.frame.removeMouseListener(weapon);
			weapon.handleCommand("Dequip");
		}
		weapon = w;
		game.frame.frame.addMouseListener(weapon);
	}
}
