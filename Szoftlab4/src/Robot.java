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
	
	// Ha az olajfoltotTesz igaz, akkor megh�vja az olajfoltotTesz f�ggv�ny�t,
	// ha pedig a ragacsotTesz igaz, akkor megh�vja a ragacsotTesz f�ggv�ny�t.
	// Ezut�n megh�vja a sebessegvaltoztatas f�ggv�nye, aminek
	// �tadja a param�ter�l kaporr valtozas -t. Ha ez is k�sz akkor megh�v�dik az ugrik f�ggv�ny.
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

	//Szerintem ez a f�ggv�ny m�g mindig nem sz�ks�ges.
	public void sebessegvaltozas(Sebesseg valtozas){}
	
	// Letesz egy ragacsot az aktu�lis hely�re.
	public void ragacsotTesz(){
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			Ragacs ragacs = new Ragacs(navigator.getMezo(0));
			pozicio.beregisztral(ragacs);
		}
	}
	
	// Letesz egy olajfoltot az aktu�lis hely�re.
	public void olajfoltotTesz(){
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			Olajfolt olajfolt = new Olajfolt(navigator.getMezo(0));
			pozicio.beregisztral(olajfolt);
		}
	}
	
	// Megh�vja a Navig�tor athelyez f�ggv�ny�t, a visszat�r�si �rt�kk�nt kapott poz�ci�t be�ll�tja
	// az �j poz�ci�j�nak. Sebess�g�t m�dos�that�v� teszi. A Navig�tor kulsomezo f�ggv�nye seg�ts�g�vel
	// leelen�rzi hogy k�ls� mez�re l�pett-e: 
	//    ha igen akkor be�ll�tja a vesztettem attr�b�tum�t igazra.
	//    ha nem akkor beregisztr�l arra a mez�re ahov� ker�lt.
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
		pozicio.leregisztral(this);
		vesztettem = true;
	}
	
	// A sebess�gek �sszehasonl�t�s�ban az a robot nyert, akire r�ugrottak,
	// a sebess�ge a k�t robot sebess�g�nek vektor�talaga lesz.
	// Az ugr� robotra megh�v�dik a vesztett�l attrib�tum.
	public void nyertel(Robot robot){
		atlag(robot.getsebesseg());
		robot.vesztettel();
	}
	
	// A robot ragacsra l�pett, a sebess�ge lefelez�dik.
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem){
		sebesseg.felez();
	}
	
	// A robot ragacsra l�pett, a sebess�g v�ltoz�j�nak modosithato attrib�tum�t be�ll�tja hamisra.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		sebesseg.modosithatatlan();
	}
	
	// A param�ter�l kapott kisrobot mezo attrib�tum�nak
	// megh�vja a leregisztr�l f�ggv�ny�t �s a Kisrobot megsemmis�l.
	void kisrobotraLeptem(Kisrobot kireLeptem){
		pozicio.leregisztral(kireLeptem);
	}
	
	// Ilyenkor a robot a kireLeptem robottal �tk�z�tt.
	// Megh�v�dik a robot sebesseg attrib�tum�nak a hasonl�t f�ggv�nye, hogy �sszehasonl�tsa
	// a saj�t sebess�g�t a kireLeptem robot�val.
	// A gy�ztes sebess�ge a k�t robot sebess�g�nek vektor�tlaga lesz, a vesztes leregisztr�l
	// a mez�r�l �s be�ll�tja a saj�t vesztettem attrib�t�m�nak �rt�k�t igazra.
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
	
	// A mez�n ahol a robot �ll, j�tt valaki. A robot sz�l neki hogy robotra l�pt�l.
	@Override
	public void jottValaki(Mezonallo joveveny){
		joveveny.robotraLeptem(this);
	}
	
	// Visszat�r hamis �rt�kkel.
	boolean szennyezodesVagyok(){
		return false;
	}
	
	// Visszat�r a sebesseg attrib�tummal.
	Sebesseg getsebesseg(){
		return sebesseg;
	}
	
	// Be�ll�tja a sebess�g attrib�tumot.
	void setsebesseg(Sebesseg sebesseg){
		this.sebesseg = sebesseg;
	}
	
	// Visszat�r a mezo attrib�tummal.
	Mezo getpozicio(){
		return pozicio;
	}
	
	// Be�ll�tja a mezo attrib�tumot.
	void setpozicio(Mezo poz){
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
