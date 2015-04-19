public class Sebesseg {
	
	private int x;
	private int y;
	private boolean modosithato;
	
	public Sebesseg (int x, int y) {
		this.x = x;
		this.y = y;
		this.modosithato = true;
	}
	
	//Hozzzáadja a bemeneten kapott sebességet ehhez a sebességhez, ha ez módosítható.
	public void hozzaad(Sebesseg sebesseg){
		if(modosithato) {
		x += sebesseg.x;
		y += sebesseg.y;
		} else {
			//TODO
		}
	}
	//Felezi a sebességet ha módosítható.
	public void felez(){
		if (modosithato) {
		x /= 2;
		y /= 2;
		} else {
			//TODO
		}
	}
	//Igaz értékkel tér vissza, ha a bemeneten kapott sebesség nagyobb mint ez.
	public boolean hasonlit(Sebesseg sebesseg) {
		if ((x*x+y*y) < (sebesseg.x*sebesseg.x + sebesseg.y*sebesseg.y)) return true;
		else return false;
	}
	
	//Beállítja a modosithatatlan paramétert hamisra.
	public void modosithatatlan(){
		modosithato = false;
	}
	
	//Beállítja a modosithatatlan paramétert igazra.
	public void modosithato(){
		modosithato = true;
	}
	
	//Lekérdezi az x értékét.
	public int getx() {
		return x;
	}
	
	//Beállítja az x értékét.
	public void setx(int x) {
		this.x=x;
	}
	
	//Lekérdezi az y értékét.
	public int gety() {
		return y;
	}
	
	//Beállítja az y értékét.
	public void sety(int y) {
		this.y=y;
	}
	
	//Lekérdezi a modosithato értékét.
	public boolean getmodosithato() {
		return modosithato;
	}
	
	//Beállítja a modosithato értékét.
	public void setmodosithato(boolean modosithato) {
		this.modosithato=modosithato;
	}
	
	//Ellentétes irányba állítja a sebességet. (180 fokos forgatás.)
	public void inverz() {
		x *= -1;
		y *= -1;
	}
	
	public double hossz() {
		return Math.sqrt(x*x+y*y);
	}
	
	public void atlag(Sebesseg be){
		x = (x + be.x)/2;
		y = (y + be.y)/2;
	}
	
	//Új kör kezdésekor végrehajtandó mûveletek ide kerülhetnek.
	public void tick () {
		//Jelenleg a sebességgel nem történik semmi új kör kezdésekor.
		//Például ha lenne súrlódás, ami adott idõközönként csökkenti a sebességet az ide kerülhetne.
	}
}