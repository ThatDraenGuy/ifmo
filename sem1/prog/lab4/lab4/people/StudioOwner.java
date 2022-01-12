package people;
import main.*;

public class StudioOwner extends Lunatic {
	protected double greed;
	public StudioOwner(String n, SpaceObject l, double g) {
		super(n, l);
		this.greed=g;
	}
	public double getGreed() {
		return this.greed;
	}
	public void setGreed(double g) {
		this.greed=g;
	}
	public void talk() {
		System.out.println(this.getName()+" says: I want money....");
	}
	public void describe() {
		System.out.println("This is a Studio Owner named "+this.getName());
	}
	public int hashCode() {
		return super.hashCode()+4;
	}
}