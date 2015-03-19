import java.util.Collection;


public class Navigator {
	
	Collection<Mezo> terkep;
	
	Mezo athelyez(Mezo honnan, Sebesseg sebesseg){
		Mezo hova = null;
		System.out.println("[navigator: Navigator]");
		System.out.println("A honnan és a sebesseg paraméterekbõl kiszámolja, hogy melyik mezõre érkezik a robot és azzal visszatér.");
		System.out.println("[navigator: Navigator] <--- return athelyez: hova ---");
		return hova;
	}
	
	boolean kulsoMezo(Mezo mezo){
		boolean kulso = false;
		System.out.println("[navigator: Navigator]");
		System.out.println("Visszaadja, hogy egy paraméterül átadott mezõ külsõ-e.");
		System.out.println("[navigator: Navigator] <--- return kulsoMezo: kulso ---");
		return kulso;
	}
}
