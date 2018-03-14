package game;

import java.util.Random;

import game.objects.Building;

public class ItemTable {
	int[] spawn;
	Building parent;
	GamePanel game;
	ItemManager items;

	int rx, ry;

	public ItemTable(int id, GamePanel p, Building o) {
		spawn = p.sID.spawns[id];
		// System.out.println(spawn.length);
		parent = o;

		rx = parent.floor.getWidth() * parent.sizeFactor;
		ry = parent.floor.getHeight() * parent.sizeFactor;

		game = p;
		items = p.items;

		init();
	}

	void init() {
		Random rnd = new Random();

		for (int i = 0; i < 5; i++) {
			items.spawnItem(spawn[rnd.nextInt(spawn.length)], rnd.nextInt(rx) + parent.x, rnd.nextInt(ry) + parent.y);
		}
	}
}
