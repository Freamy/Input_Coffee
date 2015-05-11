public class Ragacs implements Mezonallo {
	
	private Gyar grafikusGyar;

	private Mezo pozicio;		//A ragacs tart�zkod�si mez�je.
	private int kopas;			//Megmutatja, hogy m�g h�ny robot ugorhat bele 
								//miel�tt elkopna a ragacs takar�t�s n�lk�l.
	private int x;
	private int y;
	private String nev;
	private static int autoincrement = 0;
	
	private GrafikusRagacs grafika;
	
	private boolean megsemmisult;
	
	public Ragacs(Mezo mezo,int kopas, int kord[], Kepernyo k){
		pozicio = mezo;
		this.kopas = kopas;
		
		grafikusGyar = new RagacsGyar();
		
		grafika = (GrafikusRagacs) grafikusGyar.grafikaKeszitese(k, this);
		
		autoincrement++;
		nev = "ragacs" + autoincrement;
		
		megsemmisult = false;
		
		System.out.println("["+nev+"] l�trej�tt x=("+kord[0]+","+kord[1]+"), "+kopas+" kop�s.");
		
		this.x = mezo.getX();
		this.y = mezo.getY();
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
	
	//Megsemmis�ti a ragacsot.
	public void megsemmisul () {
		pozicio.leregisztral(this);
		System.out.println("["+nev+"] megsemmis�lt.");
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
	
	//Be�ll�tja a kopas attrib�tum �rt�k�t.
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

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public boolean getMegsemmisult() {
		return megsemmisult;
	}

	
}