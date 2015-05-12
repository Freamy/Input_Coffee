import java.awt.Graphics;
import java.awt.Graphics2D;
public class GrafikusKisrobot extends GrafikusElem {
	

	public GrafikusKisrobot(String utvonal, Kepernyo kepernyo, Kisrobot kisrobot) {
		super(kisrobot.getPozicio().getX(), kisrobot.getPozicio().getY(), utvonal, kepernyo);
		kepernyo.grafikusElemHozzaad(this);
	}
	
	public void rajzol(Graphics g){
		if(!megsemmisult) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(kep,x*64,y*64,null);
		}
	}
	
	public void frissit(Kisrobot kisrobot){
		x = kisrobot.getPozicio().getX();
		y = kisrobot.getPozicio().getY();
		megsemmisult = kisrobot.getMegsemmisult();
	}
}