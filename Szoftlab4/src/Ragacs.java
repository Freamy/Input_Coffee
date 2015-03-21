public class Ragacs implements Mezonallo{

	private Mezo mezo;
	
	public Ragacs(Mezo mezo){
		this.mezo = mezo;
	}
	
	@Override
	public void jottValaki(Mezonallo joveveny) {
		System.out.println("[ragacs: Ragacs] --- ragacsraLeptem(ragacs) ---> [joveveny: Mezonallo]");
		System.out.println("//Az ott l�v� ragacs sz�l, hogy ragacsra l�pet a j�vev�ny.");
		joveveny.ragacsraLeptem(this);
		System.out.println("[ragacs: Ragacs] <--- return ragacsraLeptem --- [joveveny: Mezonallo]");
	}

	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		System.out.println("[ragacs: Ragacs] --- leregisztral(ragacs) ---> [mezo: Mezo]");
		System.out.println("// Az �j ragacs leregisztr�lja a r�gi ragacsot a mez�r�l.");
		mezo.leregisztral(kireLeptem);
		System.out.println("[ragacs: Ragacs] <--- return leregisztral --- [mezo: Mezo]");
	}

	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		System.out.println("[ragacs: Ragacs] --- leregisztral(olajfolt) ---> [mezo: Mezo]");
		System.out.println("//Az �j ragacs leregisztr�lja az olajfoltot a mez�r�l.");
		mezo.leregisztral(kireLeptem);
		System.out.println("[ragacs: Ragacs] <--- return leregisztral --- [mezo: Mezo]");
	}

	@Override
	public void robotraLeptem(Robot kireLeptem) {
		
	}
	
}