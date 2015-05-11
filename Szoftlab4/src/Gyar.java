
public abstract class Gyar {
	
	protected Kepernyo kepernyo;
	
	public Gyar (Kepernyo kepernyo) {
		this.kepernyo = kepernyo;
	}
	public abstract GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m);
	
	public abstract GrafikusElem grafikaKeszitese(Kepernyo k);
}
