package main.java.moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class QuickAttack extends PhysicalMove {
    public QuickAttack(){
        super(Type.NORMAL, 40, 100, 1, 1);
    }

    @Override
    protected String describe() {
        return ("Покемон атакует цель на высокой скорости");
    }
}
