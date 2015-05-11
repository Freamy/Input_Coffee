public class RobotGyar extends Gyar {
	public RobotGyar(Kepernyo kepernyo) {
		super(kepernyo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusRobot("gfx/robot.png", k, (Robot) m);
	}
}