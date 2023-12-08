package main.java.moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class Facade extends PhysicalMove {
     public Facade(){
         super(Type.NORMAL, 70, 100);
     }

     @Override
     protected double calcBaseDamage(Pokemon att, Pokemon def){

         return (att.getCondition() == Status.BURN || att.getCondition() == Status.PARALYZE ||
                 att.getCondition() == Status.POISON ? 2.0 : 1.0) * (0.4 *
                 (double)att.getLevel() + 2.0) * this.power / 150.0;
     }

    @Override
    protected String describe() {
        return ("преодолевает трудности, атакуя с двойной силой");
    }
}
