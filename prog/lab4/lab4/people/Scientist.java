package people;
import main.*;
public class Scientist extends Lunatic implements Interviewer {
	protected Profession speciality;
	public Scientist (String n, SpaceObject l) {
		super(n, l);
		this.speciality=Profession.values()[Utility.Randomize(0, Profession.values().length)];
	}
	public Scientist (String n, SpaceObject l, Profession p) {
		super(n, l);
		this.speciality=p;
	}
	public void ask(Traveller t) {
		if (t.getLocation()==this.getLocation()) {
			System.out.print(this.getSpeciality()+" "+this.getName()+" asks " + t.getName());
			int choise;
			if (this.getSpeciality()!=Profession.Astronomer) {
				choise = Utility.Randomize(0, 2);
				switch (choise) {
					case 0: System.out.println(" about Big Earth");
						break;
					case 1: System.out.println(" about planet he visited");
						break;
					default: System.out.println(" how he's feeling");
				}
			} else {
				choise = 2;
				System.out.println(" whether Big Earth has a solid shell or not");
			}
			t.answer(this, choise);
		} else {
			System.out.println(this.getName()+" can't ask "+t.getName()+" anything because they're on different planets!");
		}
	}
	public Profession getSpeciality() {
		return this.speciality;
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