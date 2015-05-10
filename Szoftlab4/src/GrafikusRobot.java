import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class GrafikusRobot extends GrafikusElem {

	private boolean aktiv;
	private Sebesseg sebesseg;
	
	public GrafikusRobot(String utvonal, Kepernyo kepernyo, Robot robot) {
		super(robot.getPozicio().getX(), robot.getPozicio().getY(), utvonal, kepernyo);

		this.sebesseg = robot.getSebesseg();
		this.aktiv = false;
	}
	
	public void rajzol(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		if(!megsemmisult){
			if(aktiv){
				int sebX = (sebesseg.getx()+x)*20;
				int sebY = (sebesseg.gety()+y)*20;
				g2.drawImage(kep,x*20,y*20,null);
				g2.setColor(Color.green);
				g2.fillRect(sebX+20,sebY, 20, 20);
				g2.fillRect(sebX-20,sebY, 20, 20);
				g2.fillRect(sebX,sebY+20, 20, 20);
				g2.fillRect(sebX,sebY-20, 20, 20);
			}
			else{
				g2.drawImage(kep,x*20,y*20,null);
			}
		}
	}
	
	public void frissit (Robot robot){
		this.x = robot.getX();
		this.y = robot.getY();
		this.megsemmisult = robot.getMegsemmisult();
		this.sebesseg = robot.getSebesseg();
		this.aktiv = robot.getAktiv();
		kepernyo.rajzol();
	}
}
