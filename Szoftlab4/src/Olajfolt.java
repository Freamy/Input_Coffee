public class Olajfolt implements Mezonallo{
	
	private Mezo pozicio;		//Az olajfolt tartózkodási mezõje.
	private int kopas;			//Megmutatja, hogy hány kör múlva szívódik fel az olajfolt
								//takarítás nélkül.
	
	private String nev;
	
	public Olajfolt(String nev, Mezo mezo, int kopas, int kord[]){
		this.nev = nev;
		this.pozicio = mezo;
		this.kopas = kopas;
		
		System.out.println("["+nev+"] létrejött x=("+kord[0]+","+kord[1]+"), kopás = "+kopas+".");
		pozicio.beregisztral(this);
	}
	
	//Az olajfolt pozíciójára érkezõ elemnek szól, hogy olajfoltra lépett.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.olajfoltraLeptem(this);
	}

	//A pozícióra újonnan érkezett olajfolt, leregisztrálja már a mezõn lévõ ragacsot.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		pozicio.leregisztral(kireLeptem);
	}

	//A mezõre újonnan érkezett olajfolt, leregisztrálja már a mezõn lévõ olajfoltot.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		pozicio.leregisztral(kireLeptem);
	}

	//Nem hajt végre feladatot.
	@Override
	public void robotraLeptem(Robot kireLeptem) {	
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
	}
	
	//Nem hajt végre feladatot.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem){
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
	}
	
	//Új kört kezd.Csökkenti a kopás értékét eggyel és ha nulla vagy az allati értéket vett fel
	//akkor leregisztrálja a pozicio-rol az olajfoltot.
	@Override
	public void tick() {
		kopas -= 1;
		if(kopas <= 0 ){
			pozicio.leregisztral(this);
		}
	}
	
	public void tickend() {
		
	}
	
	//Visszatér igaz értékel, mert az olajfoltot szenyezõdésnek tekintjük.
	@Override
	public boolean szennyezodesVagyok(){
		return true;
	}
	
	//Megsemmisíti az olajfoltot.
	public void megsemmisul () {
		pozicio.leregisztral(this);
		System.out.println("["+nev+"] megsemmisült.");
	}
	
	//Viasszaadja az olajfolt pozicio attribútumát. 
	@Override
	public Mezo getPozicio() {
		return pozicio;
	}

	//Beaállítja az olajfolt pozicio attribútumát.
	@Override
	public void setPozicio(Mezo m) {
		pozicio = m;
	}
	
	//Visszatér a kopas attribútum értékével.
	public int getkopas(){
		return kopas;
	}
	
	//Beállítja a kopas attribútum értékét.
	public void setkopas(int kopas){
		this.kopas=kopas;
	}
	
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}
	
	public void adatokKiirasa(String param) {
		if(param.equals("") || param.equals(nev)) {
			System.out.println("["+nev+"]:\nPozicio: "+pozicio.getNev()+"\nKopas: "+kopas
					+"\n");
		}
	}

}