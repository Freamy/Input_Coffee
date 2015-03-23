public class Olajfolt implements Mezonallo{
	
	private Mezo mezo;
	
	public Olajfolt(Mezo mezo){
		this.mezo = mezo;
		mezo.beregisztral(this);
	}
	
	@Override
	public void jottValaki(Mezonallo joveveny) {
		System.out.println("[olajfolt: Olajfolt] --- olajfoltraLeptem(olajfolt) ---> [joveveny: Mezonallo]");
		System.out.println("//Az ottl�v� olajfolt sz�l, hogy olajfoltra l�pett a j�vev�ny.");
		joveveny.olajfoltraLeptem(this);
		System.out.println("[olajfolt: Olajfolt] <--- return olajfoltraLeptem --- [joveveny: Mezonallo]");
	}

	@Override
	public void ragacsraLeptem(Ragacs kireLeptem) {
		System.out.println("[olajfolt: Olajfolt] --- leregisztral(ragacs) ---> [mezo: Mezo]");
		System.out.println("// Az �j olajfolt leregisztr�lja a ragacsot a mez�r�l.");
		mezo.leregisztral(kireLeptem);
		System.out.println("[olajfolt: Olajfolt] <--- return leregisztral --- [mezo: Mezo]");
	}

	@Override
	public void olajfoltraLeptem(Olajfolt kireLeptem) {
		System.out.println("[olajfolt: Olajfolt] --- leregisztral(regiolajfolt) ---> [mezo: Mezo]");
		System.out.println("// Az �j olajfolt leregisztr�lja a r�gi olajfoltot a mez�r�l.");
		mezo.leregisztral(kireLeptem);
		System.out.println("[olajfolt: Olajfolt] <--- return leregisztral --- [mezo: Mezo]");
	}

	@Override
	public void robotraLeptem(Robot kireLeptem) {
		
	}
}