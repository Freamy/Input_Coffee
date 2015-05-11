import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GrafikusElem {
	protected int x;
	protected int y;
	protected boolean megsemmisult;
	protected Image kep;
	protected Kepernyo kepernyo;
	
	public GrafikusElem(){
		
	}
	
	public GrafikusElem(int x, int y, String utvonal, Kepernyo kepernyo){
		this.x = x;
		this.y = y;
		this.kep = kepBetoltese(utvonal);
		this.kepernyo = kepernyo;
		this.megsemmisult = false;
	}
	public Kepernyo getKepernyo(){
		return kepernyo;
	}
	public Image kepBetoltese(String utvonal){
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(new File(utvonal));
			kep = bi;
		}
		catch(IOException e){
			System.out.println("Invalid path! in GrafikusElem.kepBetoltese");
		}
		return kep;
	}
	public void rajzol(Graphics g){}
}
