public class OlajGyar extends Gyar {
	public OlajGyar(Kepernyo kepernyo) {
		super(kepernyo);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusOlajfolt("gfx/rajz.png", k, (Olajfolt) m);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k) {
		return null;
	}
}
