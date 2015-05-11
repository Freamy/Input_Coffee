

public class OlajGyar extends Gyar {
	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusOlajfolt("gfx/rajz.png", k, (Olajfolt) m);
	}
}
