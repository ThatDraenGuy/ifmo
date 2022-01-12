package people;
import main.*;
public class Scientist extends Person implements Interviewer {
	public Scientist (String n, SpaceObject l) {
		super(n, l);
	}
	public void ask(Traveller t) {
		if (t.getLocation()==this.getLocation()) {
			System.out.print(this.getName()+" asks " + t.getName());
			int choise = Utility.Randomize(0, 2);
			switch (choise) {
				case 0: System.out.println(" about Big Earth");
					break;
				case 1: System.out.println(" about planet he visited");
					break;
				default: System.out.println(" how he's feeling");
			}
			t.answer(this, choise);
		} else {
			System.out.println(this.getName()+" can't ask "+t.getName()+" anything because they're on different planets!");
		}
	}
	@Override
	public int hashCode() {
		return super.hashCode()+2;
	}
	public void describe() {
		System.out.println("This is a scientist named "+this.getName());
	}
	public void talk() {
		System.out.println(this.getName()+" says: I have so much work to do...");
	}
}