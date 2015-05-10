import java.util.*;

public class Mezo {
	
	ArrayList<Mezonallo> rajtamAllok;
	
	private String nev;
	private int x;
	private int y;
	
	public Mezo(int x, int y){
		this.x=x;
		this.y=y;
		rajtamAllok = new ArrayList<Mezonallo>();
		//Majd a Navigator nevez el minket, mert a nev�nkben a kordin�t�ink is szerepelnek.
	}
	
	void beregisztral(Mezonallo joveveny){
		
		System.out.println("["+nev+"] beregisztr�lta: "+joveveny.getNev()+".");
		
		rajtamAllok.add(joveveny);
		for(Mezonallo mezonallo: rajtamAllok){
			if(!mezonallo.equals(joveveny))
				mezonallo.jottValaki(joveveny);
		}
	}
	
	void leregisztral(Mezonallo joveveny){
		
		System.out.println("["+nev+"] leregisztr�lta: "+joveveny.getNev()+".");
		
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
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
