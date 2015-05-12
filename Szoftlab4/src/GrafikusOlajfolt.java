import java.awt.Graphics;
import java.awt.Graphics2D;

public class GrafikusOlajfolt extends GrafikusElem {
	

	public GrafikusOlajfolt(String utvonal, Kepernyo kepernyo, Olajfolt olajfolt) {
		super(olajfolt.getPozicio().getX(), olajfolt.getPozicio().getY(), utvonal, kepernyo);
		kepernyo.grafikusElemHozzaad(this);
	}
	
	public void rajzol(Graphics g){
		if(!megsemmisult) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(kep,x*64,y*64,null);
		}
	}
	
	public void frissit(Olajfolt olajfolt){
		x = olajfolt.getPozicio().getX();
		y = olajfolt.getPozicio().getY();
		megsemmisult = olajfolt.getMegsemmisult();
	}
}