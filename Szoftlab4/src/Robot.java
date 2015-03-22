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
		System.out.println("// Ha volt rá igény akkor lerak olajfoltot vagy ragacsot aztán leregisztrál a mezõrõl.");
		System.out.println("// Meghívja a sebesség hozzáad függvényét és a robot ugrik függvényét.");
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
		System.out.println("// Meghívja a Navigátor athelyez függvényét, a visszatérési értékként kapott pozíciót beállítja");
		System.out.println("// az új pozíciójának. Sebességét módosíthatóvá teszi. A Navigátor kulsomezo függvénye segítségével");
		System.out.println("// leelenõrzi hogy külsõ mezõre lépett-e: ");
		System.out.println("//    ha igen akkor beállítja a vesztettem attríbútumát igazra.");
		System.out.println("//    ha nem akkor beregisztrál arra a mezõre ahová került.");
		
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
			System.out.println("// Beállítja a robot vesztettem attribútumát igazra.");
		}
		else{
			System.out.println("[robot: Robot]<---       hamis      <---[navigator: Navigator]");
			System.out.println("[robot: Robot]--->beregisztral(robot)--->[pozicio: Mezo]");
			pozicio.beregisztral(this);
			System.out.println("[robot: Robot]<---return beregisztral<---[pozicio: Mezo]");
		}
		//Itt kell majd hozzáadni a megtett úthoz a sebességvektor hosszát.
		megtettUt+=3;
	}
	
	public void ragacsotTesz(){
		System.out.println("// Letesz egy ragacsot az aktuális helyére.");
		System.out.println("[robot Robot]--->Ragacs(pozicio)--->[ragacs:Ragacs]");
		// A ragacs a konstruktorában kapott mezõre beregisztrálja saját magát.
		Ragacs ragacs = new Ragacs(pozicio);
	}
	
	public void olajfoltotTesz(){
		System.out.println("// Letesz egy olajfoltot az aktuális helyére");
		System.out.println("[robot Robot]--->Olajfolt(pozicio)--->[olajfolt:Olajfolt]");
		// Az olajfolt a konstruktorában kapott mezõre beregisztrálja saját magát.
		Olajfolt olajfolt = new Olajfolt(pozicio);
	}
	
	public boolean getVesztett(){
		System.out.println("// Visszaadja hogy vesztett-e.");
		// Ezt majd a visszatérés után a Játékmesternek kell lekezelnie.
		return vesztettem;
	}
	
	public void lokodik(Sebesseg ujsebesseg){
		System.out.println("// Az ellökött robot sebessége megváltozik az õt ellökõ robot sebességének a felére.");
		System.out.println("// A sebesség megváltoztatása és az érkezési mezõ kiszámítása után");
		System.out.println("// meghívja magára az ugrik függvényt.");
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
		System.out.println("// Az aktuális sebességet lefelezi a felére és ehhez a");
		System.out.println("// Sebesseg osztálynak a felez metódusát hívja meg.");
		System.out.println("[robot Robot]--->   lefelez()  --->[sebesseg Sebesseg]");
		sebesseg.felez(sebesseg);
		System.out.println("[robot Robot]<---return lefelez<---[sebesseg Sebesseg]");
	}
	
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		System.out.println("// Beállítja a sebességének valtoztathato attribútumát hamisra.");
		System.out.println("[robot Robot]--->   modosithatatlan()  --->[sebesseg Sebesseg]");
		sebesseg.modosithatatlan();
		System.out.println("[robot Robot]<---return modosithatatlan<---[sebesseg Sebesseg]");
	}
	
	@Override
	public void robotraLeptem(Robot kireLeptem){
		System.out.println("// Ez hívódik meg ha a robot robottal ütközik.");
		// Gondolom nem nullával szeretnénk hogy elugorjon, de az én verziómban még nincs a sebesség függvénynek
		// paraméteres konstruktora.
		Sebesseg ujsebesseg = new Sebesseg();
		System.out.println("[robot Robot]--->lokodik(ujsebesseg)--->[kireLeptem: Robot]");
		kireLeptem.lokodik(ujsebesseg);
		System.out.println("[robot Robot]<---   return lokodik  <---[kireLeptem: Robot]");
	}
	
	@Override
	public void jottValaki(Mezonallo joveveny){
		System.out.println("// A paraméterül kapott jövevénynek meghívja a robotraLeptem metódusát.");
		System.out.println("[robot Robot]--->robotraLeptem(robot)--->[sebesseg Sebesseg]");
		joveveny.robotraLeptem(this);
		System.out.println("[robot Robot]<---return robotraLeptem<---[sebesseg Sebesseg]");
	}
}
