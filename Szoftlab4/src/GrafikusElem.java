import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GrafikusElem {
	private int x;
	private int y;
	private boolean megsemmisult;
	private Image kep;
	private Kepernyo kepernyo;
	
	public GrafikusElem(){
		
	}
	
	public GrafikusElem(String utvonal, Kepernyo kepernyo){
		this.kep = kepBetoltese(utvonal);
		this.kepernyo = kepernyo;
	}
	public Kepernyo getKepernyo(){
		return kepernyo;
	}
	public Image kepBetoltese(String utvonal){
		//placeholder
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(new File(utvonal));
			kep = bi;
		}
		catch(IOException e){
			System.out.println("Invalid path! in GrafikusElem.kepBetoltese");
			kep = null;
		}
		return kep;
	}
	public void rajzol(){}
}
