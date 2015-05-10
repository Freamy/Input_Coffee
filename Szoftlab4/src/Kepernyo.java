import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Kepernyo extends JFrame{
	private ArrayList<GrafikusElem> grafikak;
	private boolean kijelez;
	public Kepernyo(){
		grafikak = new ArrayList<GrafikusElem>();
	}
	public void rajzol(){
		for(GrafikusElem ge: grafikak){
			Graphics g = this.getGraphics();
			ge.rajzol(g);
		}
	}
	public void grafikusElemHozzaad(GrafikusElem grafikusElem){
		grafikak.add(grafikusElem);
	}	
	public void grafikusElemKivesz(GrafikusElem grafikusElem){
		grafikak.remove(grafikusElem);
	}
	public void Menu(boolean aktiv){
		this.kijelez = !aktiv;
	}
}
