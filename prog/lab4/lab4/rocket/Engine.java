package rocket;
import main.*;

public class Engine extends MachinePart {
	private double power;
	private double fuelConsumption;
	public Engine(double p, double f, String n) throws ValueOutOfBoundsException {
		if (0.0<f && f<=1.0) {
			power=p;
			fuelConsumption=f;
			name=n;
		} else {
			throw new ValueOutOfBoundsException("Engine's fuelConsumption is out of bounds!");
		}
	}
	public double getPower() {
		return power;
	}
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	public void describe() {
		System.out.print("Engine called "+this.getName()+". It's power is "+this.getPower()+"W. It's fuel consumption rate is "+this.getFuelConsumption()+"L/s.");
	}
	@Override
	public String toString() {
		return "ENGINE "+this.getName()+" with stats: Power: "+this.getPower()+" FuelConsumption: "+this.getFuelConsumption();
	}
	@Override
	public boolean equals(Object e) {
		if (e instanceof Engine && this.getName()==((Engine)e).getName() && this.getPower()==((Engine)e).getPower() && this.getFuelConsumption()==((Engine)e).getFuelConsumption()) {
			return true;
		} else return false;
	}
	@Override
	public int hashCode() {
		return super.hashCode() + Math.toIntExact(Math.round(this.getPower()+this.getFuelConsumption())) + 2;
	}
	public double rating() {
		return power+fuelConsumption;
	}
}