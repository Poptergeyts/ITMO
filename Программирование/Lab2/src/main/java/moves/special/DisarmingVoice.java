package main.java.moves.special;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class DisarmingVoice extends SpecialMove {
    public DisarmingVoice(){
        super(Type.FAIRY, 40, Double.POSITIVE_INFINITY);
    }

    @Override
    protected String describe() {
        return ("трогательно плачет, нанося цели эмоциональное потрясение");
    }
}
