import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class GrafikusKisrobot extends GrafikusElem {
	private int x;
	private int y;
	private boolean megsemmisult;
	private Image kep;
	private Kepernyo kepernyo;
	
	public GrafikusKisrobot(String utvonal,Kepernyo kepernyo,Kisrobot kisrobot){
		this.kep = kepBetoltese(utvonal);
		this.kepernyo = kepernyo;
		this.x = kisrobot.getX();
		this.y = kisrobot.getY();
		this.megsemmisult = kisrobot.getMegsemmisult();
	}
	
	public void frissit(Kisrobot kisrobot){
		this.x = kisrobot.getX();
		this.y = kisrobot.getY();
		this.megsemmisult = kisrobot.getMegsemmisult();
		kepernyo.rajzol();
	}
	public void rajzol(Graphics g){
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(kep,x*20,y*20,null);
	}
	
}
