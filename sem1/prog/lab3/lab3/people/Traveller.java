package people;
import main.*;
import davilon.*;
import rocket.*;
public class Traveller extends Person{
	protected String[] observations = new String[4];
	public Traveller (String s, SpaceObject l) {
		super(s, l);
	}
	public void observe(double observationRate) {
		if (this.getLocation()!=SpaceObject.BigEarth) {
			observations[1]=SpaceObject.SpaceObjectObservation(this.getLocation(), observationRate);
			System.out.println(this.getName()+" meets citizens of Davilon! "+Davilon.getAbout());
		} else observations[0]=SpaceObject.SpaceObjectObservation(this.getLocation(), observationRate);
	}
	public void tell(Rocket r) {
		System.out.println(this.getName()+" exits the rocket and says: I'm back! Let me start the story by telling you everything I know about my rocket.");
		r.describe();
	}
	public void answer(Person asker, int choise) {
		System.out.print(this.getName()+" answers to "+asker.getName()+": ");
		if (observations[0]==null) {
			System.out.println("Rocket didn't work...");
		} else {
			if (asker instanceof Journalist) {
				switch (choise) {
					case 0: System.out.println("I was eating space food, a special food in tubes prepared for space travel");
						break;
					case 1: System.out.println("Mostly water.");
						break;
					case 2: System.out.println("I was dreaming about home.");
						break;
					case 3: System.out.print("They were really "+Davilon.getBehavior()+". ");
						if (Davilon.getBehavior()==Mood.Angry | Davilon.getBehavior()==Mood.Smart) {
							System.out.println("I didn't really like them...");
						} else System.out.println("I liked them.");
							break;

					default: System.out.println("I'm good.");
				}
			} else {
				switch (choise) {
					case 0: System.out.println(observations[0]);
						break;
					case 1: System.out.println(observations[1]);
						break;
					default: System.out.println("I'm good.");
				}
			}
		}
	}
	@Override
	public int hashCode() {
		return super.hashCode()*10+3;
	}
	public void describe() {
		System.out.println("This is a traveller named "+this.getName());
	}
	public void talk() {
		System.out.println(this.getName()+" says: I'm lucky to be where I am today.");
	}
}