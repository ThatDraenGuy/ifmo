package people;
import main.*;
public class Journalist extends Person implements Interviewer {
	public Journalist (String n, SpaceObject l) {
		super(n, l);
	}
	public void ask(Traveller t) {
		if (t.getLocation()==this.getLocation()) {
			System.out.print(this.getName()+" asks " + t.getName());
			int choise = Utility.Randomize(0, 4);
			switch (choise) {
				case 0: System.out.println(" about what he was eating while travelling");
					break;
				case 1: System.out.println(" about what he was drinking while travelling");
					break;
				case 2: System.out.println(" what he was dreaming about while travelling");
					break;
				case 3: System.out.println(" wether he liked citizens of Vavilon or not");
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
		return super.hashCode()+1;
	}
	public void describe() {
		System.out.println("This is a journalist named "+this.getName());
	}
	public void talk() {
		System.out.println(this.getName()+" says: I'm not getting paid enough for all this...");
	}
}