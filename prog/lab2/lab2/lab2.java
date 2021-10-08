import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;
import pokemons.*;
public class lab2 {
	public static void main(String[] args) {
		Battle b = new Battle();
		Pokemon p1 = new Heatmor("William", 1);
		Pokemon p4 = new Pikipek("Phil", 1);
		Pokemon p2 = new Stufful("Tommy", 1);
		Pokemon p3 = new Bewear("George", 1);
		Pokemon p5 = new Trumbeak("Tech", 1);
		Pokemon p6 = new Toucannon("Grian", 1);
		b.addAlly(p1);
		b.addFoe(p2);
		b.addAlly(p3);
		b.addFoe(p4);
		b.addFoe(p5);
		b.addFoe(p6);
		b.go();
	}
}