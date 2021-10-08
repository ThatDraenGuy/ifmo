package moves;
import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {
	private static Effect confideEff = new Effect().stat(Stat.SPECIAL_ATTACK, -1);
	public Confide() {
		super(Type.NORMAL, 0, 1);
	}
	
	@Override
	protected void applyOppEffects(Pokemon opp) {
		opp.addEffect(confideEff);
	}

	@Override
	protected String describe() {
		return "tells opponent a secret";
	}
}