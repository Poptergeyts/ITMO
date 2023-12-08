package main.java.pokemons;

import main.java.moves.special.DazzlingGleam;
import main.java.moves.physical.Facade;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Ralts extends Pokemon{
    public Ralts(String name, int level){
        super(name, level);
        setType(Type.PSYCHIC, Type.FAIRY);
        setStats(28, 25, 25, 45, 35, 40);
        setMove(new DazzlingGleam(), new Facade() );
    }
}
