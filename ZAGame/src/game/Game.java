package game;

import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {
	JFrame frame = new JFrame();
	GamePanel panel = new GamePanel(this);
	
	final String version = "0.2.3a", update = "Objects & Terrain:\n - Screwed up while adding object hitboxes\n"
			+ " - As a conclusion, object hitboxes are a postponed project\n\n"
			+ " - Added basic terrain and generation\n"
			+ " - If it lags when you head to the edge of the generated map it means that the terrain is generating\n"
			+ " - So don't call it laggy, I'll fix it\n"
			+ "0.2.1\n"
			+ " - Added biome\n"
			+ " - Fixed chunk cluster glitch\n"
			+ " - Adding foliage soon\n"
			+ "0.2.2\n"
			+ " - Added foliage (shitty lowres pine trees)\n"
			+ "0.2.3\n"
			+ " - Added basic building objects (currently just a clusterfuck of houses replaced the"
			+ "clusterfuck of mailboxes)\n"
			+ " - Plan to add settlement generator and road";
	
	void setup() throws IOException {
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));
		frame.setResizable(false);
		frame.addKeyListener(panel);
		frame.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("genImages/Icon.png")));
		frame.setTitle("Item Game v"+version);
		
		frame.pack();
		frame.setVisible(true);
		
		JOptionPane.showMessageDialog(frame,"ItemGame "+version+" Update: \n\n"+update,"Update Notes",JOptionPane.INFORMATION_MESSAGE);
		panel.startGame();
	}
	
	public static void main(String[] args) throws IOException {
		new Game().setup();
	}
}
