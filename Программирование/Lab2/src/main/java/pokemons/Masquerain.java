package main.java.pokemons;

import main.java.moves.physical.AerialAce;
import ru.ifmo.se.pokemon.Type;

public class Masquerain extends Surskit{
    public Masquerain(String name, int level){
        super(name, level);
        setType(Type.BUG, Type.FLYING);
        setStats(70, 60, 62, 100, 82, 80);
        addMove(new AerialAce());
    }
}
