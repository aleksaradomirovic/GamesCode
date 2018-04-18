package game.itemTypes;

public class ItemGeneral {
	public final int[][] vars = new int[Item.items][10];
	
	public ItemGeneral() {
		init();
	}
	
	void init() {
		vars[0] = new int[] {0,Item.clothes,Clothes.shirt,0};
		vars[1] = new int[] {1,Item.clothes,Clothes.pants,0};
		vars[2] = new int[] {2,Item.clothes,Clothes.shirt,0};
		vars[3] = new int[] {3,Item.food,Food.water,700};
		vars[4] = new int[] {4,Item.food,Food.med,20};
		vars[5] = new int[] {5,Item.weapon,0,10,60};
		vars[6] = new int[] {6,Item.food,Food.food,700};
		vars[7] = new int[] {7,Item.tool,0};
	}
}
