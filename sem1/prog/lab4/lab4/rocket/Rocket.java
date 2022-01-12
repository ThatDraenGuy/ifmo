package rocket;
import main.*;
import people.*;
public class Rocket implements Describable {
	protected String name;
	protected Engine engine;
	protected Fuel fuel;
	protected Core core;
	protected Traveller[] passangers;
	protected SpaceObject location;
	public Rocket(SpaceObject l, String n) {
		location=l;
		name=n;
	}
	public Rocket(SpaceObject l, String n, Engine e, Fuel f, Core c) {
		location=l;
		name=n;
		engine=e;
		fuel=f;
		core=c;	
	}
	public Rocket(SpaceObject l, String n, Engine e, Fuel f, Core c, Traveller[] p) {
		location=l;
		name=n;
		engine=e;
		fuel=f;
		core=c;
		passangers=p;	
	}
	public String getName() {
		return name;
	}
	public Engine getEngine() {
		return engine;
	}
	public Fuel getFuel() {
		return fuel;
	}
	public Core getCore() {
		return core;
	}
	public void setEngine(Engine e) {
		engine=e;
	}
	public void setFuel(Fuel f) {
		fuel=f;
	}
	public void setCore(Core c) {
		core=c;
	}
	public Traveller[] getPassangers() {
		return passangers;
	}
	public SpaceObject getLocation() {
		return location;
	}
	public double calcPower() {
		double totalpower = this.getEngine().getPower() * this.getFuel().getAmount() * this.getFuel().getEfficiency() / this.getEngine().getFuelConsumption();
		return totalpower;	
	}
	public boolean calcLaunch(SpaceObject destination) {
		if (this.calcPower()>=SpaceObject.SpaceObjectPower(destination)) {
			return true;
		} else
			return false;
	}
	public void launch(SpaceObject destination) throws RocketNotReadyException {
		if (this.getEngine()== null || this.getFuel()==null || this.getCore()==null) {
			throw new RocketNotReadyException("Rocket isn't ready for launch!");
		}
		System.out.println("Rocket's destination: "+destination);
		if (this.calcLaunch(destination)) {
			this.location=destination;
			this.getFuel().setAmount(0.0);
			System.out.print("Rocket flies off to the "+destination+"! The passanger");
			if (passangers.length > 1) {
				System.out.print("s are: ");
			} else
				System.out.print(" is: ");
			for (Traveller i : passangers) {
					System.out.println(i.getName()+" ");
					i.observe(this.getCore().getObservationRate());
					i.setLocation(destination);
					i.observe(this.getCore().getObservationRate());
			}
			System.out.println("");
		} else 
			System.out.println("Rocket doesn't have enough power to go to " + destination);
	}
	public void returnToEarth() {
		if (this.location==SpaceObject.BigEarth) {
			System.out.println("Rocket is already at Big Earth!");
		} else {
			System.out.println("Rocket returns to the Big Earth! Journalists and scientists await brave travellers.");
			this.location=SpaceObject.BigEarth;
			for (Traveller i : passangers) {
				i.setLocation(SpaceObject.BigEarth);
			}
		}
	}
	public void describe() {
		if (this.getLocation()==SpaceObject.BigEarth) {
			System.out.print("The rocket is standing on launching pad. ");
		}
		System.out.print("This rocket is called \"" + this.getName() + "\", it contains ");
		engine.describe();
		System.out.print(" and ");
		core.describe();
		System.out.print(" This rocket has ");
		fuel.describe();
		System.out.println();
	}
	@Override
	public String toString() {
		return "ROCKET "+this.getName()+" on "+this.getLocation().toString()+" out of: "+this.getCore().toString()+"; "+this.getEngine().toString()+"; "+this.getFuel().toString();
	}
	@Override
	public boolean equals(Object r) {
		if (r instanceof Rocket && this.getName()==((Rocket)r).getName() && this.getCore().equals(((Rocket)r).getCore()) && this.getFuel().equals(((Rocket)r).getFuel()) && this.getEngine().equals(((Rocket)r).getEngine())) {
			return true;
		} else return false;
	}
	@Override
	public int hashCode() {
		return this.getName().hashCode() + this.getFuel().hashCode() + this.getEngine().hashCode() + this.getCore().hashCode();
	}
}