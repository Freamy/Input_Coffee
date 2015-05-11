

public class KisRobotGyar extends Gyar {

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusKisrobot("gfx/rajz.png", k, (Kisrobot) m);
	}

}
