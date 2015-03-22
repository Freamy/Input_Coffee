
public class Sebesseg {
	
	private int x;
	private int y;
	private boolean modosithato;
	
	public void hozzaad(Sebesseg sebesseg){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("A sebességvektor értékét megnöveli egy másik sebességvektoréval (sebesseg paraméter).");
	}
	
	public void felez(Sebesseg sebesseg){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("Megfelezi a paraméterül kapott sebességvektort.");
	}
	
	public void modosithatatlan(){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("A modosithato attribútumot beállítja hamisra.");
	}
	
	public void modosithato(){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("A modosithato attribútumot beállítja igazra.");
	}
}
