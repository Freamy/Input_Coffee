

public class PalyaGyar extends Gyar {
	public PalyaGyar(Kepernyo kepernyo) {
		super(kepernyo);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k) {
		return new GrafikusPalya(0, 0, 0, "gfx/rajz2.png", "gfx/rajz3.png", k);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusPalya(0, 0, 0, "gfx/rajz2.png", "gfx/rajz3.png", k);
	}
}
