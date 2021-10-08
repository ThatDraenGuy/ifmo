package pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import moves.Scratch;

public class Trumbeak extends Pikipek {
	public Trumbeak (String name, int level) {
		super (name, level);
		setStats(55, 85, 50, 40, 50, 75);
		addMove(new Scratch());
	}
}