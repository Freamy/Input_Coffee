

public class RagacsGyar extends Gyar {

	public RagacsGyar(Kepernyo kepernyo) {
		super(kepernyo);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusRagacs("gfx/rajz.png", k, (Ragacs) m);
	}

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k) {
		// TODO Auto-generated method stub
		return null;
	}

}
