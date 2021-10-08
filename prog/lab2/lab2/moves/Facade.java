package moves;
import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
	public Facade() {
		super(Type.NORMAL, 70, 1);
	}
	
	@Override
	protected double calcBaseDamage(Pokemon att, Pokemon def) {
		double damage = super.calcBaseDamage(att, def);
		Status attStatus=att.getCondition();
		if (attStatus==Status.BURN || attStatus==Status.PARALYZE || attStatus==Status.POISON) {
			damage *= 2;
		}
		return damage;
	}

	@Override
	protected String describe() {
		return "attacks (facade)";
	}
}