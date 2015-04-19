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
		boolean running = false; // a j�t�k fut�s�t vizsg�lja, ha false akkor csak az exit �s a start parancsok h�vhat�k
		while(jatekMester.korszam< 30){ //egy j�t�k 30 k�r�s (t�bbre/kevesebbre is �ll�thatjuk ha szeretn�tek)
			
			BufferedReader be = new BufferedReader(new InputStreamReader(System.in)); 
			String bemenet;
			while((bemenet=be.readLine())!=null && bemenet.length()!=0){ //soronk�nt beolvassuk a bemeneteket, att�l f�gg�en, hogy van.
				String parancs = bemenet;
				String[] parameterek = new String[10];
				/*
				for(index=0; index<bemenet.length(); index++){
					char c = bemenet.charAt(index);
					if(c=='('){ //Ha z�r�jelet is beolvastunk, akkor param�ter is van k�t r�szre bontjuk
						parancs = bemenet.substring(0, index); //Parancs r�szre, ami alapj�n �rtelnezz�k a bemenetet.
						kezdo = index + 1;
					}
					
					else if(c==',' || c==')'){ //illetve param�ter r�szre, amiket t�mben t�rolunk, mert lehet t�bb is (max 10)
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
				String[] darabolo = parancs.split(";");
				if(darabolo.length > 1){
					// Vannak param�terek
					parancs = darabolo[0];
					darabolo = darabolo[1].split(",");
					int i = 0;
					for(String darab: darabolo){
						parameterek[i] = darab;
						i++;
					}
				}else{
					// Nincsnenek param�terek
					parancs = darabolo[0];
				}
				/** Until like this part **/
				
				if(parancs.equals("Start")){ //Elind�tja a j�t�kot
					running = true;
					System.out.println("[Jatek] indul�s.");
				}
				else if(parancs.equals("Stop")){ //Meg�ll�tja a j�t�kot, startal �jraind�that�
					running = false;
					System.out.println("[Jatek] meg�ll�s.");
				}
				else if(parancs.equals("Exit")){ //Le�ll a program fut�sa
					running = false;
					System.exit(0);
				}
				else if(parancs.equals("Tick") && running){ //Megh�vja a j�t�kmester tick f�ggv�ny�t
					jatekMester.tick();
				}
				else if(parancs.equals("AdatokMindenki") && running){
					
					//Ha a lenti TODO-k k�sz vannak az al�bbi sor kommentez�se feloldhat�:
					//navigator.adatKiirasa("");
					
					// TODO:
					// Navigator: adatKiirasa(String) Ki�rja a saj�t adatait (ha a param =  ""), majd ha param= " megh�vja minden Mez�re az adatKiirasa(param) f�ggv�nyt ami benne van.
					// Mezo: adatKiirasa(String param) Ki�rja a saj�t adatait (ha a param =  a nev�vel, vagy "") majd megh�vja minden Mezonallora az adatKiirasa(param) f�ggv�nyt ami benne van.
					// Mezonallok: adatKiirasa(String param) Ki�rja a saj�t adatait (ha a param = a nev�vel, vagy "").
				}
				else if(parancs.equals("AdatokNev") && running){
					
					
					//Ha a f�ggv�ny implement�lva van az al�bbi sor kommentez�se feloldhat�:
					//navigator.adatKiirasa(parameterek[0]);
					
				}
				else if(parancs.equals("AdatokMezo") && running){
					
					int x = Integer.parseInt(parameterek[0]);
					int y = Integer.parseInt(parameterek[1]);
					
					//Ha a lenti TODO-k k�sz vannak az al�bbi sor kommentez�se feloldhat�:
					//navigator.adatKiirasa(x,y);
					
					//TODO:
					//Navigator: adatKiirasa(int x, int y) megh�vja az (x,y) kordin�t�n l�v� mez�re az adatKiirasa("") f�ggv�nyt.
					
					
				}
				else if(parancs.equals("Torol") && running){ // T�rli a p�ly�n l�v� �sszes elemet
					

					ArrayList<Mezonallo> elemek;
					elemek = jatekMester.navigator.getOsszesElem(); //Visszaadja az �sszes elemet, ami a p�ly�n van �s egy list�ba menti.
					
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
				else if(parancs.equals("TorolMezonallo") && running){ //t�rli a param�terk�nt kapott mez�n�ll�t
					
					String uj = parameterek[0];
					ArrayList<Mezonallo> elemek;
					elemek = jatekMester.navigator.getOsszesElem(); //Visszaadja az �sszes elemet, ami a p�ly�n van �s egy list�ba menti.
					
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
					// Itt nem tudom hogy hogyan k�ne a parameter[0]-t a robot nev�nek adni, ha nincs n�v param�tere,
					// v�ltoz� nev� v�ltoz�t pedig nem tudom hogy k�ne l�trehozni
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
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					Ragacs uj = new Ragacs(jatekMester.navigator.getMezo(x, y), Integer.parseInt(parameterek[3]));
					uj.setPozicio(jatekMester.navigator.getMezo(x, y));
					uj.setNev(parameterek[0]);
					uj.getPozicio().beregisztral(uj);
				}
				else if(parancs.equals("UjOlajfolt") && running){
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					Olajfolt uj = new Olajfolt(jatekMester.navigator.getMezo(x, y), Integer.parseInt(parameterek[3]), "");
					uj.setPozicio(jatekMester.navigator.getMezo(x, y));
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
		
		System.out.println("[Jatek] �j k�r.");
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
		System.out.println("Addja meg a vizsgalni kiv�nt use-case szamat.");
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
		System.out.println("Addja meg a vizsgalni kiv�nt use-case szamat.");
		System.out.println("+-------------+");
		System.out.println("| 1 - Leallit |");
		System.out.println("| 2 - Lerak   |");
		System.out.println("+-------------+");
	}
	
	private void inditasAlternativaTabla(){
		System.out.println("Addja meg a vizsgalni kiv�nt alternativa szamat.");
		System.out.println("+----------------------+");
		System.out.println("| 1 - Olajfoltra ugrik |");
		System.out.println("| 2 - Ragacsra ugrik   |");
		System.out.println("| 3 - Robotra ugrik    |");
		System.out.println("+----------------------+");
	}
	
	private void leallitUseCase(){
		System.out.println("Java allat itt maximum egy GC h�v�s szerepelhet.");
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
		System.out.println("Addja meg a vizsgalni kiv�nt use-case szamat.");
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
