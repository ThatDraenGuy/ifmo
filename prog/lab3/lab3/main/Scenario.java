package main;
import main.*;
import people.*;
import rocket.*;
import davilon.*;
public class Scenario {
	public static void Scene() {
		Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.BigEarth);
		Scientist s = new Scientist("Hugo", SpaceObject.BigEarth);
		Traveller[] t = {n};
		Core c = new Core(11.0, 11.0, "Titan GH-73");
		Engine e = new Engine(100.0, 1.0, "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(1.0, 100.0, "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		n.describe();
		r.launch(SpaceObject.Moon);
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
		Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.BigEarth);
		Scientist s = new Scientist("Hugo", SpaceObject.BigEarth);
		Traveller[] t = {n};
		Core c = new Core(Utility.RandomizeDouble(0, 20), Utility.RandomizeDouble(0, 20), "Titan GH-73");
		Engine e = new Engine(Utility.RandomizeDouble(0, 200), Utility.RandomizeDouble(0.5, 1.5), "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(Utility.RandomizeDouble(0.5, 1.5), Utility.RandomizeDouble(0, 200), "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		n.describe();
		r.launch(SpaceObject.values()[Utility.Randomize(1, SpaceObject.values().length)]);
		if (r.getLocation()!=SpaceObject.BigEarth) {
			r.returnToEarth();
			n.tell(r);
			System.out.println("");
			j.ask(n);
			s.ask(n);
		}
	}
	public static void TestMethods() {
		Davilon.generate();
		Traveller n = new Traveller("Neznayka", SpaceObject.BigEarth);
		Journalist j = new Journalist("Pimanov", SpaceObject.BigEarth);
		Scientist s = new Scientist("Hugo", SpaceObject.BigEarth);
		Traveller[] t = {n};
		Core c = new Core(11.0, 11.0, "Titan GH-73");
		Engine e = new Engine(100.0, 1.0, "Kerbodyne KR-2L+ \"Rhino\" Liquid Fuel Engine");
		Fuel f = new Fuel(100.0, 1.0, "Liquid fuel \"Ananas\"");
		Rocket r = new Rocket(SpaceObject.BigEarth, "Zeus ZF-24", e, f, c, t);
		System.out.println("");
		System.out.println("Testing different methods: ");
		System.out.println("Rocket.toString(): "+r.toString());
		System.out.println("Person.toString(): "+j.toString());
		Core c2 = new Core(11.0, 11.0, "Titan GH-73");
		System.out.println("Core.equals(): "+c.equals(c2));
		System.out.println("Person.equals(): "+n.equals(n));
		System.out.println("Person.hashCode(): "+n.hashCode());
		System.out.println("Rocket.hashCode(): "+r.hashCode());
		System.out.println("c.rating(): "+c.rating());
		System.out.println("");
	}
	public static void SceneSet() {
		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("");
	}
}