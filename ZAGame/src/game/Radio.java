package game;

import java.util.Random;

public class Radio {
	public static final String news = "news";
	
	public static String[] generalCivilian = new String[] {
			"Hi. Welcome to PostApocalypse Radio on freq.",
			"And now the news: ",
			news
	};
	
	public static String[] generalNews = new String[] {
			"Reports of hordes have been found outside one of the major settlements.",
			"A large salvage location has been found in an abandoned factory to the far north.",
			"Another pocket of survivors has been located after a band of 'prospectors' were wandering.",
			"Multiple airplanes have been located flying overhead and dropping packets of supplies.",
			"Another UN Coalition helicopter has been shot down by unidentified defenses. Two of the crewmen survived, saying that missiles hit the craft.",
			"Lots of gunshots wre reported by scavengers near an old military installation.",
			"Two UN trucks arrived outside of our settlement yesterday, saying they were devastated by heavily armed individuals. A team has been sent to recover the rest of the convoy."
	};
	
	public static String[] generateBroadcast(String frequency) {
		String[] r = new String[64];
		
		int i;
		for(i = 0; i < generalCivilian.length; i++) {
			r[i] = filter(generalCivilian[i], frequency);
			if(generalCivilian[i].equals(news)) {
				i++;
				int j = new Random().nextInt(generalNews.length) + i;
				int k = i;
				for(;i < j; i++) {
					r[i-1] = generalNews[i-k]; 
				}
			}
		}
		
		return r;
	}
	
	private static String filter(String i, String frequency) {
		i.replace("freq", frequency);
		
		return i;
	}
}
