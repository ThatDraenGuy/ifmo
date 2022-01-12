package main;
import java.util.Random;
public class Utility {
	public static int Randomize(int a, int b) {
		final Random random = new Random();
		int r = random.nextInt(b-a)+a;
		return r;
	}
	public static double RandomizeDouble(double a, double b) {
		return Math.random()*(b-a)+a;
	}
}