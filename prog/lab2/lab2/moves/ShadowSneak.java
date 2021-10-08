package moves;
import ru.ifmo.se.pokemon.*;

public class ShadowSneak extends PhysicalMove {
	public ShadowSneak() {
		super(Type.GHOST, 40, 1, 1, 1);
	}
	
	@Override
	protected String describe() {
		return "extends its shadow and attacks from behind";
	}
}