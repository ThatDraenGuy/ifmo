package people;
import main.*;
public abstract class Person implements Describable {
	protected String name;
	protected SpaceObject location;
	public Person(String n, SpaceObject l) {
		name=n;
		location=l;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name=n;
	}
	public SpaceObject getLocation() {
		return location;
	}
	public void setLocation(SpaceObject l) {
		location=l;
	}
	public abstract void talk();
	@Override
	public String toString() {
		return this.getClass().toString().substring(13)+" "+this.getName()+" on "+this.getLocation();
	}
	@Override
	public boolean equals(Object p) {
		if (p instanceof Person) {
			if (this.getClass()==p.getClass() && this.getName()==((Person) p).getName()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		int res = this.getName().hashCode()*10;
		return res;
	}	
}