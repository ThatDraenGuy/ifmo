package moves;
import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
	private static Effect doubleEff = new Effect().stat(Stat.EVASION, 1);
	public DoubleTeam() {
		super(Type.NORMAL, 0, 1);
	}
	
	@Override
	protected void applySelfEffects(Pokemon self) {
		self.addEffect(doubleEff);
	}

	@Override
	protected String describe() {
		return "creates illusory copies";
	}
}