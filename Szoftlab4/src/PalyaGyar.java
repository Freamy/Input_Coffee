

public class PalyaGyar extends Gyar {
	public PalyaGyar(Kepernyo kepernyo) {
		super(kepernyo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusPalya(0, 0, 0, "gfx/rajz2.png", "gfx/rajz3.png", k);
	}
}
