package game;

import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {
	JFrame frame = new JFrame();
	GamePanel panel = new GamePanel(this);

	final String version = "0.4.3a", update = "Food & Water, Immunity Constraints \n"
			+ " - Food and Water constraints will add more survivalism to the game (my aim)\n"
			+ " - You spawn with 2-3 days worth of water consumed\n"
			+ " - You spawn with 3-10 days worth of food consumed\n"
			+ " - You spawn with 50% immunity, which can be increased with antibiotics\n"
			+ " 0.4.1-3 : Redid Inventory\n"
			+ " - Items now stack (infinitely, I realize i'm stupid)\n"
			+ " - A few glitches with equipped items and clothes remain, will fix\n"
			+ " - Intend to start NPCs after polishing inventory and zombies";

	void setup() throws IOException {
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.addKeyListener(panel);
		frame.addKeyListener(panel.cheats);
		frame.addMouseListener(panel.p1);
		frame.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("genImages/Icon2.png")));
		frame.setTitle("RETROVIRUS v" + version);

		frame.pack();
		frame.setVisible(true);

		JOptionPane.showMessageDialog(frame, "RETROVIRUS " + version + " Update: \n\n" + update, "Update Notes",
				JOptionPane.INFORMATION_MESSAGE);
		panel.startGame();
	}

	public static void main(String[] args) throws IOException {
		new Game().setup();
	}
}
