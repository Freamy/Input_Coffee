
public class Sebesseg {
	
	private int x;
	private int y;
	private boolean modosithato;
	
	public void hozzaad(Sebesseg sebesseg){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("A sebess�gvektor �rt�k�t megn�veli egy m�sik sebess�gvektor�val (sebesseg param�ter).");
	}
	
	public void felez(Sebesseg sebesseg){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("Megfelezi a param�ter�l kapott sebess�gvektort.");
	}
	
	public void modosithatatlan(){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("A modosithato attrib�tumot be�ll�tja hamisra.");
	}
	
	public void modosithato(){
		System.out.println("[sebesseg: Sebesseg]");
		System.out.println("A modosithato attrib�tumot be�ll�tja igazra.");
	}
}
