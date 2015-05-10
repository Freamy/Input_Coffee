import java.io.*;
import java.util.ArrayList;


public class Jatekmester {
	
	private Navigator navigator;
	private ArrayList<Kisrobot> kisrobotok;
	private ArrayList<Robot> robotok;
	private int korszam;
	
	public static void main(String[] args){
		try{
		Jatekmester jatekMester = new Jatekmester();
		boolean running = false; // a játék futását vizsgálja, ha false akkor csak az exit és a start parancsok hívhatók
		while(jatekMester.korszam< 30){ //egy játék 30 körös (többre/kevesebbre is állíthatjuk ha szeretnétek)
			
			BufferedReader be = new BufferedReader(new InputStreamReader(System.in)); 
			String bemenet;
			while((bemenet=be.readLine())!=null && bemenet.length()!=0){ //soronként beolvassuk a bemeneteket, attól függően, hogy van.
				String parancs = bemenet;
				String[] parameterek = new String[10];
				/*
				for(index=0; index<bemenet.length(); index++){
					char c = bemenet.charAt(index);
					if(c=='('){ //Ha zárójelet is beolvastunk, akkor paraméter is van két részre bontjuk
						parancs = bemenet.substring(0, index); //Parancs részre, ami alapján értelnezzük a bemenetet.
						kezdo = index + 1;
					}
					
					else if(c==',' || c==')'){ //illetve paraméter részre, amiket tömben tárolunk, mert lehet több is (max 10)
						parameterek[tobbparameter] = bemenet.substring(kezdo, index); 
						tobbparameter++;
						kezdo = index + 1;
						if(c==')') break;
					}
				}*/
				
				/** This is kinda not perfect yet **/
				if(parancs.contains(")")){
					parancs = bemenet.substring(0, bemenet.indexOf(")"));
				}
				String[] darabolo = parancs.split("\\(");
				if(darabolo.length > 1){
					// Vannak paraméterek
					parancs = darabolo[0];
					darabolo = darabolo[1].split(",");
					int i = 0;
					for(String darab: darabolo){
						parameterek[i] = darab;
						i++;
					}
				}else{
					// Nincsnenek paraméterek
					parancs = darabolo[0];
				}
				/** Until like this part **/
				
				if(parancs.equals("Start")){ //Elindítja a játékot
					running = true;
					System.out.println("[Jatek] indulás.");
				}
				else if(parancs.equals("Stop")){ //Megállítja a játékot, startal újraindítható
					running = false;
					System.out.println("[Jatek] megállás.");
				}
				else if(parancs.equals("Exit")){ //Leáll a program futása
					running = false;
					System.exit(0);
				}
				else if(parancs.equals("Tick") && running){ //Meghívja a játékmester tick függvényét
					jatekMester.tick();
				}
				else if(parancs.equals("AdatokMindenki") && running){
					
					//Ha a lenti TODO-k kész vannak az alábbi sor kommentezése feloldható:
					//navigator.adatKiirasa("");
					
					// TODO:
					// Navigator: adatKiirasa(String) Kiírja a saját adatait (ha a param =  ""), majd ha param= " meghívja minden Mezőre az adatKiirasa(param) függvényt ami benne van.
					// Mezo: adatKiirasa(String param) Kiírja a saját adatait (ha a param =  a nevével, vagy "") majd meghívja minden Mezonallora az adatKiirasa(param) függvényt ami benne van.
					// Mezonallok: adatKiirasa(String param) Kiírja a saját adatait (ha a param = a nevével, vagy "").
				}
				else if(parancs.equals("AdatokNev") && running){
					
					
					//Ha a függvény implementálva van az alábbi sor kommentezése feloldható:
					//navigator.adatKiirasa(parameterek[0]);
					
				}
				else if(parancs.equals("AdatokMezo") && running){
					
					int x = Integer.parseInt(parameterek[0]);
					int y = Integer.parseInt(parameterek[1]);
					
					//Ha a lenti TODO-k kész vannak az alábbi sor kommentezése feloldható:
					//navigator.adatKiirasa(x,y);
					
					//TODO:
					//Navigator: adatKiirasa(int x, int y) meghívja az (x,y) kordinátán lévő mezőre az adatKiirasa("") függvényt.
					
					
				}
				else if(parancs.equals("Torol") && running){ // Törli a pályán lévő összes elemet
					

					ArrayList<Mezonallo> elemek;
					elemek = jatekMester.navigator.getOsszesElem(); //Visszaadja az összes elemet, ami a pályán van és egy listába menti.
					
					for(Mezonallo m : elemek){
						m.getPozicio().leregisztral(m);
						if(jatekMester.robotok.contains(m)) jatekMester.robotok.remove(m);
						else if(jatekMester.kisrobotok.contains(m)) jatekMester.kisrobotok.remove(m);
					}
				}
				else if(parancs.equals("Palya") && running){ //
					
					Integer n = Integer.parseInt(parameterek[0]);
					Integer k = Integer.parseInt(parameterek[1]);
					jatekMester.navigator = new Navigator();
					jatekMester.navigator.palyaKeszites(n,k);
					
				}
				else if(parancs.equals("TorolMezonallo") && running){ //törli a paraméterként kapott mezőnállót
					
					String uj = parameterek[0];
					ArrayList<Mezonallo> elemek;
					elemek = jatekMester.navigator.getOsszesElem(); //Visszaadja az összes elemet, ami a pályán van és egy listába menti.
					
					for(Mezonallo m : elemek){
						String vizsgalt = m.getNev();
						if(vizsgalt.equals(uj)){
							m.getPozicio().leregisztral(m);
							if(jatekMester.robotok.contains(m)) jatekMester.robotok.remove(m);
							else if(jatekMester.kisrobotok.contains(m)) jatekMester.kisrobotok.remove(m);
						}
					}
				}
				else if(parancs.equals("TorolMezo") && running){
					
					int x = Integer.parseInt(parameterek[0]);
					int y = Integer.parseInt(parameterek[1]);
					ArrayList<Mezonallo> elemek;
					Mezo torlendo = jatekMester.navigator.getMezo(x, y);
					elemek = torlendo.getRajtamAllok();
					for(Mezonallo m : elemek){
							m.getPozicio().leregisztral(m);
							if(jatekMester.robotok.contains(m)) jatekMester.robotok.remove(m);
							else if(jatekMester.kisrobotok.contains(m)) jatekMester.kisrobotok.remove(m);
					}
					
				}
				
				else if((parancs.equals("Kulso") || parancs.equals("Belso")) && running){
					
					int x = Integer.parseInt(parameterek[0]);
					int y = Integer.parseInt(parameterek[1]);
						if(parancs.equals("Kulso")){
							jatekMester.navigator.setKulsoMezo(x,y,true);
						}
						else{
							jatekMester.navigator.setKulsoMezo(x,y,false);
						}
				}
				
				else if(parancs.equals("UjRobot") && running){ 
					// Itt nem tudom hogy hogyan kéne a parameter[0]-t a robot nevének adni, ha nincs név paramétere,
					// változó nevű változót pedig nem tudom hogy kéne létrehozni
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					Robot uj = new Robot(jatekMester.navigator.getMezo(x, y), jatekMester.navigator);

					uj.getPozicio().beregisztral(uj);
					
					Integer vx = Integer.parseInt(parameterek[3]);
					Integer vy = Integer.parseInt(parameterek[4]);
					Sebesseg s = new Sebesseg(vx,vy);
					if(parameterek[5].equals("true")){
						s.modosithato();
					}
					else if(parameterek[5].equals("false")){
						s.modosithatatlan();
					}
					uj.sebessegvaltozas(s);
					
					Integer ragacsDB = Integer.parseInt(parameterek[6]);
					Integer olajDB = Integer.parseInt(parameterek[7]);
					uj.setRagacsDb(ragacsDB);
					uj.setOlajDb(olajDB);
					
					if(parameterek[8].equals("true")){
						uj.setVesztett(true);
					}
					else if (parameterek[8].equals("false")){
						uj.setVesztett(false);
					}
					
					Integer ut = Integer.parseInt(parameterek[9]);
					uj.setmegtettUt(ut);
					uj.setNev(parameterek[0]);
					jatekMester.robotok.add(uj);
				}
				
				else if(parancs.equals("UjKisrobot") && running){
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					Kisrobot uj = new Kisrobot(jatekMester.navigator.getMezo(x, y), jatekMester.navigator);
					uj.setPozicio(jatekMester.navigator.getMezo(x, y));
					uj.getPozicio().beregisztral(uj);
					uj.setNev(parameterek[0]);
					jatekMester.kisrobotok.add(uj);
				}
				else if(parancs.equals("UjRagacs") && running){

					int kord[] = {Integer.parseInt(parameterek[1]),Integer.parseInt(parameterek[2])};
					
					Ragacs uj = new Ragacs(jatekMester.navigator.getMezo(kord[0], kord[1]), Integer.parseInt(parameterek[3]), kord);
					uj.setPozicio(jatekMester.navigator.getMezo(kord[0], kord[1]));
					uj.setNev(parameterek[0]);
					uj.getPozicio().beregisztral(uj);
				}
				else if(parancs.equals("UjOlajfolt") && running){
					
					int kord[] = {Integer.parseInt(parameterek[1]),Integer.parseInt(parameterek[2])};
					
					Olajfolt uj = new Olajfolt(jatekMester.navigator.getMezo(kord[0], kord[1]), Integer.parseInt(parameterek[3]), "", kord);
					uj.setPozicio(jatekMester.navigator.getMezo(kord[0], kord[1]));
					uj.setNev(parameterek[0]);
					uj.getPozicio().beregisztral(uj);
				}
				
				else if(parancs.equals("ModositRobotHely") && running){
					
					String uj = parameterek[0];
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.getPozicio().leregisztral(r);
							r.setPozicio(jatekMester.navigator.getMezo(x, y));
							r.getPozicio().beregisztral(r);
						}
					}
				}
				
				else if(parancs.equals("ModositRobotSebesseg") && running){
					
					String uj = parameterek[0];
					Integer vx = Integer.parseInt(parameterek[1]);
					Integer vy = Integer.parseInt(parameterek[2]);
					Sebesseg s = new Sebesseg(vx,vy);
					if(parameterek[3].equals("true")){
						s.modosithato();
					}
					else if(parameterek[3].equals("false")){
						s.modosithatatlan();
					}
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.setSebesseg(s);
						}
					}
				}
				
				else if(parancs.equals("ModositRobotKeszlet") && running){
					
					String uj = parameterek[0];
					Integer ragacs = Integer.parseInt(parameterek[1]);
					Integer olaj = Integer.parseInt(parameterek[2]);
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.setOlajDb(olaj);
							r.setRagacsDb(ragacs);
						}
					}
				}
				
				else if(parancs.equals("ModositRobotVesztett") && running){
					
					String uj = parameterek[0];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							if(parameterek[1].equals("true")){
								r.setVesztett(true);
							}
							else if(parameterek[1].equals("false")){
								r.setVesztett(false);
							}
						}
					}
				}
				else if(parancs.equals("ModositRobotMegtettUt") && running){
					
					String uj = parameterek[0];
					Integer ut = Integer.parseInt(parameterek[1]);
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.setmegtettUt(ut);
						}
					}
				}
				else if(parancs.equals("ModositKisrobot") && running){
					
					String uj = parameterek[0];
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					
					for(Kisrobot kr : jatekMester.kisrobotok){
						String vizsgalt = kr.getNev();
						if(vizsgalt.equals(uj)){
							kr.getPozicio().leregisztral(kr);
							kr.setPozicio(jatekMester.navigator.getMezo(x,y));
							kr.getPozicio().beregisztral(kr);
						}
					}
				}
				else if((parancs.equals("ModositRagacs")|| parancs.equals("ModositOlajfolt")) && running){
					
					String uj = parameterek[0];
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					Integer kop = Integer.parseInt(parameterek[3]);
					ArrayList<Mezonallo> elemek;
					elemek = jatekMester.navigator.getOsszesElem();
					
					for(Mezonallo m : elemek){
						String vizsgalt = m.getNev();
						if(vizsgalt.equals(uj)){
							m.getPozicio().leregisztral(m);
							m.setPozicio(jatekMester.navigator.getMezo(x, y));
							m.getPozicio().beregisztral(m);
							if(m.szennyezodesVagyok()){
								m.setkopas(kop);
							}
						}
					}
				}
				else if(parancs.equals("LeteszRagacs") && running){
					
					String uj = parameterek[0];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.ragacsotTesz();
						}
					}
				}
				
				else if(parancs.equals("LeteszOlajfolt") && running){
					
					String uj = parameterek[0];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.olajfoltotTesz();
						}
					}
				}
				
				else if(parancs.equals("Ugrik") && running){
					
					String uj = parameterek[0];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.ugrik();
						}
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void leptet(){
		for(Robot r : robotok){
			r.ugrik();
		}
		for(Kisrobot kr : kisrobotok){
			kr.ugrik();
		}
	}
	
	void ujKisrobot(Kisrobot uj){
		kisrobotok.add(uj);
	}
	
	void torolKisrobot(Kisrobot torolt){
		kisrobotok.remove(torolt);
	}
	
	void ujRobot(Robot uj){
		robotok.add(uj);
	}
	
	void torolRobot(Robot torolt){
		robotok.remove(torolt);
	}
	
	int getkorszam(){
		return korszam;
	}
	
	void setkorszam(int szam){
		korszam = szam;
	}
	
	void tick(){
		
		System.out.println("[Jatek] új kör.");
		korszam++;
		navigator.tick();
	}
	
	/*boolean running = true;
	while(running){
<<<<<<< HEAD
		main.useCaseTablaKiirasa();
		bemenet = main.bemenetBekerese();
		if(bemenet.equals("1")){
			main.inditUseCase();
=======
		jatekMester.useCaseTablaKiirasa();
		bemenet = jatekMester.bemenetBekerese();
		if(bemenet.equals("1")){
			jatekMester.inditUseCase();
>>>>>>> branch 'master' of https://github.com/Freamy/Input_Coffee.git
		}else if(bemenet.equals("kilepes")){
			System.exit(0);
		}
	
	} 
	private void useCaseTablaKiirasa(){
		System.out.println("Addja meg a vizsgalni kivánt use-case szamat.");
		System.out.println("+-------------+");
		System.out.println("| 1 - Indit   |");
		System.out.println("+-------------+");
		System.out.println("A \"kilepes\" parancsra befejezodik a teszteles.");
	}
	
	private void inditUseCase(){
		navigator = new Navigator();
		robot1 = new Robot(navigator.getMezo(0) ,navigator);
		System.out.println("? Interakcio?: (y/n)");
		String bemenet = bemenetBekerese();
		if(bemenet.equals("y")){
			inditasAlternativaTabla();
			bemenet = bemenetBekerese();
			if(bemenet.equals("1")){
				Olajfolt olaj = new Olajfolt(navigator.getMezo(1));
				robot1.lep(null, false, false);
			}
			else if(bemenet.equals("2")){
				Ragacs ragacs = new Ragacs(navigator.getMezo(1));
				robot1.lep(null, false, false);
			}
			else if(bemenet.equals("3")){
				robot2 = new Robot(navigator.getMezo(1), navigator);
				robot1.lep(null, false, false);
			}
		}else{
			nemInteraktivInditasTabla();
			bemenet = bemenetBekerese();
			if(bemenet.equals("1")){
				leallitUseCase();
			}
			else if(bemenet.equals("2")){
				lerakUseCase();
			}
		}
	}
	
	private void nemInteraktivInditasTabla(){
		System.out.println("Addja meg a vizsgalni kivánt use-case szamat.");
		System.out.println("+-------------+");
		System.out.println("| 1 - Leallit |");
		System.out.println("| 2 - Lerak   |");
		System.out.println("+-------------+");
	}
	
	private void inditasAlternativaTabla(){
		System.out.println("Addja meg a vizsgalni kivánt alternativa szamat.");
		System.out.println("+----------------------+");
		System.out.println("| 1 - Olajfoltra ugrik |");
		System.out.println("| 2 - Ragacsra ugrik   |");
		System.out.println("| 3 - Robotra ugrik    |");
		System.out.println("+----------------------+");
	}
	
	private void leallitUseCase(){
		System.out.println("Java allat itt maximum egy GC hívás szerepelhet.");
	}
	
	private void lerakUseCase(){
		String bemenet;
		lerakUseCaseTableKiirasa();
		bemenet = bemenetBekerese();
		if(bemenet.equals("1")){
			robot1.olajfoltotTesz();
		}else if(bemenet.equals("2")){
			robot1.ragacsotTesz();
		}
	}
	
	private void lerakUseCaseTableKiirasa(){
		System.out.println("Addja meg a vizsgalni kivánt use-case szamat.");
		System.out.println("+----------------------+");
		System.out.println("| 1 - Olajfoltot lerak |");
		System.out.println("| 2 - Ragacsot lerak   |");
		System.out.println("+----------------------+");
	}
	
	private String bemenetBekerese(){
		try {
			System.out.print("? ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
}