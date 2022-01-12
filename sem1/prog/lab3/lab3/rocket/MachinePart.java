package rocket;
import main.*;
public abstract class MachinePart implements Describable {
	protected String name;
	public String getName() {
		return name;
	}
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	public abstract double rating();
}