import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



public class GrafikusRobot extends GrafikusElem {

	private boolean aktiv;
	private Sebesseg sebesseg;
	
	public GrafikusRobot(String utvonal, Kepernyo kepernyo, Robot robot) {
		super(robot.getPozicio().getX(), robot.getPozicio().getY(), utvonal, kepernyo);
		kepernyo.grafikusElemHozzaad(this);
		this.sebesseg = robot.getSebesseg();
		this.aktiv = false;
	}
	
	public void rajzol(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		int meret = 64;
		if(!megsemmisult){
			g2.drawImage(kep,x*meret,y*meret,null);
			if(aktiv){
				int sebX = (sebesseg.getx()+x)*meret;
				int sebY = (sebesseg.gety()+y)*meret;
				g2.setColor(Color.green);
				g2.fillRect(sebX+meret,sebY, meret, meret);
				g2.fillRect(sebX-meret,sebY, meret, meret);
				g2.fillRect(sebX,sebY+meret, meret, meret);
				g2.fillRect(sebX,sebY-meret, meret, meret);
			}
		}
	}
	
	public void frissit (Robot robot){
		this.x = robot.getX();
		this.y = robot.getY();
		this.megsemmisult = robot.getMegsemmisult();
		this.sebesseg = robot.getSebesseg();
		this.aktiv = robot.getAktiv();
	}
}
