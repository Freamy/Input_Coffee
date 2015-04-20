import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


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
				String[] parameterek = new String[15];
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
				String[] darabolo = parancs.split("\\(");
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
					jatekMester.robotok = new ArrayList<Robot>();
					jatekMester.kisrobotok = new ArrayList<Kisrobot>();
					System.out.println("[Jatek] indul�s.");
				}
				else if(parancs.equals("Stop")){ //Meg�ll�tja a j�t�kot, startal �jraind�that�
					running = false;
					System.out.println("[Jatek] meg�ll�s.");
					System.exit(0);
				}
				else if(parancs.equals("Exit")){ //Le�ll a program fut�sa
					running = false;
					System.exit(0);
				}
				else if(parancs.equals("Tick") && running){ //Megh�vja a j�t�kmester tick f�ggv�ny�t
					jatekMester.tick();
				}
				else if(parancs.equals("AdatokMindenki") && running){

					jatekMester.navigator.adatokKiirasa("");
					
				}
				else if(parancs.equals("AdatokNev") && running){
					
					jatekMester.navigator.adatokKiirasa(parameterek[0]);
					
				}
				else if(parancs.equals("AdatokMezo") && running){
					
					jatekMester.navigator.adatokKiirasa(parameterek[0]+" "+parameterek[1]);		
					
				}
				else if(parancs.equals("Torol") && running){ // T�rli a p�ly�n l�v� �sszes elemet
					

					ArrayList<Mezonallo> elemek;
					elemek = jatekMester.navigator.getOsszesElem(); //Visszaadja az �sszes elemet, ami a p�ly�n van �s egy list�ba menti.
					
					ArrayList<Mezonallo> elemekIteralt = new ArrayList<Mezonallo>(elemek);
					
					for(Mezonallo m : elemekIteralt) {
						m.megsemmisul();
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
					
					
					ArrayList<Mezonallo> elemekIteralt = new ArrayList<Mezonallo>(elemek);
					
					
					
					for(Mezonallo mezonallo : elemekIteralt){
						String vizsgalt = mezonallo.getNev();
						if(vizsgalt.equals(uj)){
							mezonallo.megsemmisul();
						}
					}
					
				}
				else if(parancs.equals("TorolMezo") && running){
					int x = Integer.parseInt(parameterek[0]);
					int y = Integer.parseInt(parameterek[1]);
					
					ArrayList<Mezonallo> elemek;

					Mezo torlendo = jatekMester.navigator.getMezo(x, y);
					if (torlendo != null) {
					
					elemek = torlendo.getRajtamAllok();
					
					ArrayList<Mezonallo> elemekIteralt = new ArrayList<Mezonallo>(elemek);
					
					for(Mezonallo m : elemekIteralt) {
						m.megsemmisul();
					}
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
					try{

						String nev = parameterek[0];
						Integer x = Integer.parseInt(parameterek[1]);
						Integer y = Integer.parseInt(parameterek[2]);
						Integer vx = Integer.parseInt(parameterek[3]);
						Integer vy = Integer.parseInt(parameterek[4]);
						boolean modosithato;
						if (parameterek[5].equals("1")) modosithato = true;
						else modosithato = false;
						
						Integer ragacsDb = Integer.parseInt(parameterek[6]);
						Integer olajDb = Integer.parseInt(parameterek[7]);
						
						boolean vesztett;
						if (parameterek[8].equals("1")) vesztett = true;
						else vesztett = false;
						
						Double megtettUt = Double.parseDouble(parameterek[9]);
						
						Sebesseg seb = new Sebesseg(vx,vy);
						seb.setmodosithato(modosithato);
						
						Robot uj;
						Mezo poz = jatekMester.navigator.getMezo(x, y);
						if(poz != null) {
							uj = new Robot(nev, poz, jatekMester.navigator, seb, ragacsDb, olajDb, vesztett, megtettUt);
						    jatekMester.robotok.add(uj);
						}
						
					}catch(Exception e){
						//e.printStackTrace();
					}
				}
				
				else if(parancs.equals("UjKisrobot") && running){
					String nev = parameterek[0];
					Integer x = Integer.parseInt(parameterek[1]);
					Integer y = Integer.parseInt(parameterek[2]);
					Kisrobot uj;
					Mezo poz = jatekMester.navigator.getMezo(x, y);
					if(poz!=null) {
					uj = new Kisrobot(nev, poz, jatekMester.navigator);
					jatekMester.kisrobotok.add(uj);
					}
				}
				else if(parancs.equals("UjRagacs") && running){
					String nev = parameterek[0];
					int kord[] = {Integer.parseInt(parameterek[1]),Integer.parseInt(parameterek[2])};
					
					Ragacs uj;
					Mezo poz = jatekMester.navigator.getMezo(kord[0], kord[1]);
					if(poz!=null) {
					uj = new Ragacs(nev, poz, Integer.parseInt(parameterek[3]), kord);
					} 
				}
				else if(parancs.equals("UjOlajfolt") && running){
					String nev = parameterek[0];
					int kord[] = {Integer.parseInt(parameterek[1]),Integer.parseInt(parameterek[2])};
					
					Olajfolt uj;
					Mezo poz = jatekMester.navigator.getMezo(kord[0], kord[1]);
					if(poz!=null) {
					 uj = new Olajfolt(nev, poz, Integer.parseInt(parameterek[3]), kord);
					}

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
					
					String robotnev = parameterek[0];
					String ragacsnev = parameterek[1];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(robotnev)){
							r.ragacsotTesz(ragacsnev);
						}
					}
				}
				
				else if(parancs.equals("LeteszOlajfolt") && running){
					
					String robotnev = parameterek[0];
					String olajnev = parameterek[1];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(robotnev)){
							r.olajfoltotTesz(olajnev);
						}
					}
				}
				
				else if(parancs.equals("Ugrik") && running){
					
					String uj = parameterek[0];
					
					for(Robot r : jatekMester.robotok){
						String vizsgalt = r.getNev();
						if(vizsgalt.equals(uj)){
							r.lep(new Sebesseg(0,0) ,"","");
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
		
		System.out.println("[Jatek] �j k�r.");
		korszam++;
		navigator.tick();
	}
}
