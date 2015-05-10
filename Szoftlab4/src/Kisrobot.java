public class Kisrobot implements Mezonallo {

	private Mezo pozicio;			//A kisrobot tartózkodási mezõje.
	private Navigator navigator;	//A kisrobot eltárolja, hogy kiszámoltathassa vele azt, hogy hova ugorjon.
	private boolean lekoppant;		//Igaz ha robotnak vagy kisrobotnak ütközött.
	private int megsemmisul;		//Mennyi kör után semmisül meg a kisrobot.	
	
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
		System.out.println("["+nev+"] létrejött x=("+kord[0]+","+kord[1]+").");
		pozicio.beregisztral(this);
	}
	
	//Navigátorral a kisrobot saját poziciója segítségével kiszámolja hova kell ugrania.
	//Leregrisztrál aktuális poziciójáról és beregrisztrál a kapott helyre.
	//Amenyiben ellökõdik, visszatér eredeti helyére.
	public void ugrik(){
		Mezo hova = navigator.kozeliszennyezodes(pozicio);
		pozicio.leregisztral(this);
		
		int[] kord = navigator.koordinataKonverter(hova);
		System.out.println("["+nev+"] elugrott a(z) ("+kord[0]+","+kord[1]+") kordinátára.");
		
		hova.beregisztral(this);
		if(lekoppant){
			hova.leregisztral(this);
			int[] kord2 = navigator.koordinataKonverter(pozicio);
			System.out.println("["+nev+"] visszalép ("+kord2[0]+","+kord2[1]+").");
			pozicio.beregisztral(this);

		} else {
			pozicio = hova;
		}
		lekoppant = false;
	}	
	
	//A kisrobot poziciojára érkezõ elemnek szól, hogy kisrobotra lépet.
	@Override
	public void jottValaki(Mezonallo joveveny) {
		joveveny.kisrobotraLeptem(this);
	}

	//A paraméterül kapott ragacsot feltakarítja. Leregisztrálja a ragacs pozicio attribútumáról.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		if(!lekoppant){
			System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
			kireLeptem.megsemmisul();
		}	
	}
	
	//A paraméterül kapott olajfoltot feltakarítja. Leregisztrálja az olajfolt pozicio attribútumáról.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		if(!lekoppant){
			System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
			kireLeptem.megsemmisul();
		}
	}

	//A kisrobot ellökõdik.Beállítja lekoppant attribútumát true-ba.
	@Override
	public void robotraLeptem(Robot kireLeptem) {
		lekoppant = true;
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
	}

	//A kisrobot ellökõdik.Beállítja lekoppant attribútumát true-ba.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
		lekoppant = true;
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		
	}

	//Viasszatér false értékkel, mert a kisrobotot nem tekintjük szennyezõdésnek.
	@Override
	public boolean szennyezodesVagyok() {
		return false;
	}
	
	public void megsemmisul() {
		pozicio.leregisztral(this);
		
		System.out.println("["+nev+"] megsemmisült.");
	}
	
	//Megsemmisíti a kisrobotot.
	public void robotMegsemmisiti () {
		
		pozicio.leregisztral(this);
		
		int kord[] = navigator.koordinataKonverter(pozicio);
		
		new Olajfolt (pozicio, 1, nev, kord);
		
		System.out.println("["+nev+"] megsemmisült.");
	}
	
	//Visszatér a kisrobot pozicio attribútrumával.
	@Override
	public Mezo getPozicio() {
		return pozicio;
	}

	//Beállítja a m-re a pozicio értékét.
	@Override
	public void setPozicio(Mezo m) {
		pozicio = m;
	}

	//Beállítja false-ra az lekoppant értékét. Ha a kisrobot ideje lejárt akkor leregisztrálja magát.
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
