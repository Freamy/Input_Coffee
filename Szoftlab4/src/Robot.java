public class Robot implements Mezonallo{
	private Mezo pozicio;
	private Navigator navigator;
	private Sebesseg sebesseg;
	private boolean vesztettem;
	private int ragacsDb;
	private int olajDb;
	private double megtettUt;
	private boolean ugrottMar;
	
	private String nev;
	private static int autoincrement=0;
	
	private GrafikusRobot grafikusRobot;
	
	public Robot(Mezo mezo, Navigator navigator){
		this.pozicio = mezo;
		this.navigator = navigator;
		this.vesztettem = false;
		this.ragacsDb = 5;
		this.olajDb = 5;
		this.megtettUt = 0;
		this.ugrottMar = false;
		sebesseg = new Sebesseg(0,0);
		
		autoincrement++;
		nev = "robot" + autoincrement;
		int[] kord = navigator.koordinataKonverter(mezo);
		System.out.println("["+nev+"] l�trej�tt, x=("+kord[0]+","+kord[1]+"), v=("
						+sebesseg.getx()+","+sebesseg.gety()+"), "
						+((sebesseg.getmodosithato()) ? "modosithato" : "modosithatatlan")+ ", "
						+ragacsDb+" ragacs, "
						+olajDb+" olajfolt, "
						+((vesztettem) ? "vesztett" : "nem vesztett")+ ", "
						+megtettUt+" �t.");
		
		mezo.beregisztral(this);
	}
	
	// Ha az olajfoltotTesz igaz, akkor megh�vja az olajfoltotTesz f�ggv�ny�t,
	// ha pedig a ragacsotTesz igaz, akkor megh�vja a ragacsotTesz f�ggv�ny�t.
	// Ezut�n megh�vja a sebessegvaltoztatas f�ggv�nye, aminek
	// �tadja a param�ter�l kaporr valtozas -t. Ha ez is k�sz akkor megh�v�dik az ugrik f�ggv�ny.
	public void lep(Sebesseg valtozas, boolean olajfoltotTesz, boolean ragacsotTesz){
		if (vesztettem) {
			System.out.println("Hiba: ["+nev+"] nem tud ugrani, mert vesztett.");
		} else if(ugrottMar) {
			System.out.println("Hiba: ["+nev+"] ebben a k�rben m�r ugrott.");
		} else {
			if(olajfoltotTesz){
				olajfoltotTesz();
				//Nem itt vonunk ki az olajDb-b�l hanem a letev� f�ggv�nyen bel�l!
			}
			else if(ragacsotTesz){
				ragacsotTesz();
				//Nem itt vonunk ki a ragacsDb-b�l hanem a letev� f�ggv�nyen bel�l!
			}
			pozicio.leregisztral(this);
			sebesseg.hozzaad(valtozas);
			ugrik();
			ugrottMar = true;
		}
	}

	//Szerintem ez a f�ggv�ny m�g mindig nem sz�ks�ges.
	public void sebessegvaltozas(Sebesseg valtozas){}
	
	// Letesz egy ragacsot az aktu�lis hely�re.
	public void ragacsotTesz(){
		if(ragacsDb==0) {
			System.out.println("Hiba: ["+nev+"] elfogyott a ragacsk�szlet.");
		} else {
		System.out.println("["+nev+"] ragacsot tesz le.");
		int kord[] = navigator.koordinataKonverter(pozicio);
		Ragacs ragacs = new Ragacs(pozicio, 5, kord);
		pozicio.beregisztral(ragacs);
		ragacsDb--;
		}
	}
	
	// Letesz egy olajfoltot az aktu�lis hely�re.
	public void olajfoltotTesz(){
		if(olajDb==0) {
			System.out.println("Hiba: ["+nev+"] elfogyott az olajfoltk�szlet.");
		} else {
		System.out.println("["+nev+"] olajfoltot tesz le.");
		int kord[] = navigator.koordinataKonverter(pozicio);
		Olajfolt olajfolt = new Olajfolt(pozicio, 5, "", kord);
		pozicio.beregisztral(olajfolt);
		olajDb--;
		}
	}
	
	// Megh�vja a Navig�tor athelyez f�ggv�ny�t, a visszat�r�si �rt�kk�nt kapott poz�ci�t be�ll�tja
	// az �j poz�ci�j�nak. Sebess�g�t m�dos�that�v� teszi. A Navig�tor kulsomezo f�ggv�nye seg�ts�g�vel
	// leelen�rzi hogy k�ls� mez�re l�pett-e: 
	//    ha igen akkor be�ll�tja a vesztettem attr�b�tum�t igazra.
	//    ha nem akkor beregisztr�l arra a mez�re ahov� ker�lt.
	public void ugrik(){	
		pozicio = navigator.athelyez(pozicio,sebesseg);
		int[] kord = navigator.koordinataKonverter(pozicio);
		System.out.println("["+nev+"] elugrott a(z) ("+kord[0]+","+kord[1]+") kordin�t�ra.");
		sebesseg.modosithato();
		pozicio.beregisztral(this); //A robot nem regisztr�l le a mez�r�l ha vesztett.
		if(navigator.kulsoMezo(pozicio)){
			vesztettel(); //Az�rt hogy ki�rja a sz�veget a vesztett f�ggv�ny �s ha m�dos�tani akarjuk ne 2 helyen kelljen.
		}
	}
	

	
	// Az ell�k�tt robot sebess�ge megv�ltozik az �t ell�k� robot sebess�g�nek a fel�re.
	// A sebess�g megv�ltoztat�sa �s az �rkez�si mez� kisz�m�t�sa ut�n
	// megh�vja mag�ra az ugrik f�ggv�nyt.
	//		public void lokodik(Sebesseg ujsebesseg){
	//			this.sebesseg = ujsebesseg;
	//			pozicio.leregisztral(this);
	//			ugrik();
	//		}
	
	// A sebess�gek �sszehasonl�t�s�ban vesztes robotra h�vj�k meg ezt a f�ggv�nyt.
	// A robot akire megh�vj�k leregisztr�l a mez�r�l.
	public void vesztettel(){
		System.out.println("["+nev+"] vesztett.");
		vesztettem = true;
	}
	
	// A sebess�gek �sszehasonl�t�s�ban az a robot nyert, akire r�ugrottak,
	// a sebess�ge a k�t robot sebess�g�nek vektor�talaga lesz.
	// Az ugr� robotra megh�v�dik a vesztett�l attrib�tum.
	public void nyertel(Robot robot){
		sebesseg.atlag(robot.getSebesseg());
		robot.vesztettel();
	}
	
	// A robot ragacsra l�pett, a sebess�ge lefelez�dik.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem){
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		sebesseg.felez();
		System.out.println("["+nev+"] v=("+sebesseg.getx()+","+sebesseg.gety()+").");
		int kopas = kireLeptem.getkopas() - 1;
		kireLeptem.setkopas(kopas);
		
	}
	
	// A robot ragacsra l�pett, a sebess�g v�ltoz�j�nak modosithato attrib�tum�t be�ll�tja hamisra.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		sebesseg.modosithatatlan();
		System.out.println("["+nev+"] sebess�ge nem m�dos�that�.");
	}
	
	// A param�ter�l kapott kisrobot mezo attrib�tum�nak
	// megh�vja a leregisztr�l f�ggv�ny�t �s a Kisrobot megsemmis�l.
	public void kisrobotraLeptem(Kisrobot kireLeptem){
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		kireLeptem.robotMegsemmisiti();
	}
	
	// Ilyenkor a robot a kireLeptem robottal �tk�z�tt.
	// Megh�v�dik a robot sebesseg attrib�tum�nak a hasonl�t f�ggv�nye, hogy �sszehasonl�tsa
	// a saj�t sebess�g�t a kireLeptem robot�val.
	// A gy�ztes sebess�ge a k�t robot sebess�g�nek vektor�tlaga lesz, a vesztes leregisztr�l
	// a mez�r�l �s be�ll�tja a saj�t vesztettem attrib�t�m�nak �rt�k�t igazra.
	@Override
	public void robotraLeptem(Robot kireLeptem){
		System.out.println("["+nev+"] �tk�z�tt: "+kireLeptem.getNev()+".");
		if(sebesseg.hasonlit(kireLeptem.getSebesseg())){
			kireLeptem.megsemmisul();
			sebesseg.atlag(kireLeptem.sebesseg);
		}
		else {
			megsemmisul();
			kireLeptem.sebesseg.atlag(sebesseg);
		}	
	}
	
	// A mez�n ahol a robot �ll, j�tt valaki. A robot sz�l neki hogy robotra l�pt�l.
	@Override
	public void jottValaki(Mezonallo joveveny){
		joveveny.robotraLeptem(this);
	}
	
	// Visszat�r hamis �rt�kkel.
	public boolean szennyezodesVagyok(){
		return false;
	}
	
	//Megsemmis�ti a robotot.
	public void megsemmisul () {
		pozicio.leregisztral(this);
		System.out.println("["+nev+"] megsemmis�lt.");
	}
	
	// Visszat�r a sebesseg attrib�tummal.
	Sebesseg getSebesseg(){
		return sebesseg;
	}
	
	// Be�ll�tja a sebess�g attrib�tumot.
	void setSebesseg(Sebesseg sebesseg){
		System.out.println("["+nev+"] v=("+sebesseg.getx()+","+sebesseg.gety()+").");
		this.sebesseg = sebesseg;
	}
	
	// Visszat�r a mezo attrib�tummal.
	public Mezo getPozicio(){
		return pozicio;
	}
	
	// Be�ll�tja a mezo attrib�tumot.
	public void setPozicio(Mezo poz){
		this.pozicio = poz;
	}
	
	// int getkorSzam(){return 0;}
	
	// A f�ggv�ny visszat�r�si �rt�ke a vesztettem attrib�tum �rt�ke.
	// Igaz: m�r vesztett, kiker�lt m�r a j�t�kb�l. 
	// Hamis: m�g j�t�kban van.
	public boolean getVesztett(){
		return vesztettem;
	}
	
	// Be�ll�tja a vesztettem attrib�tumot.
	void setVesztett(boolean vesztett){
		this.vesztettem = vesztett;
	}
	
	// Visszat�r a ragacsDb attrib�tummal.
	public int getRagacsDb(){
		return ragacsDb;
	}
	
	// Be�ll�tja a ragacsDb attrib�tumot.
	public void setRagacsDb(int darab){
		this.ragacsDb = darab;
	}
	
	// Visszat�r az olajDb attrib�tummal.
	public int getOlajDb(){
		return olajDb;
	}
	
	// Be�ll�tja a olajDb attrib�tumot.
	public void setOlajDb(int darab){
		this.olajDb = darab;
	}
	
	// Visszat�r a megtettUt attrib�tummal.
	public double getmegtettUt(){
		return megtettUt;
	}
	
	// Be�ll�tja a megtettUt attrib�tumot.
	public void setmegtettUt(double tav){
		this.megtettUt = tav;
	}
	
	// �j k�rt kezd.
	public void tick(){
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

	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return false;
	}
	
}