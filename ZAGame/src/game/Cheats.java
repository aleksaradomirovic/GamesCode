package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Cheats implements KeyListener{
	String command = "";
	String[] history = new String[64];
	Color[] colorHist = new Color[64];
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
		
		for(int i = 0; i < 5; i++) {
			if(colorHist[i] != null)
				g.setColor(colorHist[i]);
			else
				g.setColor(Color.BLACK);
			
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
			colorHist[i] = colorHist[i-1];
		}
		history[0] = update;
		colorHist[0] = Color.BLACK;
	}
	
	void handleCommand(String command) {
		if(command.charAt(0) == '@' || command.charAt(0) == '/') {
			int space1 = 0;
			while(space1 < command.length() && command.charAt(space1) != ' ') {
				space1++;
			}
			System.out.println(command.substring(0, space1));
			
			if(command.substring(1,space1).equals("Settlements")) {
				for(int i = 0; i < game.terrain.terrain.size(); i++) {
					if(game.terrain.terrain.get(i).chunkSettlement != null) {
						game.terrain.terrain.get(i).chunkSettlement.printLocation();
					}
				}
			} else if(command.substring(1,space1).equals("tp") || command.substring(1,space1).equals("teleport")) {
				int tX = game.p1.x, tY = game.p1.y;
				
				int s2 = space1 + 1, s3;
				while(s2 < command.length() && command.charAt(s2) != ' ') {
					s2++;
				}
				s3 = s2 + 1;
				while(s3 < command.length() && command.charAt(s3) != ' ') {
					s3++;
				}
				
				if(s2 != command.length() && space1 != command.length()) {
					boolean c = true;
					try {
						tX = Integer.parseInt(command.substring(space1 + 1, s2));
						tY = Integer.parseInt(command.substring(s2 + 1, s3));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						c = false;
						printErrorTrace("Invalid TELEPORT argument");
					}
					if(c) {
						game.p1.x = tX;
						game.p1.y = tY;
					}
				} else {
					printErrorTrace("Invalid TELEPORT argument");
				}
			} else if(command.substring(1,space1).equals("movebind")) {
				int s2 = space1+1;
				while(s2 < command.length() && command.charAt(s2) != ' ') {
					s2++;
				}
				
				if(s2 != command.length() && space1 != command.length()) {
					if(command.substring(space1+1, s2).equals("wasd")) {
						println("Set movement bind to WASD");
					} else if(command.substring(space1+1, s2).equals("arrows")) {
						println("Set movement bind to ARROWS");
						// TODO println("");
					}
				}
			}
		} else {
			System.out.println("not command");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(game.debug) {
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
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void println(String s) {
		updateHistory(s);
	}
	
	public void printErrorTrace(String s) {
		for(int i = 63; i > 0; i--) {
			history[i] = history[i-1];
			colorHist[i] = colorHist[i-1];
		}
		history[0] = s;
		colorHist[0] = Color.RED;
	}
}
