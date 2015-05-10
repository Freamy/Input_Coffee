import java.util.ArrayList;
import javax.swing.*;

public class Kepernyo extends JFrame{
	private ArrayList<GrafikusElem> grafikak;
	private boolean kijelez;
	
	public void rajzol(){
		for(GrafikusElem ge: grafikak){
			ge.rajzol();
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
