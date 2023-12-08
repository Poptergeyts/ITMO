package main.java.moves.physical;

import ru.ifmo.se.pokemon.*;

public class PlayRough extends PhysicalMove {
    public PlayRough(){
        super(Type.FAIRY, 90, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        p.addEffect(new Effect().chance(0.1).stat(Stat.ATTACK, -1).turns(-1));
    }

    @Override
    protected String describe() {
        return ("грубо играет с целью, а затем атакует");
    }
}
