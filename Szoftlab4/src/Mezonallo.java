public interface Mezonallo {
	
	void jottValaki(Mezonallo joveveny);
	
	void ragacsraLeptem(Ragacs kireLeptem);
	
	void olajfoltraLeptem(Olajfolt kireLeptem);
	
	void robotraLeptem(Robot kireLeptem);
	
	void kisrobotraLeptem(Kisrobot kireLeptem);
	
	boolean szennyezodesVagyok();
	
	Mezo getPozicio();
	
	void setPozicio(Mezo m);
	
	public String getNev();
	
	void tick();

	void setkopas(Integer kop);
	
}