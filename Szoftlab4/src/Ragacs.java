public class Ragacs implements Mezonallo {

	private Mezo pozicio;		//A ragacs tartózkodási mezõje.
	private int kopas;			//Megmutatja, hogy még hány robot ugorhat bele 
								//mielõtt elkopna a ragacs takarítás nélkül.
	
	private String nev;
	private static int autoincrement = 0;
	
	public Ragacs(Mezo mezo,int kopas, int kord[]){
		pozicio = mezo;
		this.kopas = kopas;
		
		autoincrement++;
		nev = "ragacs" + autoincrement;
		
		System.out.println("["+nev+"] létrejött x=("+kord[0]+","+kord[1]+"), "+kopas+" kopás.");
		
		mezo.beregisztral(this);
	}
	
	//Az ragacs poziciójára érkezõ elemnek szól, hogy ragacsra lépett.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.ragacsraLeptem(this);
	}

	//A mezõre újonnan érkezõ ragacsfolt leregrisztrálja már ott lévõ ragacsot.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		pozicio.leregisztral(kireLeptem);
	}

	//A mezõre újonnan érkezõ ragacsfolt leregisztrálja a már ott lévõ olajfoltot.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		pozicio.leregisztral(kireLeptem);
	}

	//Nem hajt végre feladatot.
	@Override
	public void robotraLeptem(Robot kireLeptem) {
	}
	
	//Nem hajt végre feladatot.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
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



	
}