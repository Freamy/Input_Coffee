public class Ragacs implements Mezonallo {

	private Mezo pozicio;		//A ragacs tartózkodási mezõje.
	private int kopas;			//Megmutatja, hogy még hány robot ugorhat bele 
								//mielõtt elkopna a ragacs takarítás nélkül.
	
	private String nev;
	
	public Ragacs(String nev, Mezo mezo,int kopas, int kord[]){
		this.nev = nev;
		this.pozicio = mezo;
		this.kopas = kopas;
		
		System.out.println("["+nev+"] létrejött x=("+kord[0]+","+kord[1]+"), kopás = "+kopas+".");
		pozicio.beregisztral(this);
	}
	
	//Az ragacs poziciójára érkezõ elemnek szól, hogy ragacsra lépett.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.ragacsraLeptem(this);
	}

	//A mezõre újonnan érkezõ ragacsfolt leregrisztrálja már ott lévõ ragacsot.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		pozicio.leregisztral(kireLeptem);
	}

	//A mezõre újonnan érkezõ ragacsfolt leregisztrálja a már ott lévõ olajfoltot.
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
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
	}

	//Igaz értékkel tér vissza, mert a ragacsot szennyezõdésnek tekintjül.
	@Override
	public boolean szennyezodesVagyok() {
		return true;
	}
	
	//Megsemmisíti a ragacsot.
	public void megsemmisul () {
		pozicio.leregisztral(this);
		System.out.println("["+nev+"] megsemmisült.");
	}
	
	//Visszaadja a ragacs pozicio attribútumát.
	@Override
	public Mezo getPozicio() {
		return pozicio;
	}

	//Beállítja a ragacs pozicio attribútumát m értékére.
	@Override
	public void setPozicio(Mezo m) {
		pozicio = m;
	}

	//Új kört kezd.
	@Override
	public void tick() {
		// TODO Auto-generated method stub		
	}
	
	public void tickend() {
		
	}
	
	//Visszatér a kopas attribútum értékével.
	public int getkopas(){
		return kopas;
	}
	
	//Beállítja a kopas attribútum értékét.
	public void setkopas(int kopas){
		this.kopas = kopas;
		if(kopas <= 0){
			pozicio.leregisztral(this);
		}
	}
	
	@Override
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