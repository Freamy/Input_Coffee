import java.util.Collection;


public class Navigator {
	
	Collection<Mezo> terkep;
	
	Mezo athelyez(Mezo honnan, Sebesseg sebesseg){
		Mezo hova = null;
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
