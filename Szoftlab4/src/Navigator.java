import java.util.ArrayList;


public class Navigator {
	
	private Mezo[][] terkep;
	private boolean[][] kulsoMezok;
	
	public Navigator(){
	}
	
	public Mezo getMezo(int id){
		int x = id%terkep.length;
		int y = id/terkep[0].length;
		if(x > 0 && y > 0 && x < terkep.length && y < terkep[0].length) return terkep[x][y];
		else throw new IndexOutOfBoundsException();
	}
	
	Mezo athelyez(Mezo honnan, Sebesseg sebesseg){
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
		x += sebesseg.getx();
		y += sebesseg.gety();
		return terkep[x][y];
	}
	
	public Mezo kozeliszennyezodes(Mezo honnan){
		int x = 0;
		int y = 0;
		int[] koordinatak = koordinataKoverter(honnan);
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
			int[] koordinatak = koordinataKoverter(honnan);
			// ASTAR //
			boolean kesz = false;
			
			ArrayList<Mezo> ut = new ArrayList<Mezo>();
			
			ArrayList<Mezo> hatar = new ArrayList<Mezo>();
			ArrayList<Mezo> vizsgalt = new ArrayList<Mezo>();
			Mezo jelenlegi = honnan;
			
			String[][] elozoHely = new String[terkep.length][terkep[0].length];
			
			vizsgalt.add(honnan);
			
			
			while(!kesz && vizsgalt.size() != terkep.length*terkep[0].length){
				if(jelenlegi.equals(hova)){
					kesz = true;
				}
				koordinatak = koordinataKoverter(jelenlegi);
				int x = koordinatak[0];
				int y = koordinatak[1];
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
				for(int i = 0; i < 4; i++){
					
				}
				int minTavolsag = Integer.MAX_VALUE;
				for(Mezo kozeli: hatar){
					koordinatak = koordinataKoverter(hova);
					int i = koordinatak[0];
					int j = koordinatak[1];
					koordinatak = koordinataKoverter(kozeli);
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
			
			if(kesz){
				
				kesz = false;
				boolean once = false;
				Mezo utEpito = hova;
				while(!kesz){
					koordinatak = koordinataKoverter(utEpito);
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
	
	private int[] koordinataKoverter(Mezo honnan){
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
		
		return false;
	}
}
