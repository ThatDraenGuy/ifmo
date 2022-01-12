import main.*;
import java.util.Scanner;
public class lab3 {
	public static void main(String[] args) {
		InputHandler starter = new InputHandler() {
			public void PromptInput() {
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
				this.PromptInput();
			}				
		};
		InputHandler newStarter = new InputHandler() {
			public void PromptInput() {
				Scanner wantInput = new Scanner(System.in);
				System.out.println("Choose the scenario you wish to see:");
				System.out.println("Type \"1\" for a normal scene; type \"2\" for a randomized scene; type \"3\" for a method test; type \"4\" for a new (lab4) scene; type \"5\" for a randomized new (lab4) scene; type \"6\" for a RocketNotReadyException test; type \"7\" for a ValueOutOfBoundsException test; type \"exit\" to exit");
				String input = wantInput.nextLine();
				Scenario.SceneSet();
				switch (input) {
					case "1": Scenario.Scene();
						break;
					case "2": Scenario.SceneRandom();
						break;
					case "3": Scenario.TestMethods();
						break;
					case "4": Scenario.SceneNew();
						break;
					case "5": Scenario.SceneNewRandom();
						break;
					case "6": Scenario.RocketNotReadyExceptionTest();
						break;
					case "7": Scenario.ValueOutOfBoundsExceptionTest();
						break;
					case "exit": System.exit(0);
					default: System.out.println("Wrong input");
				}
				Scenario.SceneSet();
				this.PromptInput();
			}		
		};
		newStarter.PromptInput();
	}
}