package moves;
import ru.ifmo.se.pokemon.*;

public class Discharge extends SpecialMove {
	private static Effect paralyzeEff = new Effect().chance(0.3).turns(-1).condition(Status.PARALYZE);
	public Discharge() {
		super(Type.ELECTRIC, 80, 1);
	}
	
	@Override
	protected void applyOppEffects(Pokemon opp) {
		opp.addEffect(paralyzeEff);
	}

	@Override
	protected String describe() {
		return "throws a flair of electricity";
	}
}