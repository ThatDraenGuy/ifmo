package moves;
import ru.ifmo.se.pokemon.*;

public class HealPulse extends StatusMove {
	public HealPulse() {
		super(Type.PSYCHIC, 0, 1);
	}
	
	@Override
	protected void applySelfEffects(Pokemon self) {
		double HP = self.getHP();
		int Heal = (int) HP;
		Heal = -1*Heal;
		self.setMod(Stat.HP, Heal);
	}

	@Override
	protected String describe() {
		return "heals itself";
	}
}