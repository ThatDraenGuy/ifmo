package davilon;
import main.*;
public class Davilon {
	protected static int size;
	protected static Mood behavior;
	protected static String about = "Citizens of Davilon are ";
	public static void generate() {
		about="Citizens of Davilon are ";
		behavior = Mood.values()[Utility.Randomize(0, Mood.values().length)];
		size = Utility.Randomize(100, 230);
		about=about.concat(behavior.toString());
		int variation = Utility.Randomize(0, 4);
		switch (variation){
			case 0: about=about.concat(" grey creatures with a lot of fur and 4 legs.");
				break;
			case 1: about=about.concat(" very round-like purple creatures with very short hands and legs.");
				break;
			case 2: about=about.concat(" bird-like creatures of different colors.");
				break;
			case 3: about=about.concat(" almost identical to us creatures.");
				break;
			default: about=about.concat(" non-existent. There aren't any...");
		}
		about=about.concat(" Their average height is " + size + "cm");
	}
	public static int getSize() {
		return size;
	}
	public static Mood getBehavior() {
		return behavior;
	}
	public static String getAbout() {
		return about;
	}
}