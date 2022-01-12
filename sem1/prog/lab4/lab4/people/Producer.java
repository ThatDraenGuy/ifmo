package people;
import main.*;
public class Producer extends Lunatic {
	protected double stupidity;
	protected boolean peopleFound;
	public Producer (String n, SpaceObject l, double s) {
		super(n, l);
		this.stupidity=s;
	}
	public void search(Traveller t) {
		if (t.getLocation()==this.getLocation()) {
			System.out.println(this.getName()+" found "+t.getName()+"! The news about him are spreading.");
			this.peopleFound=true;
		} else {
			System.out.println(this.getName()+" didn't find anything interesting...");
			this.peopleFound=false;
		}
	}
	public double getStupidity() {
		return this.stupidity;
	}
	public boolean getPeopleFound() {
		return this.peopleFound;
	}
	public void makeADeal(StudioOwner s, TVWatcher[] t) {
		class Negotiation {
			private int dealCounter;
			private Producer producer;
			public Negotiation(int d, Producer pr) {
				this.dealCounter=d;
				this.producer=pr;
			}
			public int getDealCounter() {
				return dealCounter;
			}
			public void setDealCounter(int d) {
				this.dealCounter=d;
			}
			public void iteration() {
				this.setDealCounter(this.getDealCounter()+1);
				s.setGreed(s.getGreed()-10);
				System.out.println(this.getDealCounter()+" round of negotiations was unsuccesful");
				if (this.getDealCounter()>=3) {
					System.out.println("TVWatchers became more annoyed!");
					for (TVWatcher tv : t) {
						tv.threat(s);
					}
				}
				cycle();
			}
			public void cycle() {
				if (this.producer.getPeopleFound()) {
					System.out.println("");
					System.out.println(this.producer.getName()+" tries to reach an agreement with "+s.getName()+"!");
					if (this.producer.getStupidity()>s.getGreed()) {
						System.out.println("The negotiations are over. "+s.getName()+" paid "+s.getGreed()*10+" Lunar Dollars");
					} else {
						this.iteration();
					}
				} else {
					System.out.println(this.producer.getName()+" didn't find anyone: there is nothing to make a deal about");
				}
			}
		}
		Negotiation counter = new Negotiation(0, this);
		System.out.println("");
		System.out.println("The negotiations are starting!");
		System.out.println("");
		counter.cycle();
		System.out.println("");
	}
	public void talk() {
		System.out.println(this.getName()+" says: I'm not stupid, am I?");
	}
	public void describe() {
		System.out.println("This is a producer named "+this.getName());
	}
	public int hashCode() {
		return super.hashCode()+6;
	}
} 