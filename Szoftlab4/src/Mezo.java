import java.util.*;

public class Mezo {
	
	ArrayList<Mezonallo> rajtamAllok;
	
	private String nev;
	
	public Mezo(){
		rajtamAllok = new ArrayList<Mezonallo>();
		//Majd a Navigator nevez el minket, mert a nevünkben a kordinátáink is szerepelnek.
	}
	
	void beregisztral(Mezonallo joveveny){
		
		System.out.println("["+nev+"] beregisztrálta: "+joveveny.getNev()+".");
		
		rajtamAllok.add(joveveny);
		for(Mezonallo mezonallo: rajtamAllok){
			mezonallo.jottValaki(joveveny);
		}
		
		
		
	}
	
	void leregisztral(Mezonallo joveveny){
		
		System.out.println("["+nev+"] leregisztrálta: "+joveveny.getNev()+".");
		
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
	
	public ArrayList<Mezonallo> getRajtamAllok(){
		return rajtamAllok;
	}
	
	void tick(){
		for(Mezonallo mezonallo : rajtamAllok){
			mezonallo.tick();
		}
	}
	
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}
}
