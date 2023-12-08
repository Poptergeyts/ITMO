package main.java.pokemons;

import main.java.moves.physical.QuickAttack;
import main.java.moves.special.Scald;
import main.java.moves.status.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Surskit extends Pokemon{
    public Surskit(String name, int level){
        super(name, level);
        setType(Type.BUG, Type.WATER);
        setStats(40, 30, 32, 50, 52, 65);
        setMove(new QuickAttack(), new Scald(), new Swagger());
    }
}
