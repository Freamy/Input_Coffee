import java.util.ArrayList;


import java.util.ArrayList;


public class Navigator {
	
	private Gyar grafikaGyar;
	
	private GrafikusPalya grafikusPalya;
	private Mezo[][] terkep;
	private boolean[][] kulsoMezok;
	
	public Navigator(){
		grafikaGyar = new PalyaGyar();
		grafikusPalya = (GrafikusPalya) grafikaGyar.grafikaKeszitese(Jatekmester.kepernyo, null);
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
		return terkep[x][y];
	}
	
	Mezo athelyez(Mezo honnan, Sebesseg sebesseg){
		int[] koordinatak = koordinataKonverter(honnan);
		int x = koordinatak[0];
		int y = koordinatak[1];
		x += sebesseg.getx();
		y += sebesseg.gety();
		return terkep[x][y];
	}
	
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
				if(j != x && i != y && honnan.szennykeres()){
					tavolsag = ((y-i)*(y-i))+((x-j)*(x-j));
					if(tavolsag < legkozelebbi){
						legkozelebbi = tavolsag;
						kozeliX = j;
						kozeliY = i;
					}
				}
			}
		}
		return terkep[kozeliX][kozeliY];
	}
	
	public Mezo legrovidebbut(Mezo honnan, Mezo hova){
			if(honnan.equals(hova)) return null;
			int[] koordinatak = koordinataKonverter(honnan);
			// ASTAR //
			boolean kesz = false;
			
			ArrayList<Mezo> ut = new ArrayList<Mezo>();
			
			ArrayList<Mezo> hatar = new ArrayList<Mezo>();
			ArrayList<Mezo> vizsgalt = new ArrayList<Mezo>();
			Mezo jelenlegi = honnan;
			
			String[][] elozoHely = new String[terkep.length][terkep[0].length];
			
			vizsgalt.add(honnan);
			
			// Megkezdj�k az �tkeres�st
			while(!kesz && vizsgalt.size() != terkep.length*terkep[0].length){
				// Ha a vizsg�lt mez� a c�lunk akkor megtal�ltuk az utat.
				if(jelenlegi.equals(hova)){
					kesz = true;
				}
				koordinatak = koordinataKonverter(jelenlegi);
				int x = koordinatak[0];
				int y = koordinatak[1];
				// Felvessz�k az utolj�ra vizsg�lt elem szomsz�dait a hat�r list�ba.
				if(y > terkep[0].length-1 || x > terkep.length-1){
				}else{
					if(x < terkep.length-1 && !kulsoMezo(terkep[x+1][y]) && !vizsgalt.contains(terkep[x+1][y]) && !kulsoMezok[x+1][y]){
						hatar.add(terkep[x+1][y]);
						elozoHely[x+1][y] = x+";"+y;}
					if(x > 0 && !kulsoMezo(terkep[x-1][y]) && !vizsgalt.contains(terkep[x-1][y]) && !kulsoMezok[x-1][y]){
						hatar.add(terkep[x-1][y]);
						elozoHely[x-1][y] = x+";"+y;}
					if(y < terkep[0].length-1 && !kulsoMezo(terkep[x][y+1]) && !vizsgalt.contains(terkep[x][y+1]) && !kulsoMezok[x][y+1]){
						hatar.add(terkep[x][y+1]);
						elozoHely[x][y+1] = x+";"+y;}
					if(y > 0 && !kulsoMezo(terkep[x][y-1]) && !vizsgalt.contains(terkep[x][y-1]) && !kulsoMezok[x][y-1]){
						hatar.add(terkep[x][y-1]);
						elozoHely[x][y-1] = x+";"+y;}
				}
				// Megkeress�k a hat�r mez�k k�z�l a legkevesebb k�lts�g�t
				int minTavolsag = Integer.MAX_VALUE;
				for(Mezo kozeli: hatar){
					koordinatak = koordinataKonverter(hova);
					int i = koordinatak[0];
					int j = koordinatak[1];
					koordinatak = koordinataKonverter(kozeli);
					x = koordinatak[0];
					y = koordinatak[1];
					int tavolsag = ((y-j)*(y-j))+((x-i)*(x-i));
					if(tavolsag < minTavolsag){
						minTavolsag = tavolsag;
						jelenlegi = kozeli;
					}
					
				}
				hatar.remove(jelenlegi);
				vizsgalt.add(jelenlegi);
			}
			// Ha tal�ltunk egy �tvonalat akkor felkell �p�ten�nk azt, majd visszat�r�nk az els� elem�vel.
			if(kesz){
				
				kesz = false;
				boolean once = false;
				Mezo utEpito = hova;
				while(!kesz){
					koordinatak = koordinataKonverter(utEpito);
					int x = koordinatak[0];
					int y = koordinatak[1];
					String[] elozoKoordinata = elozoHely[x][y].split(";");
					if(!once){
						once = true;
					}
					ut.add(terkep[Integer.parseInt(elozoKoordinata[0])][Integer.parseInt(elozoKoordinata[1])]);
				System.out.println(ut.size()+" "+x+" "+y);
					utEpito = ut.get(ut.size()-1);
					if(utEpito.equals(honnan)) kesz = true;
				}
			}else
				return null;
			if(ut.size() > 1) return ut.get(ut.size()-2);
			return ut.get(ut.size()-1);
	}
	
	public int[] koordinataKonverter(Mezo honnan){
		int x = 0;
		int y = 0;
		for(int i = 0; i < terkep.length; i++){
			for(int j = 0; j < terkep[i].length; j++){
				if(honnan.equals(terkep[i][j])) {
					x = j;
					y = i;
				}
			}
		}
		int t[] = {x,y};
		return t;
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
}