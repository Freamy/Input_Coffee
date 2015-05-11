

public class RagacsGyar extends Gyar {

	@Override
	public GrafikusElem grafikaKeszitese(Kepernyo k, Mezonallo m) {
		return new GrafikusRagacs("gfx/rajz.png", k, (Ragacs) m);
	}

}
