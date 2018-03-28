package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import game.itemTypes.Clothes;
import game.itemTypes.Food;
import game.itemTypes.Item;
import game.itemTypes.ItemGeneral;
import game.itemTypes.Weapon;

public class ItemManager {
	Random rnd = new Random();
	public ArrayList<Item> items = new ArrayList<Item>();
	GamePanel panel;
	ItemGeneral i = new ItemGeneral();

	public ItemManager(GamePanel p) {
		panel = p;
	}

	Item spawnItem(int id) {
		int type = i.vars[id - 1][1];
		// if(type != 0) {
		if (type == Item.clothes) {
			items.add(new Clothes(rnd.nextInt(801), rnd.nextInt(801), id, panel, i));
		} else if (type == Item.food) {
			items.add(new Food(rnd.nextInt(801), rnd.nextInt(801), id, panel, i));
		} else if (type == Item.weapon) {
			items.add(new Weapon(rnd.nextInt(801), rnd.nextInt(801), id, panel, i));
		}
		// }
		
		return items.get(items.size()-1);
	}

	void spawnItem(int id, int x, int y) {
		int type = i.vars[id - 1][1];

		if (type == Item.clothes) {
			items.add(new Clothes(x, y, id, panel, i));
		} else if (type == Item.food) {
			items.add(new Food(x, y, id, panel, i));
		} else if (type == Item.weapon) {
			items.add(new Weapon(x, y, id, panel, i));
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
