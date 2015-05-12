import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;


public class Kepernyo extends JPanel{
	private ArrayList<GrafikusElem> grafikak;
	private boolean kijelez;
	
	public Kepernyo(Navigator navigator){
		grafikak = new ArrayList<GrafikusElem>();
	}
	
	public void initFrame(){
		//frame = new JFrame();
		//frame.add(this);
		//frame.setSize(1024, 960);
		//frame.setVisible(true);
	}
	
	public void rajzol(JFrame j){
		j.invalidate();
		Graphics g = j.getGraphics();
		for(int i = grafikak.size() - 1; i>=0; i--){
			grafikak.get(i).rajzol(g);
		}
		/*for(GrafikusElem ge: grafikak){
			//Graphics g = j.getGraphics();
			ge.rajzol(g, navigator);
		}
		*/
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