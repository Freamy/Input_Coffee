import java.io.*;
import java.util.ArrayList;


public class Jatekmester {
	
	private Navigator navigator;
	private ArrayList<Kisrobot> kisrobotok;
	private ArrayList<Robot> robotok;
	private int korszam;
	
	public static void main(String[] args){
		
		Jatekmester main = new Jatekmester();
		boolean running = false; // a j�t�k fut�s�t vizsg�lja, ha false akkor csak az exit �s a start parancsok h�vhat�k
		
		while(main.korszam< 30){ //egy j�t�k 30 k�r�s (t�bbre/kevesebbre is �ll�thatjuk ha szeretn�tek)
			
			BufferedReader be = new BufferedReader(new InputStreamReader(System.in)); 
			String bemenet;
			
			while((bemenet=be.readLine())!=null && bemenet.length()!=0){ //soronk�nt beolvassuk a bemeneteket, att�l f�gg�en, hogy van.
				
				int index = 0;
				int kezdo = 0;
				int tobbparameter = 0;
				String parancs;
				String[] parameterek = new String[10];
				
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
				}
				
				if(parancs.equals("Start")){ //Elind�tja a j�t�kot
					running = true;
				}
				else if(parancs.equals("Stop")){ //Meg�ll�tja a j�t�kot, startal �jraind�that�
					running = false;
				}
				else if(parancs.equals("Exit")){ //Le�ll a program fut�sa
					running = false;
					System.exit(0);
				}
				else if(parancs.equals("Tick") && running){ //Megh�vja a j�t�kmester tick f�ggv�ny�t
					main.tick();
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
					elemek = main.navigator.getOsszesElem(); //Visszaadja az �sszes elemet, ami a p�ly�n van �s egy list�ba menti.
					
					for(Mezonallo m : elemek){
						m.getpozicio().leregisztral(m);
						if(robotok.contains(m)) robotok.remove(m);
						else if(kisrobotok.contains(m)) kisrobotok.remove(m);
					}
				}
				else if(parancs.equals("Palya") && running){ //
					
					Integer n = parameter[0];
					Integer k = parameter[1];
					main.navigator = new Navigator();
					
				}
				else if(parancs.equals("TorolMezonallo") && running){ //t�rli a param�terk�nt kapott mez�n�ll�t
					
					String uj = parameter[0];
					ArrayList<Mezonallo> elemek;
					elemek = main.navigator.getOsszesElem(); //Visszaadja az �sszes elemet, ami a p�ly�n van �s egy list�ba menti.
					
					for(Mezonallo m : elemek){
						String vizsgalt = (String) m;
						if(vizsgalt.equals(uj)){
							m.getpozicio().leregisztral(m);
							if(robotok.contains(m)) robotok.remove(m);
							else if(kisrobotok.contains(m)) kisrobotok.remove(m);
						}
					}
				}
				else if(parancs.equals("TorolMezo") && running){
					
					String uj = parameter[0];
					ArrayList<Mezonallo> elemek;
					elemek = main.navigator.getOsszesElem();
					
					for(Mezonallo m : elemek){
						String vizsgalt = (String) m.getpozicio();
						if(vizsgalt.equals(uj)){
							m.getpozicio().leregisztral(m);
							if(robotok.contains(m)) robotok.remove(m);
							else if(kisrobotok.contains(m)) kisrobotok.remove(m);
						}
					}
					
				}
				
				else if((parancs.equals("Kulso") || parancs.equals("Belso")) && running){
					
					String uj = parameter[0];
					int i,j =0;
					
					while(main.navigator.terkep[i]!=null){ //A p�lya �sszes elem�n v�gigmegy�nk �s ha megtal�ljuk be�ll�tjuk.
						
						while(main.navigator.terkep[i][j]!=null){
							
							String vizsgalt = (String) main.navigator.terkep[i][j];
							
							if(vizsgalt.equals(uj)){
								
								if(parancs.equals("Kulso")){
									main.navigator.setKulso(true);
								}
								else{
									main.navigator.setKulso(false);
								}
							}
							j++;
						}
						i++;
					}
				}
				
				else if(parancs.equals("UjRobot") && running){ 
					// Itt nem tudom hogy hogyan k�ne a parameter[0]-t a robot nev�nek adni, ha nincs n�v param�tere,
					// v�ltoz� nev� v�ltoz�t pedig nem tudom hogy k�ne l�trehozni
					Robot uj = new Robot();
					
					Integer x = Integer.parseInt(parameter[1]);
					Integer y = Integer.parseInt(parameter[2]);
					uj.setpozicio(main.navigator.terkep[x][y]);
					uj.getpozicio().beregisztral(uj);
					
					Integer vx = Integer.parseInt(parameter[3]);
					Integer vy = Integer.parseInt(parameter[4]);
					Sebesseg s = new Sebesseg(vx,vy);
					if(parameter[5].equals("true")){
						s.modosithato();
					}
					else if(parameter[5].equals("false")){
						s.modosithatatlan();
					}
					
					Integer ragacsDB = Integer.parseInt(parameter[6]);
					Integer olajDB = Integer.parseInt(parameter[7]);
					uj.setragacsDb(ragacsDB);
					uj.setolajDb(olajDB);
					
					if(parameter[8].equals("true")){
						uj.setVesztett(true);
					}
					else if (parameter[8].equals("false")){
						uj.setVesztett(false);
					}
					
					Integer ut = Integer.parseInt(parameter[9]);
					uj.setmegtettUt(ut);
					
					robotok.add(uj);
				}
				
				else if(parancs.equals("UjKisrobot") && running){
					
					Kisrobot uj = new Kisrobot();
					Integer x = Integer.parseInt(parameter[1]);
					Integer y = Integer.parseInt(parameter[2]);
					uj.setpozicio(main.navigator.terkep[x][y]);
					uj.getpozicio().beregisztral(uj);
					kisrobotok.add(uj);
				}
				else if(parancs.equals("UjRagacs") && running){
					
					Ragacs uj = new Ragacs();
					Integer x = Integer.parseInt(parameter[0]);
					Integer y = Integer.parseInt(parameter[1]);
					uj.setpozicio(main.navigator.terkep[x][y]);
					uj.getpozicio().beregisztral(uj);
				}
				else if(parancs.equals("UjOlajfolt") && running){
					
					Olajfolt uj = new Olajfolt();
					Integer x = Integer.parseInt(parameter[0]);
					Integer y = Integer.parseInt(parameter[1]);
					uj.setpozicio(main.navigator.terkep[x][y]);
					uj.getpozicio().beregisztral(uj);
				}
				
				else if(parancs.equals("ModositRobotHely") && running){
					
					String uj = parameter[0];
					Integer x = Integer.parseInt(parameter[1]);
					Integer y = Integer.parseInt(parameter[2]);
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							
							r.getpozicio().leregisztral(r);
							r.setpozicio(main.navigator.terkep[x][y]);
							r.getpozicio().beregisztral(r);
						}
					}
				}
				
				else if(parancs.equals("ModositRobotSebesseg") && running){
					
					String uj = parameter[0];
					Integer vx = Integer.parseInt(parameter[1]);
					Integer vy = Integer.parseInt(parameter[2]);
					Sebesseg s = new Sebesseg(vx,vy);
					if(parameter[3].equals("true")){
						s.modosithato();
					}
					else if(parameter[3].equals("false")){
						s.modosithatatlan();
					}
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							r.setSebesseg(s);
						}
					}
				}
				
				else if(parancs.equals("ModositRobotKeszlet") && running){
					
					String uj = parameter[0];
					Integer ragacs = Integer.parseInt(parameter[1]);
					Integer olaj = Integer.parseInt(parameter[2]);
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							r.setolajDb(olaj);
							r.setragacsDb(ragacs);
						}
					}
				}
				
				else if(parancs.equals("ModositRobotVesztett") && running){
					
					String uj = parameter[0];
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							if(parameter[1].equals("true")){
								r.setVesztett(true);
							}
							else if(parameter[1].equals("false")){
								r.setVesztett(false);
							}
						}
					}
				}
				else if(parancs.equals("ModositRobotMegtettUt") && running){
					
					String uj = parameter[0];
					Integer ut = Integer.parseInt(parameter[1]);
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							r.setmegtettUt(ut);
						}
					}
				}
				else if(parancs.equals("ModositKisrobot") && running){
					
					String uj = parameter[0];
					Integer x = Integer.parseInt(parameter[1]);
					Integer y = Integer.parseInt(parameter[2]);
					
					for(Kisrobot kr : kisrobotok){
						String vizsgalt = (String) kr;
						if(vizsgalt.equals(uj)){
							kr.getpozicio().leregisztral(kr);
							kr.setpozicio(main.navigator.terkep[x][y]);
							kr.getpozicio().beregisztral(kr);
						}
					}
				}
				else if((parancs.equals("ModositRagacs")|| parancs.equals("ModositOlajfolt")) && running){
					
					String uj = parameter[0];
					Integer x = Integer.parseInt(parameter[1]);
					Integer y = Integer.parseInt(parameter[2]);
					Integer kop = Integer.parseInt(parameter[3]);
					ArrayList<Mezonallo> elemek;
					elemek = main.navigator.getOsszesElem();
					
					for(Mezonallo m : elemek){
						String vizsgalt = (String) m;
						if(vizsgalt.equals(uj)){
							m.getpozicio().leregisztral(m);
							m.setpozicio(main.navigator.terkep[x][y]);
							m.getpozicio().beregisztral(kr);
							if(m.szennyezodesVagyok()){
								m.setkopas(kop);
							}
						}
					}
				}
				else if(parancs.equals("LeteszRagacs") && running){
					
					String uj = parameter[0];
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							Ragacs uj = new Ragacs(r.getpozicio());
						}
					}
				}
				
				else if(parancs.equals("LeteszOlajfolt") && running){
					
					String uj = parameter[0];
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							Olajfolt uj = new Olajfolt(r.getpozicio());
						}
					}
				}
				
				else if(parancs.equals("Ugrik") && running){
					
					String uj = parameter[0];
					
					for(Robot r : robotok){
						String vizsgalt = (String) r;
						if(vizsgalt.equals(uj)){
							r.ugrik();
						}
					}
				}
			}
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
		
		korszam++;
		navigator.tick();
		
		for(Robot r : robotok){
			r.tick();
		}
		
		for(Kisrobot kr : kisrobotok){
			kr.tick();
		}
	}
	
	/*boolean running = true;
	while(running){
		main.useCaseTablaKiirasa();
		bemenet = main.bemenetBekerese();
		if(bemenet.equals("1")){
			main.inditUseCase();
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