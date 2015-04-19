public class Ragacs implements Mezonallo{

	private Mezo pozicio;		//A ragacs tartózkodási mezõje.
	private int kopas;			//Megmutatja, hogy még hány robot ugorhat bele 
								//mielõtt elkopna a ragacs takarítás nélkül.
	
	private String nev;
	
	public Ragacs(Mezo mezo,int kopas){
		pozicio = mezo;
		this.kopas = kopas;
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
	
	@Override
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	//Beállítja a kopas attribútum értékét. Ha nulla vagy alá megy az érték akkor leregrisztálja.
	@Override
	public void setkopas(Integer kop) {
		this.kopas = kopas;
		if(kopas <= 0){
			pozicio.leregisztral(this);
		}
	}

	
}