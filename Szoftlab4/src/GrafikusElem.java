import java.awt.Image;

public abstract class GrafikusElem {
	private int x;
	private int y;
	private boolean megsemmisult;
	private Image kep;
	private Kepernyo kepernyo;
	
	public GrafikusElem(String utvonal, Kepernyo kepernyo){
		this.kep = kepBetoltese(utvonal);
		this.kepernyo = kepernyo;
	}
	public Kepernyo getKepernyo(){
		return kepernyo;
	}
	public Image kepBetoltese(String utvonal){
		//placeholder
		return kep;
	}
	public void rajzol(){}
}
