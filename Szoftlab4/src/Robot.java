import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Robot implements Mezonallo{
	private Mezo pozicio;
	private Navigator navigator;
	private Sebesseg sebesseg;
	private boolean vesztettem;
	private int ragacsDb;
	private int olajDb;
	private double megtettUt;
	private boolean ugrottMar;
	
	public Robot(Mezo mezo, Navigator navigator){
		this.pozicio = mezo;
		this.navigator = navigator;
		this.vesztettem = false;
		this.ragacsDb = 5;
		this.olajDb = 5;
		this.megtettUt = 0;
		this.ugrottMar = false;
		sebesseg = new Sebesseg();
		mezo.beregisztral(this);
	}
	
	// Ha az olajfoltotTesz igaz, akkor meghívja az olajfoltotTesz függvényét,
	// ha pedig a ragacsotTesz igaz, akkor meghívja a ragacsotTesz függvényét.
	// Ezután meghívja a sebessegvaltoztatas függvénye, aminek
	// átadja a paraméterül kaporr valtozas -t. Ha ez is kész akkor meghívódik az ugrik függvény.
	public void lep(Sebesseg valtozas, boolean olajfoltotTesz, boolean ragacsotTesz){
		if(olajfoltotTesz){
			olajfoltotTesz();
			olajDb--;
		}
		else if(ragacsotTesz){
			ragacsotTesz();
			ragacsDb--;
		}
		pozicio.leregisztral(this);
		sebesseg.hozzaad(valtozas);
		ugrik();
		ugrottMar = true;
	}

	//Szerintem ez a függvény még mindig nem szükséges.
	public void sebessegvaltozas(Sebesseg valtozas){}
	
	// Letesz egy ragacsot az aktuális helyére.
	public void ragacsotTesz(){
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			Ragacs ragacs = new Ragacs(navigator.getMezo(0));
			pozicio.beregisztral(ragacs);
		}
	}
	
	// Letesz egy olajfoltot az aktuális helyére.
	public void olajfoltotTesz(){
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			Olajfolt olajfolt = new Olajfolt(navigator.getMezo(0));
			pozicio.beregisztral(olajfolt);
		}
	}
	
	// Meghívja a Navigátor athelyez függvényét, a visszatérési értékként kapott pozíciót beállítja
	// az új pozíciójának. Sebességét módosíthatóvá teszi. A Navigátor kulsomezo függvénye segítségével
	// leelenõrzi hogy kûlsõ mezõre lépett-e: 
	//    ha igen akkor beállítja a vesztettem attríbútumát igazra.
	//    ha nem akkor beregisztrál arra a mezõre ahová kerûlt.
	public void ugrik(){	
		pozicio = navigator.athelyez(pozicio,sebesseg);
		sebesseg.modosithato();
		if(navigator.kulsoMezo(pozicio)){
			vesztettem = true;
		}
		else{
			pozicio.beregisztral(this);
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
		pozicio.leregisztral(this);
		vesztettem = true;
	}
	
	// A sebességek összehasonlításában az a robot nyert, akire ráugrottak,
	// a sebessége a két robot sebességének vektorátalaga lesz.
	// Az ugró robotra meghívódik a vesztettél attribútum.
	public void nyertel(Robot robot){
		atlag(robot.getsebesseg());
		robot.vesztettel();
	}
	
	// A robot ragacsra lépett, a sebessége lefelezõdik.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem){
		sebesseg.felez();
	}
	
	// A robot ragacsra lépett, a sebesség változójának modosithato attribútumát beállítja hamisra.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		sebesseg.modosithatatlan();
	}
	
	// A paraméterül kapott kisrobot mezo attribútumának
	// meghívja a leregisztrál függvényét és a Kisrobot megsemmisül.
	void kisrobotraLeptem(Kisrobot kireLeptem){
		pozicio.leregisztral(kireLeptem);
	}
	
	// Ilyenkor a robot a kireLeptem robottal ütközött.
	// Meghívódik a robot sebesseg attribútumának a hasonlít függvénye, hogy összehasonlítsa
	// a saját sebességét a kireLeptem robotéval.
	// A gyõztes sebessége a két robot sebességének vektorátlaga lesz, a vesztes leregisztrál
	// a mezõrõl és beállítja a saját vesztettem attribútúmának értékét igazra.
	@Override
	public void robotraLeptem(Robot kireLeptem){
		if(hasonlit(kireLeptem.getsebesseg())){
			atlag(kireLeptem.getsebesseg());
			kireLeptem.vesztettel();
		}
		else {
			kireLeptem.nyertel(this);
		}	
	}
	
	// A mezõn ahol a robot áll, jött valaki. A robot szól neki hogy robotra léptél.
	@Override
	public void jottValaki(Mezonallo joveveny){
		joveveny.robotraLeptem(this);
	}
	
	// Visszatér hamis értékkel.
	boolean szennyezodesVagyok(){
		return false;
	}
	
	// Visszatér a sebesseg attribútummal.
	Sebesseg getsebesseg(){
		return sebesseg;
	}
	
	// Beállítja a sebesség attribútumot.
	void setsebesseg(Sebesseg sebesseg){
		this.sebesseg = sebesseg;
	}
	
	// Visszatér a mezo attribútummal.
	Mezo getpozicio(){
		return pozicio;
	}
	
	// Beállítja a mezo attribútumot.
	void setpozicio(Mezo poz){
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
		ugrottMar = false;
	}
	
	private String bemenetBekerese(){
		try {
			System.out.print("? ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
