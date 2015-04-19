public class Ragacs implements Mezonallo{

	private Mezo pozicio;		//A ragacs tart�zkod�si mez�je.
	private int kopas;			//Megmutatja, hogy m�g h�ny robot ugorhat bele 
								//miel�tt elkopna a ragacs takar�t�s n�lk�l.
	
	private String nev;
	
	public Ragacs(Mezo mezo,int kopas){
		pozicio = mezo;
		this.kopas = kopas;
		mezo.beregisztral(this);
	}
	
	//Az ragacs pozici�j�ra �rkez� elemnek sz�l, hogy ragacsra l�pett.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.ragacsraLeptem(this);
	}

	//A mez�re �jonnan �rkez� ragacsfolt leregrisztr�lja m�r ott l�v� ragacsot.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		pozicio.leregisztral(kireLeptem);
	}

	//A mez�re �jonnan �rkez� ragacsfolt leregisztr�lja a m�r ott l�v� olajfoltot.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		pozicio.leregisztral(kireLeptem);
	}

	//Nem hajt v�gre feladatot.
	@Override
	public void robotraLeptem(Robot kireLeptem) {
	}
	
	//Nem hajt v�gre feladatot.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
	}

	//Igaz �rt�kkel t�r vissza, mert a ragacsot szennyez�d�snek tekintj�l.
	@Override
	public boolean szennyezodesVagyok() {
		return true;
	}

	//Visszaadja a ragacs pozicio attrib�tum�t.
	@Override
	public Mezo getPozicio() {
		return pozicio;
	}

	//Be�ll�tja a ragacs pozicio attrib�tum�t m �rt�k�re.
	@Override
	public void setPozicio(Mezo m) {
		pozicio = m;
	}

	//�j k�rt kezd.
	@Override
	public void tick() {
		// TODO Auto-generated method stub		
	}
	
	//Visszat�r a kopas attrib�tum �rt�k�vel.
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

	//Be�ll�tja a kopas attrib�tum �rt�k�t. Ha nulla vagy al� megy az �rt�k akkor leregriszt�lja.
	@Override
	public void setkopas(Integer kop) {
		this.kopas = kopas;
		if(kopas <= 0){
			pozicio.leregisztral(this);
		}
	}

	
}