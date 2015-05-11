import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;


public class Kepernyo extends JPanel{
	private ArrayList<GrafikusElem> grafikak;
	private boolean kijelez;
	private JFrame frame;
	
	public Kepernyo(){
		grafikak = new ArrayList<GrafikusElem>();
	}
	
	public void initFrame(){
		//frame = new JFrame();
		//frame.add(this);
		//frame.setSize(1024, 960);
		//frame.setVisible(true);
	}
	
	public void rajzol(JFrame j){
		for(GrafikusElem ge: grafikak){
			Graphics g = j.getGraphics();
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
	public Sebesseg sebessegkerdezo() {
		Sebesseg sebesseg = null;
		return sebesseg;
	}
	public boolean ragacslekerdezo() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean olajlekerdezo() {
		// TODO Auto-generated method stub
		return false;
	}
}
