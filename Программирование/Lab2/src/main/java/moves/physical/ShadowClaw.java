package main.java.moves.physical;

import ru.ifmo.se.pokemon.*;

public class ShadowClaw extends PhysicalMove {
    public ShadowClaw(){
        super(Type.GHOST, 70, 100);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def){
        if (att.getStat(Stat.SPEED) / 512.0 / 3 > Math.random()) {
            return 2.0;
        } else {
            return 1.0;
        }
    }

    @Override
    protected String describe() {
        return ("атакует цель острыми когтями, созданными из теней");
    }
}
