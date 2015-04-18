
public interface Mezonallo {
	
	void jottValaki(Mezonallo joveveny);
	
	void ragacsraLeptem(Ragacs kireLeptem);
	
	void olajfoltraLeptem(Olajfolt kireLeptem);
	
	void robotraLeptem(Robot kireLeptem);
	
	void kisrobotraLeptem(Kisrobot kireLeptem);
	
	boolean szennyezodesVagyok();
	
	Mezo getpozicio();
	
	void setpozicio(Mezo m);
	
	void tick();
	
}
