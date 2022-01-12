package pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import moves.DoubleTeam;
import moves.Rest;

public class Pikipek extends Pokemon {
	public Pikipek (String name, int level) {
		super (name, level);
		setStats(35, 75, 30, 30, 30, 65);
		setType(Type.NORMAL, Type.FLYING);
		addMove(new DoubleTeam());
		addMove(new Rest());
	}
}