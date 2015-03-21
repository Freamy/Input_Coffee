public class Ragacs implements Mezonallo{

	private Mezo mezo;
	
	public Ragacs(Mezo mezo){
		this.mezo = mezo;
	}
	
	@Override
	public void jottValaki(Mezonallo joveveny) {
		System.out.println("[ragacs: Ragacs] --- ragacsraLeptem(ragacs) ---> [joveveny: Mezonallo]");
		System.out.println("//Az ott lévõ ragacs szól, hogy ragacsra lépet a jövevény.");
		joveveny.ragacsraLeptem(this);
		System.out.println("[ragacs: Ragacs] <--- return ragacsraLeptem --- [joveveny: Mezonallo]");
	}

	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		System.out.println("[ragacs: Ragacs] --- leregisztral(ragacs) ---> [mezo: Mezo]");
		System.out.println("// Az új ragacs leregisztrálja a régi ragacsot a mezõrõl.");
		mezo.leregisztral(kireLeptem);
		System.out.println("[ragacs: Ragacs] <--- return leregisztral --- [mezo: Mezo]");
	}

	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		System.out.println("[ragacs: Ragacs] --- leregisztral(olajfolt) ---> [mezo: Mezo]");
		System.out.println("//Az új ragacs leregisztrálja az olajfoltot a mezõrõl.");
		mezo.leregisztral(kireLeptem);
		System.out.println("[ragacs: Ragacs] <--- return leregisztral --- [mezo: Mezo]");
	}

	@Override
	public void robotraLeptem(Robot kireLeptem) {
		
	}
	
}