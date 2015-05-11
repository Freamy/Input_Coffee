public class RobotGyar extends Gyar {
	public RobotGyar(Kepernyo kepernyo) {
		super(kepernyo);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusRobot("gfx/Robot.png", k, (Robot) m);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k) {
		// TODO Auto-generated method stub
		return null;
	}
}
