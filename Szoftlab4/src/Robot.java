import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Robot implements Mezonallo{
	private Mezo pozicio;
	private Navigator navigator;
	private Sebesseg sebesseg;
	
	public Robot(Mezo mezo, Navigator navigator){
		this.pozicio = mezo;
		this.navigator = navigator;
		
		sebesseg = new Sebesseg();
		mezo.beregisztral(this);
	}
	
	//KÃ©sz
	public void lep(Sebesseg valtozas, boolean olajfoltotTesz, boolean ragacsotTesz){
		System.out.println("// Ha volt rá igény akkor lerak olajfoltot vagy ragacsot aztán leregisztrál a mezõrõll.");
		System.out.println("// Meghívja a sebesség hozzáad függvényt és a robot ugrik függvényét.");
		if(olajfoltotTesz){
			System.out.println("[robot: Robot]--->   olajfoltotTesz()  --->[robot: Robot]");
			olajfoltotTesz();
			System.out.println("[robot: Robot]<---return olajfoltotTesz<---[robot: Robot]");
		}
		else if(ragacsotTesz){
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

	//Szerintem ez a fÃ¼ggvÃ©ny nem szÃ¼ksÃ©ges.
	public void sebessegvaltozas(Sebesseg valtozas){}
	
	//Meggtett Ãºt kiszÃ¡mÃ­tÃ¡sa mÃ©g hiÃ¡nyzik, SebessÃ©g osztÃ¡ly get() fÃ¼ggvÃ©nyei kellenek hozzÃ¡
	public void ugrik(){
		System.out.println("// Meghívja a Navigátor athelyez függvényét, a visszatérési értékként kapott pozíciót beállítja");
		System.out.println("// az új pozíciójának. Sebességét módosíthatóvá teszi. A Navigátor kulsomezo függvénye segítségével");
		System.out.println("// leelenõrzi hogy kûlsõ mezõre lépett-e: ");
		System.out.println("//    ha igen akkor beállítja a vesztettem attríbútumát igazra.");
		System.out.println("//    ha nem akkor beregisztrál arra a mezõre ahová kerûlt.");
		
		System.out.println("[robot: Robot]--->athelyez(pozicio,sebesseg)--->[navigator: Navigator]");
		pozicio = navigator.athelyez(pozicio,sebesseg);
		System.out.println("[robot: Robot]<---         Mezo hova        <---[navigator: Navigator]");
		
		System.out.println("[robot: Robot]--->modosithato()--->[sebesseg: Sebesseg]");
		sebesseg.modosithato();
		System.out.println("[robot: Robot]<---modosithato()<---[sebesseg: Sebesseg]");
		
		System.out.println("[robot: Robot]--->kulsomezo(pozicio)--->[navigator: Navigator]");
		if(navigator.kulsoMezo(pozicio)){
			System.out.println("[robot: Robot]<---        igaz      <---[navigator: Navigator]");
			System.out.println("// BeÃ¡llÃ­tja a robot vesztettem attribÃºtumÃ¡t igazra.");
		}
		else{
			System.out.println("[robot: Robot]<---       hamis      <---[navigator: Navigator]");
			System.out.println("[robot: Robot]--->beregisztral(robot)--->[pozicio: Mezo]");
			pozicio.beregisztral(this);
			System.out.println("[robot: Robot]<---return beregisztral<---[pozicio: Mezo]");
		}
		//Ehhez kellenek mÃ©g a SebessÃ©g osztÃ¡ly get() fÃ¼ggvÃ©nyei.
	}
	
	//KÃ©sz
	public void ragacsotTesz(){
		System.out.println("// Letesz egy ragacsot az aktuális helyére.");
		System.out.println("? Maradt ragacs a roboton?: (y/n)");
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			Ragacs ragacs = new Ragacs(navigator.getMezo(0));
			System.out.println("[robot Robot]--->beregisztral(ragacs)--->[pozicio Mezo]");
			pozicio.beregisztral(ragacs);
			System.out.println("[robot Robot]<---return beregisztral <---[pozicio Mezo]");
		}
		else
			System.out.println("// Sajnálom, elfogyott a ragacsod.");
	}
	
	//KÃ©sz
	public void olajfoltotTesz(){
		System.out.println("// Letesz egy olajfoltot az aktuális helyére");
		System.out.println("? Maradt olaj a roboton?: (y/n)");
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			Olajfolt olajfolt = new Olajfolt(navigator.getMezo(0));
			System.out.println("[robot Robot]--->beregisztral(olajfolt)--->[pozicio Mezo]");
			pozicio.beregisztral(olajfolt);
			System.out.println("[robot Robot]<---return beregisztral <---[pozicio Mezo]");
		}
		else
			System.out.println("// Sajnálom, elfogyott a olajfoltod.");
	}
	
	//KÃ©sz, visszatÃ©rÃ©si Ã©rtÃ©k alapjÃ¡n kell a JÃ¡tÃ©kmesternek eljÃ¡rnia.
	public boolean getVesztett(){
		System.out.println("// Visszaadja hogy vesztett-e.");
		return false;
	}
	
	//KÃ©sz
	public void lokodik(Sebesseg ujsebesseg){
		System.out.println("// Az ellökött robot sebessége megváltozik az õt ellökö robot sebességének a felére.");
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
	
	//KÃ©sz
	@Override
	public void ragacsraLeptem(Ragacs kireLeptem){
		System.out.println("// Az aktuális sebességet lefelezi a felére és ehhez a");
		System.out.println("// Sebesseg osztálynak a felez metódusát hívja meg.");
		System.out.println("[robot Robot]--->   lefelez()  --->[sebesseg Sebesseg]");
		sebesseg.felez();
		System.out.println("[robot Robot]<---return lefelez<---[sebesseg Sebesseg]");
	}
	
	//KÃ©sz
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem){
		System.out.println("// Beállítja a sebességének valtoztathato attribútumát hamisra.");
		System.out.println("[robot Robot]--->   modosithatatlan()  --->[sebesseg Sebesseg]");
		sebesseg.modosithatatlan();
		System.out.println("[robot Robot]<---return modosithatatlan<---[sebesseg Sebesseg]");
	}
	
	//MÃ©g kell a SebessÃ©g fÃ¼ggvÃ©ny get() (esetleg set()) fÃ¼ggvÃ©nye
	@Override
	public void robotraLeptem(Robot kireLeptem){
		System.out.println("// Ez hívódik meg ha a robot robottal találkozik.");
		//Itt mÃ©g lesz nÃ©hÃ¡ny fÃ¼ggvÃ©ny.
		Sebesseg ujsebesseg = new Sebesseg();
		System.out.println("[robot Robot]--->lokodik(ujsebesseg)--->[kireLeptem: Robot]");
		kireLeptem.lokodik(ujsebesseg);
		System.out.println("[robot Robot]<---   return lokodik  <---[kireLeptem: Robot]");
	}
	
	//KÃ©sz
	@Override
	public void jottValaki(Mezonallo joveveny){
		System.out.println("// A paraméterül kapott jövevénynek meghívja a robotraLeptem metódusát.");
		System.out.println("[robot Robot]--->robotraLeptem(robot)--->[sebesseg Sebesseg]");
		joveveny.robotraLeptem(this);
		System.out.println("[robot Robot]<---return robotraLeptem<---[sebesseg Sebesseg]");
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
