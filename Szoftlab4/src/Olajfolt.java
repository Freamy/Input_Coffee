public class Olajfolt implements Mezonallo{
	
	private Mezo pozicio;		//Az olajfolt tartózkodási mezõje.
	private int kopas;			//Megmutatja, hogy hány kör múlva szívódik fel az olajfolt
								//takarítás nélkül.
	
	private String nev;
	
	public Olajfolt(Mezo mezo, int kopas){
		this.pozicio = mezo;
		this.kopas = kopas;
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
		pozicio.leregisztral(kireLeptem);
	}

	//A mezõre újonnan érkezett olajfolt, leregisztrálja már a mezõn lévõ olajfoltot.
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
	public void kisrobotraLeptem(Kisrobot kireLeptem){
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
	
	//Visszatér igaz értékel, mert az olajfoltot szenyezõdésnek tekintjük.
	@Override
	public boolean szennyezodesVagyok(){
		return true;
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
	
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	//Beállítja a kopas attribútum értékét.
	@Override
	public void setkopas(Integer kop) {
		kopas = kop;
		if(kopas <= 0 ){
			pozicio.leregisztral(this);
		}		
	}
}