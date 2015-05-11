public class KisRobotGyar extends Gyar {

	public KisRobotGyar (Kepernyo k) {
		super(k);
	}
	
	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusKisrobot("gfx/rajz.png", kepernyo, (Kisrobot) m);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k) {
		// TODO Auto-generated method stub
		return null;
	}

}
