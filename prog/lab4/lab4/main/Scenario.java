package main;
import main.*;
import people.*;
import rocket.*;

public class Scenario {
	public static void Scene() {
		Lunatic.Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.BigEarth);
		Scientist s = new Scientist("Hugo", SpaceObject.BigEarth);
		Traveller[] t = {n};
		Core c = new Core(11.0, 1.0, "Titan GH-73");
		Engine e = new Engine(100.0, 1.0, "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(100.0, 1.0, "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		n.describe();
		try {
			r.launch(SpaceObject.Moon);		
		} catch (RocketNotReadyException err) {
			System.out.println(err.getMessage());
		}
		if (r.getLocation()!=SpaceObject.BigEarth) {
			r.returnToEarth();
			n.tell(r);
			System.out.println("");
			j.ask(n);
			s.ask(n);
			j.talk();
		}
	}
	public static void SceneRandom() {
		Lunatic.Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.BigEarth);
		Scientist s = new Scientist("Hugo", SpaceObject.BigEarth);
		Traveller[] t = {n};
		Core c = new Core(Utility.RandomizeDouble(0, 20), Utility.RandomizeDouble(0.5, 1.5), "Titan GH-73");
		Engine e = new Engine(Utility.RandomizeDouble(0, 200), Utility.RandomizeDouble(0.5, 1.5), "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(Utility.RandomizeDouble(0.5, 1.5), Utility.RandomizeDouble(0, 200), "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		n.describe();
		try {
			r.launch(SpaceObject.values()[Utility.Randomize(1, SpaceObject.values().length)]);
		} catch (RocketNotReadyException err) {
			System.out.println(err.getMessage());
		}
		if (r.getLocation()!=SpaceObject.BigEarth) {
			r.returnToEarth();
			n.tell(r);
			System.out.println("");
			j.ask(n);
			s.ask(n);
		}
	}
	public static void TestMethods() {
		Lunatic.Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.BigEarth);
		Scientist s = new Scientist("Hugo", SpaceObject.BigEarth);
		Traveller[] t = {n};
		Core c = new Core(11.0, 1.0, "Titan GH-73");
		Engine e = new Engine(100.0, 1.0, "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(100.0, 1.0, "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		System.out.println("");
		System.out.println("Testing different methods: ");
		System.out.println("Rocket.toString(): "+r.toString());
		System.out.println("Person.toString(): "+j.toString());
		Core c2 = new Core(11.0, 1.0, "Titan GH-73");
		System.out.println("Core.equals(): "+c.equals(c2));
		System.out.println("Person.equals(): "+n.equals(n));
		System.out.println("Person.hashCode(): "+n.hashCode());
		System.out.println("Rocket.hashCode(): "+r.hashCode());
		System.out.println("c.rating(): "+c.rating());
		System.out.println("");
	}
	public static void SceneNew() {
		Lunatic.Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.Moon);
		Scientist s = new Scientist("Alpha", SpaceObject.Moon, Profession.Astronomer);
		Scientist s3 = new Scientist("Memega", SpaceObject.Moon, Profession.Lunolog);
		Producer p = new Producer("Miga", SpaceObject.Moon, 500.0);
		Producer p2 = new Producer("Julio", SpaceObject.Moon, 600.0);
		StudioOwner st = new StudioOwner("Richard", SpaceObject.Moon, 1000.0);
		TVWatcher tv = new TVWatcher("William", SpaceObject.Moon, 10.0);
		TVWatcher tv2 = new TVWatcher("Aubrey", SpaceObject.Moon, 30.0);
		Traveller[] t = {n};
		TVWatcher[] tv3 = {tv, tv2};
		Scientist[] s4 = {s, s3};
		Journalist[] j2 = {j};
		Core c = new Core(11.0, 1.0, "Titan GH-73");
		Engine e = new Engine(100.0, 1.0, "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(100.0, 1.0, "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		tv.watchTV();
		n.describe();
		try {
			r.launch(SpaceObject.Moon);
		} catch (RocketNotReadyException err) {
			System.out.println(err.getMessage());
		}
		if (r.getLocation()!=SpaceObject.BigEarth) {
			p.search(n);
			if (p.getPeopleFound()==true) {
				p.makeADeal(st, tv3);
				Conference con = new Conference(n, s4, j2, r);
				con.start();
				Conference.Broadcast b = con.new Broadcast(tv3);
				b.start();
			}
		}
	}
	public static void SceneNewRandom() {
		Lunatic.Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.Moon);
		Scientist s = new Scientist("Alpha", SpaceObject.Moon, Profession.Astronomer);
		Scientist s3 = new Scientist("Memega", SpaceObject.Moon, Profession.Lunolog);
		Producer p = new Producer("Miga", SpaceObject.Moon, Utility.RandomizeDouble(250, 750));
		Producer p2 = new Producer("Julio", SpaceObject.Moon, Utility.RandomizeDouble(250, 750));
		StudioOwner st = new StudioOwner("Richard", SpaceObject.Moon, Utility.RandomizeDouble(750, 1250));
		TVWatcher tv = new TVWatcher("William", SpaceObject.Moon, Utility.RandomizeDouble(5, 50));
		TVWatcher tv2 = new TVWatcher("Aubrey", SpaceObject.Moon, Utility.RandomizeDouble(5, 50));
		Traveller[] t = {n};
		TVWatcher[] tv3 = {tv, tv2};
		Scientist[] s4 = {s, s3};
		Journalist[] j2 = {j};
		Core c = new Core(11.0, 1.0, "Titan GH-73");
		Engine e = new Engine(100.0, 1.0, "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(100.0, 1.0, "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		tv.watchTV();
		n.describe();
		try {
			r.launch(SpaceObject.Moon);
		} catch (RocketNotReadyException err) {
			System.out.println(err.getMessage());
		}
		if (r.getLocation()!=SpaceObject.BigEarth) {
			p.search(n);
			if (p.getPeopleFound()==true) {
				p.makeADeal(st, tv3);
				Conference con = new Conference(n, s4, j2, r);
				con.start();
				Conference.Broadcast b = con.new Broadcast(tv3);
				b.start();
			}
		}
	}
	public static void RocketNotReadyExceptionTest() {
		Rocket r = new Rocket(SpaceObject.BigEarth, "Cool rocket");
		try {
			r.launch(SpaceObject.Moon);
		} catch (RocketNotReadyException err) {
			System.out.println(err.getMessage());
		}
	}
	public static void ValueOutOfBoundsExceptionTest() {
		Core c = new Core(11.0, 1.2, "Cool Core");
	}
	public static void SceneSet() {
		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("");
	}
}