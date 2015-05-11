import java.awt.Graphics;
import java.awt.Graphics2D;

public class GrafikusRagacs extends GrafikusElem {
	

	public GrafikusRagacs(String utvonal, Kepernyo kepernyo, Ragacs ragacs) {
		super(ragacs.getPozicio().getX(), ragacs.getPozicio().getY(), utvonal, kepernyo);
		kepernyo.grafikusElemHozzaad(this);
		
	}
	
	public void rajzol(Graphics g, Navigator navigator){
		if(!megsemmisult) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(kep,x*64,y*64,null);
			
		}
	}
	
	public void frissit(Ragacs ragacs){
		x = ragacs.getPozicio().getX();
		y = ragacs.getPozicio().getY();
		megsemmisult = ragacs.getMegsemmisult();
	}
}