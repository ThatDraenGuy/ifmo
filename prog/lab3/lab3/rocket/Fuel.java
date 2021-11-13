package rocket;
import main.*;

public class Fuel extends MachinePart {
	private double efficiency;
	private double amount;
	public Fuel(double e, double a, String n) {
		efficiency=e;
		amount=a;
		name=n;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double a) {
		amount=a;
	}
	public double getEfficiency() {
		return efficiency;
	}
	public void describe() {
		System.out.print("Fuel called "+this.getName()+". It's efficiency is "+this.getEfficiency()+" and there's "+this.getAmount()+"L of it");
	}
	@Override
	public String toString() {
		return "FUEL "+this.getName()+" with stats: Amount: "+this.getAmount()+" Efficiency: "+this.getEfficiency();
	}
	@Override
	public boolean equals(Object f) {
		if (f instanceof Fuel && this.getName()==((Fuel)f).getName() && this.getEfficiency()==((Fuel)f).getEfficiency()) {
			return true;
		} else return false;
	}
	@Override
	public int hashCode() {
		return super.hashCode() + Math.toIntExact(Math.round(this.getEfficiency())) + 3;
	}
	public double rating() {
		return efficiency+amount;
	}
}