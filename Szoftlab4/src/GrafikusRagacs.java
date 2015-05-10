import java.awt.Graphics;
import java.awt.Graphics2D;


public class GrafikusRagacs extends GrafikusElem{

	//Inicializálja az õs tagváltozóit a paraméterben megadott értékek szerint
	public GrafikusRagacs(String utvonal, Kepernyo kepernyo, Ragacs ragacs){
		super( utvonal, kepernyo );
		this.x=ragacs.getX();
		this.y=ragacs.getY();
	}
	
	//A paramétere kapott objektum segítségével kirajzolja a maga képét a pálya megfelelõ helyére
	public void rajzol(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(kep,x*20,y*20,null);
	}
	
	//Frissíti tagváltozóit a ragacs paraméter szerint
	public void frissit (Ragacs ragacs){
		this.x = ragacs.getX();
		this.y = ragacs.getY();
		kepernyo.rajzol();
	}
}
