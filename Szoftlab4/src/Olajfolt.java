public class Olajfolt implements Mezonallo{
	
	private Gyar grafikusGyar;
	
	private Mezo pozicio;		//Az olajfolt tart�zkod�si mez�je.
	private int kopas;			//Megmutatja, hogy h�ny k�r m�lva sz�v�dik fel az olajfolt
								//takar�t�s n�lk�l.
	
	private boolean megsemmisult;
	private String nev;

	
	private GrafikusOlajfolt grafika;

	private static int autoincrement = 0;
	
	public Olajfolt(Mezo mezo,int kopas, String kisrobotneve, int kord[]){

		this.pozicio = mezo;
		this.kopas = kopas;
		
		grafikusGyar = new OlajGyar();
		
		grafika = (GrafikusOlajfolt) grafikusGyar.grafikaKeszitese(this);
		
		if(kisrobotneve != "") {
			nev = kisrobotneve+"olajfoltja";
		} else {
		autoincrement++;
		nev = "olajfolt" + autoincrement;
		}
		megsemmisult = false;
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
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		pozicio.leregisztral(kireLeptem);
	}

	//A mez�re �jonnan �rkezett olajfolt, leregisztr�lja m�r a mez�n l�v� olajfoltot.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		pozicio.leregisztral(kireLeptem);
	}

	//Nem hajt v�gre feladatot.
	@Override
	public void robotraLeptem(Robot kireLeptem) {	
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
	}
	
	//Nem hajt v�gre feladatot.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem){
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
	}
	
	//�j k�rt kezd.Cs�kkenti a kop�s �rt�k�t eggyel �s ha nulla vagy az allati �rt�ket vett fel
	//akkor leregisztr�lja a pozicio-rol az olajfoltot.
	@Override
	public void tick() {
		kopas -= 1;
		if(kopas <= 0 ){
			pozicio.leregisztral(this);
			megsemmisult = true;
			if(grafika!=null) grafika.frissit(this);
		}
	}
	
	public void tickend() {
		
	}
	
	//Visszat�r igaz �rt�kel, mert az olajfoltot szenyez�d�snek tekintj�k.
	@Override
	public boolean szennyezodesVagyok(){
		return true;
	}
	
	//Megsemmis�ti az olajfoltot.
	public void megsemmisul () {
		megsemmisult = true;
		pozicio.leregisztral(this);
		System.out.println("["+nev+"] megsemmis�lt.");
		if(grafika!=null) grafika.frissit(this);
	}
	
	public boolean getMegsemmisult() {
		return megsemmisult;
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
		if(grafika!=null) grafika.frissit(this);
	}
	
	//Visszat�r a kopas attrib�tum �rt�k�vel.
	public int getkopas(){
		return kopas;
	}
	
	//Be�ll�tja a kopas attrib�tum �rt�k�t.
	public void setkopas(int kopas){
		this.kopas=kopas;
		if(kopas <= 0)  {
			megsemmisult = true;
			if(grafika!=null) grafika.frissit(this);
		}
	}
	
	public String getNev() {
		return nev;
	}
	
	public void setNev(String nev) {
		this.nev = nev;
	}
	
	public void setGrafikusOlajfolt (GrafikusOlajfolt go) {
		this.grafika = go;
	}
	
	public GrafikusOlajfolt getGrafikusOlajfolt (){
		return grafika;
	}
	public void adatokKiirasa(String param) {
		if(param.equals("") || param.equals(nev)) {
			System.out.println("["+nev+"]:\nPozicio: "+pozicio.getNev()+"\nKopas: "+kopas
					+"\n");
		}
	}

}
