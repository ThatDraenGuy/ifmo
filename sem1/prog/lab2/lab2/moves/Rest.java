package moves;
import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
	private static Effect restEff = new Effect().turns(2);
	public Rest() {
		super(Type.PSYCHIC, 0, 1);
	}
	
	@Override
	protected void applySelfEffects(Pokemon self) {
		self.restore();
		restEff.sleep(self);
	}

	@Override
	protected String describe() {
		return "sleeps";
	}
}