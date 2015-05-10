import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class GrafikusOlajfolt extends GrafikusElem {
	
	//Inicializálja az õs tagváltozóit a paraméterben megadott értékek szerint
	public GrafikusOlajfolt(String utvonal, Kepernyo kepernyo, Olajfolt olajfolt){
		super( utvonal, kepernyo );
		this.x=olajfolt.getX();
		this.y=olajfolt.getY();
	}
	
	//A paramétere kapott objektum segítségével kirajzolja a maga képét a pálya megfelelõ helyére
	public void rajzol(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(kep,x*20,y*20,null);
		
	}
	
	//Frissíti tagváltozóit az olajfolt paraméter szerint
	public void frissit (Olajfolt olajfolt){
		this.x = olajfolt.getX();
		this.y = olajfolt.getY();
		kepernyo.rajzol();
	}
}
