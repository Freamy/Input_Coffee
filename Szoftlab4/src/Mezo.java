import java.util.*;

public class Mezo {
	
	ArrayList<Mezonallo> rajtamAllok;
	
	private int x;
	private int y;
	private String nev;
	
	public Mezo(int x, int y){
		this.x = x;
		this.y = y;
		rajtamAllok = new ArrayList<Mezonallo>();
		//Majd a Navigator nevez el minket, mert a nevünkben a kordinátáink is szerepelnek.
		 nev = "Mezo("+x+","+y+")";
	}
	
	void beregisztral(Mezonallo joveveny){
		
		System.out.println("["+nev+"] beregisztrálta: "+joveveny.getNev()+".");
		
		ArrayList<Mezonallo> rajtamAllokIteralt = new ArrayList<Mezonallo>(rajtamAllok);
		
		for(Mezonallo mezonallo : rajtamAllokIteralt){
			mezonallo.jottValaki(joveveny);
		}
		
		rajtamAllok.add(joveveny);
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
		ArrayList<Mezonallo> rajtamAllokIteralt = new ArrayList<Mezonallo>(rajtamAllok);
		
		for(Mezonallo mezonallo : rajtamAllokIteralt){
			mezonallo.tick();
		}
	}
	
	void tickend(){
		ArrayList<Mezonallo> rajtamAllokIteralt = new ArrayList<Mezonallo>(rajtamAllok);
		
		for(Mezonallo mezonallo : rajtamAllokIteralt){
			mezonallo.tickend();
		}
	}
	
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}
	

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public void adatKiirasa(String param) {
		if(param.equals("") || param.equals(nev)) {
			System.out.println("["+nev+"]"
					+"\n");
		}
		if(param.equals("")) {
			Iterator<Mezonallo> it = rajtamAllok.iterator();
			while(it.hasNext()){
				Mezonallo iterated = it.next();
				iterated.adatokKiirasa(param);
			}
		}
	}
}