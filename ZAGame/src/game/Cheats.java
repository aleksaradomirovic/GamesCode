package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Cheats implements KeyListener{
	String command = "";
	String[] history = new String[64];
	GamePanel game;
	
	public Cheats(GamePanel p) {
		game = p;
		history[0] = "";
		history[1] = "";
		history[2] = "";
		history[3] = "";
		history[4] = "";
	}
	
	public void draw(Graphics g, Font f) {
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 500, 800, 12);
		g.setFont(f);
		g.setColor(Color.WHITE);
		if(game.write)
			g.drawString(command+"_", 1, 509);
		else
			g.drawString(command, 1, 509);
		
		g.setColor(Color.BLACK);
		for(int i = 0; i < 5; i++) {
			g.drawString(history[i], 1, 509 - (i+1)*12);
		}
	}
	
	public void newLine(boolean deleteLast) {
		if(!deleteLast) {
			updateHistory("["+game.playerName+"]: "+command);
		}
		command = "";
	}
	
	private void updateHistory(String update) {
		for(int i = 63; i > 0; i--) {
			history[i] = history[i-1];
		}
		history[0] = update;
	}
	
	void handleCommand(String command) {
		if(command.charAt(0) == '@' || command.charAt(0) == '/') {
			int space1 = 0;
			while(space1 < command.length() && command.charAt(space1) != ' ') {
				space1++;
			}
			System.out.println(command.substring(0, space1));
		} else {
			System.out.println("not command");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		if(kc == KeyEvent.VK_BACK_SPACE) {
			command = command.substring(0, command.length()-1);
		} else if(kc == KeyEvent.VK_ENTER && command.length() > 0) {
			handleCommand(command);
			newLine(false);
		} else {
			if(!(kc == KeyEvent.VK_F3 || kc == KeyEvent.VK_SHIFT))
				command+=e.getKeyChar();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
