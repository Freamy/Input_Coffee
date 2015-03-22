public class Robot implements Mezonallo{
	private Mezo pozicio;
	private Navigator navigator;
	private Sebesseg sebesseg;
	private boolean vesztettem;
	private int ragacsDb;
	private int olajDb;
	private double megtettUt;
	
	public Robot(Mezo mezo, Navigator navigator){
		this.pozicio = mezo;
		this.navigator = navigator;
		vesztettem = false;
		ragacsDb = 5;
		olajDb = 5;
		megtettUt = 0;
	}
	
	public void lep(Sebesseg valtozas, boolean olajfoltotTesz, boolean ragacsotTesz){
		System.out.println("// Ha volt r� ig�ny akkor lerak olajfoltot vagy ragacsot azt�n leregisztr�l a mez�r�l.");
		System.out.println("// Megh�vja a sebess�g hozz�ad f�ggv�ny�t �s a robot ugrik f�ggv�ny�t.");
		if(olajfoltotTesz){
			System.out.println("[robot: Robot]--->   olajfoltotTesz()  --->[robot: Robot]");
			olajfoltotTesz();
			System.out.println("[robot: Robot]<---return olajfoltotTesz<---[robot: Robot]");
		}
		if(ragacsotTesz){
			System.out.println("[robot: Robot]--->   ragacsotTesz()  --->[robot: Robot]");
			ragacsotTesz();
			System.out.println("[robot: Robot]<---return ragacsotTesz<---[robot: Robot]");
		}
		System.out.println("[robot: Robot]--->leregisztral(robot)--->[pozicio: Mezo]");
		pozicio.leregisztral(this);
		System.out.println("[robot: Robot]<---return leregisztral<---[pozicio: Mezo]");
		System.out.println("[robot: Robot]--->hozzaad(Sebesseg valtozas)--->[sebesseg: Sebesseg]");
		sebesseg.hozzaad(valtozas);
		System.out.println("[robot: Robot]<---      return hozzaad      <---[sebesseg: Sebesseg]");
		System.out.println("[robot: Robot]--->   ugrik()  --->[robot: Robot]");
		ugrik();
		System.out.println("[robot: Robot]<---return ugrik<---[robot: Robot]");
	}

	public void sebessegvaltozas(Sebesseg valtozas){}
	
	public void ugrik(){
		System.out.println("// Megh�vja a Navig�tor athelyez f�ggv�ny�t, a visszat�r�si �rt�kk�nt kapott poz�ci�t be�ll�tja");
		System.out.println("// az �j poz�ci�j�nak. Sebess�g�t m�dos�that�v� teszi. A Navig�tor kulsomezo f�ggv�nye seg�ts�g�vel");
		System.out.println("// leelen�rzi hogy k�ls� mez�re l�pett-e: ");
		System.out.println("//    ha igen akkor be�ll�tja a vesztettem attr�b�tum�t igazra.");
		System.out.println("//    ha nem akkor beregisztr�l arra a mez�re ahov� ker�lt.");
		
		System.out.println("[robot: Robot]--->athelyez(pozicio,sebesseg)--->[navigator: Navigator]");
		pozicio = navigator.athelyez(pozicio,sebesseg);
		System.out.println("[robot: Robot]<---         Mezo hova        <---[navigator: Navigator]");
		
		System.out.println("[robot: Robot]--->modosithato()--->[sebesseg: Sebesseg]");
		sebesseg.modosithato();
		System.out.println("[robot: Robot]<---modosithato()<---[sebesseg: Sebesseg]");
		
		System.out.println("[robot: Robot]--->kulsomezo(pozicio)--->[navigator: Navigator]");
		if(navigator.kulsoMezo(pozicio)){
			System.out.println("[robot: Robot]<---        igaz      <---[navigator: Navigator]");
			vesztettem = true;
			System.out.println("// Be�ll�tja a robot vesztettem attrib�tum�t igazra.");
		}
		else{
			System.out.println("[robot: Robot]<---       hamis      <---[navigator: Navigator]");
			System.out.println("[robot: Robot]--->beregisztral(robot)--->[pozicio: Mezo]");
			pozicio.beregisztral(this);
			System.out.println("[robot: Robot]<---return beregisztral<---[pozicio: Mezo]");
		}
		//Itt kell majd hozz�adni a megtett �thoz a sebess�gvektor hossz�t.
		megtettUt+=3;
	}
	
	public void ragacsotTesz(){
		System.out.println("// Letesz egy ragacsot az aktu�lis hely�re.");
		System.out.println("[robot Robot]--->Ragacs(pozicio)--->[ragacs:Ragacs]");
		// A ragacs a konstruktor�ban kapott mez�re beregisztr�lja saj�t mag�t.
		Ragacs ragacs = new Ragacs(pozicio);
	}
	
	public void olajfoltotTesz(){
		System.out.println("// Letesz egy olajfoltot az aktu�lis hely�re");
		System.out.println("[robot Robot]--->Olajfolt(pozicio)--->[olajfolt:Olajfolt]");
		// Az olajfolt a konstruktor�ban kapott mez�re beregisztr�lja saj�t mag�t.
		Olajfolt olajfolt = new Olajfolt(pozicio);
	}
	
	public boolean getVesztett(){
		System.out.println("// Visszaadja hogy vesztett-e.");
		// Ezt majd a visszat�r�s ut�n a J�t�kmesternek kell lekezelnie.
		return vesztettem;
	}
	
	public void lokodik(Sebesseg ujsebesseg){
		System.out.println("// Az ell�k�tt robot sebess�ge megv�ltozik az �t ell�k� robot sebess�g�nek a fel�re.");
		System.out.println("// A sebess�g megv�ltoztat�sa �s az �rkez�si mez� kisz�m�t�sa ut�n");
		System.out.println("// megh�vja mag�ra az ugrik f�ggv�nyt.");
		this.sebesseg = ujsebesseg;
		System.out.println("[kireLeptem Robot]--->leregisztral(kireLeptem)--->[pozicio Mezo]");
		pozicio.leregisztral(this);
		System.out.println("[kireLeptem Robot]<---  return leregisztral  <---[pozicio Mezo]");
		System.out.println("[kireLeptem Robot]--->   ugrik()  --->[kireLeptem Robot]");
		ugrik();
		System.out.println("[kireLeptem Robot]<---return ugrik<---[kireLeptem Robot]");
	}
	
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem){
		System.out.println("// Az aktu�lis sebess�get lefelezi a fel�re �s ehhez a");
		System.out.println("// Sebesseg oszt�lynak a felez met�dus�t h�vja meg.");
		System.out.println("[robot Robot]--->   lefelez()  --->[sebesseg Sebesseg]");
		sebesseg.felez(sebesseg);
		System.out.println("[robot Robot]<---return lefelez<---[sebesseg Sebesseg]");
	}
	
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		System.out.println("// Be�ll�tja a sebess�g�nek valtoztathato attrib�tum�t hamisra.");
		System.out.println("[robot Robot]--->   modosithatatlan()  --->[sebesseg Sebesseg]");
		sebesseg.modosithatatlan();
		System.out.println("[robot Robot]<---return modosithatatlan<---[sebesseg Sebesseg]");
	}
	
	@Override
	public void robotraLeptem(Robot kireLeptem){
		System.out.println("// Ez h�v�dik meg ha a robot robottal �tk�zik.");
		// Gondolom nem null�val szeretn�nk hogy elugorjon, de az �n verzi�mban m�g nincs a sebess�g f�ggv�nynek
		// param�teres konstruktora.
		Sebesseg ujsebesseg = new Sebesseg();
		System.out.println("[robot Robot]--->lokodik(ujsebesseg)--->[kireLeptem: Robot]");
		kireLeptem.lokodik(ujsebesseg);
		System.out.println("[robot Robot]<---   return lokodik  <---[kireLeptem: Robot]");
	}
	
	@Override
	public void jottValaki(Mezonallo joveveny){
		System.out.println("// A param�ter�l kapott j�vev�nynek megh�vja a robotraLeptem met�dus�t.");
		System.out.println("[robot Robot]--->robotraLeptem(robot)--->[sebesseg Sebesseg]");
		joveveny.robotraLeptem(this);
		System.out.println("[robot Robot]<---return robotraLeptem<---[sebesseg Sebesseg]");
	}
}
