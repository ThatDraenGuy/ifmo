package pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import moves.DoubleTeam;
import moves.Confide;
import moves.Facade;

public class Stufful extends Pokemon {
	public Stufful (String name, int level) {
		super (name, level);
		setStats(70, 75, 50, 45, 50, 50);
		setType(Type.NORMAL, Type.FIGHTING);
		addMove(new DoubleTeam());
		addMove(new Confide());
		addMove(new Facade());
	}
}