public class Kisrobot implements Mezonallo {

	private Mezo pozicio;			//A kisrobot tart�zkod�si mez�je.
	private Navigator navigator;	//A kisrobot elt�rolja, hogy kisz�moltathassa vele azt, hogy hova ugorjon.
	private boolean lekoppant;		//Igaz ha robotnak vagy kisrobotnak �tk�z�tt.
	private Sebesseg sebesseg;
	private int megsemmisul;		//Mennyi k�r ut�n semmis�l meg a kisrobot.	
	
	private String nev;
	
	public Kisrobot(Mezo mezo, Navigator navigator){
		this.navigator = navigator;
		pozicio = mezo;
		lekoppant= false;
		pozicio.beregisztral(this);
	}
	
	//Navig�torral a kisrobot saj�t pozici�ja seg�ts�g�vel kisz�molja hova kell ugrania.
	//Leregrisztr�l aktu�lis pozici�j�r�l �s beregrisztr�l a kapott helyre.
	//Amenyiben ell�k�dik, visszat�r eredeti hely�re.
	public void ugrik(){
		Mezo hova = navigator.kozeliszennyezodes(pozicio);
		pozicio.leregisztral(this);
		hova.beregisztral(this);
		if(lekoppant){
			sebesseg.inverz();
			Mezo eredeti = navigator.athelyez(pozicio, sebesseg);
			eredeti.beregisztral(this);
		}
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
			kireLeptem.getPozicio().leregisztral(kireLeptem);
		}
		
	}
	
	//A param�ter�l kapott olajfoltot feltakar�tja. Leregisztr�lja az olajfolt pozicio attrib�tum�r�l.
	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		if(!lekoppant){
			kireLeptem.getPozicio().leregisztral(kireLeptem);
		}
	}

	//A kisrobot ell�k�dik.Be�ll�tja lekoppant attrib�tum�t true-ba.
	@Override
	public void robotraLeptem(Robot kireLeptem) {
		lekoppant = true;
	}

	//A kisrobot ell�k�dik.Be�ll�tja lekoppant attrib�tum�t true-ba.
	@Override
	public void kisrobotraLeptem(Kisrobot kireLeptem) {
		lekoppant = true;
		
	}

	//Viasszat�r false �rt�kkel, mert a kisrobotot nem tekintj�k szennyez�d�snek.
	@Override
	public boolean szennyezodesVagyok() {
		return false;
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

	@Override
	public void setkopas(Integer kop) {
		// TODO Auto-generated method stub
		
	}

}