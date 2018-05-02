package game.entity;

import game.Settlement;

public class NPC {
	boolean freeRoam = true;
	int x, y;
	public NPC(int x, int y, Settlement home) {
		if(home != null) {
			//TODO alignment init
			freeRoam = false;
		}
		this.x = x;
		this.y = y;
	}
	
	public void speakToPlayer() {
	}
}