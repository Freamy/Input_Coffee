import java.awt.Image;


public class GrafikusRobot extends GrafikusElem {
	private int x;
	private int y;
	private boolean megsemmisult;
	private Image kep;
	private Kepernyo kepernyo;

	public GrafikusRobot(String utvonal, Kepernyo kepernyo, Robot robot) {
		super(utvonal, kepernyo);
		this.x = robot.getX();
		this.y=robot.getY();
		this.megsemmisult=robot.getMegsemmisult();
	}
	
	public void rajzol(){
		if(!megsemmisult){
			//Under Construction.
		}
	}
	
	public void frissit (Robot robot){
		this.x = robot.getX();
		this.y=robot.getY();
		this.megsemmisult=robot.getMegsemmisult();
		kepernyo.rajzol();
	}
}
