public class KisRobotGyar extends Gyar {

	public KisRobotGyar (Kepernyo k) {
		super(k);
	}
	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo kepernyo,Mezonallo m) {
		return new GrafikusKisrobot("gfx/kisrobot.png", kepernyo, (Kisrobot) m);
	}

}