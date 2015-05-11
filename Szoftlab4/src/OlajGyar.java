public class OlajGyar extends Gyar {
	public OlajGyar(Kepernyo kepernyo) {
		super(kepernyo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusOlajfolt("gfx/olajfolt.png", k, (Olajfolt) m);
	}
}