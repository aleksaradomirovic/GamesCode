package game.itemTypes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.utils;

public class Item {
	/*
	 * id, type (0 generic, 1 clothes, 2 food), sectype, othervars [3-9]
	 */

	public static final int clothes = 1, food = 2, weapon = 3, tool = 4;

	int x, y;

	public int id;
	int x_s, y_s;
	BufferedImage img;
	GamePanel game;
	Rectangle hitBox = new Rectangle();
	public ArrayList<String> invContextMenu = new ArrayList<String>();
	utils u = new utils();
	ItemGeneral info;

	public final static int items = 9;
	public static int vars[][] = new int[items][10];

	public final static String[] names = new String[] { "Green Parka", "Jeans", "Red Shirt", "Soda", "Antibiotics",
			"Pickaxe", "Potato", "Watch", "Rag" };

	public String name;

	public Item(int x, int y, int id, GamePanel p, String name, ItemGeneral i) {
		info = i;
		init();

		this.x = x;
		this.y = y;
		this.id = id;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("image/" + id + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		game = p;
		this.name = name;
	}

	public void drawIfEquipped(Graphics g) {
	};

	public void update() {
		hitBox.setBounds(x_s, y_s, img.getWidth() * 2, img.getHeight() * 2);
		if (hitBox.intersects(game.p1.hitBox)) {
			game.ds = true;
			if (game.pickup) {
				game.p1.inventory.add(id-1);
				game.items.items.remove(this);
			}
		}
	}

	public void draw(Graphics g) {
		x_s = x - game.p1.x;
		y_s = y - game.p1.y;
		g.drawImage(img, x_s, y_s, img.getWidth() * 2, img.getHeight() * 2, null);
	}

	void addGeneralContext() {
		invContextMenu.add("Drop");
	}

	public void handleCommand(String command) {
		if (command.equals("Drop")) {
			x = game.p1.x + 400;
			y = game.p1.y + 300;

			game.items.items.add(this);
			game.p1.inventory.remove(id-1);
		} else {
			handleSpecialCommand(command);
		}
	}

	public void handleSpecialCommand(String command) {
	}

	void init() {
		vars = info.vars;
	}
}