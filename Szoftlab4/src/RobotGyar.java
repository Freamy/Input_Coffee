

public class RobotGyar extends Gyar {
	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusRobot("gfx/Robot.png", k, (Robot) m);
	}
}
