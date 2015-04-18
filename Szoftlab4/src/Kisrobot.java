
public class Kisrobot implements Mezonallo {

	private Mezo pozicio;			//A kisrobot tartózkodási mezõje.
	private Navigator navigator;	//A kisrobot eltárolja, hogy kiszámoltathassa vele azt, hogy hova ugorjon.
	private boolean lekoppant;		//Igaz ha robotnak vagy kisrobotnak ütközött.
	private Sebesseg sebesseg;
	private int megsemmisul;		//Mennyi kör után semmisül meg a kisrobot.	
	
	public Kisrobot(Mezo mezo, Navigator navigator){
		this.navigator = navigator;
		pozicio = mezo;
		lekoppant= false;
		pozicio.beregisztral(this);
	}
	
	//Navigátorral a kisrobot saját poziciója segítségével kiszámolja hova kell ugrania.
	//Leregrisztrál aktuális poziciójáról és beregrisztrál a kapott helyre.
	//Amenyiben ellökõdik, visszatér eredeti helyére.
	public void ugrik(){
		Mezo hova = navigator.kozeliszennyezodes(pozicio);
		pozicio.leregisztral(this);
		hova.beregisztral(this);
		if(lekoppant){
			Sebesseg invert = sebesseg.invert();
			Mezo eredeti = navigator.athelyez(pozicio, invert);
			eredeti.beregisztral(this);
		}
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
			kireLeptem.getpozicio().leregisztral(kireLeptem);
		}
		
	}
	
	//A paraméterül kapott olajfoltot feltakarítja. Leregisztrálja az olajfolt pozicio attribútumáról.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		if(!lekoppant){
			kireLeptem.getpozicio().leregisztral(kireLeptem);
		}
	}

	//A kisrobot ellökõdik.Beállítja lekoppant attribútumát true-ba.
	@Override
	public void robotraLeptem(Robot kireLeptem) {
		lekoppant = true;
	}

	//A kisrobot ellökõdik.Beállítja lekoppant attribútumát true-ba.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
		lekoppant = true;
		
	}

	//Viasszatér false értékkel, mert a kisrobotot nem tekintjük szennyezõdésnek.
	@Override
	public boolean szennyezodesVagyok() {
		return false;
	}

	//Visszatér a kisrobot pozicio attribútrumával.
	@Override
	public Mezo getpozicio() {
		return pozicio;
	}

	//Beállítja a m-re a pozicio értékét.
	@Override
	public void setpozicio(Mezo m) {
		// TODO Auto-generated method stub
		
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

}
