package moves;
import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove {
	private static Effect paralyzeEff = new Effect().chance(0.1).turns(-1).condition(Status.PARALYZE);
	public Thunderbolt() {
		super(Type.ELECTRIC, 90, 1);
	}
	
	@Override
	protected void applyOppEffects(Pokemon opp) {
		opp.addEffect(paralyzeEff);
	}

	@Override
	protected String describe() {
		return "throws an electric blast";
	}
}