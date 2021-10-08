package moves;
import ru.ifmo.se.pokemon.*;

public class Scratch extends PhysicalMove {
	public Scratch() {
		super(Type.NORMAL, 40, 1);
	}
	
	@Override
	protected String describe() {
		return "scratches the opponent";
	}
}