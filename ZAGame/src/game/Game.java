package game;

import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {
	JFrame frame = new JFrame();
	GamePanel panel = new GamePanel(this);

	final String version = "0.3.1a", update = "SETTLEMENTS\n"
			+ " - SETTLEMENTS are a game element that I will work on a lot for a bit,\n and I'll add item spawns, spawn tables, and infected people "
			+ "(ZOMBIES) too.\n" + " - Fixed road width glitch\n"
			+ " - Fixed house cluster glitch (by adding null objects for settlement generation,\n"
			+ " as you know, the entire map is random and infinite\n" + "0.3.1\n"
			+ " - Inserted Spawn Tables into the buildings";

	void setup() throws IOException {
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.addKeyListener(panel);
		frame.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("genImages/Icon.png")));
		frame.setTitle("Item Game v" + version);

		frame.pack();
		frame.setVisible(true);

		JOptionPane.showMessageDialog(frame, "ItemGame " + version + " Update: \n\n" + update, "Update Notes",
				JOptionPane.INFORMATION_MESSAGE);
		panel.startGame();
	}

	public static void main(String[] args) throws IOException {
		new Game().setup();
	}
}
