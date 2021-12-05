package people;
import main.*;

public class TVWatcher extends Lunatic {
	protected double annoyance;
	public TVWatcher(String n, SpaceObject l, double a) {
		super(n, l);
		this.annoyance=a;
	}
	public double getAnnoyance() {
		return this.annoyance;
	}
	public void setAnnoyance(double a) {
		this.annoyance=a;
	}
	public void watchTV() {
		System.out.println(this.getName()+" is watching TV but he can't find anything interesting...");
	}
	public void threat(StudioOwner s) {
		System.out.println(this.getName()+" is threataning "+s.getName()+"!");
		s.setGreed(s.getGreed()-this.getAnnoyance());
		this.setAnnoyance(this.getAnnoyance()*2);
	}
	public void talk() {
		System.out.println(this.getName()+" says: I wonder what should I watch...");
	}
	public void describe() {
		System.out.println("This is a TV watcher named "+this.getName());
	}
	public int hashCode() {
		return super.hashCode()+5;
	}
} 