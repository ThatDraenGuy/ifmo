package rocket;
import main.*;

public class Core extends MachinePart {
	private double strength;
	private double observationRate;
	public Core(double s, double o, String n) {
		strength=s;
		observationRate=o;
		name=n;
	}
	public double getStrength() {
		return strength;
	}
	public double getObservationRate() {
		return observationRate;
	}
	public void describe() {
		System.out.print("Core called "+this.getName()+". It's hull integrity is "+this.getStrength()+". It's observation rate is "+this.getObservationRate()+".");
	}
	@Override
	public String toString() {
		return "CORE "+this.getName()+" with stats: Strength: "+this.getStrength()+" ObservationRate: "+this.getObservationRate();
	}
	@Override
	public boolean equals(Object c) {
		if (c instanceof Core && this.getName()==((Core)c).getName() && this.getStrength()==((Core)c).getStrength() && this.getObservationRate()==((Core)c).getObservationRate()) {
			return true;
		} else return false;
	}
	@Override
	public int hashCode() {
		return super.hashCode() + Math.toIntExact(Math.round(this.getStrength()+this.getObservationRate())) + 1;
	}
	public double rating() {
		return strength+observationRate;
	}
}