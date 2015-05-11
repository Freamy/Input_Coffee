import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
public class GrafikusPalya extends GrafikusElem {
	
	int mezomeret;
	private boolean[][] palya;
	private Image kulso;

	public GrafikusPalya(int x, int y, int mezomeret, String utvonalBelso, String utvonalKulso, Kepernyo kepernyo) {
		super(x, y, utvonalBelso, kepernyo);
		kulso = this.kepBetoltese(utvonalKulso);
		this.mezomeret = 64;
	}
	
	public void grafikusPalyaFelvevese(Navigator n){
		kepernyo = Jatekmester.kepernyo;
		kepernyo.grafikusElemHozzaad(this);
		palya = n.getPalya();
	}
	
	public void rajzol(Graphics g){
		int szelesseg = palya[0].length;
		int hosszusag = palya.length;
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < hosszusag; i++){
			for(int j = 0; j < szelesseg; j++){
				int xoffset = i*mezomeret;
				int yoffset = j*mezomeret;
				if(!palya[i][j]){
					g2.drawImage(kep,x+xoffset,y+yoffset,null);
				}
				else{
					g2.drawImage(kulso,x+xoffset,y+yoffset,null);
				}
			}
		}
	}
	
	
	public void frissit(Navigator navigator){
		for(int i=0; i<palya.length; i++) {
			for(int j=0; j<palya[0].length; j++) {
				palya[i][j] = navigator.getKulsoMezo(i, j);
			}
		}
	}

}


