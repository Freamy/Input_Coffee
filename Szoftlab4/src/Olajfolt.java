public class Olajfolt implements Mezonallo{
	
	private Mezo pozicio;		//Az olajfolt tart�zkod�si mez�je.
	private int kopas;			//Megmutatja, hogy h�ny k�r m�lva sz�v�dik fel az olajfolt
								//takar�t�s n�lk�l.
	private int x;
	private int y;
	private String nev;
	private static int autoincrement = 0;
	
	public Olajfolt(Mezo mezo,int kopas, String kisrobotneve, int kord[]){
		this.pozicio = mezo;
		this.kopas = kopas;
		this.x = mezo.getX();
		this.y = mezo.getY();
		if(kisrobotneve != "") {
			nev = kisrobotneve+"olajfoltja";
		} else {
		autoincrement++;
		nev = "olajfolt" + autoincrement;
		}
		
		System.out.println("["+nev+"] l�trej�tt x=("+kord[0]+","+kord[1]+"), "+kopas+" kop�s.");
		pozicio.beregisztral(this);
	}
	
	//Az olajfolt poz�ci�j�ra �rkez� elemnek sz�l, hogy olajfoltra l�pett.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.olajfoltraLeptem(this);
	}

	//A poz�ci�ra �jonnan �rkezett olajfolt, leregisztr�lja m�r a mez�n l�v� ragacsot.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		pozicio.leregisztral(kireLeptem);
	}

	//A mez�re �jonnan �rkezett olajfolt, leregisztr�lja m�r a mez�n l�v� olajfoltot.
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
	public void kisrobotraLeptem(Kisrobot kireLeptem){
	}
	
	//�j k�rt kezd.Cs�kkenti a kop�s �rt�k�t eggyel �s ha nulla vagy az allati �rt�ket vett fel
	//akkor leregisztr�lja a pozicio-rol az olajfoltot.
	@Override
	public void tick() {
		kopas -= 1;
		if(kopas <= 0 ){
			pozicio.leregisztral(this);
		}
	}
	
	//Visszat�r igaz �rt�kel, mert az olajfoltot szenyez�d�snek tekintj�k.
	@Override
	public boolean szennyezodesVagyok(){
		return true;
	}
	
	//Megsemmis�ti az olajfoltot.
	public void megsemmisul () {
		pozicio.leregisztral(this);
		System.out.println("["+nev+"] megsemmis�lt.");
	}
	
	//Viasszaadja az olajfolt pozicio attrib�tum�t. 
	@Override
	public Mezo getPozicio() {
		return pozicio;
	}

	//Bea�ll�tja az olajfolt pozicio attrib�tum�t.
	@Override
	public void setPozicio(Mezo m) {
		pozicio = m;
	}
	
	//Visszat�r a kopas attrib�tum �rt�k�vel.
	public int getkopas(){
		return kopas;
	}
	
	//Be�ll�tja a kopas attrib�tum �rt�k�t.
	public void setkopas(int kopas){
		this.kopas=kopas;
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