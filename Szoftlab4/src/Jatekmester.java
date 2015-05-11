import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.io.*;
import java.util.ArrayList;
import java.math.*;

import javax.swing.*;

public class Jatekmester extends JFrame implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Navigator navigator = new Navigator();
	private ArrayList<Kisrobot> kisrobotok = new ArrayList<Kisrobot>();
	private ArrayList<Robot> robotok = new ArrayList<Robot>();
	private static int korszam = 1;
	public static Kepernyo kepernyo = new Kepernyo();
	private  int jatekosszam;
	
	public static void main(String[] args){
		
		Jatekmester jatekMester = new Jatekmester();
		Jatekmester.kepernyo.Menu(true);

		jatekMester.menukezeles();
		/*
		while(korszam < 30){
			jatekMester.leptet();
			jatekMester.tick();
		}
		//parancs�rtelmez�s r�sz kezdete
		try{
		
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
				/*
				// This is kinda not perfect yet
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
				// Until like this part
				
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
		*/
		//parancskezel� r�sz v�ge
		
	}
	private JButton jatekosok = new JButton("OK");
	private JLabel limit = new JLabel("Limit: 6");
	private JTextField jszam = new JTextField(5);
	private JTextField PalyaX = new JTextField(5);
	private JTextField PalyaY = new JTextField(5);
	private JLabel x = new JLabel("X");
	private JLabel szoveg1 = new JLabel("Palya merete:");
	private JLabel szoveg2 = new JLabel("Jatekosok szama:");
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();

	public void menukezeles(){
		
		this.setLayout(new BorderLayout());
		
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		//Hozz�adogatjuk a komponenseket a panelokhoz
		p1.add(szoveg1,BorderLayout.NORTH);
		p3.add(PalyaX,BorderLayout.WEST);
		p3.add(PalyaY,BorderLayout.EAST);
		p3.add(x,BorderLayout.CENTER);
		p1.add(p3,BorderLayout.CENTER);
		p2.add(szoveg2,BorderLayout.NORTH);
		p2.add(jszam,BorderLayout.WEST);
		p2.add(limit,BorderLayout.EAST);
		p2.add(jatekosok,BorderLayout.SOUTH);
		//A jatekosok nev� gombra defini�lunk egy ActionListenert
		jatekosok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					//n a PalyaX, m a PalyaY Textfield-b�l ker�l beolvas�sra.
					int n = Integer.parseInt(PalyaX.getText());
					int m = Integer.parseInt(PalyaY.getText());
					//a jatekosszam tagv�ltoz�t felt�ltj�k a jszam-ba be�rt �rt�kkel
					jatekosszam = Integer.parseInt(jszam.getText());
					//Megvizsg�ljuk hogy a felt�teleknek megfelel� �rt�kek ker�ltek a TextFieldekbe
					if((n > 0) && (m > 0) && (jatekosszam < 7) && (jatekosszam > 0)){
						//Ha igen elk�sz�tj�k a megadott m�ret� p�ly�t (Itt lehetne egy fels� korl�t is az n-re, m-re
						navigator.palyaKeszites(n,m);
					}
					
					else{
						//Ha nem, akkor be�ll�tunk alap�rt�keket a p�lya �s a j�t�kossz�m param�tereire
						//Ha b�rmelyik rosszul lett megadva, akkor az alapbe�ll�t�s l�p �rv�nybe.
						System.out.println("Rossz �rt�kek! Alap�rt�kek be�ll�t�sa:");	
						n = 15; m = 15;
						navigator.palyaKeszites(n,m);
						jatekosszam= 3;
					}
					//T�r�lj�k az �sszes komponenst a frame-r�l
					removeAll();
					//L�thatatlann� tessz�k
					setVisible(false);
					//inicializ�ljuk a megadott p�lyam�ret mellett a robotokat
					inicializal(navigator.getX(),navigator.getY());
					//Megh�vjuk a jatekosmegadas f�ggv�nyt, ami felnyit egy �j frame-t
					jatekosmegadas();
			}
			
		});
		//Itt hozz�adjuk a panelokat a frame-hez
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.SOUTH);
		//Be�ll�tjuk a m�ret�t, poz�ci�j�t,l�that�s�g�t.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//�tm�retez�s letiltva.
		this.setResizable(false);
		//Piros X gomb-ra l�pjen ki.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void jatekosmegadas(){
		
		//L�trehozunk egy �j frame-t �s a p1,p2,p3 panelokr�l let�rl�nk minden komponenst.
		final JFrame frame = new JFrame();
		p1.removeAll();
		p2.removeAll();
		p3.removeAll();
		
		//Minden kor�bban haszn�lt JTextField sz�veg�t �resre �ll�tjuk, mivel �jrafelhaszn�ljuk �ket
		jszam.setText("");
		PalyaX.setText("");
		PalyaY.setText("");
		
		//A label-ek sz�vegeit be�ll�tjuk, �s l�trehozunk �jjakat
		x.setText("Jatekosok nevei");
		szoveg1.setText("1");
		szoveg2.setText("2");
		JLabel szoveg3 = new JLabel("3");
		JLabel szoveg4 = new JLabel("4");
		JLabel szoveg5 = new JLabel("5");
		JLabel szoveg6 = new JLabel("6");
		
		// L�trehozunk �j TextFieldeket
		JTextField jatekos4 = new JTextField(5);
		JTextField jatekos5 = new JTextField(5);
		JTextField jatekos6 = new JTextField(5);
		
		//L�trehozunk 9 �j panelt
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel p11 = new JPanel();
		JPanel p12 = new JPanel();
		JPanel p21 = new JPanel();
		JPanel p22 = new JPanel();
		JPanel p31 = new JPanel();
		JPanel p32 = new JPanel();
		//Be�ll�tjuk a panelok �s a frame layoutj�t BorderLayoutra
		frame.setLayout(new BorderLayout());
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		p11.setLayout(new BorderLayout());
		p12.setLayout(new BorderLayout());
		p21.setLayout(new BorderLayout());
		p22.setLayout(new BorderLayout());
		p31.setLayout(new BorderLayout());
		p32.setLayout(new BorderLayout());
		
		//p11 panel t�rolja a szoveg1 �s jszam komponenseket
		p11.add(szoveg1,BorderLayout.WEST);
		p11.add(jszam,BorderLayout.EAST);
		
		//p12 panel t�rolja a szoveg4 �s jatekos4 komponenseket
		p12.add(szoveg4,BorderLayout.WEST);
		p12.add(jatekos4,BorderLayout.EAST);
		
		//p1 panel t�rolja a p11 �s a p12 komponenseket
		p1.add(p11,BorderLayout.WEST);
		p1.add(p12,BorderLayout.EAST);
		
		//panel1 t�rolja a x �s p1 komponenseket
		panel1.add(x,BorderLayout.NORTH);
		panel1.add(p1,BorderLayout.SOUTH);
			
		//p21 panel t�rolja a szoveg2 �s PalyaX komponenseket
		p21.add(szoveg2,BorderLayout.WEST);
		p21.add(PalyaX,BorderLayout.EAST);
		
		//p22 panel t�rolja a szoveg5 �s jatekos5 komponenseket
		p22.add(szoveg5,BorderLayout.WEST);
		p22.add(jatekos5,BorderLayout.EAST);
		
		//p2 panel t�rolja p21 x �s p22 komponenseket
		p2.add(p21,BorderLayout.WEST);
		p2.add(p22,BorderLayout.EAST);
		
		
		
		//p31 t�rolja a szoveg3 �s a PalyaY komponenseket
		p31.add(szoveg3,BorderLayout.WEST);
		p31.add(PalyaY,BorderLayout.EAST);
		
		//p32 t�rolja a szoveg6 �s jatekos6 komponenseket
		p32.add(szoveg6,BorderLayout.WEST);
		p32.add(jatekos6,BorderLayout.EAST);
		
		//p3 t�rolja a p31 �s p32 komponenseket
		p3.add(p31,BorderLayout.WEST);
		p3.add(p32,BorderLayout.EAST);
		
		//panel2 t�rolja a p2 �s p3 komponenseket
		panel2.add(p2,BorderLayout.NORTH);
		panel2.add(p3,BorderLayout.SOUTH);
		
		//panel t�rolja a panel1 �s panel2 komponenseket
		panel.add(panel1,BorderLayout.NORTH);
		panel.add(panel2,BorderLayout.SOUTH);
		
		//A frame pedig t�rolja a panelt �s a kezdes nev� gombot.
		frame.add(panel,BorderLayout.NORTH);
		
		//L�trehozunk egy kezdes nev� gombot, amit ha megnyomunk, akkor a frame elt�nik, elkezd�dik a j�t�k �s kirajzol�dik a p�lya
		JButton kezdes = new JButton("Start!");
		kezdes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				mainLoop();
			}
			
		});

		frame.add(kezdes,BorderLayout.SOUTH);
		//Ha kevesebb mint 6 j�t�kso j�tszik, akkor letiltunk annyi TextFieldet
		if(jatekosszam < 6) jatekos6.setEnabled(false);
		if(jatekosszam < 5) jatekos5.setEnabled(false);
		if(jatekosszam < 4) jatekos4.setEnabled(false);
		if(jatekosszam < 3) PalyaY.setEnabled(false);
		if(jatekosszam < 2) PalyaX.setEnabled(false);
		
		//A robotoknak be�ll�tjuk a nev attrib�tum�t, att�l f�gg�en, hogy mennyi van.
		for(int i=0; i < jatekosszam; i++){
			if(i==0) robotok.get(i).setNev(jszam.getText());
			if(i==1) robotok.get(i).setNev(PalyaX.getText());
			if(i==2) robotok.get(i).setNev(PalyaY.getText());
			if(i==3) robotok.get(i).setNev(jatekos4.getText());
			if(i==4) robotok.get(i).setNev(jatekos5.getText());
			if(i==5) robotok.get(i).setNev(jatekos6.getText());
		}
		//Frame-nek be�ll�tjuk a tulajdons�gait.
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Ezt a f�ggv�nyt akkor h�vjuk meg, ha a felhaszn�l� megadta, hogy h�ny j�t�kost szeretne egy n*m-es p�ly�n
	//�s l�trehozunk annyi robotot ah�ny j�t�kos van, valamint elhelyezz�k �ket egy adott mez�re a p�ly�n
	void inicializal(int n, int m){
		if ( n > m ) n = m;
		for(int i = 0; i < jatekosszam; i++){
			int random = (int) (Math.random()*(n)); 
			int random2 = random + i;
			if(random2 > n) random2 = random2 - n;
			Mezo mezo = navigator.getMezo(random,random2);
			while(navigator.kulsoMezo(mezo)){
				random++;
				random2--;
				if(random > n) random -=n;
				if(random2 < 0) random2 +=n;
				mezo = navigator.getMezo(random,random2);
			}
			ujRobot(mezo);
		}
		navigator.setKulsoMezo(2, 2, true);
	}
	
	//A l�ptet f�ggv�ny minden k�rben megh�v�dik �s az �sszes robotot l�ptetj�k, ehhez a felhaszn�l�
	//�ltal megadott �rt�kekre is sz�ks�g van(sebess�gv�ltoztat�s,ragacsot v olajat le akar tenni).
	//Ezenfel�l a kisrobotokat is l�pteti.
	void leptet(){
		for(Robot r : robotok){
			Sebesseg sebesseg;
			boolean ragacsle,olajle;
			sebesseg = kepernyo.sebessegkerdezo();
			ragacsle = kepernyo.ragacslekerdezo();
			olajle = kepernyo.olajlekerdezo();
			r.lep(sebesseg, ragacsle, olajle, kepernyo);
			r.getGrafika().frissit(r);
		}
		for(Kisrobot kr : kisrobotok){
			kr.ugrik();
		}
	}
	
	//L�trehozunk egy kisrobotot, ha a 3-as sz�mot kaptuk a pl: 6-7 koodin�t�ra
	/**Itt nincs lekezelve hogy mi van akkor ha 6-n�l �s 7-n�l kisebb a p�lya**/
	void ujKisrobot(int n, int m){
		if (n > m); n = m;
		int random  = (int) (Math.random() * 6 + 1);
		if (random == 3){
			int random2 = random * random;
			if(random2 > n) random2-=n;
			Mezo kisrobotbelepes = navigator.getMezo(random,random2);
			while(navigator.kulsoMezo(kisrobotbelepes)){
				random++;
				random2--;
				if(random > n) random -=n;
				if(random2 < 0) random2 +=n;
				kisrobotbelepes = navigator.getMezo(random,random2);
			}
			Kisrobot uj = new Kisrobot(kisrobotbelepes,navigator, kepernyo);
			kisrobotok.add(uj);
		}
	}
	
	//T�rl�nk egy megadott kisrobotot
	void torolKisrobot(Kisrobot torolt){
		kepernyo.grafikusElemKivesz(torolt.getGrafika());
		kisrobotok.remove(torolt);
	}
	
	//L�trehozunk egy Robotot a megadott mez�re
	void ujRobot(Mezo hova){
		Robot uj = new Robot(hova,navigator);
		robotok.add(uj);
	}
	
	//T�rl�nk egy megadott robotot
	void torolRobot(Robot torolt){
		kepernyo.grafikusElemKivesz(torolt.getGrafika());
		robotok.remove(torolt);
	}
	
	int getkorszam(){
		return korszam;
	}
	
	void setkorszam(int szam){
		korszam = szam;
	}
	
	//L�ptetj�k egyel a k�rsz�mot �s megpr�b�lunk l�trehozni egy ujKisrobotot, valamint minden elemn�l l�ptet�nk k�rsz�mot
	//a navigator.tick h�v�s seg�ts�g�vel, �s ezzel a kop�s �rt�kek cs�kkennek.
	void tick(){
		System.out.println("[Jatek] �j k�r.");	
		korszam++;
		ujKisrobot(navigator.getX(),navigator.getY());
		navigator.tick();
	}
	
	
	private void mainLoop(){
		this.removeAll();
		this.add(kepernyo);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setResizable(true);
		this.setSize(12*64, 12*64);
		this.setResizable(false);
		
		this.setVisible(true);
		
		navigator.getGrafikusPalya().grafikusPalyaFelvevese(navigator);
		
		kepernyo.initFrame();
		
		navigator.getGrafikusPalya().frissit(navigator);
		while(true){
			robotok.get(0).setAktiv(true);
			robotok.get(0).getGrafika().frissit(robotok.get(0));
			kepernyo.rajzol(this);
		}
		
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
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("!!");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}