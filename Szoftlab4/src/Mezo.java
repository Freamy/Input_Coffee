import java.util.*;

public class Mezo {
	
	ArrayList<Mezonallo> rajtamAllok;
	
	public Mezo(){
		rajtamAllok = new ArrayList<Mezonallo>();
	}
	
	void beregisztral(Mezonallo joveveny){
		System.out.println("[pozicio: Mezo]");
		System.out.println("A rajtamAllok arraylist elemeinek megh�vja a jottValaki met�dus�t a j�vev�ny param�terrel �s berakja a list�ba az elemet.");
		System.out.println("[pozicio: Mezo] --- jottValaki(joveveny: Mezonallo) --->");
		
		rajtamAllok.add(joveveny);
		
		for(Mezonallo mezonallo: rajtamAllok){
			mezonallo.jottValaki(joveveny);
		}
		
	}
	
	void leregisztral(Mezonallo joveveny){
		System.out.println("[pozicio: Mezo]");
		System.out.println("T�rli a rajtamAllok-b�l a joveveny elemet.");
		
		rajtamAllok.remove(joveveny);
	}
	
	boolean szennykeres(){
		
		boolean talalat = false;
		for(Mezonallo mezonallo : rajtamAllok){
			if(mezonallo.szennyezodesVagyok()){
				talalat = true;
			}
		}
		return talalat;
	}
	
	void tick(){
		for(Mezonallo mezonallo : rajtamAllok){
			mezonallo.tick();
		}
	}
}
