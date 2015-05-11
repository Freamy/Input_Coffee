public interface Mezonallo {
	
	void jottValaki(Mezonallo joveveny);
	
	void ragacsraLeptem(Ragacs kireLeptem);
	
	void olajfoltraLeptem(Olajfolt kireLeptem);
	
	void robotraLeptem(Robot kireLeptem);
	
	void kisrobotraLeptem(Kisrobot kireLeptem);
	
	boolean szennyezodesVagyok();
	
	Mezo getPozicio();
	
	void setPozicio(Mezo m);
	
	String getNev();
	
	void setNev(String nev);
	
	void setkopas(int kop);
	
	void tick();
	
	void tickend();
	
	void megsemmisul();
	
	void adatokKiirasa(String param);
}