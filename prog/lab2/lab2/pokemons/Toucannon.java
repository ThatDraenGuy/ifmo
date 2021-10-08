package pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import moves.HealPulse;

public class Toucannon extends Trumbeak {
	public Toucannon (String name, int level) {
		super (name, level);
		setStats(80, 120, 75, 75, 75, 60);
		addMove(new HealPulse());
	}
}