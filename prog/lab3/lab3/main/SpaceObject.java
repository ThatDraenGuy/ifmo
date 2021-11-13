package main;

public enum SpaceObject {
	BigEarth,
	Moon,
	Mars,
	Kerbin;
	public static double SpaceObjectPower(SpaceObject s) {
		double power;
		switch (s){
			case BigEarth: power=0;
				break;
			case Moon: power=1000;
				break;
			case Mars: power=6000;
				break;
			case Kerbin: power=1000000;
				break;
			default: power=0;
				break;
		}
		return power;
	}
	public static String SpaceObjectObservation(SpaceObject s, double o) {
		String observation;
		if (o<10) {
			observation="Rocket's illuminators were too bad. I couldn't see anything";
		} else {
			switch (s) {
				case BigEarth: observation="It was really beautiful. Big Earth was so big, I felt smaller than ever before in my life!";
					break;
				case Moon: observation="Moon was beautiful. At first I thought it for sure was made out of cheese!";
					break;
				case Mars: observation="It was red and scary.";
					break;
				case Kerbin: observation="It's really similar to our Big Earth, just not as big. Also, I think I saw a couple other spacecrafts around me?";
					break;
				default: observation="I was sleeping and didn't see anything... Sorry...";
					break;
			}
		}
		return observation;
	}
}