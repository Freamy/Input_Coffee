import java.awt.Graphics;
import java.awt.Graphics2D;


public class GrafikusRagacs extends GrafikusElem{

	//Inicializ�lja az �s tagv�ltoz�it a param�terben megadott �rt�kek szerint
	public GrafikusRagacs(String utvonal, Kepernyo kepernyo, Ragacs ragacs){
		super( utvonal, kepernyo );
		this.x=ragacs.getX();
		this.y=ragacs.getY();
	}
	
	//A param�tere kapott objektum seg�ts�g�vel kirajzolja a maga k�p�t a p�lya megfelel� hely�re
	public void rajzol(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(kep,x*20,y*20,null);
	}
	
	//Friss�ti tagv�ltoz�it a ragacs param�ter szerint
	public void frissit (Ragacs ragacs){
		this.x = ragacs.getX();
		this.y = ragacs.getY();
		kepernyo.rajzol();
	}
}
