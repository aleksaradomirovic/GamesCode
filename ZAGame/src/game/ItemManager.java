package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import game.itemTypes.Ammunition;
import game.itemTypes.Clothes;
import game.itemTypes.Food;
import game.itemTypes.Item;
import game.itemTypes.ItemGeneral;
import game.itemTypes.Tool;
import game.itemTypes.Weapon;

public class ItemManager {
	Random rnd = new Random();
	public ArrayList<Item> items = new ArrayList<Item>();
	GamePanel panel;
	ItemGeneral i = new ItemGeneral();

	public ItemManager(GamePanel p) {
		panel = p;
	}

	public Item spawnItem(int id, boolean spawnInMap) {
		int type = i.vars[id - 1][1];
		Item r = null;
		
		if(type == Item.clothes) {
			r = new Clothes(0,0,id,panel,i);
		} else if(type == Item.food) {
			r = new Food(0,0,id,panel,i);
		} else if(type == Item.weapon) {
			r = new Weapon(0,0,id,panel,i);
		} else if(type == Item.tool) {
			r = new Tool(0,0,id,panel,i);
		} else if(type == Item.ammo) {
			r = new Ammunition(0,0,id,panel,i);
		}
		
		if(spawnInMap && r != null) {
			items.add(r);
		} 
		
		return r;
	}

	void spawnItem(int id, int x, int y) {
		int type = i.vars[id - 1][1];

		if (type == Item.clothes) {
			items.add(new Clothes(x, y, id, panel, i));
		} else if (type == Item.food) {
			items.add(new Food(x, y, id, panel, i));
		} else if (type == Item.weapon) {
			items.add(new Weapon(x, y, id, panel, i));
		} else if (type == Item.tool) {
			items.add(new Tool(x, y, id, panel, i));
		}
	}

	void update() {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}
	}

	void draw(Graphics g) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).draw(g);
		}
	}
}
