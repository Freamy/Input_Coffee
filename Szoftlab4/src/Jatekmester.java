import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.math.*;
import javax.swing.*;

public class Jatekmester extends JFrame{
	
	private static Navigator navigator = new Navigator();;
	private ArrayList<Kisrobot> kisrobotok = new ArrayList<Kisrobot>();
	private ArrayList<Robot> robotok = new ArrayList<Robot>();
	private int korszam = 1;
	private static Kepernyo kepernyo = new Kepernyo(navigator); 
	private  int jatekosszam;
	private KisRobotGyar kisgyar = new KisRobotGyar(kepernyo);
	private RobotGyar nagygyar = new RobotGyar(kepernyo);
	
	public static void setKepernyo(Kepernyo kepernyo) {
		Jatekmester.kepernyo = kepernyo;
	}
	public static Kepernyo getKepernyo() {
		return kepernyo;
	}
	public static void main(String[] args){
		//Parancsértelmezős rész
		try{
			Jatekmester jatekMester = new Jatekmester();
			System.out.println("MarsOne : Konzolosan vagy grafikusan szeretne játszani?");
			System.out.println("0: konzolosan, 1: grafikusan");
			BufferedReader be1 = new BufferedReader(new InputStreamReader(System.in)); 
			String szam0 = be1.readLine();
			Integer szam = Integer.parseInt(szam0);
			/*while((szam != 0) || (szam != 1)){
				System.out.println("Rossz értéket írt be, próbálja újból!");
				szam0 = be1.readLine();
				szam = Integer.parseInt(szam0);
			}*/
			if(szam == 0){
				boolean running = false; // a játék futását vizsgálja, ha false akkor csak az exit és a start parancsok hívhatók
				while(jatekMester.korszam< 30){ //egy játék 30 körös (többre/kevesebbre is állíthatjuk ha szeretnétek)
					
					BufferedReader be = new BufferedReader(new InputStreamReader(System.in)); 
					String bemenet;
					while((bemenet=be.readLine())!=null && bemenet.length()!=0){ //soronként beolvassuk a bemeneteket, attól függően, hogy van.
						String parancs = bemenet;
						String[] parameterek = new String[10];
						
						/*for(index=0; index<bemenet.length(); index++){
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
						// This is kinda not perfect yet
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
						// Until like this part
						
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
							
							//int x = Integer.parseInt(parameterek[0]);
							//int y = Integer.parseInt(parameterek[1]);
							
							//Ha a lenti TODO-k kész vannak az alábbi sor kommentezése feloldható:
							navigator.adatokKiirasa(parameterek[0]);
							
							//TODO:
							//Navigator: adatKiirasa(int x, int y) meghívja az (x,y) kordinátán lévő mezőre az adatKiirasa("") függvényt.
							
							
						}
						else if(parancs.equals("Torol") && running){ // Törli a pályán lévő összes elemet
							

							ArrayList<Mezonallo> elemek;
							elemek = navigator.getOsszesElem(); //Visszaadja az összes elemet, ami a pályán van és egy listába menti.
							
							for(Mezonallo m : elemek){
								m.getPozicio().leregisztral(m);
								if(jatekMester.robotok.contains(m)) jatekMester.robotok.remove(m);
								else if(jatekMester.kisrobotok.contains(m)) jatekMester.kisrobotok.remove(m);
							}
						}
						else if(parancs.equals("Palya") && running){ //
							
							Integer n = Integer.parseInt(parameterek[0]);
							Integer k = Integer.parseInt(parameterek[1]);
							navigator = new Navigator();
							navigator.palyaKeszites(n,k);
							
						}
						else if(parancs.equals("TorolMezonallo") && running){ //törli a paraméterként kapott mezőnállót
							
							String uj = parameterek[0];
							ArrayList<Mezonallo> elemek;
							elemek = navigator.getOsszesElem(); //Visszaadja az összes elemet, ami a pályán van és egy listába menti.
							
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
							Mezo torlendo = navigator.getMezo(x, y);
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
									navigator.setKulsoMezo(x,y,true);
								}
								else{
									navigator.setKulsoMezo(x,y,false);
								}
						}
						
						else if(parancs.equals("UjRobot") && running){ 
							// Itt nem tudom hogy hogyan kéne a parameter[0]-t a robot nevének adni, ha nincs név paramétere,
							// változó nevű változót pedig nem tudom hogy kéne létrehozni
							Integer x = Integer.parseInt(parameterek[1]);
							Integer y = Integer.parseInt(parameterek[2]);
							Robot uj = new Robot("UJRobot"+x+y,navigator.getMezo(x, y), navigator,new Sebesseg(1,1),0,0,false,0,jatekMester.nagygyar);

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
							KisRobotGyar gyar = new KisRobotGyar(kepernyo);
							Kisrobot uj = new Kisrobot(navigator.getMezo(x, y), navigator,gyar);
							uj.setPozicio(navigator.getMezo(x, y));
							uj.getPozicio().beregisztral(uj);
							uj.setNev(parameterek[0]);
							jatekMester.kisrobotok.add(uj);
						}
						else if(parancs.equals("UjRagacs") && running){

							int kord[] = {Integer.parseInt(parameterek[1]),Integer.parseInt(parameterek[2])};
							
							Ragacs uj = new Ragacs(navigator.getMezo(kord[0], kord[1]), Integer.parseInt(parameterek[3]), kord,kepernyo);
							uj.setPozicio(navigator.getMezo(kord[0], kord[1]));
							uj.setNev(parameterek[0]);
							uj.getPozicio().beregisztral(uj);
						}
						else if(parancs.equals("UjOlajfolt") && running){
							
							int kord[] = {Integer.parseInt(parameterek[1]),Integer.parseInt(parameterek[2])};
							
							Olajfolt uj = new Olajfolt(navigator.getMezo(kord[0], kord[1]), Integer.parseInt(parameterek[3]), "", kord,kepernyo);
							uj.setPozicio(navigator.getMezo(kord[0], kord[1]));
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
									r.setPozicio(navigator.getMezo(x, y));
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
									kr.setPozicio(navigator.getMezo(x,y));
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
							elemek = navigator.getOsszesElem();
							
							for(Mezonallo m : elemek){
								String vizsgalt = m.getNev();
								if(vizsgalt.equals(uj)){
									m.getPozicio().leregisztral(m);
									m.setPozicio(navigator.getMezo(x, y));
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
									r.ragacsotTesz(r.getNev()+"ragacsa");
								}
							}
						}
						
						else if(parancs.equals("LeteszOlajfolt") && running){
							
							String uj = parameterek[0];
							
							for(Robot r : jatekMester.robotok){
								String vizsgalt = r.getNev();
								if(vizsgalt.equals(uj)){
									r.olajfoltotTesz(r.getNev()+"ragacsa");
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
			}
			else if(szam == 1){
				Jatekmester.getKepernyo().Menu(true);
				GrafikusPalya ge = new GrafikusPalya(0,0,20,"gfx/rajz2.png","gfx/rajz3.png",getKepernyo());
				navigator.setGrafika(ge);
				jatekMester.menukezeles();
			}
		}catch(Exception ex){
			System.out.println("Exception.");
		}
		
	}
	private JButton jatekosok = new JButton("OK");
	private JLabel limit = new JLabel("Limit: 6");
	private JTextField jszam = new JTextField(5);
	private JTextField PalyaX = new JTextField(5);
	private JTextField PalyaY = new JTextField(5);
	private JTextField jatekos4 = new JTextField(5);
	private JTextField jatekos5 = new JTextField(5);
	private JTextField jatekos6 = new JTextField(5);
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
		//Hozzáadogatjuk a komponenseket a panelokhoz
		p1.add(szoveg1,BorderLayout.NORTH);
		p3.add(PalyaX,BorderLayout.WEST);
		p3.add(PalyaY,BorderLayout.EAST);
		p3.add(x,BorderLayout.CENTER);
		p1.add(p3,BorderLayout.CENTER);
		p2.add(szoveg2,BorderLayout.NORTH);
		p2.add(jszam,BorderLayout.WEST);
		p2.add(limit,BorderLayout.EAST);
		p2.add(jatekosok,BorderLayout.SOUTH);
		//A jatekosok nevű gombra definiálunk egy ActionListenert
		jatekosok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
					//n a PalyaX, m a PalyaY Textfield-ből kerül beolvasásra.
					int n = Integer.parseInt(PalyaX.getText());
					int m = Integer.parseInt(PalyaY.getText());
					//a jatekosszam tagváltozót feltöltjük a jszam-ba beírt értékkel
					jatekosszam = Integer.parseInt(jszam.getText());
					//Megvizsgáljuk hogy a feltételeknek megfelelő értékek kerültek a TextFieldekbe
					if((n > 0) && (m > 0) && (jatekosszam < 7) && (jatekosszam > 0)){
						//Ha igen elkészítjük a megadott méretű pályát (Itt lehetne egy felső korlát is az n-re, m-re
						navigator.palyaKeszites(n,m);
					}
					
					else{
						//Ha nem, akkor beállítunk alapértékeket a pálya és a játékosszám paramétereire
						//Ha bármelyik rosszul lett megadva, akkor az alapbeállítás lép érvénybe.
						System.out.println("Rossz értékek! Alapértékek beállítása:");	
						n = 15; m = 15;
						navigator.palyaKeszites(n,m);
						jatekosszam= 3;
					}
					//Töröljük az összes komponenst a frame-ről
					removeAll();
					//Láthatatlanná tesszük
					setVisible(false);
					navigator.setKulsoMezo(1,1,true);
					//inicializáljuk a megadott pályaméret mellett a robotokat
					inicializal(navigator.getX(),navigator.getY());
					//Meghívjuk a jatekosmegadas függvényt, ami felnyit egy új frame-t
					jatekosmegadas();
			}
			
		});
		//Itt hozzáadjuk a panelokat a frame-hez
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.SOUTH);
		//Beállítjuk a méretét, pozícióját,láthatóságát.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//Átméretezés letiltva.
		this.setResizable(false);
		//Piros X gomb-ra lépjen ki.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void jatekosmegadas(){
		
		//Létrehozunk egy új frame-t és a p1,p2,p3 panelokról letörlünk minden komponenst.
		final JFrame frame = new JFrame();
		p1.removeAll();
		p2.removeAll();
		p3.removeAll();
		
		//Minden korábban használt JTextField szövegét üresre állítjuk, mivel újrafelhasználjuk őket
		jszam.setText("");
		PalyaX.setText("");
		PalyaY.setText("");
		
		//A label-ek szövegeit beállítjuk, és létrehozunk újjakat
		x.setText("Jatekosok nevei");
		szoveg1.setText("1");
		szoveg2.setText("2");
		JLabel szoveg3 = new JLabel("3");
		JLabel szoveg4 = new JLabel("4");
		JLabel szoveg5 = new JLabel("5");
		JLabel szoveg6 = new JLabel("6");
		
		// Létrehozunk új TextFieldeket
		//JTextField jatekos4 = new JTextField(5);
		//JTextField jatekos5 = new JTextField(5);
		//JTextField jatekos6 = new JTextField(5);
		
		//Létrehozunk 9 új panelt
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel p11 = new JPanel();
		JPanel p12 = new JPanel();
		JPanel p21 = new JPanel();
		JPanel p22 = new JPanel();
		JPanel p31 = new JPanel();
		JPanel p32 = new JPanel();
		//Beállítjuk a panelok és a frame layoutját BorderLayoutra
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
		
		//p11 panel tárolja a szoveg1 és jszam komponenseket
		p11.add(szoveg1,BorderLayout.WEST);
		p11.add(jszam,BorderLayout.EAST);
		
		//p12 panel tárolja a szoveg4 és jatekos4 komponenseket
		p12.add(szoveg4,BorderLayout.WEST);
		p12.add(jatekos4,BorderLayout.EAST);
		
		//p1 panel tárolja a p11 és a p12 komponenseket
		p1.add(p11,BorderLayout.WEST);
		p1.add(p12,BorderLayout.EAST);
		
		//panel1 tárolja a x és p1 komponenseket
		panel1.add(x,BorderLayout.NORTH);
		panel1.add(p1,BorderLayout.SOUTH);
			
		//p21 panel tárolja a szoveg2 és PalyaX komponenseket
		p21.add(szoveg2,BorderLayout.WEST);
		p21.add(PalyaX,BorderLayout.EAST);
		
		//p22 panel tárolja a szoveg5 és jatekos5 komponenseket
		p22.add(szoveg5,BorderLayout.WEST);
		p22.add(jatekos5,BorderLayout.EAST);
		
		//p2 panel tárolja p21 x és p22 komponenseket
		p2.add(p21,BorderLayout.WEST);
		p2.add(p22,BorderLayout.EAST);
		
		
		
		//p31 tárolja a szoveg3 és a PalyaY komponenseket
		p31.add(szoveg3,BorderLayout.WEST);
		p31.add(PalyaY,BorderLayout.EAST);
		
		//p32 tárolja a szoveg6 és jatekos6 komponenseket
		p32.add(szoveg6,BorderLayout.WEST);
		p32.add(jatekos6,BorderLayout.EAST);
		
		//p3 tárolja a p31 és p32 komponenseket
		p3.add(p31,BorderLayout.WEST);
		p3.add(p32,BorderLayout.EAST);
		
		//panel2 tárolja a p2 és p3 komponenseket
		panel2.add(p2,BorderLayout.NORTH);
		panel2.add(p3,BorderLayout.SOUTH);
		
		//panel tárolja a panel1 és panel2 komponenseket
		panel.add(panel1,BorderLayout.NORTH);
		panel.add(panel2,BorderLayout.SOUTH);
		
		//A frame pedig tárolja a panelt és a kezdes nevű gombot.
		frame.add(panel,BorderLayout.NORTH);
		
		//Létrehozunk egy kezdes nevű gombot, amit ha megnyomunk, akkor a frame eltűnik, elkezdődik a játék és kirajzolódik a pálya
		JButton kezdes = new JButton("Start!");
		kezdes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//A robotoknak beállítjuk a nev attribútumát, attól függően, hogy mennyi van.
				for(int i=0; i < jatekosszam; i++){
					if(i==0) robotok.get(i).setNev(jszam.getText());
					if(i==1) robotok.get(i).setNev(PalyaX.getText());
					if(i==2) robotok.get(i).setNev(PalyaY.getText());
					if(i==3) robotok.get(i).setNev(jatekos4.getText());
					if(i==4) robotok.get(i).setNev(jatekos5.getText());
					if(i==5) robotok.get(i).setNev(jatekos6.getText());
				}
				frame.setVisible(false);
				getKepernyo().Menu(false);
				getKepernyo().rajzol(getFrame());
				mainloop();
			}
			
		});
		
		frame.add(kezdes,BorderLayout.SOUTH);
		//Ha kevesebb mint 6 játékso játszik, akkor letiltunk annyi TextFieldet
		if(jatekosszam < 6) jatekos6.setEnabled(false);
		if(jatekosszam < 5) jatekos5.setEnabled(false);
		if(jatekosszam < 4) jatekos4.setEnabled(false);
		if(jatekosszam < 3) PalyaY.setEnabled(false);
		if(jatekosszam < 2) PalyaX.setEnabled(false);
		
		for(int i=0; i < jatekosszam; i++){
			if(i==0) robotok.get(i).setNev(jszam.getText());
			if(i==1) robotok.get(i).setNev(PalyaX.getText());
			if(i==2) robotok.get(i).setNev(PalyaY.getText());
			if(i==3) robotok.get(i).setNev(jatekos4.getText());
			if(i==4) robotok.get(i).setNev(jatekos5.getText());
			if(i==5) robotok.get(i).setNev(jatekos6.getText());
		}
		
		//Frame-nek beállítjuk a tulajdonságait.
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public JFrame getFrame(){
		return this;
	}
	//Ezt a függvényt akkor hívjuk meg, ha a felhasználó megadta, hogy hány játékost szeretne egy n*m-es pályán
	//És létrehozunk annyi robotot ahány játékos van, valamint elhelyezzük őket egy adott mezőre a pályán
	void inicializal(int n, int m){
		if ( n > m ) n = m;
		for(int i = 0; i < jatekosszam; i++){
			int random = (int) (Math.random()*(n)); 
			int random2 = random/2;
			if(random2 > n) random2 = random2 - n;
			if(random > n) random = random - n;
			Mezo mezo = navigator.getMezo(random,random2);
			while(navigator.getKulsoMezo(random,random2) || navigator.getMezo(random,random2).rajtamAllok.size()>0){
				random++;
				random2--;
				if(random > n) random -=n;
				if(random2 < 0) random2 +=n;
				mezo = navigator.getMezo(random,random2);
			}
			ujRobot(mezo,i);
		}
	}
	
	//A léptet függvény minden körben meghívódik és az összes robotot léptetjük, ehhez a felhasználó
	//által megadott értékekre is szükség van(sebességváltoztatás,ragacsot v olajat le akar tenni).
	//Ezenfelül a kisrobotokat is lépteti.
	public class Ugrasevent implements KeyListener{
		Jatekmester jatekmester;
		public Ugrasevent(Jatekmester jm){
			this.jatekmester = jm;
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			char c = arg0.getKeyChar();
			
			if( c =='1' && !ujragacs){
				jatekmester.ujolaj = true;
			}
			else if( c =='2' && !ujolaj){
				jatekmester.ujragacs = true;
			}
		    if( c=='w' || c=='a' || c=='s' || c=='d'){
		    	Sebesseg sebesseg = new Sebesseg(0,0);
				if( c =='w'){
					sebesseg.setx(0);
					sebesseg.sety(-1);
					jatekmester.ujsebesseg = sebesseg;
				}
				else if( c =='a'){
					sebesseg.setx(-1);
					sebesseg.sety(0);
					jatekmester.ujsebesseg = sebesseg;
				}
				else if( c =='s'){
					sebesseg.setx(0);
					sebesseg.sety(+1);
					jatekmester.ujsebesseg = sebesseg;
				}
				else if( c =='d'){
					sebesseg.setx(1);
					sebesseg.sety(0);
					jatekmester.ujsebesseg = sebesseg;
				}
				int kiesettdb = 0;
				for(int j=0; j<jatekosszam; j++){
					if(robotok.get(j).getMegsemmisult() || robotok.get(j).getVesztett())
						kiesettdb++;
				}
				if(korszam < 30 && kiesettdb<jatekosszam-1){
					leptet();
				}
				else{
					System.out.println("A játék véget ért");
					String message = new String();
					for(int j=0; j<jatekosszam; j++){
						message+=robotok.get(j).getNev();
						message+=":";
						message+=robotok.get(j).getmegtettUt();
						message+=", ";
					}
					JOptionPane.showMessageDialog(getFrame(), message);
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	void leptet(){
		robotok.get(i).lep(ujsebesseg,ujolaj,ujragacs,kepernyo);
		this.ujsebesseg = this.robot.getSebesseg();
		robotok.get(i).setAktiv(false);
		robotok.get(i).getGrafika().frissit(robotok.get(i));
		getKepernyo().rajzol(this);
		i++;
		if(i == jatekosszam){
			i = 0;
			tick();
		}
		this.robot = robotok.get(i);
		this.ujsebesseg = this.robot.getSebesseg();
		this.ujolaj = false;
		this.ujragacs = false;
		robotok.get(i).setAktiv(true);
		robotok.get(i).getGrafika().frissit(robotok.get(i));
		getKepernyo().rajzol(this);
	}
	
	void kisrobotlepteto(){
		for(Kisrobot kr : kisrobotok){
			kr.ugrik();
		}
	}
	
	private int i = 0;
	private Robot robot;
	private Sebesseg ujsebesseg;
	private boolean ujragacs = false, ujolaj =false;
	//Létrehozunk egy kisrobotot,  ha a 3-as számot kaptuk a pl: 6-7 koodinátára
	/**Itt nincs lekezelve hogy mi van akkor ha 6-nál és 7-nél kisebb a pálya**/
	
	
	void ujKisrobot(int n, int m){
		if (n > m); n = m;
		int random  = (int) (Math.random() * 6 + 1);
		if (random == 3){
			int random2 = random/2;
			if(random2 > n) random2-=n;
			Mezo kisrobotbelepes = navigator.getMezo(random,random2);
			while(navigator.kulsoMezo(kisrobotbelepes)){
				random++;
				random2--;
				if(random > n) random -=n;
				if(random2 < 0) random2 +=n;
				kisrobotbelepes = navigator.getMezo(random,random2);
			}
			Kisrobot uj = new Kisrobot(kisrobotbelepes,navigator,kisgyar);
			kisrobotok.add(uj);
			getKepernyo().grafikusElemHozzaad(uj.getGrafika());
		}
	}
	
	//Törlünk egy megadott kisrobotot
	void torolKisrobot(Kisrobot torolt){
		getKepernyo().grafikusElemKivesz(torolt.getGrafika());
		kisrobotok.remove(torolt);
	}
	
	//Létrehozunk egy Robotot a megadott mezőre
	void ujRobot(Mezo hova, int i){
		Sebesseg sebesseg = new Sebesseg(0,0);
		Robot uj = new Robot("Robot"+i,hova,navigator,sebesseg,5,5,false,0,nagygyar);
		getKepernyo().grafikusElemHozzaad(uj.getGrafika());
		robotok.add(uj);
	}
	
	//Törlünk egy megadott robotot
	void torolRobot(Robot torolt){
		getKepernyo().grafikusElemKivesz(torolt.getGrafika());
		robotok.remove(torolt);
	}
	
	int getkorszam(){
		return korszam;
	}
	
	void setkorszam(int szam){
		korszam = szam;
	}
	
	//Léptetjük egyel a körszámot és megpróbálunk létrehozni egy ujKisrobotot, valamint minden elemnél léptetünk körszámot
	//a navigator.tick hívás segítségével, és ezzel a kopás értékek csökkennek.
	void tick(){
		System.out.println("[Jatek] új kör.");	
		korszam++;
		for(Robot r : robotok){
			r.tick();
		}
		ujKisrobot(navigator.getX(),navigator.getY());
		kisrobotlepteto();
		navigator.tick();
	}
	private void mainloop(){
		this.removeAll();
		this.add(kepernyo);
		this.setFocusable(true);
		this.setResizable(true);
		this.setSize(navigator.getX()*64,navigator.getY()*64);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		navigator.getGrafikusPalya().grafikusPalyaFelvevese(navigator);
		kepernyo.initFrame();
		navigator.getGrafikusPalya().rajzol(this.getGraphics());
		Ugrasevent e = new Ugrasevent(this);
		this.addKeyListener(e);
		this.robot = robotok.get(i);
		this.ujsebesseg = robot.getSebesseg();
		robotok.get(i).setAktiv(true);
		robotok.get(i).getGrafika().frissit(robotok.get(i));
		kepernyo.rajzol(this);
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