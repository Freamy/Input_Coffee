import java.util.ArrayList;


import java.util.ArrayList;


public class Navigator {
	
	private Gyar grafikaGyar;
	
	private GrafikusPalya grafikusPalya;
	private Mezo[][] terkep;
	private boolean[][] kulsoMezok;
	
	public Navigator(){
		grafikaGyar = new PalyaGyar(Jatekmester.getKepernyo());
		grafikusPalya = (GrafikusPalya) grafikaGyar.grafikaKeszitese(Jatekmester.getKepernyo(), null);
	}
	
	public Mezo getMezo(int id){
		int x = id%terkep.length;
		int y = id/terkep[0].length;
		if(x > 0 && y > 0 && x < terkep.length && y < terkep[0].length) return terkep[x][y];
		else {
			System.out.println("Hiba: [Palya] nem l�tez� mez�.");
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Mezo getMezo(int x, int y){
		if(x < 0 || y < 0 || x >= terkep.length || y >= terkep[0].length){
			System.out.println("Hiba: [Palya] nem l�tez� mez�.");
			return null;
		}
		return terkep[x][y];
	}
	
	Mezo athelyez(Mezo honnan, Sebesseg sebesseg){
		int[] koordinatak = koordinataKonverter(honnan);
		int x = koordinatak[0];
		int y = koordinatak[1];
		x += sebesseg.getx();
		y += sebesseg.gety();
		if(x < 0 || y < 0 || x >= terkep.length || y >= terkep[0].length) 
		{
			return null;
		} else {
			return terkep[x][y];
		}
	}
	
	//Meg keress�k egy adott poz�ci�tol legk�zelebbi szennyez�d�st tartalmaz� mez�t, �s ezt	visszaadjuk.
	public Mezo kozeliszennyezodes(Mezo honnan){
		int x = 0;
		int y = 0;
		int[] koordinatak = koordinataKonverter(honnan);
		x = koordinatak[0];
		y = koordinatak[1];
		int tavolsag = 0;
		int legkozelebbi = Integer.MAX_VALUE;
		int kozeliX = 0;
		int kozeliY = 0;
		for(int i = 0; i < terkep.length; i++){
			for(int j = 0; j < terkep[0].length; j++){
				if(!(j == x && i == y) && terkep[i][j].szennykeres()){
					tavolsag = ((x-j)*(x-j))+((y-i)*(y-i));
					if(tavolsag < legkozelebbi){
						legkozelebbi = tavolsag;
						kozeliX = i;
						kozeliY = j;
					}
				}
			}
		}
		if(legkozelebbi != Integer.MAX_VALUE) {
			return terkep[kozeliX][kozeliY];
		} else {
			return null;
		}
	}
	
	
	//Dijkstra algortimus haszn�lat�val a f�ggv�ny megkeresi a legr�videbb utat a jelenglei �s a c�l kordin�ta k�z�tt. 
	//Majd ennek az �tnak az els� l�p�s�t vissza adja.
	public Mezo legrovidebbut(Mezo honnan, Mezo hova){

			ArrayList<Mezo> lezart = new ArrayList<Mezo>();
			ArrayList<Mezo> vizsgalt = new ArrayList<Mezo>();
			
			int uthosszak[][] = new int[terkep.length][terkep[0].length];
			Mezo keresztul[][] = new Mezo[terkep.length][terkep[0].length];
			
			for(int i = 0; i<terkep.length; i++) {
				for(int j = 0; j<terkep[0].length; j++) {
					uthosszak[i][j] = Integer.MAX_VALUE;
				}
			}
			vizsgalt.add(honnan);
			int kord[] = koordinataKonverter(honnan);
			uthosszak[kord[0]][kord[1]] = 0;
			
			while(!vizsgalt.isEmpty() && !lezart.contains(hova)) {
				Mezo aktualis = null;
				int min = Integer.MAX_VALUE;
				for(Mezo m : vizsgalt) {
					kord = koordinataKonverter(m);
					if(uthosszak[kord[0]][kord[1]] < min){
						min = uthosszak[kord[0]][kord[1]];
						aktualis = m;
					}
				}
				
				if(aktualis!=null) {
					vizsgalt.remove(aktualis);
					lezart.add(aktualis);
					
					
					kord = koordinataKonverter(aktualis);
					
					// Fent
					if (0<=kord[0] && kord[0]<terkep.length && 0<=(kord[1]-1) && (kord[1]-1)<terkep[0].length) {
						if(!kulsoMezok[kord[0]][kord[1]-1]) {
							if (uthosszak[kord[0]][kord[1]] + 1 < uthosszak[kord[0]][kord[1]-1]) {
								uthosszak[kord[0]][kord[1]-1] = uthosszak[kord[0]][kord[1]] + 1;
								keresztul[kord[0]][kord[1]-1] = terkep[kord[0]][kord[1]];
								vizsgalt.add(terkep[kord[0]][kord[1]-1]);
							}
						}
					}
					
					// Lent
					if (0<=kord[0] && kord[0]<terkep.length && 0<=(kord[1]+1) && (kord[1]+1)<terkep[0].length) {
						if(!kulsoMezok[kord[0]][kord[1]+1]) {
							if (uthosszak[kord[0]][kord[1]] + 1 < uthosszak[kord[0]][kord[1]+1]) {
								uthosszak[kord[0]][kord[1]+1] = uthosszak[kord[0]][kord[1]] + 1;
								keresztul[kord[0]][kord[1]+1] = terkep[kord[0]][kord[1]];
								vizsgalt.add(terkep[kord[0]][kord[1]+1]);
							}
						}
					}
					// Jobbra
					if (0<=(kord[0]+1) && (kord[0]+1)<terkep.length && 0<=kord[1] && kord[1]<terkep[0].length) {
						if(!kulsoMezok[kord[0]+1][kord[1]]) {
							if (uthosszak[kord[0]][kord[1]] + 1 < uthosszak[kord[0]+1][kord[1]]) {
								uthosszak[kord[0]+1][kord[1]] = uthosszak[kord[0]][kord[1]] + 1;
								keresztul[kord[0]+1][kord[1]] = terkep[kord[0]][kord[1]];
								vizsgalt.add(terkep[kord[0]+1][kord[1]]);
							}
						}
					}
					//Balra
					if (0<=(kord[0]-1) && (kord[0]-1)<terkep.length && 0<=kord[1] && kord[1]<terkep[0].length) {
						if(!kulsoMezok[kord[0]-1][kord[1]]) {
							if (uthosszak[kord[0]][kord[1]] + 1 < uthosszak[kord[0]-1][kord[1]]) {
								uthosszak[kord[0]-1][kord[1]] = uthosszak[kord[0]][kord[1]] + 1;
								keresztul[kord[0]-1][kord[1]] = terkep[kord[0]][kord[1]];
								vizsgalt.add(terkep[kord[0]-1][kord[1]]);
							}
						}
					}
				}
			}
			
			
			if (lezart.contains(hova)) {
				kord = koordinataKonverter(hova);
				while(keresztul[kord[0]][kord[1]] != honnan) {
					kord = koordinataKonverter(keresztul[kord[0]][kord[1]]);
				}
				
				return terkep[kord[0]][kord[1]];
			}
			
			
			return null;
	}
	
	public int[] koordinataKonverter(Mezo honnan){
			if(honnan!=null) {
			int x = 0;
			int y = 0;
			for(int i = 0; i < terkep.length; i++){
				for(int j = 0; j < terkep[i].length; j++){
					if(honnan.equals(terkep[i][j])) {
						x = i;
						y = j;
					}
				}
			}
			int t[] = {x,y};
			return t;
			}
			else {
				System.out.println("Hiba: [Palya] olyan mez�t adt�l ami nem l�tezik.");
				return null;
			}
	}
	
	boolean kulsoMezo(Mezo mezo){
		int[] koordinatak = koordinataKonverter(mezo);
		int x = koordinatak[0];
		int y = koordinatak[1];
		return kulsoMezok[x][y];
	}

	public ArrayList<Mezonallo> getOsszesElem() {
		ArrayList<Mezonallo> osszesElem = new ArrayList<Mezonallo>();
		for(int i = 0; i < terkep.length; i++){
			for(int j = 0; j < terkep[0].length; j++){
				if(terkep[i][j] != null) {
					osszesElem.addAll(terkep[i][j].getRajtamAllok());
				}
			}
		}
		return osszesElem;
	}

	public void palyaKeszites(Integer n, Integer k) {
		terkep = new Mezo[n][k];
		kulsoMezok = new boolean[n][k];
		System.out.println("[Palya] l�trej�tt, "+n+"*"+k+".");
		
		//Elnevezz�k a mez�ket, az�rt mi csin�ljuk mert mi tudjuk a kordin�t�ikat �s az r�sze a n�vnek.
		for (int i = 0; i<n; i++) {
			for(int j=0; j<k; j++) {
				terkep[i][j] = new Mezo(i,j);
				terkep[i][j].setNev("Mezo("+i+","+j+")");
			}
		}
		
	}

	public void tick() {
		for (int i = 0; i<terkep.length; i++) {
			for(int j=0; j<terkep[0].length; j++) {
				terkep[i][j].tick();
			}
		}
		
		for (int i = 0; i<terkep.length; i++) {
			for(int j=0; j<terkep[0].length; j++) {
				terkep[i][j].tickend();
			}
		}
	}

	public void setKulsoMezo(int x, int y, boolean kulso) {
		kulsoMezok[x][y] = kulso;
		System.out.println("["+terkep[x][y].getNev()+"] k�ls� mez� lett.");
	}
	
	public boolean[][] getPalya(){
		return kulsoMezok;
	}
	
	public GrafikusPalya getGrafikusPalya(){
		return grafikusPalya;
	}
	
	public void setGrafikusPalya(GrafikusPalya gp){
		grafikusPalya=gp;
	}
	
	//Visszat�r a p�lya sz�lles�g�vel 
	public int getX(){
		return terkep.length;
	}
	
	//visszat�r a p�lya magass�g�val
	public int getY(){
		return terkep[0].length;
	}

	public boolean getKulsoMezo(int i, int j) {
		return kulsoMezok[i][j];
	}
	
	public void setGrafika(GrafikusPalya ge){
		this.grafikusPalya = ge;
	}
public void adatokKiirasa (String param) {
		if (param.contains(" ")) {
			String[] darabolo = param.split(" ");
			int x = Integer.parseInt(darabolo[0]);
			int y = Integer.parseInt(darabolo[1]);
			
			if(x > 0 && y > 0 && x < terkep.length && y < terkep[0].length) {
				if(terkep[x][y]!=null) terkep[x][y].adatKiirasa("");
			}
		} else {
			if(param.equals("")) System.out.println("[Palya]\nx: "+terkep.length+"\ny: "+terkep[0].length
					+"\n");
			for(int i = 0; i < terkep.length; i++){
				for(int j = 0; j < terkep[i].length; j++){
					if(terkep[i][j]!=null) terkep[i][j].adatKiirasa(param);
				}
			}
		}
	}
}