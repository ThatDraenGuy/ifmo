package main;
import java.util.Random;
import java.util.Scanner;
public class Utility {
	public static int Randomize(int a, int b) {
		final Random random = new Random();
		int r = random.nextInt(b-a)+a;
		return r;
	}
	public static double RandomizeDouble(double a, double b) {
		return Math.random()*(b-a)+a;
	}
	public static void PromptInput() {
		Scanner wantInput = new Scanner(System.in);
		System.out.println("Choose the scenario you wish to see:");
		System.out.println("Type \"1\" for a normal scene; type \"2\" for a randomized scene; type \"3\" for a method test; type \"exit\" to exit");
		String input = wantInput.nextLine();
		Scenario.SceneSet();
		switch (input) {
			case "1": Scenario.Scene();
				break;
			case "2": Scenario.SceneRandom();
				break;
			case "3": Scenario.TestMethods();
				break;
			case "exit": System.exit(0);
			default: System.out.println("Wrong input");
		}
		Scenario.SceneSet();
		Utility.PromptInput();
	}
}