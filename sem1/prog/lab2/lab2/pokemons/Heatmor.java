package pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import moves.Thunderbolt;
import moves.Discharge;
import moves.Psybeam;
import moves.Confide;
public class Heatmor extends Pokemon {
	public Heatmor (String name, int level) {
		super (name, level);
		setStats(85, 97, 66, 105, 66, 65);
		setType(Type.FIRE);
		addMove(new Thunderbolt());
		addMove(new Discharge());
		addMove(new Psybeam());
		addMove(new Confide());
	}
}