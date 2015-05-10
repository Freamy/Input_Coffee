public class Kisrobot implements Mezonallo {

	private Mezo pozicio;			//A kisrobot tart�zkod�si mez�je.
	private Navigator navigator;	//A kisrobot elt�rolja, hogy kisz�moltathassa vele azt, hogy hova ugorjon.
	private boolean lekoppant;		//Igaz ha robotnak vagy kisrobotnak �tk�z�tt.
	private int megsemmisul;		//Mennyi k�r ut�n semmis�l meg a kisrobot.	
	
	private String nev;
	private static int autoincrement = 0;
	private GrafikusKisrobot grafikusKisrobot;
	
	public Kisrobot(Mezo mezo, Navigator navigator){
		this.navigator = navigator;
		pozicio = mezo;
		lekoppant= false;
		
		autoincrement++;
		nev = "kisrobot" + autoincrement;
		int[] kord = navigator.koordinataKonverter(mezo);
		System.out.println("["+nev+"] l�trej�tt x=("+kord[0]+","+kord[1]+").");
		pozicio.beregisztral(this);
	}
	
	//Navig�torral a kisrobot saj�t pozici�ja seg�ts�g�vel kisz�molja hova kell ugrania.
	//Leregrisztr�l aktu�lis pozici�j�r�l �s beregrisztr�l a kapott helyre.
	//Amenyiben ell�k�dik, visszat�r eredeti hely�re.
	public void ugrik(){
		Mezo hova = navigator.kozeliszennyezodes(pozicio);
		pozicio.leregisztral(this);
		
		int[] kord = navigator.koordinataKonverter(hova);
		System.out.println("["+nev+"] elugrott a(z) ("+kord[0]+","+kord[1]+") kordin�t�ra.");
		
		hova.beregisztral(this);
		if(lekoppant){
			hova.leregisztral(this);
			int[] kord2 = navigator.koordinataKonverter(pozicio);
			System.out.println("["+nev+"] visszal�p ("+kord2[0]+","+kord2[1]+").");
			pozicio.beregisztral(this);

		} else {
			pozicio = hova;
		}
		lekoppant = false;
	}	
	
	//A kisrobot pozicioj�ra �rkez� elemnek sz�l, hogy kisrobotra l�pet.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.kisrobotraLeptem(this);
	}

	//A param�ter�l kapott ragacsot feltakar�tja. Leregisztr�lja a ragacs pozicio attrib�tum�r�l.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		if(!lekoppant){
			System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
			kireLeptem.megsemmisul();
		}	
	}
	
	//A param�ter�l kapott olajfoltot feltakar�tja. Leregisztr�lja az olajfolt pozicio attrib�tum�r�l.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		if(!lekoppant){
			System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
			kireLeptem.megsemmisul();
		}
	}

	//A kisrobot ell�k�dik.Be�ll�tja lekoppant attrib�tum�t true-ba.
	@Override
	public void robotraLeptem(Robot kireLeptem) {
		lekoppant = true;
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
	}

	//A kisrobot ell�k�dik.Be�ll�tja lekoppant attrib�tum�t true-ba.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
		lekoppant = true;
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		
	}

	//Viasszat�r false �rt�kkel, mert a kisrobotot nem tekintj�k szennyez�d�snek.
	@Override
	public boolean szennyezodesVagyok() {
		return false;
	}
	
	public void megsemmisul() {
		pozicio.leregisztral(this);
		
		System.out.println("["+nev+"] megsemmis�lt.");
	}
	
	//Megsemmis�ti a kisrobotot.
	public void robotMegsemmisiti () {
		
		pozicio.leregisztral(this);
		
		int kord[] = navigator.koordinataKonverter(pozicio);
		
		new Olajfolt (pozicio, 1, nev, kord);
		
		System.out.println("["+nev+"] megsemmis�lt.");
	}
	
	//Visszat�r a kisrobot pozicio attrib�trum�val.
	@Override
	public Mezo getPozicio() {
		return pozicio;
	}

	//Be�ll�tja a m-re a pozicio �rt�k�t.
	@Override
	public void setPozicio(Mezo m) {
		pozicio = m;
	}

	//Be�ll�tja false-ra az lekoppant �rt�k�t. Ha a kisrobot ideje lej�rt akkor leregisztr�lja mag�t.
	@Override
	public void tick() {
		lekoppant = false;
		megsemmisul -= 1;
		if(megsemmisul <= 0){
			pozicio.leregisztral(this);
		}
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}
	
	public void setkopas(int kop) {
		
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean getMegsemmisult() {
		// TODO Auto-generated method stub
		return false;
	}

	public GrafikusKisrobot getGrafika() {
		// TODO Auto-generated method stub
		return grafikusKisrobot;
	}
	public void setGrafika(GrafikusKisrobot gk) {
		// TODO Auto-generated method stub
		this.grafikusKisrobot = gk;
	}
}
