public class Sebesseg {
	
	private int x;
	private int y;
	private boolean modosithato;
	
	public Sebesseg (int x, int y) {
		this.x = x;
		this.y = y;
		this.modosithato = true;
	}
	
	//Hozzz�adja a bemeneten kapott sebess�get ehhez a sebess�ghez, ha ez m�dos�that�.
	public void hozzaad(Sebesseg sebesseg){
		if(modosithato) {
		x += sebesseg.x;
		y += sebesseg.y;
		} else {
			//TODO
		}
	}
	//Felezi a sebess�get ha m�dos�that�.
	public void felez(){
		if (modosithato) {
		x /= 2;
		y /= 2;
		} else {
			//TODO
		}
	}
	//Igaz �rt�kkel t�r vissza, ha a bemeneten kapott sebess�g nagyobb mint ez.
	public boolean hasonlit(Sebesseg sebesseg) {
		if ((x*x+y*y) < (sebesseg.x*sebesseg.x + sebesseg.y*sebesseg.y)) return true;
		else return false;
	}
	
	//Be�ll�tja a modosithatatlan param�tert hamisra.
	public void modosithatatlan(){
		modosithato = false;
	}
	
	//Be�ll�tja a modosithatatlan param�tert igazra.
	public void modosithato(){
		modosithato = true;
	}
	
	//Lek�rdezi az x �rt�k�t.
	public int getx() {
		return x;
	}
	
	//Be�ll�tja az x �rt�k�t.
	public void setx(int x) {
		this.x=x;
	}
	
	//Lek�rdezi az y �rt�k�t.
	public int gety() {
		return y;
	}
	
	//Be�ll�tja az y �rt�k�t.
	public void sety(int y) {
		this.y=y;
	}
	
	//Lek�rdezi a modosithato �rt�k�t.
	public boolean getmodosithato() {
		return modosithato;
	}
	
	//Be�ll�tja a modosithato �rt�k�t.
	public void setmodosithato(boolean modosithato) {
		this.modosithato=modosithato;
	}
	
	//Ellent�tes ir�nyba �ll�tja a sebess�get. (180 fokos forgat�s.)
	public void inverz() {
		x *= -1;
		y *= -1;
	}
	
	public double hossz() {
		return Math.sqrt(x*x+y*y);
	}
	
	//�j k�r kezd�sekor v�grehajtand� m�veletek ide ker�lhetnek.
	public void tick () {
		//Jelenleg a sebess�ggel nem t�rt�nik semmi �j k�r kezd�sekor.
		//P�ld�ul ha lenne s�rl�d�s, ami adott id�k�z�nk�nt cs�kkenti a sebess�get az ide ker�lhetne.
	}
}
