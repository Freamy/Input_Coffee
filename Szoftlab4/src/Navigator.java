import java.util.ArrayList;


public class Navigator {
	
	ArrayList<Mezo> terkep;
	
	public Navigator(){
		terkep = new ArrayList<Mezo>();
		terkep.add(new Mezo());
		terkep.add(new Mezo());
		terkep.add(new Mezo());
	}
	
	public Mezo getMezo(int id){
		if(id < 0 && id > terkep.size()) throw new IndexOutOfBoundsException();
		return terkep.get(id);
	}
	
	Mezo athelyez(Mezo honnan, Sebesseg sebesseg){
		Mezo hova = terkep.get(terkep.indexOf(honnan)+1);
		System.out.println("[navigator: Navigator]");
		System.out.println("A honnan �s a sebesseg param�terekb�l kisz�molja, hogy melyik mez�re �rkezik a robot �s azzal visszat�r.");
		System.out.println("[navigator: Navigator] <--- return athelyez: hova ---");
		return hova;
	}
	
	boolean kulsoMezo(Mezo mezo){
		boolean kulso = false;
		System.out.println("[navigator: Navigator]");
		System.out.println("Visszaadja, hogy egy param�ter�l �tadott mez� k�ls�-e.");
		System.out.println("[navigator: Navigator] <--- return kulsoMezo: kulso ---");
		return kulso;
	}
}
