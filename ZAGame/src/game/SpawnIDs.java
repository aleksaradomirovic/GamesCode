package game;

public class SpawnIDs {
	public static final int spawnAmount = 1;

	int[][] spawns = new int[spawnAmount][1024];

	public SpawnIDs() {
		init();
	}

	void init() {
		spawns[0] = new int[] { 1, 2, 3, 4 };
	}
}
