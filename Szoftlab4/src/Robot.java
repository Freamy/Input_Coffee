public class Robot implements Mezonallo{
	private Mezo pozicio;
	private Navigator navigator;
	private Sebesseg sebesseg;
	private boolean vesztettem;
	private int ragacsDb;
	private int olajDb;
	private double megtettUt;
	private boolean ugrottMar;
	private boolean megsemmisult;
	private String nev;

	private static int autoincrement=0;
	
	private Gyar grafikaGyar = new RobotGyar(Jatekmester.getKepernyo());
	private GrafikusRobot grafikusRobot;
	
	private boolean aktiv;
	
	/*public Robot(Mezo mezo, Navigator navigator){
		grafikaGyar = 

	}*/
	public Robot(String nev, Mezo mezo, Navigator navigator, Sebesseg sebesseg, int ragacsDb, int olajDb, boolean vesztettem, double megtettUt, Gyar gyar){
		
		this.nev = nev;
		this.navigator = navigator;
		this.sebesseg = sebesseg;
		this.vesztettem = vesztettem;
		this.ragacsDb = ragacsDb;
		this.olajDb = olajDb;
		this.megtettUt = megtettUt;
		this.ugrottMar = false;

		sebesseg = new Sebesseg(0,0);

		megsemmisult = false;
		
		int[] kord = navigator.koordinataKonverter(mezo);
		System.out.println("["+nev+"] létrejött, x=("+kord[0]+","+kord[1]+"), v=("
						+sebesseg.getx()+","+sebesseg.gety()+"), "
						+((sebesseg.getmodosithato()) ? "modosithato" : "modosithatatlan")+ ", "
						+ragacsDb+" ragacs, "
						+olajDb+" olajfolt, "
						+((vesztettem) ? "vesztett" : "nem vesztett")+ ", "
						+megtettUt+" út.");
		
		mezo.beregisztral(this);
		this.pozicio = mezo;
		grafikusRobot = (GrafikusRobot) gyar.grafikaKeszitese(Jatekmester.getKepernyo(), this);
	}
	
	// Ha az olajfoltotTesz igaz, akkor meghívja az olajfoltotTesz függvényét,
	// ha pedig a ragacsotTesz igaz, akkor meghívja a ragacsotTesz függvényét.
	// Ezután meghívja a sebessegvaltoztatas függvénye, aminek
	// átadja a paraméterül kaporr valtozas -t. Ha ez is kész akkor meghívódik az ugrik függvény.

	public void lep(Sebesseg valtozas, boolean olajfoltotTesz, boolean ragacsotTesz, Kepernyo k){

		if(!megsemmisult) {
		if (vesztettem) {
			System.out.println("Hiba: ["+nev+"] nem tud ugrani, mert vesztett.");
		} else if(ugrottMar) {
			System.out.println("Hiba: ["+nev+"] ebben a körben már ugrott.");
		} else {
			if(olajfoltotTesz){
				olajfoltotTesz("Olajfolt"+autoincrement+olajDb);
			}
				//Nem itt vonunk ki az olajDb-bõl hanem a letevõ függvényen belül!
			else if(ragacsotTesz){
				ragacsotTesz("Ragacs"+autoincrement+ragacsDb);
			}
				//Nem itt vonunk ki a ragacsDb-bõl hanem a letevõ függvényen belül!
			pozicio.leregisztral(this);
			sebesseg.hozzaad(valtozas);
			ugrik();
			ugrottMar = true;
		}
		}
	}

	//Szerintem ez a függvény még mindig nem szükséges.
	public void sebessegvaltozas(Sebesseg valtozas){}
	
	// Letesz egy ragacsot az aktuális helyére.

	public void ragacsotTesz(String ragacsneve){
		if(!megsemmisult) {
		System.out.println("["+nev+"] ragacsot tesz le.");

		if(ragacsDb==0) {
			System.out.println("Hiba: ["+nev+"] elfogyott a ragacskészlet.");
		} else {
		int kord[] = navigator.koordinataKonverter(pozicio);
		Ragacs ragacs = new Ragacs(pozicio, 5, kord,Jatekmester.getKepernyo());
		pozicio.beregisztral(ragacs);
		ragacsDb--;
		}
		}
	}
	
	// Letesz egy olajfoltot az aktuális helyére.

	public void olajfoltotTesz(String olajfoltneve){
		if(!megsemmisult) {
		System.out.println("["+nev+"] olajfoltot tesz le.");

		if(olajDb==0) {
			System.out.println("Hiba: ["+nev+"] elfogyott az olajfoltkészlet.");
		} else {
		int kord[] = navigator.koordinataKonverter(pozicio);

		Olajfolt olajfolt = new Olajfolt(pozicio, 5, "", kord,Jatekmester.getKepernyo());
		pozicio.beregisztral(olajfolt);

		olajDb--;
		}
		}
	}
	
	// Meghívja a Navigátor athelyez függvényét, a visszatérési értékként kapott pozíciót beállítja
	// az új pozíciójának. Sebességét módosíthatóvá teszi. A Navigátor kulsomezo függvénye segítségével
	// leelenõrzi hogy kûlsõ mezõre lépett-e: 
	//    ha igen akkor beállítja a vesztettem attríbútumát igazra.
	//    ha nem akkor beregisztrál arra a mezõre ahová került.
	public void ugrik(){
		if(!megsemmisult) {
		if (vesztettem) {
			System.out.println("Hiba: ["+nev+"] nem tud ugrani, mert vesztett.");
		} else if(ugrottMar) {
			System.out.println("Hiba: ["+nev+"] ebben a körben már ugrott.");
		} else {
		Mezo hova = navigator.athelyez(pozicio,sebesseg);
		if(hova!=null) {
		int[] kord = navigator.koordinataKonverter(hova);
		pozicio = hova;
		System.out.println("["+nev+"] elugrott a(z) ("+kord[0]+","+kord[1]+") kordinátára.");
		sebesseg.modosithato();
		pozicio.beregisztral(this); //A robot nem regisztrál le a mezõrõl ha vesztett.
		if(navigator.kulsoMezo(pozicio)){
			vesztettel(); //Azért hogy kiírja a szöveget a vesztett függvény és ha módosítani akarjuk ne 2 helyen kelljen.
		}
		} else {
			System.out.println("["+nev+"] nem tud ugrani, mert a pálya szélén van.");
		}
		}
		}
	}
	

	
	// Az ellökött robot sebessége megváltozik az õt ellökö robot sebességének a felére.
	// A sebesség megváltoztatása és az érkezési mezõ kiszámítása után
	// meghívja magára az ugrik függvényt.
	//		public void lokodik(Sebesseg ujsebesseg){
	//			this.sebesseg = ujsebesseg;
	//			pozicio.leregisztral(this);
	//			ugrik();
	//		}
	
	// A sebességek összehasonlításában vesztes robotra hívják meg ezt a függvényt.
	// A robot akire meghívják leregisztrál a mezõrõl.
	public void vesztettel(){
		if(!megsemmisult) {
		System.out.println("["+nev+"] vesztett.");
		vesztettem = true;
		}
	}
	
	// A sebességek összehasonlításában az a robot nyert, akire ráugrottak,
	// a sebessége a két robot sebességének vektorátalaga lesz.
	// Az ugró robotra meghívódik a vesztettél attribútum.
	public void nyertel(Robot robot){
		if(!megsemmisult) {
		sebesseg.atlag(robot.getSebesseg());
		robot.vesztettel();
		}
	}
	
	// A robot ragacsra lépett, a sebessége lefelezõdik.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem){
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		sebesseg.felez();
		System.out.println("["+nev+"] v=("+sebesseg.getx()+","+sebesseg.gety()+").");
		int kopas = kireLeptem.getkopas() - 1;
		kireLeptem.setkopas(kopas);
		
	}
	
	// A robot ragacsra lépett, a sebesség változójának modosithato attribútumát beállítja hamisra.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		sebesseg.modosithatatlan();
		System.out.println("["+nev+"] sebessége nem módosítható.");
	}
	
	// A paraméterül kapott kisrobot mezo attribútumának
	// meghívja a leregisztrál függvényét és a Kisrobot megsemmisül.
	public void kisrobotraLeptem(Kisrobot kireLeptem){
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		kireLeptem.robotMegsemmisiti(Jatekmester.getKepernyo());
	}
	
	// Ilyenkor a robot a kireLeptem robottal ütközött.
	// Meghívódik a robot sebesseg attribútumának a hasonlít függvénye, hogy összehasonlítsa
	// a saját sebességét a kireLeptem robotéval.
	// A gyõztes sebessége a két robot sebességének vektorátlaga lesz, a vesztes leregisztrál
	// a mezõrõl és beállítja a saját vesztettem attribútúmának értékét igazra.
	@Override
	public void robotraLeptem(Robot kireLeptem){
		System.out.println("["+nev+"] ütközött: "+kireLeptem.getNev()+".");
		if(sebesseg.hasonlit(kireLeptem.getSebesseg())){
			megsemmisul();
			kireLeptem.sebesseg.atlag(sebesseg);
			System.out.println("["+kireLeptem.getNev()+"] v=("+kireLeptem.sebesseg.getx()+","+kireLeptem.sebesseg.gety()+").");
		}
		else {
			kireLeptem.megsemmisul();
			sebesseg.atlag(kireLeptem.sebesseg);
			System.out.println("["+nev+"] v=("+sebesseg.getx()+","+sebesseg.gety()+").");
		}	
	}
	
	// A mezõn ahol a robot áll, jött valaki. A robot szól neki hogy robotra léptél.
	@Override
	public void jottValaki(Mezonallo joveveny){
		joveveny.robotraLeptem(this);
	}
	
	// Visszatér hamis értékkel.
	public boolean szennyezodesVagyok(){
		return false;
	}
	
	//Megsemmisíti a robotot.
	public void megsemmisul () {
		pozicio.leregisztral(this);
		megsemmisult = true;
		System.out.println("["+nev+"] megsemmisült.");
	}
	
	// Visszatér a sebesseg attribútummal.
	Sebesseg getSebesseg(){
		return sebesseg;
	}
	
	// Beállítja a sebesség attribútumot.
	void setSebesseg(Sebesseg sebesseg){
		System.out.println("["+nev+"] v=("+sebesseg.getx()+","+sebesseg.gety()+").");
		this.sebesseg = sebesseg;
	}
	
	// Visszatér a mezo attribútummal.
	public Mezo getPozicio(){
		return pozicio;
	}
	
	// Beállítja a mezo attribútumot.
	public void setPozicio(Mezo poz){
		this.pozicio = poz;
	}
	
	// int getkorSzam(){return 0;}
	
	// A függvény visszatérési értéke a vesztettem attribútum értéke.
	// Igaz: már vesztett, kikerült már a játékból. 
	// Hamis: még játékban van.
	public boolean getVesztett(){
		return vesztettem;
	}
	
	// Beállítja a vesztettem attribútumot.
	void setVesztett(boolean vesztett){
		this.vesztettem = vesztett;
	}
	
	// Visszatér a ragacsDb attribútummal.
	public int getRagacsDb(){
		return ragacsDb;
	}
	
	// Beállítja a ragacsDb attribútumot.
	public void setRagacsDb(int darab){
		this.ragacsDb = darab;
	}
	
	// Visszatér az olajDb attribútummal.
	public int getOlajDb(){
		return olajDb;
	}
	
	// Beállítja a olajDb attribútumot.
	public void setOlajDb(int darab){
		this.olajDb = darab;
	}
	
	// Visszatér a megtettUt attribútummal.
	public double getmegtettUt(){
		return megtettUt;
	}
	
	// Beállítja a megtettUt attribútumot.
	public void setmegtettUt(double tav){
		this.megtettUt = tav;
	}
	
	// Új kört kezd.
	public void tick(){

	}
	
	public void tickend() {
		ugrottMar = false;
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
		return pozicio.getX();
	}

	public int getY() {
		// TODO Auto-generated method stub
		return pozicio.getY();
	}

	public boolean getMegsemmisult() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getAktiv() {
		return aktiv;
	}
	
	public void setAktiv(boolean a){
		aktiv = a;
	}

	public GrafikusRobot getGrafika() {
		return grafikusRobot;
	}

	public void setGrafika(GrafikusRobot ge) {
		grafikusRobot = ge;
	}
	
	public void adatokKiirasa(String param) {
		if(param.equals("") || param.equals(nev)) {
			System.out.println("["+nev+"]:\nPozicio: "+pozicio.getNev()
					+"\nSebesseg: ("+sebesseg.getx()+","+sebesseg.gety()+")"
					+"\nVesztettem: "+vesztettem
					+"\nRagacsDb: "+ragacsDb
					+"\nOlajDb: "+olajDb
					+"\nMegtettUt: "+megtettUt
					+"\nUgrottMar: "+ugrottMar
					+"\n");
		}
	}

}
