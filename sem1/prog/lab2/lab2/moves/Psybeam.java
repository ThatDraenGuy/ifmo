package moves;
import ru.ifmo.se.pokemon.*;

public class Psybeam extends SpecialMove {
	Effect e = new Effect().chance(0.1);
	public Psybeam() {
		super(Type.PSYCHIC, 65, 1);
	}
	
	@Override
	protected void applyOppEffects(Pokemon opp) {
		e.confuse(opp);
	}

	@Override
	protected String describe() {
		return "fires a peculiar ray";
	}
}