import java.util.ArrayList;

public class Kepernyo {
	private ArrayList<GrafikusElem> grafikak;
	
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
}
