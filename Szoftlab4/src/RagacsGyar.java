public class RagacsGyar extends Gyar {

	public RagacsGyar(Kepernyo kepernyo) {
		super(kepernyo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusRagacs("gfx/rajz.png", k, (Ragacs) m);
	}

}