import java.awt.Image;

public abstract class GrafikusElem {
	protected int x;
	protected int y;
	protected boolean megsemmisult;
	protected Image kep;
	protected Kepernyo kepernyo;
	
	public GrafikusElem(){
		
	}
	
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
