import java.util.ArrayList;

public class Mezo {
	
	ArrayList<Mezonallo> rajtamAllok;
	
	public Mezo(){
		rajtamAllok = new ArrayList<Mezonallo>();
	}
	
	void beregisztral(Mezonallo joveveny){
		System.out.println("[pozicio: Mezo]");
		System.out.println("A rajtamAllok arraylist elemeinek megh�vja a jottValaki met�dus�t a j�vev�ny param�terrel �s berakja a list�ba az elemet.");
		System.out.println("[pozicio: Mezo] --- jottValaki(joveveny: Mezonallo) --->");
		for(Mezonallo mezonallo: rajtamAllok){
			mezonallo.jottValaki(joveveny);
		}
		rajtamAllok.add(joveveny);
		
	}
	
	void leregisztral(Mezonallo joveveny){
		System.out.println("[pozicio: Mezo]");
		System.out.println("T�rli a rajtamAllok-b�l a joveveny elemet.");
	}
}
