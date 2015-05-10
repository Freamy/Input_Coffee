import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class GrafikusOlajfolt extends GrafikusElem {
	
	//Inicializ�lja az �s tagv�ltoz�it a param�terben megadott �rt�kek szerint
	public GrafikusOlajfolt(String utvonal, Kepernyo kepernyo, Olajfolt olajfolt){
		super( utvonal, kepernyo );
		this.x=olajfolt.getX();
		this.y=olajfolt.getY();
	}
	
	//A param�tere kapott objektum seg�ts�g�vel kirajzolja a maga k�p�t a p�lya megfelel� hely�re
	public void rajzol(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(kep,x*20,y*20,null);
		
	}
	
	//Friss�ti tagv�ltoz�it az olajfolt param�ter szerint
	public void frissit (Olajfolt olajfolt){
		this.x = olajfolt.getX();
		this.y = olajfolt.getY();
		kepernyo.rajzol();
	}
}
