import java.util.ArrayList;

public class Mezo {
	
	ArrayList<Mezonallo> rajtamAllok;
	
	void beregisztral(Mezonallo joveveny){
		System.out.println("[pozicio: Mezo]");
		System.out.println("A rajtamAllok arraylist elemeinek meghívja a jottValaki metódusát a jövevény paraméterrel és berakja a listába az elemet.");
		System.out.println("[pozicio: Mezo] --- jottValaki(joveveny: Mezonallo) --->");
		
	}
	void leregisztral(Mezonallo joveveny){
		System.out.println("[pozicio: Mezo]");
		System.out.println("Törli a rajtamAllok-ból a joveveny elemet.");
	}
}
