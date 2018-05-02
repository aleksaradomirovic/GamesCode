package game.entity;

import java.util.Random;

public class StaticDialogueData {
	/*
	 * 
	 * dialogue types::
	 * greeting
	 * already met
	 */
	
	static Random rnd = new Random();
	
	public static final int DIALOGUE_NEUTRAL = 0;
	public static final int TYPE_MEET = 0, TYPE_HELLO = 1;
	
	public static String[][] neutralDialogue = new String[][] 
	{
/*greeting    */ {"Hello.","Hi."},
/*already met */ {"Hi again.","Nice to see you, <p1_name>."}
	};
	
	public static String produceDialogue(String player, int dialogue, int type) {
		String r;
		String[] dialogueReference;
		
		if(dialogue == DIALOGUE_NEUTRAL) {
			dialogueReference = neutralDialogue[type];
		} else {
			System.out.println("ERROR: INVALID DIALOGUE INPUT");
			return null;
		}
		
		r = dialogueReference[rnd.nextInt(dialogueReference.length)];
				
		r.replace("<p1_name>", player);
		
		return r;
	}
}
