package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	public String playerName;
	
	int min, hr, day, mo, yr;
	
	Timer cap = new Timer(1000 / 60, this);
	
	public ItemManager items = new ItemManager(this);
	public TerrainManager terrain = new TerrainManager(this);
	public Player p1 = new Player(this);
	SpawnIDs sID = new SpawnIDs();
	public boolean ds;
	Random rnd = new Random();
	String message, tutorialText;
	int GameTimer = 0;
	Cheats cheats = new Cheats(this);
	boolean write;
	public int currentTutorial = 0, lastTutorialms;
	// Settlement s = new Settlement(0, 0, 2 * rnd.nextInt(5)+2,3 + 2 * rnd.nextInt(2), this, terrain);

	public Font classic = new Font("Arial", Font.PLAIN, 10);
	public Font statusMessage = new Font("Terminal", Font.BOLD, 20);

	public boolean pickup = false, inv = false, invContext = false, enterContext = false, esc = false;
	int inv_Sel, invContext_Sel;
	public StatusManager status = new StatusManager(this);
	Game frame;
	public boolean generate;
	boolean debug = false;
	int framerate, currentFrame; long msTimer;

	public GamePanel(Game g) {
		frame = g;
	}

	@SuppressWarnings("static-access")
	void startGame() {
		Calendar start = Calendar.getInstance();
		p1.initPlayer();
		cap.start();
//		for (int i = 0; i < 20; i++) {
//			items.spawnItem(rnd.nextInt(Item.items) + 1);
//		}
//		for(int i = 0; i < 20; i++) {
//			terrain.spawnObject(1);
//		}
		System.out.println("StartGame");
		terrain.genChunk(0, 0, true);
		
		playerName = "LOCAL_SERVER_HOST_USER";
		
		min = start.MINUTE;
		hr = start.HOUR_OF_DAY;
		day = start.DAY_OF_MONTH;
		mo = start.MONTH;
		yr = start.YEAR;
		
		msTimer = System.currentTimeMillis();
	}

	void updateGame() {
		GameTimer++;
		
		//TICK
		if(GameTimer%60 == 0) {
			status.tick();
			min++;
			if(min > 59) {
				min = 0;
				hr++;
				if(hr > 23) {
					hr = 0;
					day++;
					if(utils.dayPastMonth(day, mo)) {
						day = 1;
						mo++;
						if(mo > 11) {
							mo = 0;
							yr++;
						}
					}
				}
			}
		}
	
		p1.update();
		items.update();
		status.update();
		terrain.update();
		
		if(System.currentTimeMillis() - msTimer < 1000) {
			currentFrame++;
			if(System.currentTimeMillis() - msTimer > 500) {
				write = true;
			} else {
				write = false;
			}
		} else {
			framerate = currentFrame;
			msTimer = System.currentTimeMillis();
			currentFrame = 0;
		}
	}

	void drawGame(Graphics g) {
		// Background
		// g.setFont(classic);
		// g.setColor(Color.GREEN);
		// g.fillRect(0, 0, 800, 800);

		// Items and entities
		terrain.draw(g);
		items.draw(g);
		terrain.drawRoof(g);
		p1.draw(g);
		terrain.drawFoliage(g);

		g.setColor(Color.RED);
		if (ds) {
			g.setFont(classic);
			g.drawString("Press [F] to pick up", 425, 300);
			// System.out.println("ds true");
		}
		if (generate || GameTimer < 60) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			
			g.setColor(Color.RED);
			g.setFont(statusMessage);
			g.drawString("Generating terrain...", 0, 30);
		}
		ds = false;

		status.draw(g);
		drawTools(g);
		p1.drawInventory(g, classic);
		
		drawTutorial(g);
		
		if(debug)
			drawDebug(g);
		if(esc)
			drawEsc(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		updateGame();
	}

	@Override
	public void paintComponent(Graphics g) {
		drawGame(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("KeyPressed "+e.getKeyChar());
		if(!debug && !esc) {
			if (e.getKeyChar() == 'a') {
				p1.left = true;
			} else if (e.getKeyChar() == 'd') {
				p1.right = true;
			}
			if (e.getKeyChar() == 'w') {
				p1.up = true;
			} else if (e.getKeyChar() == 's') {
				p1.down = true;
			}
			if (e.getKeyChar() == 'f') {
				pickup = true;
			}
			
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				p1.speed = 5;
			} if (e.getKeyChar() == 'g') {
				p1.testInventory();
				if (!inv) {
					inv = true;
					inv_Sel = 0;
				} else {
					inv = false;
				}
			}
		}

		if (inv || esc) {
			if (invContext) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					invContext_Sel++;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					invContext_Sel--;
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					enterContext = true;
				}
			} else {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					//while(!p1.existInInv[inv_Sel])
					inv_Sel++;
					System.out.println("inventory select+");
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					//while(!p1.existInInv[inv_Sel])
					inv_Sel--;
					System.out.println("inventory select-");
				}
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (invContext) {
				invContext = false;
			} else if(inv) {
				inv = false;
			} else {
				if(esc)
					esc = false;
				else
					esc = true;
			}
			// all screens esc
		}

		if(e.getKeyCode() == KeyEvent.VK_F3) {
			if(debug) {
				debug = false;
			} else {
				debug = true;
				cheats.newLine(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("KeyReleased "+e.getKeyChar());
		if(!debug) {
			if (e.getKeyChar() == 'a') {
				p1.left = false;
			} else if (e.getKeyChar() == 'd') {
				p1.right = false;
			}
			if (e.getKeyChar() == 'w') {
				p1.up = false;
			} else if (e.getKeyChar() == 's') {
				p1.down = false;
			}
			if (e.getKeyChar() == 'f') {
				pickup = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				p1.speed = 3;
			}

			if (inv) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					invContext = true;
					invContext_Sel = 0;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("KeyTyped "+e.getKeyChar());
		
	}

	public void displayString(String d) {
		message = d;
		ds = true;
	}
	
	void drawDebug(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(classic);
		g.drawString("XY player1: ("+p1.x+","+p1.y+")", 10, 25);
		g.drawString(framerate+"fps", 10, 50);
		cheats.draw(g,classic);
	}
	
	void drawEsc(Graphics g) {
		g.setColor(new Color(255,255,255,200));
		g.fillRect(0, 0, 800, 600);
	}
	
	void drawTutorial(Graphics g) {
		/* TUTORIAL MODES
		 * 0 = WASD instructions
		 * 1 = open inv
		 * 2 = nav inv
		 */
		if(currentTutorial == 0) {
			tutorialText = "use W, A, S, and D to move";
			if(GameTimer > 1200)
				currentTutorial++;
		} else if(currentTutorial == 1) {
			tutorialText = "G to open inventory, ARROW KEYS, ENTER, and ESC to navigate";
			if(GameTimer > 2400) {
				currentTutorial++;
			}
		}
		
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, 400, 50, 10, 10);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, 400, 50, 10, 10);
		g.setFont(classic);
		g.drawString(tutorialText, 10, 30);
	}
	
	void drawTools(Graphics g) {
		if(p1.invContains(Player.inv_Watch)) {
			g.setColor(new Color(255,255,255,100));
			g.fillRect(700, 556, 100, 15);
			g.setColor(Color.BLACK);
			g.setFont(classic);
			g.drawString(utils.stringTime(min, hr), 703, 568);
		}
	}
}